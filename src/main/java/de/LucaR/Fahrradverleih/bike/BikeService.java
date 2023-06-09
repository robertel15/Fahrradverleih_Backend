package de.LucaR.Fahrradverleih.bike;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.LucaR.Fahrradverleih.location.Location;
import de.LucaR.Fahrradverleih.location.LocationRepository;

@Service
public class BikeService {

	private final BikeRepository bikeRepository;
	private final LocationRepository locationRepository;
	
	@Autowired
	public BikeService(BikeRepository bikeRepository, LocationRepository locationRepository) {
		this.bikeRepository = bikeRepository;
		this.locationRepository = locationRepository;
	}
	
	public List<Bike> getBikes() {
		return bikeRepository.findAll();
	}
	
	public Optional<List<Bike>> getAvailableBikes() {
		
		Optional<List<Bike>> availableBikes = bikeRepository.findBikeByAvailability();
		
		return availableBikes;
	}

//	public Bike addNewBike(BikeConfig bike) {
//		Location location = locationRepository.findById(bike.locationID).get();
//		
//		Bike b = new Bike();
//		
//		b.setName(bike.name);
//		b.setPrice(bike.price);
//		b.setAvailable(bike.isAvailable);
//		b.setType(bike.type);
//		b.setPictureLink(bike.pictureLink);
//		b.setLocation(location);
//		
//		
//		return bikeRepository.save(b);
//	}

	public Bike addNewBike(Bike bike) {
		
		
		return bikeRepository.save(bike);
	}

	public void deleteBike(UUID bikeId) {
		// TODO Auto-generated method stub
		bikeRepository.findById(bikeId).orElseThrow(()
				-> new IllegalStateException("Bike mit der ID "+ bikeId + " existiert nicht!"));
		
		bikeRepository.deleteById(bikeId);
	}

	@Transactional
	public void updateBike(UUID id, String name, double price, boolean isAvailable, BikeType type, Location location) {
		// TODO Auto-generated method stub
		Bike bike = bikeRepository.findById(id).orElseThrow(()
				-> new IllegalStateException("Bike mit der ID "+ id + " existiert nicht!"));
		
		/*if(bike.getLocation().getId().equals(location.getId())) { //wenn hier kein Standort übergeben wird rip Fkt macht eh kein sinn
			throw new IllegalStateException("Standort ist der gleiche"); //wie verhindere ich hier dass nicht valide Standorte angegeben werden
		}*/
		
		bike.setName(name);
		bike.setPrice(price);
		bike.setAvailable(isAvailable);
		bike.setType(type);
		bike.setLocation(location);
	}

	
}
