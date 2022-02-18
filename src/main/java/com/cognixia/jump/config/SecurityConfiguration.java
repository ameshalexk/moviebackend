package com.cognixia.jump.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.cognixia.jump.filter.JwtRequestFilter;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
	
	@Autowired
	JwtRequestFilter jwtRequestFilter;
	

	// handle the authentication (who are you?)
	// lookup if the credentials passed in the request match any of the users for this service
	@Override
	protected void configure( AuthenticationManagerBuilder auth ) throws Exception {
		
		auth.userDetailsService(userDetailsService);
	}
	
	// handle password encoding -> takes the password and hashes so it isn't stored as plain text
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		// new way to remove password encoding
//		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
		return new BCryptPasswordEncoder();

		// use old way to set up encoding, new way above will cause issues
//		return NoOpPasswordEncoder.getInstance();
	}
	
	
	// which users have access to which uri paths (API)
	// authorization (what do you want?)
	@Override
	protected void configure( HttpSecurity http ) throws Exception {
		
		
		http.csrf().disable()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST, "/api/user").permitAll()
//			.antMatchers("/api/authenticate").permitAll() // permit anyone to create jwts
			.antMatchers("/api/movie").hasAnyRole("ADMIN", "USER", "DEV")
			.antMatchers("/**").hasRole("ADMIN") // admin has access to any of the APIs not stated above
			.antMatchers(HttpMethod.GET, "/api/user" ).permitAll() // any user can use GET
			.anyRequest().authenticated()
			.and().httpBasic() // allow for basic auth.
			.and().sessionManagement()								    // tell spring security not to create any session,
				.sessionCreationPolicy(SessionCreationPolicy.STATELESS);// we want to be stateless b/c we're using jwts
		
		
			
		// make sure jwt filter is checked first before any other filter, especially the filter
		// used to check the username and password of a user
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
		
	}
	
	
	
	// just providing a method spring security can use to access the AuthenticationManager needed when
	// authenticating users accessing the APIs (used in the autowired manager in HelloController)
	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		
		return super.authenticationManagerBean();
	}
	
	
	
}







