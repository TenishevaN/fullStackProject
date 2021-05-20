/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.PaymentOrderRepository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.PaymentOrder;


/*
 * It is for writing business logic for a PaymentOrder class. 
 * version 1.0
 * author Tenisheva N.I.
 */


@Service
@Transactional
public class PaymentOrderService {
	@Autowired
	PaymentOrderRepository paymentOrderRepository;
	
	public List<PaymentOrder> checkPaymentTax(Date data_from, Date data_to) {
		return (List<PaymentOrder>) paymentOrderRepository.checkPaymentTax(data_from, data_to);
	}	
	
	public PaymentOrder save (PaymentOrder paymentOrder){
		return paymentOrderRepository.save(paymentOrder);
	}
	
	public List<PaymentOrder> getAllPaymentOrdersByUser(String userName) {
		return (List<PaymentOrder>) paymentOrderRepository.findListPaymentOrdersByUser(userName);
	}
	
	
	public PaymentOrder getPaymentOrder(String userName, Long id){
		return paymentOrderRepository.findById(id).get();		
	}
	

	public void deleteById(long id) {
		paymentOrderRepository.deleteById(id);
	}
	
}
