package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
	

}
