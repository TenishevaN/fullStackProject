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
import com.entrepreneurship.rest.webservices.restfulwebservices.model.PaymentOrder;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.PaymentOrderService;

/*
 * It is designed to handle web requests to a PaymentOrder class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class PaymentOrderController {

	@Autowired
	private PaymentOrderService paymentOrderService;
	
	@Autowired
	private UserDao userDao;
	
	
	
	@GetMapping("/jpa/{username}/paymentOrders")
	public List<PaymentOrder> getAllPaymentOrders(@PathVariable String username){
	
		return paymentOrderService.getAllPaymentOrdersByUser(username);
	}	
	
		
	@GetMapping("/jpa/{username}/paymentOrders/{id}")
	public PaymentOrder getPaymentOrder(@PathVariable String username, @PathVariable long id){
		return paymentOrderService.getPaymentOrder(username, id);
	}
	
	
	@PostMapping("/jpa/{username}/paymentOrders/")
	public ResponseEntity<Void> createPaymentOrder(
			@PathVariable String username, @RequestBody PaymentOrder paymentOrder){
		
		paymentOrder.setUser(userDao.findByUsername(username));
		PaymentOrder createdPaymentOrder = paymentOrderService.save(paymentOrder);
		
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdPaymentOrder.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}	
	
	@PutMapping("/jpa/{username}/paymentOrders/{id}")
	public ResponseEntity<PaymentOrder> updatePaymentOrder(
			@PathVariable String username,
			@PathVariable long id, @RequestBody PaymentOrder paymentOrder){
		
		paymentOrder.setUser(userDao.findByUsername(username)); 	
		PaymentOrder updatedPaymentOrder = paymentOrderService.save(paymentOrder);
		
		return new ResponseEntity<PaymentOrder>(updatedPaymentOrder, HttpStatus.OK);
	}
	
		@DeleteMapping("/jpa/{username}/paymentOrders/{id}")
		public ResponseEntity<Void> deletePaymentOrder(
			@PathVariable String username, @PathVariable long id) {

			paymentOrderService.deleteById(id);

				return ResponseEntity.noContent().build();
		}
		
	
}

