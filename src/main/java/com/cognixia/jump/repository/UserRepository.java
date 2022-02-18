package com.cognixia.jump.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.User;
import com.cognixia.jump.service.MyUserDetailsService;

@Repository
public interface UserRepository extends JpaRepository<User, Long>{
	
	
	public Optional<User> findByUsername(String username);

//	public MyUserDetailsService loadUserByUsername(String username);

}
