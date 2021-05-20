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
import com.entrepreneurship.rest.webservices.restfulwebservices.model.FundsReceipt;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.FundsReceiptService;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.InvoiceService;


/*
 * It is designed to handle web requests to a FundsReceipt class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class FundsReceiptController {

	@Autowired
	private FundsReceiptService fundsReceiptService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private InvoiceService invoiceService;
	
	
	
	@GetMapping("/jpa/{username}/fundsReceipts")
	public List<FundsReceipt> getAllFundsReceipts(@PathVariable String username){
	
		return fundsReceiptService.getAllFundsReceiptsByUser(username);
	}	
	
		
	// DELETE /users/{username}/todos/{id}
	@DeleteMapping("/jpa/{username}/fundsReceipts/{id}")
	public ResponseEntity<Void> deletePaymentOrder(
		@PathVariable String username, @PathVariable long id) {

		fundsReceiptService.deleteById(id);

			return ResponseEntity.noContent().build();
	}
	
		
	@GetMapping("/jpa/{username}/fundsReceipts/{id}")
	public FundsReceipt getFundsReceipt(@PathVariable String username, @PathVariable long id){
		return fundsReceiptService.getFundsReceipt(username, id);
	}
	
	
	@PutMapping("/jpa/{username}/fundsReceipts/{id}/{invoice_id}")
	public ResponseEntity<FundsReceipt> updateFundsReceipt(
			@PathVariable String username,
			@PathVariable long id, @PathVariable long invoice_id, @RequestBody FundsReceipt fundsReceipt){
		
		fundsReceipt.setUser(userDao.findByUsername(username)); 	
		fundsReceipt.setInvoice(invoiceService.getInvoiceById(invoice_id));
		FundsReceipt updatedFundsReceipt = fundsReceiptService.save(fundsReceipt);
		
		return new ResponseEntity<FundsReceipt>(updatedFundsReceipt, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/{username}/fundsReceipts/{invoice_id}")
	public ResponseEntity<Void> createFundsReceipt(
			@PathVariable String username, @PathVariable long invoice_id,  @RequestBody FundsReceipt fundsReceipt){
		
		fundsReceipt.setUser(userDao.findByUsername(username));
		fundsReceipt.setInvoice(invoiceService.getInvoiceById(invoice_id));
		FundsReceipt createdFundsReceipt = fundsReceiptService.save(fundsReceipt);
		
	    URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdFundsReceipt.getId()).toUri();
		
		return ResponseEntity.created(uri).build();
	}	
		
	
}

