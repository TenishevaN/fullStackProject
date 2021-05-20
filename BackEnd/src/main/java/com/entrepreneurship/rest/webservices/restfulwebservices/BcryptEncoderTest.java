package com.entrepreneurship.rest.webservices.restfulwebservices;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/*
 * BcryptEncoderTest class generates encoding password as it expires
@author from tutorial
 */

public class BcryptEncoderTest {

	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		
		for(int i=1; i<=10; i++){
		String encoded = encoder.encode("dummy");
		System.out.println(encoded);
		}
		

	}

}
