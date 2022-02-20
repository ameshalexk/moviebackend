package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.exception.ResourceNotFoundException;
import com.cognixia.jump.model.AuthenticationRequest;
import com.cognixia.jump.model.AuthenticationResponse;
import com.cognixia.jump.model.User;
import com.cognixia.jump.repository.UserRepository;
import com.cognixia.jump.service.MyUserDetailsService;
import com.cognixia.jump.util.JwtUtil;

@RequestMapping("/api")
@RestController
public class UserController {
	
	@Autowired
	UserRepository repo;
	
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	AuthenticationManager authenticationManager;
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtUtil jwtUtil;

	
	@GetMapping("/user")
	public List<User> getUsers() {
		return repo.findAll();
	}
	
//	@GetMapping("/user/{id}")
//	public ResponseEntity<User> getUser(@PathVariable long id) throws ResourceNotFoundException {
//		
//		Optional<User> found = repo.findById(id);
//		
//		if(found.isEmpty()) {
//			throw new ResourceNotFoundException("User with id = " + id + " was not found");
//		}
//		
//		return ResponseEntity.status(200).body(found.get());
//	}
	
	@GetMapping("/user/{username}")
	public ResponseEntity<User> getUser(@PathVariable String username) throws ResourceNotFoundException {
		
		Optional<User> found = repo.findByUsername(username);
		
		if(found.isEmpty()) {
			throw new ResourceNotFoundException("User with username = " + username + " was not found");
		}
		
		return ResponseEntity.status(200).body(found.get());
	}
		
	@PostMapping("/user")
	public ResponseEntity<User> createUser(@RequestBody User user) {
		System.out.println("craeted *****1");

		user.setId(null);
		
		user.setPassword(encoder.encode(user.getPassword()));
		
		User created = repo.save(user);
		
		System.out.println("craeted *****2"+ created);
		
		return ResponseEntity.status(201).body(created);
	}
	
	// a user will be able to provide their credentials and get back a jwt
		// once they get the jwt, it can be passed instead of their credentials when making other requests
		// to this API service
		@PostMapping("/authenticate")
		public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest request) throws Exception {
			
			// will catch the exception for bad credentials and...
			try {
				// make sure we can authenticate our user based on the username and password
				authenticationManager.authenticate(
							new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
						);
			} catch(BadCredentialsException e) {
				
				//...then provide own message as to why user could not be authenticated
				throw new Exception("Incorrect username or password");
			}
			
			// as long as user is found, we can create the JWT
			
			// find the user
			final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
			
			// generate token for this user
			final String jwt = jwtUtil.generateTokens(userDetails);
			
			// return token
			return ResponseEntity.status(200).body( new AuthenticationResponse(jwt) );
			
		}
		
	}


