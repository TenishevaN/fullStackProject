/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.PaymentOrder;

/*
 * It is a storage, retrieval, and search mechanism for a PaymentOrder class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface PaymentOrderRepository extends CrudRepository<PaymentOrder, Long>{
	
	 @Query(nativeQuery = true, value = "select payment_order.* from PAYMENT_ORDER payment_order where payment_order.paid = false")
	 List<PaymentOrder> getUnpaidPaymentOrder();
	 
	 @Query(nativeQuery = true, value = "select payment_order.* from PAYMENT_ORDER payment_order where  payment_order.date between :period_from and :period_to")
	 List<PaymentOrder> checkPaymentTax(@Param("period_from") Date period_from, @Param("period_to") Date period_to);
	 
	 @Query(nativeQuery = true, value = "select p_o.*  from PAYMENT_ORDER p_o inner join users u on u.id=p_o.id_username  where u.username = :username")
	 List<PaymentOrder> findListPaymentOrdersByUser(@Param("username") String username);
	 
}
