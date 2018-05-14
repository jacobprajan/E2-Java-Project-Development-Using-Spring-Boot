package com.dev.flightreservation.services;

import com.dev.flightreservation.dto.ReservationRequest;
import com.dev.flightreservation.entities.Reservation;

public interface ReservationService {

	public Reservation bookFlight(ReservationRequest request);
}
