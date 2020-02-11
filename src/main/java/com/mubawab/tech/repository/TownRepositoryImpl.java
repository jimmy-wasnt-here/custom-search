package com.mubawab.tech.repository;

import com.mubawab.tech.domain.Town;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class TownRepositoryImpl implements TownRepositoryExtended {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private TownRepository townRepository;

	@Override
	public Town getTownIfFound(String[] params) {
		Iterable<Town> towns = townRepository.findAll();
		for (String param : params){
			for (Town town : towns){
				if (param.equalsIgnoreCase(town.getName())){
					logger.debug("Town found: {}", param);
					return town;
				}
			}
		}
		return null;
	}
}
