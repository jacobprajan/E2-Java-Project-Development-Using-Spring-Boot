package com.dev.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.flightreservation.entities.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{

}
