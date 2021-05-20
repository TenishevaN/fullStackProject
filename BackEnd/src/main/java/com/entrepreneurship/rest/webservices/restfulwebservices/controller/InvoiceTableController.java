/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.controller;

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
import com.entrepreneurship.rest.webservices.restfulwebservices.model.InvoiceTable;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.InvoiceTableService;

/*
 * It is designed to handle web requests to a InvoiceTable class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class InvoiceTableController {

	@Autowired
	private InvoiceTableService invoiceTableService;
	
	
	@GetMapping("/jpa/invoiceTableById/{id}")
	public List<InvoiceTable> getInvoiceTableById(@PathVariable long id){
	
		return invoiceTableService.getInvoiceTableById(id);
	}	
	
	@GetMapping("/jpa/invoiceTableByContractId/{id}")
	public List<InvoiceTable> getInvoiceTableByContractId(@PathVariable long id){
	
		return invoiceTableService.getInvoiceTableByContractId(id);
	}
	
	@GetMapping("/jpa/invoiceTableByInvoiceId/{id}")
	public List<InvoiceTable> getInvoiceTableByInvoiceId(@PathVariable long id){
	
		return invoiceTableService.getInvoiceTableByInvoiceId(id);
	}
	
	
	@PutMapping("/jpa/updateInvoiceTable")
	public ResponseEntity<InvoiceTable> updateInvoiceTable(
			 @RequestBody InvoiceTable invoiceTable){
				
		InvoiceTable updatedInvoiceTable = invoiceTableService.save(invoiceTable);
		
		return new ResponseEntity<InvoiceTable>(updatedInvoiceTable, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/createInvoiceTable")
	public InvoiceTable createInvoice(
			 @RequestBody InvoiceTable invoiceTable){
		
		InvoiceTable createdInvoiceTable = invoiceTableService.save(invoiceTable);
		
		ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(createdInvoiceTable.getId()).toUri();
		
		return createdInvoiceTable;
	}
	
		@DeleteMapping("/jpa/deleteInvoiceTable/{id}")
		public ResponseEntity<Void> deleteInvoice(
			@PathVariable long id) {

			invoiceTableService.deleteById(id);

				return ResponseEntity.noContent().build();
		}

		
}
