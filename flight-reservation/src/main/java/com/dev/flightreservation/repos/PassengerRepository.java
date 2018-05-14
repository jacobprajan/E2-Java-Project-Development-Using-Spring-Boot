package com.dev.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.flightreservation.entities.Passenger;

public interface PassengerRepository extends JpaRepository<Passenger, Long>{

}
