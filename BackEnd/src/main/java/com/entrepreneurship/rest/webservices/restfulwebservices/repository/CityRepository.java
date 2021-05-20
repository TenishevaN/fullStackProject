/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.City;

/*
 * It is a storage, retrieval, and search mechanism for a City class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface CityRepository extends CrudRepository<City, Long>{
	
	
	
}
