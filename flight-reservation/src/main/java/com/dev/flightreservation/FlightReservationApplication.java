package com.dev.flightreservation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

// War file deploy 
// Modify package type in pom.xml also

@SpringBootApplication
public class FlightReservationApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(FlightReservationApplication.class, args);
	}
	
	// For deploying application on war file
	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(FlightReservationApplication.class);
	}
}
