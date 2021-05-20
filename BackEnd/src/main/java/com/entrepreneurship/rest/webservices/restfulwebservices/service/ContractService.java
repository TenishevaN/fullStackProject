/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Contract;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.ContractRepository;

/*
 * It is for writing business logic for a Contract class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Service
@Transactional
public class ContractService {
	@Autowired
	ContractRepository contractRepository;
	
	public List<Contract> getAllContractsByUser(String userName) {
		return (List<Contract>) contractRepository.findListContractsByUser(userName);
	}
	
	public Contract getContractById(Long id) {
		
		return  contractRepository.findById(id).get();  
	}
	
	public Contract save (Contract contract){
		return contractRepository.save(contract);
	}
	
	public void deleteById(long id) {
		contractRepository.deleteById(id);
	}
}
