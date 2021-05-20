/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.controller;

import java.net.URI;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Contract;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.CityService;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.ContractService;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.CustomerService;

/*
 * It is designed to handle web requests to a Contract class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class ContractController {
	
	@Autowired
	private ContractService contractService;
	
	@Autowired
	private CityService cityService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/jpa/{username}/contracts")
	public List<Contract> getAllContracts(@PathVariable String username){
	
		return contractService.getAllContractsByUser(username);
	}
	
	@GetMapping("/jpa/{username}/contracts/modal/{id}")
	public Contract getContractModal1(@PathVariable long id){
		return contractService.getContractById(id);
	}
	
	@GetMapping("/jpa/{username}/contractsmodal/{id}")
	public Contract getContractModal(@PathVariable long id){
		return contractService.getContractById(id);
	}
	
	// DELETE /users/{username}/todos/{id}
		@DeleteMapping("/jpa/{username}/contracts/{id}")
		public ResponseEntity<Void> deleteContract(
			@PathVariable String username, @PathVariable long id) {

			contractService.deleteById(id);

				return ResponseEntity.noContent().build();
		}
		
			
		@GetMapping("/jpa/{username}/contracts/{id}")
		public Contract getContract(@PathVariable String username, @PathVariable long id){
			return contractService.getContractById(id);
		}
		
		
		@PutMapping("/jpa/{username}/contracts/{id}/{city_id}/{customer_id}")
		public ResponseEntity<Contract> updateContract(
				@PathVariable String username,
				@PathVariable long id, @PathVariable long city_id, @PathVariable long customer_id, @RequestBody Contract contract){
			
			contract.setUser(userDao.findByUsername(username)); 
			contract.setCity(cityService.getCityById(city_id));
			contract.setCustomer(customerService.getCustomerById(customer_id));
			
			Contract updatedContract = contractService.save(contract);
			
			return new ResponseEntity<Contract>(updatedContract, HttpStatus.OK);
		}
		
		@PostMapping("/jpa/{username}/contracts/{city_id}/{customer_id}")
		public ResponseEntity<Void> createContract(
				@PathVariable String username, @PathVariable long city_id, @PathVariable long customer_id, @RequestBody Contract contract){
			
			contract.setUser(userDao.findByUsername(username));
			contract.setCity(cityService.getCityById(city_id));
			contract.setCustomer(customerService.getCustomerById(customer_id));
			Contract createdContract = contractService.save(contract);
			
		    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").buildAndExpand(createdContract.getId()).toUri();
			
			return ResponseEntity.created(uri).build();
		}	
			
	
	
}


