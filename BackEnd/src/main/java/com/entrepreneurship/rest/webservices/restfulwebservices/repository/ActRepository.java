/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Act;

/*
 * It is a storage, retrieval, and search mechanism for an Act class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface ActRepository extends CrudRepository<Act, Long>{
	
	 @Query(nativeQuery = true, value = "select a.*, contr.*, cust.name as customer_name from act a left join users u on u.id=a.id_username  left join contract contr on a.id_contract = contr.id left join customer cust on contr.id_customer = cust.id where u.username = :username")
	 List<Act> findListActsByUser(@Param("username") String username);

	  
	 @Query(nativeQuery = true, value = "select a.*, contr.rate as contract_rate, contr.contract_number as contract_number, contr.id from act a left join users u on u.id=a.id_username  left join contract contr on a.id_contract = contr.id  where u.username = :username and a.id_contract = :id")
	 List<Act> findContractActsByUser(@Param("username") String username, @Param("id") Long id);
 
}
