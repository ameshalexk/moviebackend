package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.User;
import com.cognixia.jump.model.UsersMovie;

@Repository
public interface UserMovieRepository extends JpaRepository<UsersMovie, Long> {

	//public Optional<UsersMovie> findByUsersMovie(UsersMovie usersMovie);
}
