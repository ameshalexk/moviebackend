package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Movie;
import com.cognixia.jump.model.UsersMovie;
import com.cognixia.jump.repository.MovieRepository;
import com.cognixia.jump.repository.UserMovieRepository;

@RequestMapping("/api")
@RestController
public class UsersMovieController {
	
	@Autowired
	UserMovieRepository repo;
	
	@GetMapping("/usersmovie")
	public List<UsersMovie> getUsersMovie() {
		return repo.findAll();
	}
	
	@GetMapping("/usersmovie/{id}")
	public ResponseEntity<?> getUsersMovieById(@PathVariable Long id) {

		Optional<UsersMovie> usermovie = repo.findById(id);

		if (usermovie == null) {
			return ResponseEntity.status(404).body("Could not find usermovie.");
		}

		return ResponseEntity.status(200).body(usermovie);
	}
	

}
