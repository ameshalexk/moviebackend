package com.cognixia.jump;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
//@CrossOrigin(origins="*")
public class MovieBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(MovieBackendApplication.class, args);
	}

}
