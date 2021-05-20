/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.InvoiceTable;

/*
 *It is a storage, retrieval, and search mechanism for an InvoiceTable class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface InvoiceTableRepository extends CrudRepository<InvoiceTable, Long>{

		
	 @Query(nativeQuery = true, value = "select i_t.*, i_t.price* i_t.quantity sum from INVOICE_TABLE  i_t where i_t.id_invoice = :id")
	 List<InvoiceTable> getInvoiceTableById(@Param("id") Long id);
	 
	 @Query(nativeQuery = true, value = "select i_t.*, i_t.price* i_t.quantity sum from INVOICE_TABLE i_t left join INVOICE i on i.id = i_t.id_invoice left join contract c on c.id= i.id_contract where c.id= :id")
	 List<InvoiceTable> getInvoiceTableByContractId(@Param("id") Long id);
	 
	 @Query(nativeQuery = true, value = "select i_t.*, i_t.price* i_t.quantity sum from INVOICE_TABLE i_t left join INVOICE i on i.id = i_t.id_invoice  where i_t.id_invoice = :id")
	 List<InvoiceTable> getInvoiceTableByInvoiceId(@Param("id") Long id);


}
