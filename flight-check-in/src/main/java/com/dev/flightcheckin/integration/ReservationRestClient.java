package com.dev.flightcheckin.integration;

import com.dev.flightcheckin.integration.dto.Reservation;
import com.dev.flightcheckin.integration.dto.ReservationUpdateRequest;

public interface ReservationRestClient {

	public Reservation findReservation(Long id);
	
	public Reservation updateReservation(ReservationUpdateRequest request);
	
}
