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

import de.LucaR.Fahrradverleih.bike.Bike;
import de.LucaR.Fahrradverleih.bike.BikeService;

@RestController
@RequestMapping("api/v1/location")
public class LocationController {

	private final LocationService locationService;
	private final BikeService bikeService;
	
	@Autowired
	public LocationController(LocationService locationService, BikeService bikeService) {
		this.locationService = locationService;
		this.bikeService = bikeService;
	}
	
	@GetMapping("")
	public List<Location> getLocations() {
		return locationService.getLocations();
	}
	
	@GetMapping("{id}/bike")
	public List<Bike> getBikesByLocation(@PathVariable("id") UUID locationId) {
		return locationService.getBikesAtLocation(locationId);
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
