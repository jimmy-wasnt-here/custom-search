package com.mubawab.tech.service;

import com.google.gson.reflect.TypeToken;
import com.mubawab.tech.core.DataLoader;
import com.mubawab.tech.domain.Ad;
import com.mubawab.tech.domain.City;
import com.mubawab.tech.domain.Town;
import com.mubawab.tech.dto.AdDto;
import com.mubawab.tech.repository.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class AdService {

	@Autowired
	private AdRepository adRepository;

	@Value("${mubawab.files.ads}")
	private String sourceFile;

	@Autowired
	private CityRepository cityRepository;
	@Autowired
	private TownRepository townRepository;
	@Autowired
	private CategoryRepository categoryRepository;
	@Autowired
	private SubcategoryRepository subcategoryRepository;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	public Iterable<Ad> loadData() {
		List<Ad> jsonAds = DataLoader.loadDataList(sourceFile, new TypeToken<Collection<Ad>>() {}.getType());

		List<Ad> adsWithCities = jsonAds.stream().map(ad ->
		{
			ad.setCity(cityRepository.findById(ad.getCityId()).orElse(null));
			ad.setTown(townRepository.findById(ad.getTownId()).orElse(null));
			ad.setCategory(categoryRepository.findById(ad.getCategoryId()).orElse(null));
			ad.setSubcategory(subcategoryRepository.findById(ad.getSubCategoryId()).orElse(null));
			return ad;
		}).collect(Collectors.toList());

		return adRepository.saveAll(adsWithCities);
	}

	public List<AdDto> findAll() {
		return StreamSupport.stream(adRepository.findAll().spliterator(), false)
				.map(ad -> new AdDto(
						ad.getTitle(), ad.getDescription(), ad.getPrice(),
						ad.getCity().getName(), ad.getTown().getName(),
						ad.getCategory().getName(), ad.getSubcategory().getName(),
						ad.getQualityScore()))
				.collect(Collectors.toList());
	}

	public List<AdDto> findByParams(String[] params) {
		String[] externalParams = deleteParam(params, null);

		String subcategory = subcategoryRepository.getSubcategoryIfFound(externalParams);
		if (subcategory != null) deleteParam(externalParams, subcategory);

		String category = categoryRepository.getCategoryIfFound(externalParams);
		if (category != null) deleteParam(externalParams, category);

		City city = cityRepository.getCityIfFound(externalParams);
		if (city != null) deleteParam(externalParams, city.getName());

		Town town = townRepository.getTownIfFound(externalParams);
		if (town != null) deleteParam(externalParams, town.getName());

		boolean townAndCityMatch = validateTownAndCityMatch(city, town);

		if (paramsAreInvalid(subcategory, category, city, town)){
			log.info("No valid parameter found in query");
			return Collections.emptyList();
		}

		if (townAndCityMatch){
			Iterable<Ad> ads = adRepository.findAll();
			Iterator<Ad> it = ads.iterator();
			for (ads.iterator(); it.hasNext(); ) {
				Ad ad = it.next();
				if (!matchSubcategory(subcategory, ad)
						|| !matchCategory(category, ad)
						|| !matchCity(city, ad)
						|| !matchTown(town, ad))
					it.remove();
			}
			return StreamSupport.stream(ads.spliterator(), false)
					.map(ad -> new AdDto(
							ad.getTitle(), ad.getDescription(), ad.getPrice(),
							ad.getCity().getName(), ad.getTown().getName(),
							ad.getCategory().getName(), ad.getSubcategory().getName(),
							ad.getQualityScore()))
					.collect(Collectors.toList());
		} else return Collections.emptyList();
	}

	private boolean paramsAreInvalid(String subcategory, String category, City city, Town town) {
		return subcategory == null && category == null && city == null && town == null;
	}

	public boolean validateTownAndCityMatch(City city, Town town) {
		if (city == null || town == null) {
			return true;
		} else {
			Long cityIdOfTown = town.getCityId();
			Long cityId = city.getId();
			return cityIdOfTown.equals(cityId);
		}
	}

	private boolean matchTown(Town town, Ad ad) {
		if (town == null) return true;
		else return town.getId().equals(ad.getTownId());
	}

	private boolean matchCity(City city, Ad ad) {
		if (city == null) return true;
		else return city.getId().equals(ad.getCityId());
	}

	private boolean matchCategory(String category, Ad ad) {
		if (category == null) return true;
		else return category.equalsIgnoreCase(ad.getCategory().getCode());
	}

	private boolean matchSubcategory(String subcategory, Ad ad) {
		if (subcategory == null) return true;
		else {
			return subcategory.equalsIgnoreCase(ad.getSubcategory().getCode());
		}
	}

	private String[] deleteParam(String[] params, String paramToDelete) {
		List<String> list = new ArrayList<>(Arrays.asList(params));
		if (paramToDelete == null) {
			//At, for and in are to be deleted
			list.removeIf(item -> item.equalsIgnoreCase("at")
					|| item.equalsIgnoreCase("in")
					|| item.equalsIgnoreCase("for"));
		} else {
			list.removeIf(item -> item.equalsIgnoreCase(paramToDelete));
		}
		String[] cleanParams = new String[list.size()];
		cleanParams = list.toArray(cleanParams);
		return cleanParams;
	}
}
