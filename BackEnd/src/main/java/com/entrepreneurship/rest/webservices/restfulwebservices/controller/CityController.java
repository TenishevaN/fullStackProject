/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.controller;


/*
 * It is designed to handle web requests to a City class. 
 * version 1.0
 * author Tenisheva N.I.
 */

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.City;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.CityService;

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CityController {
	@Autowired
	private CityService cityService;
	
	
	
	@GetMapping("/jpa/cities")
	public List<City> getAllCities(){
		return cityService.getAllCity();
		}
	}


