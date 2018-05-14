package com.dev.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.flightreservation.dto.ReservationUpdateRequest;
import com.dev.flightreservation.entities.Reservation;
import com.dev.flightreservation.repos.ReservationRepository;

@RestController
public class ReservationRestController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationRestController.class);
	
	@Autowired
	private ReservationRepository reservationRepository;

	@RequestMapping("/reservations/{id}")
	public Reservation findReservation(@PathVariable("id") Long id) {
		LOGGER.info("Inside findReservation() for id : " + id);
		return reservationRepository.findById(id).get();

	}

	// While request is coming, Spring will deserialize json to ReservationUpdateRequest object
	@RequestMapping("/reservations")
	public Reservation updateReservation(@RequestBody ReservationUpdateRequest request) {

		LOGGER.info("Inside updateReservation() for request : " + request);
		Reservation reservation = reservationRepository.findById(request.getId()).get();
		reservation.setNumberOfBags(request.getNumberOfBags());
		reservation.setCheckedIn(request.getCheckedIn());

		LOGGER.info("Saving reservation");
		return reservationRepository.save(reservation);

	}

}
