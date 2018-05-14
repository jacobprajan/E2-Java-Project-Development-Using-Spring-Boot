package com.dev.flightreservation.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.flightreservation.entities.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Long>{

}
