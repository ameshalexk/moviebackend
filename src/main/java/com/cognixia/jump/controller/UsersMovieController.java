package com.cognixia.jump.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
//	@GetMapping("/usersmovie/{id}")
//	public ResponseEntity<?> findByIdOne(@PathVariable String id) {
//
//		List<UsersMovie> usermovie = (List<UsersMovie>) repo.findByIdOne(id);
//
//		if (usermovie == null) {
//			return ResponseEntity.status(404).body("Could not find usermovie.");
//		}
//
//		return ResponseEntity.status(200).body(usermovie);
//	}
//	
	@PostMapping("/usersmovie")
	public ResponseEntity<UsersMovie> addUserMovie(@RequestBody UsersMovie usersMovie) {
		usersMovie.setId(-1L);
		
		UsersMovie added = repo.save(usersMovie);


		return ResponseEntity.status(201).header("MovieUsers id").body(added);
		
	}
	
//	@PostMapping("/usersmoviecustom")
//	public ResponseEntity<?> insertMovie(@PathParam(value="id") int id, @PathParam(value="lastname") String lastname) {
//		boolean updated = service.updateLastname(id, lastname);
//		System.out.println(lastname + "check" + id);
//
//		if (updated) {
//			return ResponseEntity.status(200).body("Last name updated");
//		}
//		return ResponseEntity.status(400).body("Could not update Last name");
//
//	}


		@DeleteMapping("/usersmovie/{id}")
	public ResponseEntity<?> deleteMovie(@PathVariable Long id) {
			repo.deleteUsersByUsersId(id);

			return ResponseEntity.status(200).body(true);
		
	}

}
