package com.cognixia.jump.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import com.cognixia.jump.model.UsersMovie;

@Repository
public interface UserMovieRepository extends JpaRepository<UsersMovie, Long> {
	
	
//	UsersMovie findByUserName(String name);
	
//	@Transactional
//	@Modifying 
//	@Query("UPDATE Employee e SET e.firstName = :firstName WHERE e.id = :id")
//	public int updateFirstname(@Param(value="firstName") String firstname, @Param(value="id") int id);
//	
//	 @Query(value = "SELECT * FROM users_movie where USER_ID = ?1", nativeQuery = true)
//	 List<UsersMovie> findByIdOne(String userId);
//	 
	@Transactional
	 @Modifying
 	 @Query(value="DELETE from Users_movie u where u.user_id = ?1", nativeQuery = true)
    void deleteUsersByUsersId(Long userId);



//	
}
