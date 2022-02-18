package com.cognixia.jump.model;

// response for when /authenticate endpoint that returns the JWT
public class AuthenticationResponse {
	
	private final String jwt;
	
	public AuthenticationResponse(String jwt) {
		this.jwt = jwt;
	}

	public String getJwt() {
		return jwt;
	}

}
