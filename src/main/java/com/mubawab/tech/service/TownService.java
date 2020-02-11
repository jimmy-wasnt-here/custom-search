package com.mubawab.tech.service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.google.gson.reflect.TypeToken;
import com.mubawab.tech.core.DataLoader;
import com.mubawab.tech.domain.Town;
import com.mubawab.tech.dto.LocationDto;
import com.mubawab.tech.dto.TownDto;
import com.mubawab.tech.repository.CityRepository;
import com.mubawab.tech.repository.TownRepository;

@Service
public class TownService {

	@Autowired
	private TownRepository townRepository;
	@Autowired
	private CityRepository cityRepository;

	@Value("${mubawab.files.towns}")
	private String sourceFile;

	public Iterable<Town> loadData() {

		List<Town> jsonTowns = DataLoader.loadDataList(sourceFile, new TypeToken<Collection<Town>>() {
		}.getType());

		List<Town> townsWithCities = jsonTowns.stream().map(town ->
		{
			town.setCity(cityRepository.findById(town.getCityId()).orElse(null));
			return town;
		}).collect(Collectors.toList());

		return townRepository.saveAll(townsWithCities);
	}

	public List<TownDto> findAll() {
		return StreamSupport.stream(townRepository.findAll().spliterator(), false)
				.map(town -> new TownDto(town.getName(),
						Optional.ofNullable(town.getCenter()).isPresent()
								? new LocationDto(town.getCenter().getLatitude(), town.getCenter().getLongitude())
								: new LocationDto(),
						town.getCity().getName()))
				.collect(Collectors.toList());
	}

}
