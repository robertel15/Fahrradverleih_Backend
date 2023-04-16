package de.LucaR.Fahrradverleih.location;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {

	private final LocationService locationService;
	
	@Autowired
	public LocationController(LocationService locationService) {
		this.locationService = locationService;
	}
	
	@GetMapping("")
	public List<Location> getLocations() {
		return locationService.getLocations();
	}
	
	@PostMapping("")
	public void registerNewLocation(@RequestBody Location location) {
		locationService.addNewLocation(location);
	}
	
	@DeleteMapping("{locationId}")
	public void deleteLocation(@PathVariable("locationId") UUID locationId) {
		locationService.deleteLocation(locationId);
	}
	
	@PutMapping("")
	public void updateLocation(@RequestBody Location location) {
		locationService.updateLocation(location.getId(), location.getName(), location.getCity(), location.getStreet(), 
				location.getPostalCode(), location.getLongitude(), location.getLatitude(), location.getPictureLink());
	}
}
