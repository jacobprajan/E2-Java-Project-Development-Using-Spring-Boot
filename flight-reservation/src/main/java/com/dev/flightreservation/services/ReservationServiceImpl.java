package com.dev.flightreservation.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dev.flightreservation.dto.ReservationRequest;
import com.dev.flightreservation.entities.Flight;
import com.dev.flightreservation.entities.Passenger;
import com.dev.flightreservation.entities.Reservation;
import com.dev.flightreservation.repos.FlightRepository;
import com.dev.flightreservation.repos.PassengerRepository;
import com.dev.flightreservation.repos.ReservationRepository;
import com.dev.flightreservation.util.EmailUtil;
import com.dev.flightreservation.util.PdfGenerator;

@Service
public class ReservationServiceImpl implements ReservationService {

	// Spring will inject this property value from application.properties
	@Value("${com.dev.flightreservation.itinerary.dirpath}")
	private String ITINERARY_DIR;

	private static final Logger LOGGER = LoggerFactory.getLogger(ReservationServiceImpl.class);
	
	@Autowired
	private FlightRepository flightRepository;

	@Autowired
	private PassengerRepository passengerRepository;

	@Autowired
	private ReservationRepository reservationRepository;

	@Autowired
	private PdfGenerator pdfGenerator;

	@Autowired
	private EmailUtil emailUtil;

	@Override
	@Transactional
	public Reservation bookFlight(ReservationRequest request) {

		LOGGER.info("Inside bookFlight()");
		// Make payment

		Long flightId = request.getFlightId();
		LOGGER.info("Fetching flight for flight id : " + flightId);
		Flight flight = flightRepository.findById(flightId).get();

		Passenger passenger = new Passenger();
		passenger.setFirstName(request.getPassengerFirstName());
		passenger.setLastName(request.getPassengerLastName());
		passenger.setEmail(request.getPassengerEmail());
		passenger.setPhone(request.getPassengerPhone());

		LOGGER.info("Saving the passenger : " + passenger);
		Passenger savedPassenger = passengerRepository.save(passenger);
		
		
		Reservation reservation = new Reservation();
		reservation.setFlight(flight);
		reservation.setPassenger(savedPassenger);
		reservation.setCheckedIn(false);

		LOGGER.info("Saving the reservation : " + reservation);
		Reservation savedReservation = reservationRepository.save(reservation);

		String filePath = ITINERARY_DIR
				+ savedReservation.getId() + ".pdf";
		
		LOGGER.info("Generating the Itinerary");
		pdfGenerator.generateItinerary(reservation, filePath);
		LOGGER.info("Emailing the Itinerary");
		emailUtil.sendItinerary(passenger.getEmail(), filePath);

		return savedReservation;
	}

}
