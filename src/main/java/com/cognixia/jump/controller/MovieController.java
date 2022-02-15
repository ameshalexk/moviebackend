package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognixia.jump.model.Movie;
import com.cognixia.jump.repository.MovieRepository;

@RequestMapping("/api")
@RestController
public class MovieController {

	@Autowired
	MovieRepository repo;

	@GetMapping("/movie")
	public List<Movie> getMovies() {
		return repo.findAll();
	}

	@GetMapping("/movie/{id}")
	public ResponseEntity<?> getMovieById(@PathVariable int id) {

		Optional<Movie> movie = repo.findById(id);

		if (movie == null) {
			return ResponseEntity.status(404).body("Could not find movie.");
		}

		return ResponseEntity.status(200).body(movie);
	}

	@DeleteMapping("/movie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable int id) {

		if (repo.existsById(id)) {

			ResponseEntity<?> toDelete = getMovieById(id);

			repo.deleteById(id);

			return ResponseEntity.status(200).body(toDelete);
		}

		return ResponseEntity.status(404).body("Could not find Movie.");

	}

	@PostMapping("/student")
	public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {

		// movie.setId(-1); Not necessary I think

		Movie added = repo.save(movie);

		return ResponseEntity.status(201).header("student id", added.getId() + "").body(added);

	}

	@PutMapping("/movie")
	public ResponseEntity<?> updateMovie(@RequestBody Movie movie) {

		Movie updated = repo.save(movie);

		if (updated == null) {
			return ResponseEntity.status(404).body("Couldn't find movie with id = " + movie.getId());
		}

		return ResponseEntity.status(200).body(updated);
	}

	@PatchMapping("/movie/update/name")
	public ResponseEntity<?> updateName(@PathParam(value = "id") int id, @PathParam(value = "name") String newName) {

		if (repo.existsById(id)) {

			int count = repo.updateName(newName, id); // Needs JPA query

			if (count > 0) {
				return ResponseEntity.status(200).body("Major updated");
			}
		}
		return ResponseEntity.status(400).body("Couldn't update major");
	}
}