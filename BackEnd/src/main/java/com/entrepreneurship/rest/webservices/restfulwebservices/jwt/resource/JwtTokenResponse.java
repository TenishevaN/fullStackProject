package com.entrepreneurship.rest.webservices.restfulwebservices.jwt.resource;

import java.io.Serializable;

/*
 * It is a service class for protection database providing an encoded password. 
@version 1.0
@author from tutorial
 */
public class JwtTokenResponse implements Serializable {

  private static final long serialVersionUID = 8317676219297719109L;

  private final String token;

    public JwtTokenResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return this.token;
    }
}