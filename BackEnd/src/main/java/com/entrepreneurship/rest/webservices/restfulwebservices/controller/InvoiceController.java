/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.controller;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
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
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Invoice;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.InvoiceTable;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.InvoiceService;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.InvoiceTableService;
import net.sf.jxls.transformer.XLSTransformer;
import com.entrepreneurship.rest.webservices.restfulwebservices.service.ContractService;
import static com.entrepreneurship.rest.webservices.restfulwebservices.serviceConstants.PathOfPrintTemplate.*;

/*
 * It is designed to handle web requests to a Invoice class. 
 * @version 1.0
   @author Tenisheva N.I.
 */

@CrossOrigin(origins="http://localhost:4200")
@RestController
public class InvoiceController {

	@Autowired
	private InvoiceService invoiceService;
	
	@Autowired
	private
	InvoiceTableService invoiceTableService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ContractService contractService;
	
	@GetMapping("/jpa/{username}/invoices")
	public List<Invoice> getAllActs(@PathVariable String username){
	
		return invoiceService.getAllInvoicesByUser(username);
	}
	
	@GetMapping("/jpa/{username}/contractInvoices/{id}")
	public List<Invoice> getAllContractInvoices(@PathVariable String username,  @PathVariable long id){
	
		return invoiceService.getAllContractInvoicesByUser(username, id);
	}
	
		
		@DeleteMapping("/jpa/{username}/invoices/{id}")
	public ResponseEntity<Void> deleteAct(
		@PathVariable String username, @PathVariable long id) {

		invoiceService.deleteById(id);

			return ResponseEntity.noContent().build();
	}
	
		
	@GetMapping("/jpa/{username}/invoices/{id}")
	public Invoice getInvoice(@PathVariable String username, @PathVariable long id){
		return invoiceService.getInvoice(username, id);
	}
	
	
	@PutMapping("/jpa/{username}/invoices/{id}/{contract_id}")
	public ResponseEntity<Invoice> updateInvoice(
			@PathVariable String username,
			@PathVariable long id, @PathVariable int contract_id, @RequestBody Invoice invoice){
		
		invoice.setUser(userDao.findByUsername(username)); 
	
		invoice.setContract(contractService.getContractById((long) contract_id));
		
		Invoice updatedInvoice = invoiceService.save(invoice);
		
		return new ResponseEntity<Invoice>(updatedInvoice, HttpStatus.OK);
	}
	
	@PostMapping("/jpa/{username}/invoices/{contract_id}")
	public ResponseEntity<Invoice> createInvoice(
			@PathVariable String username, @PathVariable int contract_id ,@RequestBody Invoice invoice){
		
		invoice.setUser(userDao.findByUsername(username));
		invoice.setContract(contractService.getContractById((long) contract_id));
		Invoice createdInvoice = invoiceService.save(invoice);
		
	    return new ResponseEntity<Invoice>(createdInvoice, HttpStatus.OK);
	}
	
	
	@PutMapping("/jpa/{username}/printInvoice/{id}")
	public Invoice printInvoice(@PathVariable String username,  @PathVariable long id){
		
		Map<String, Object> beans = new HashMap<String, Object>();
		Invoice invoice = invoiceService.getInvoice(username, (long) id);
		List<InvoiceTable> invoice_table = invoiceTableService.getInvoiceTableById(id);
		beans.put("invoice", invoice);
		beans.put("invoice_table", invoice_table);
		XLSTransformer transformer = new XLSTransformer();
		try {
			transformer.transformXLS(InvoicePathExls.getPath(), beans, "D:\\Invoice.xls");

			try {

				if ((new File("D:\\Invoice.xls")).exists()) {

					Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler D:\\Invoice.xls");
					p.waitFor();

				} else {

					System.out.println("File is not exists");

				}

				System.out.println("Done");

			} catch (Exception ex) {
				ex.printStackTrace();
			}

		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return invoice;
	}
	
		
	
	
	
}
