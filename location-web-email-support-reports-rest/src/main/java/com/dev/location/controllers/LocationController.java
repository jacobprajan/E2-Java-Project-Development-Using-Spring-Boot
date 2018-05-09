package com.dev.location.controllers;

import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.location.entities.Location;
import com.dev.location.repositories.LocationRepository;
import com.dev.location.service.LocationService;
import com.dev.location.util.EmailUtil;
import com.dev.location.util.ReportUtil;

// http://localhost:8090/location-web/showCreate

@Controller
public class LocationController {

	@Autowired
	private LocationService locationService;

	@Autowired
	private EmailUtil emailUtil;
	
	@Autowired
	private ReportUtil reportUtil;
	
	@Autowired
	private LocationRepository locationRepository;
	
	@Autowired
	private ServletContext sc;

	@RequestMapping("/showCreate")
	public String showCreate() {
		return "createLocation";
	}

	// Spring container will set pojo class values and pass it in ModelAttribute
	// model attribute value is pojo class name with lowercase letter at beginning
	// modelmap for sending response back
	@RequestMapping("/saveLoc")
	public String saveLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		Location locationSaved = locationService.saveLocation(location);
		String msg = "Location saved with id: " + locationSaved.getId();
		modelMap.addAttribute("msg", msg);
		emailUtil.sendEmail("rrexrichu@gmail.com", "Location Saved",
				"Location Saved Successfully and about to return a response");
		return "createLocation";
	}

	@RequestMapping("/displayLocations")
	public String displayLocations(ModelMap modelMap) {
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}

	@RequestMapping("/deleteLocation")
	public String deleteLocation(@RequestParam("id") int id, ModelMap modelMap) {
		// Location location = locationService.getLocationById(id);
		Location location = new Location();
		location.setId(id);
		locationService.deleteLocation(location);

		List<Location> allLocations = locationService.getAllLocations();
		modelMap.addAttribute("locations", allLocations);
		return "displayLocations";
	}

	@RequestMapping("/showUpdate")
	public String showUpdatePage(@RequestParam("id") int id, ModelMap modelMap) {
		Location location = locationService.getLocationById(id);
		modelMap.addAttribute("location", location);
		return "updateLocation";
	}

	@RequestMapping("/updateLoc")
	public String updateLocation(@ModelAttribute("location") Location location, ModelMap modelMap) {
		locationService.updateLocation(location);
		List<Location> locations = locationService.getAllLocations();
		modelMap.addAttribute("locations", locations);
		return "displayLocations";
	}
	
	@RequestMapping("/generateReport")
	public String generateReport() {
		
		List<Object[]> data = locationRepository.findTypeAndTypeCount();
		// For getting real path of application
		String path = sc.getRealPath("/");
		
		reportUtil.generatePieChart(path, data);
		return "report";
	}
	
}
