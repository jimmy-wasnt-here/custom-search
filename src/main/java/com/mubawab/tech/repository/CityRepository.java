package com.mubawab.tech.repository;

import org.springframework.data.repository.CrudRepository;

import com.mubawab.tech.domain.City;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long>, CityRepositoryExtended {

}
