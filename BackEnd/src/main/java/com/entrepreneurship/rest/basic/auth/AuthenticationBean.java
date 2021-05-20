package com.entrepreneurship.rest.basic.auth;

/*
 * It is a service class for protection database providing an encoded password. 
@version 1.0
@author from tutorial
 */
public class AuthenticationBean {

    private String message;

    public AuthenticationBean(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return String.format("HelloWorldBean [message=%s]", message);
    }

}