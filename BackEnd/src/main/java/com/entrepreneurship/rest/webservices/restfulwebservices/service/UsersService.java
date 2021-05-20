/*
 * Copyright (C) 2021 Tenisheva N.I.
 */

package com.entrepreneurship.rest.webservices.restfulwebservices.service;

import javax.transaction.Transactional;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import com.entrepreneurship.rest.webservices.restfulwebservices.model.Users;
import com.entrepreneurship.rest.webservices.restfulwebservices.repository.UserDao;

/*
 * It is for writing business logic for a Users class. 
 * version 1.0
 * author Tenisheva N.I.
 */

@Component
@RequiredArgsConstructor
@Service
@Transactional
public class UsersService {
	 private final UserDao userDao=null;
	
		
	public Users findByUsername(String userName) {
       return userDao.findByUsername(userName);
	//	return userDao.findById(1);     
        
	}
	
	public Users findUserById(long id) {
        return userDao.findById(id);
    }

}
