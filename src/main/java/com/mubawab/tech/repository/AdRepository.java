package com.mubawab.tech.repository;

import com.mubawab.tech.domain.Ad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdRepository extends CrudRepository<Ad, Long> {
}
