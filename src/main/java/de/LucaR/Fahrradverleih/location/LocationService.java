package de.LucaR.Fahrradverleih.location;

import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.LucaR.Fahrradverleih.bike.Bike;
import de.LucaR.Fahrradverleih.bike.BikeRepository;

@Service
public class LocationService {

	private final LocationRepository locationRepository;
	private final BikeRepository bikeRepository;
	
	@Autowired
	public LocationService(LocationRepository locationRepository, BikeRepository bikeRepository) {
		this.locationRepository = locationRepository;
		this.bikeRepository = bikeRepository;
	}
	
	public List<Location> getLocations() {
		return locationRepository.findAll();
	}
	
	public List<Bike> getBikesAtLocation(UUID locationId) {
		
		Location l = locationRepository.findById(locationId).get();
		
		return bikeRepository.findAllByLocation(l).get();
	}

	public void addNewLocation(Location location) {
		// TODO Auto-generated method stub
		locationRepository.save(location);
	}

	public void deleteLocation(UUID locationId) {
		// TODO Auto-generated method stub
		locationRepository.deleteById(locationId);
	}

	@Transactional
	public void updateLocation(UUID id, String name, String city, String street, int postalCode, double longitude, double latitude, String pictureLink) {
		// TODO Auto-generated method stub
		Location location = locationRepository.findById(id).orElseThrow(()
				-> new IllegalStateException("Location mit der ID "+ id + " existiert nicht!"));
		
		location.setId(id);
		location.setName(name);
		location.setCity(city);
		location.setStreet(street);
		location.setPostalCode(postalCode);
		location.setLongitude(longitude);
		location.setLatitude(latitude);
		location.setPictureLink(pictureLink);
	}

	
}
