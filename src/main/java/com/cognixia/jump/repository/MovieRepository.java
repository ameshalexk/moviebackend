package com.cognixia.jump.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cognixia.jump.model.Movie;


@Repository
public interface MovieRepository extends JpaRepository<Movie, Long>{
	
	// @Query -> write out custom query
		// @Transactional & @Modifying -> create a custom query that will UPDATE/DELETE/INSERT
		@Transactional
		@Modifying
		@Query("UPDATE Movie m SET m.name = :name WHERE m.id = :id")
		public int updateName(@Param(value="name") String name, @Param(value="id") Long id );

}
