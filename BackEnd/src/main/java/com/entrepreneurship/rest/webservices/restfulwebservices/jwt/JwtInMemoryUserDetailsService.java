package com.entrepreneurship.rest.webservices.restfulwebservices.jwt;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


/*
 * It is a service class. Set for user encoded password, generated using BcryptEncoderTest class and role. 
@version 1.0
@author from tutorial
 */

@Service
public class JwtInMemoryUserDetailsService implements UserDetailsService {

  static List<JwtUserDetails> inMemoryUserList = new ArrayList<>();

  static {
    inMemoryUserList.add(new JwtUserDetails(1L, "nataliia",
        "$2a$10$PLTzgG5wZ9A6sUnzalkeMOEbV9jfHNU0GFCiMeuZyTDSfV/wrfDvS", "ROLE_DOMAIN_USER"));
    
    inMemoryUserList.add(new JwtUserDetails(2L, "admin",
    		"$2a$10$9X5GBt7MCbpURUpZEYN/8OB3Qpq7p92aPbPSnfndzWHuJVInJwcw.", "ROLE_USER_2"));
    
    
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<JwtUserDetails> findFirst = inMemoryUserList.stream()
        .filter(user -> user.getUsername().equals(username)).findFirst();

    if (!findFirst.isPresent()) {
      throw new UsernameNotFoundException(String.format("USER_NOT_FOUND '%s'.", username));
    }

    return findFirst.get();
  }

}


