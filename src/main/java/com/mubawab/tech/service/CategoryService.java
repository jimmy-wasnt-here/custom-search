package com.mubawab.tech.service;

import com.google.gson.reflect.TypeToken;
import com.mubawab.tech.core.DataLoader;
import com.mubawab.tech.domain.Category;
import com.mubawab.tech.domain.City;
import com.mubawab.tech.domain.Town;
import com.mubawab.tech.dto.CategoryDto;
import com.mubawab.tech.dto.CityDto;
import com.mubawab.tech.dto.LocationDto;
import com.mubawab.tech.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CategoryService {
	@Autowired
	private CategoryRepository categoryRepository;

	@Value("${mubawab.files.categories}")
	private String sourceFile;

	public Iterable<Category> loadData(){
		return categoryRepository.saveAll(DataLoader.loadDataList(sourceFile, new TypeToken<Collection<Category>>() {
		}.getType()));
	}

	public List<CategoryDto> findAll() {
		return StreamSupport.stream(categoryRepository.findAll().spliterator(), false)
				.map(category -> new CategoryDto(category.getCode(), category.getName()))
				.collect(Collectors.toList());
	}
}
