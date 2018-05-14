package com.dev.flightreservation.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.flightreservation.entities.User;
import com.dev.flightreservation.repos.UserRepository;
import com.dev.flightreservation.services.SecurityService;

// http://localhost:8090/flight-reservation/
// http://localhost:8090/flight-reservation/showReg
// http://localhost:8090/flight-reservation/login
// http://localhost:8090/flight-reservation/login

// http://localhost:8090/flight-reservation/admin/showAddFlight
	
@Controller
public class UserController {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private SecurityService securityService;
	
	@RequestMapping("/showReg")
	public String showRegistrationPage() {	
		LOGGER.info("Inside showRegistrationPage()");
		return "login/registerUser";
	}
	
	@RequestMapping(value="/registerUser", method=RequestMethod.POST)
	public String register(@ModelAttribute("user") User user) {
		LOGGER.info("Inside register()" + user);
		// Password encryption
		user.setPassword(encoder.encode(user.getPassword()));
		userRepository.save(user);		
		return "login/login";
	}
	
	@RequestMapping("/showLogin")
	public String showLoginPage() {
		LOGGER.info("Inside showLoginPage()");
		return "login/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(@RequestParam("email") String email, @RequestParam("password") String password, ModelMap modelMap) {
		
		LOGGER.info("Inside login() and the email is " + email);
		
		/*
		User user = userRepository.findByEmail(email);
				
		if(user.getPassword().equals(password)) {
			return "findFlights";
		} else {
			modelMap.addAttribute("msg", "Invalid username or password. Please try again!");
		}
		*/
		
		// Login authentication using Spring Security Service
		boolean loginResponse = securityService.login(email, password);
		if(loginResponse) {
			return "findFlights";
		} else {
			modelMap.addAttribute("msg", "Invalid username or password. Please try again!");
		}
		
		return "login/login";
	}
	
	
}
