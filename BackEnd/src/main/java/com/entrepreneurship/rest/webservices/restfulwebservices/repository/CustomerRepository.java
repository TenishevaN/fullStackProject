/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Customer;


/*
 * It is a storage, retrieval, and search mechanism for a Customer class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long>{
	
	 @Query(nativeQuery = true, value = "select c.id, c.name, c.inn, c.props, c.comment, c.id_username from users u left join customer c on u.id=c.id_username where u.username = :username")
	 List<Customer> findListCustomersByUser(@Param("username") String username);

}
