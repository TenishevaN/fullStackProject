/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Customer;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.CustomerRepository;

/*
 * It is for writing business logic for a Customer class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Service
@Transactional
public class CustomerService {
	
	@Autowired
	CustomerRepository customerRepository;
	
	public List<Customer> getAllCustomersByUser(String userName) {
		return (List<Customer>) customerRepository.findListCustomersByUser(userName);
	}
	
	public Customer getCustomerById(Long id) {
		return customerRepository.findById(id).get();
	}	
	
	public Customer save (Customer customer){
		return customerRepository.save(customer);
	}	

	public void deleteById(long id) {
		customerRepository.deleteById(id);
	}

}
