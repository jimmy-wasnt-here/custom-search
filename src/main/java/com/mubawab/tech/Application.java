package com.mubawab.tech;

import com.mubawab.tech.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = { "com.mubawab.tech" })
public class Application implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(Application.class);

	@Autowired
	private CityService cityService;
	@Autowired
	private TownService townService;
	@Autowired
	private AdService adService;
	@Autowired
	private CategoryService categoryService;
	@Autowired
	private SubcategoryService subcategoryService;

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Override
	public void run(String... args) {

		log.info("Starting API...");

		log.info("Loading data...");
		categoryService.loadData();
		subcategoryService.loadData();
		cityService.loadData();
		townService.loadData();
		adService.loadData();
		log.info("Data loaded");

	}

}