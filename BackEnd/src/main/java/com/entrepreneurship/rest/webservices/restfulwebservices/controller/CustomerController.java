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
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Customer;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.CustomerService;

/*
 * It is designed to handle web requests to a Customer class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private UserDao userDao;
	
	@GetMapping("/jpa/{username}/customers")
	public List<Customer> getAllCustomers(@PathVariable String username){
	
		return customerService.getAllCustomersByUser(username);	
		
	}
	
	@GetMapping("/jpa/{username}/customers/{id}")
	public Customer getCustomer(@PathVariable String username, @PathVariable long id){
		return customerService.getCustomerById(id);
	}
	
	
	@PostMapping("/jpa/{username}/customers")
	public ResponseEntity<Void> createCustomer(
			@PathVariable String username, @RequestBody Customer customer){
		
		customer.setUser(userDao.findByUsername(username)); 
		Customer createdCustomer = customerService.save(customer);
		
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdCustomer.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}	
	             
	@PutMapping("/jpa/{username}/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(
			@PathVariable String username,
			@PathVariable long id, @RequestBody Customer customer){
		
		customer.setUser(userDao.findByUsername(username)); 
		Customer updatedCustomer = customerService.save(customer);
		
		return new ResponseEntity<Customer>(updatedCustomer, HttpStatus.OK);
	}
	
		@DeleteMapping("/jpa/{username}/customers/{id}")
		public ResponseEntity<Void> deleteCustomer(
			@PathVariable String username, @PathVariable long id) {

			customerService.deleteById(id);

				return ResponseEntity.noContent().build();
		}

}
