package com.dev.location.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.location.entities.Location;

public interface LocationRepository extends JpaRepository<Location, Integer> {

}
