package com.mubawab.tech;

import com.mubawab.tech.dto.AdDto;
import com.mubawab.tech.dto.CityDto;
import com.mubawab.tech.dto.TownDto;
import com.mubawab.tech.service.AdService;
import com.mubawab.tech.service.CityService;
import com.mubawab.tech.service.TownService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class CityController {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private CityService cityService;
	@Autowired
	private TownService townService;
	@Autowired
	private AdService adsService;

	@GetMapping("/cities")
	public List<CityDto> findAllCities() {
		return cityService.findAll();
	}

	@GetMapping("/towns")
	public List<TownDto> findAllTowns() {
		return townService.findAll();
	}

	@GetMapping(value = {"/ads", "/ads/{searchText}"})
	public List<AdDto> findAds(@PathVariable(value = "searchText", required = false) Optional<String> searchText) {
		logger.info("Petition received: {}",  searchText);
		if (searchText.isPresent()){
			String params = searchText.get().toLowerCase();
			String[] paramsToSearch = params.split(" ");
			return adsService.findByParams(paramsToSearch);
		} else {
			return adsService.findAll();
		}

	}
}
