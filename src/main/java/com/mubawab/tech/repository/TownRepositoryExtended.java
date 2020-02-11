package com.mubawab.tech.repository;

import com.mubawab.tech.domain.Town;

public interface TownRepositoryExtended {
	Town getTownIfFound(String[] params);
}
