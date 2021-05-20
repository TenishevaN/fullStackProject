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
import com.entrepreneurship.rest.webservices.restfulwebservices.model.FundsReceipt;


/*
 * It is a storage, retrieval, and search mechanism for a FundsReceipt class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface FundsReceiptRepository extends CrudRepository<FundsReceipt, Long> {
	 @Query(nativeQuery = true, value = "select SUM(funds_receipt.sum) from FUNDS_RECEIPT funds_receipt where  funds_receipt.date between :period_from and :period_to")
	 Double getTotalSum(@Param("period_from") Date period_from, @Param("period_to") Date period_to);
	 
	 @Query(nativeQuery = true, value = "select f_r.*  from FUNDS_RECEIPT f_r inner join users u on u.id=f_r.id_username  where u.username = :username")
	 List<FundsReceipt> findListFundsReceiptsByUser(@Param("username") String username);

}
