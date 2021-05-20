package com.entrepreneurship.rest.webservices.restfulwebservices.jwt.resource;

/*
 * It is a service class for protection database providing an encoded password. 
@version 1.0
@author from tutorial
 */
public class AuthenticationException extends RuntimeException {
   
	private static final long serialVersionUID = 1L;

	public AuthenticationException(String message, Throwable cause) {
        super(message, cause);
    }
}

