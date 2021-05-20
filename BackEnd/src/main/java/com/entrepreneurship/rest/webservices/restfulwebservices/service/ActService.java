/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.ActRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Act;


/*
 * It is for writing business logic for a Act class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Service
@Transactional
public class ActService {
	@Autowired
	ActRepository actRepository;
	
		
	public List<Act> getAllAtsByUser(String userName) {
		return (List<Act>) actRepository.findListActsByUser(userName);
	}
	
	public List<Act> getAllContractActsByUser(String userName, Long id){
		return actRepository.findContractActsByUser(userName, id);
	}
	
	public Act getAct(String userName, Long id){
		return actRepository.findById(id).get();		
	}
	
	public Act save (Act act){
		return actRepository.save(act);
	}
	
	public void deleteById(long id) {
		actRepository.deleteById(id);
	}
	
	
}
