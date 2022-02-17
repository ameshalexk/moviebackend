package com.cognixia.jump.service;

import java.util.ArrayList;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

	// typically will a user that is pulled from the repository, but we're not going to add that in,
	// will just hard code a user
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		// no matter what username or password we use/pass in the request, this user with the 
		// below credentials will always be returned
		return new User("user1", "pw123", new ArrayList<>());
	}

}
