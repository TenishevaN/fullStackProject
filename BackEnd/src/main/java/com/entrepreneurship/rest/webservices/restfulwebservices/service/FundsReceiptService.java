/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.FundsReceiptRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.FundsReceipt;


/*
 * It is for writing business logic for a FundsReceipt class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Service
@Transactional
public class FundsReceiptService {
	
	@Autowired
	FundsReceiptRepository fundsReceiptRepository;
	
	public Double getTotalSum(Date data_from, Date data_to) {
		return fundsReceiptRepository.getTotalSum(data_from, data_to);
	}
	
	public List<FundsReceipt> getAllFundsReceiptsByUser(String userName) {
		return (List<FundsReceipt>) fundsReceiptRepository.findListFundsReceiptsByUser(userName);
	}
	
	
	public FundsReceipt getFundsReceipt(String userName, Long id){
		return fundsReceiptRepository.findById(id).get();		
	}
	
	public FundsReceipt save (FundsReceipt fundsReceipt){
		return fundsReceiptRepository.save(fundsReceipt);
	}	

	public void deleteById(long id) {
		fundsReceiptRepository.deleteById(id);
	}

}
