package com.rachana.task2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.rachana.task2.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	
	// List<User> findAll(); 

	@Query("SELECT u FROM User u WHERE u.email = :email AND u.password = :password")
	public User checkUser(@Param("email") String email,@Param("password") String password);

	
}
