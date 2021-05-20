/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Contract;

/*
 * It is a storage, retrieval, and search mechanism for a Contract class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface ContractRepository extends CrudRepository<Contract, Long>{
	
	 @Query(nativeQuery = true, value = "select c.* from users u inner join contract c  on u.id=c.id_username  where u.username = :username")
	 List<Contract> findListContractsByUser(@Param("username") String username);
	 
	 
	 
	 	

}
