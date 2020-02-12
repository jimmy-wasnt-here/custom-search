package com.mubawab.tech.repository;

import com.mubawab.tech.domain.Category;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CategoryRepositoryExtendedImpl implements CategoryRepositoryExtended {
	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private CategoryRepository categoryRepository;

	@Override
	public String getCategoryIfFound(String[] params) {
		Iterable<Category> categories = categoryRepository.findAll();
		for (String param : params) {
			for (Category category : categories) {
				if (param.equals(category.getCode())) {
					logger.debug("Category found: {}", param);
					return param;
				}
			}
		}
		return null;
	}
}
