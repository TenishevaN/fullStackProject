/*
 * Copyright (C) 2021 Tenisheva N.I.
 */


package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.InvoiceTableRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.InvoiceTable;

/*
 * It is for writing business logic for a InvoiceTable class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Service
@Transactional
public class InvoiceTableService {
	
	@Autowired
	InvoiceTableRepository invoiceTableRepository;

	public List<InvoiceTable> getInvoiceTableById(long id) {
		return  invoiceTableRepository.getInvoiceTableById(id);  
	}
	
	public List<InvoiceTable> getInvoiceTableByContractId(long id) {
		return  invoiceTableRepository.getInvoiceTableByContractId(id);  
	}
	
	public List<InvoiceTable> getInvoiceTableByInvoiceId(long id) {
		return  invoiceTableRepository.getInvoiceTableByInvoiceId(id);  
	}
	
	public InvoiceTable save (InvoiceTable invoiceTable){
		return invoiceTableRepository.save(invoiceTable);
	}
	
	public void deleteById(long id) {
		invoiceTableRepository.deleteById(id);
	}
	
}
