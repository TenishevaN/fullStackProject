/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.InvoiceRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Invoice;

/*
 * It is for writing business logic for a Invoice class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Service
@Transactional
public class InvoiceService {
	@Autowired
	InvoiceRepository invoiceRepository;
	
	public List<Invoice> getAllInvoice() {
		return (List<Invoice>) invoiceRepository.findAll();
	}
	
	public List<Invoice> getAllInvoicesByUser(String userName) {
		return (List<Invoice>) invoiceRepository.findListInvoicesByUser(userName);
	}
	
	public List<Invoice> getAllContractInvoicesByUser(String userName, Long id){
		return invoiceRepository.findContractInvoicesByUser(userName, id);
	}
	
	public Invoice getInvoice(String userName, Long id){
		return invoiceRepository.findById(id).get();		
	}
	
	public Invoice save (Invoice invoice){
		return invoiceRepository.save(invoice);
	}
	
	public void deleteById(long id) {
		invoiceRepository.deleteById(id);
	}
	
	public Invoice getInvoiceById(Long id) {
		return invoiceRepository.findById(id).get();
	}
	
	
}
