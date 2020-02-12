package com.mubawab.tech.repository;

import com.mubawab.tech.domain.Subcategory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class SubcategoryRepositoryExtendedImpl implements SubcategoryRepositoryExtended {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private SubcategoryRepository subcategoryRepository;

	@Override
	public String getSubcategoryIfFound(String[] params) {
		Iterable<Subcategory> subcategories = subcategoryRepository.findAll();
		for (String param : params) {
			for (Subcategory subcategory : subcategories) {
				if (param.equals(subcategory.getCode())) {
					logger.debug("Subcategory found: {}", param);
					return param;
				}
			}
		}
		return null;
	}
}
