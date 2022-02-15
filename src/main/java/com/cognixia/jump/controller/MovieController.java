package com.cognixia.jump.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Movie;

import com.cognixia.jump.repository.MovieRepository;


@RequestMapping("/api")
@RestController
public class MovieController {
	
	@Autowired
	MovieRepository repo;
	

	@GetMapping("/book")
	public List<Movie> getMovies() {
		return repo.findAll();
	}
	

	
}


