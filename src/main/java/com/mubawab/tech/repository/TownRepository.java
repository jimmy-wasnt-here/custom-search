package com.mubawab.tech.repository;

import org.springframework.data.repository.CrudRepository;

import com.mubawab.tech.domain.Town;
import org.springframework.stereotype.Repository;

@Repository
public interface TownRepository extends CrudRepository<Town, Long>, TownRepositoryExtended {

}
