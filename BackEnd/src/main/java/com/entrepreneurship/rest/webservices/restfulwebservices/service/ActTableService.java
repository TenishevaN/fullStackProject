/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.ActTableRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.ActTable;

/*
 * It is for writing business logic for a ActTable class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Service
@Transactional
public class ActTableService {
	
	@Autowired
	ActTableRepository actTableRepository;

	public List<ActTable> getActTableById(long id) {
		return  actTableRepository.getActTableById(id);  
	}
	
	public List<ActTable> getActTableByContractId(long id) {
		return  actTableRepository.getActTableByContractId(id);  
	}
	
	public List<ActTable> getActTableByActId(long id) {
		return  actTableRepository.getActTableByActId(id);  
	}
	
	public ActTable save (ActTable actTable){
		return actTableRepository.save(actTable);
	}
	
	public void deleteById(long id) {
		actTableRepository.deleteById(id);
	}
	
}
