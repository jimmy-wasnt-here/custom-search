package com.mubawab.tech.repository;

import com.mubawab.tech.domain.City;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CityRepositoryImpl implements CityRepositoryExtended {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CityRepository cityRepository;

	@Override
	public City getCityIfFound(String[] params) {
		Iterable<City> cities = cityRepository.findAll();
		for (String param : params){
			for (City city : cities){
				if (param.equalsIgnoreCase(city.getName())){
					logger.debug("City found: {}", param);
					return city;
				}
			}
		}
		return null;
	}
}
