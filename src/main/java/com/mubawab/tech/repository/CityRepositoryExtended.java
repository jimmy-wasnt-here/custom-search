package com.mubawab.tech.repository;

import com.mubawab.tech.domain.City;

public interface CityRepositoryExtended {
	City getCityIfFound(String[] params);
}
