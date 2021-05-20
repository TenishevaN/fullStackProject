/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.CityRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.City;

/*
 * It is for writing business logic for a City class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Service
@Transactional
public class CityService {
	
	@Autowired
	CityRepository cityRepository;
	
	public List<City> getAllCity() {
		return (List<City>) cityRepository.findAll();
	}
	
	public City getCityById(Long id) {
		return cityRepository.findById(id).get();
	}
}
