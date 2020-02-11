package com.mubawab.tech.repository;

import com.mubawab.tech.domain.Subcategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends CrudRepository<Subcategory, Long>, SubcategoryRepositoryExtended {
}
