package com.dev.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.flightreservation.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	public User findByEmail(String email);

}
