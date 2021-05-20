/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Users;


/*
 * It is a storage, retrieval, and search mechanism for a UserDao class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Repository
public interface UserDao extends JpaRepository<Users, Long>{
		
    Users findByUsername(String username);
	Users findById( long id);

}
	
	