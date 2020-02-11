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
import com.mubawab.tech.domain.City;
import com.mubawab.tech.dto.CityDto;
import com.mubawab.tech.dto.LocationDto;
import com.mubawab.tech.repository.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository cityRepository;

	@Value("${mubawab.files.cities}")
	private String sourceFile;

	public Iterable<City> loadData() {
		return cityRepository.saveAll(DataLoader.loadDataList(sourceFile, new TypeToken<Collection<City>>() {
		}.getType()));
	}

	public List<CityDto> findAll() {
		return StreamSupport.stream(cityRepository.findAll().spliterator(), false)
				.map(city -> new CityDto(city.getName(),
						Optional.ofNullable(city.getCenter()).isPresent()
								? new LocationDto(city.getCenter().getLatitude(), city.getCenter().getLongitude())
								: new LocationDto()))
				.collect(Collectors.toList());
	}

}
