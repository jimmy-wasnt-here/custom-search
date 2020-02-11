package com.mubawab.tech.service;

import com.google.gson.reflect.TypeToken;
import com.mubawab.tech.core.DataLoader;
import com.mubawab.tech.domain.City;
import com.mubawab.tech.domain.Subcategory;
import com.mubawab.tech.dto.SubcategoryDto;
import com.mubawab.tech.repository.SubcategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SubcategoryService {
	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Value("${mubawab.files.subcategories}")
	private String sourceFile;

	public Iterable<City> loadData() {
		return subcategoryRepository.saveAll(DataLoader.loadDataList(sourceFile, new TypeToken<Collection<Subcategory>>() {
		}.getType()));
	}

	public List<SubcategoryDto> findAll() {
		return StreamSupport.stream(subcategoryRepository.findAll().spliterator(), false)
				.map(subcategory -> new SubcategoryDto(subcategory.getCode(), subcategory.getName()))
				.collect(Collectors.toList());
	}
}
