/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Invoice;

/*
 * It is a storage, retrieval, and search mechanism for an Invoice class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface InvoiceRepository extends CrudRepository<Invoice, Long>{
	
	 @Query(nativeQuery = true, value = "select i.*, contr.*, cust.name as customer_name from invoice i left join users u on u.id=i.id_username  left join contract contr on i.id_contract = contr.id left join customer cust on contr.id_customer = cust.id where u.username = :username")
	 List<Invoice> findListInvoicesByUser(@Param("username") String username);

	  
	 @Query(nativeQuery = true, value = "select i.*, contr.rate as contract_rate, contr.contract_number as contract_number, contr.id from invoice i left join users u on u.id=i.id_username  left join contract contr on i.id_contract = contr.id  where u.username = :username and i.id_contract = :id")
	 List<Invoice> findContractInvoicesByUser(@Param("username") String username, @Param("id") Long id);	 
	 
}
