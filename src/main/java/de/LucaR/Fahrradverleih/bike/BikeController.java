package de.LucaR.Fahrradverleih.bike;

import java.util.List;
import java.util.Optional;
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
@RequestMapping("api/v1/bike")
public class BikeController {

	private final BikeService bikeService;
	
	@Autowired
	public BikeController(BikeService bikeService) {
		this.bikeService = bikeService;
	}
	
	@GetMapping("")
	public List<Bike> getBikes() {
		return bikeService.getBikes();
	}
	
	@GetMapping("/available")
	public Optional<List<Bike>> getAvailableBikes() {
	
		return bikeService.getAvailableBikes();
	}
	
//	@PostMapping("") Bisher ist der Fehler nicht auffindbar der immer zurückschmeißt "Given ID must not be null"
//	public Bike registerNewBike(@RequestBody BikeConfig bike) {
//		return bikeService.addNewBike(bike);
//	}
	
	@PostMapping("")
	public Bike registerNewBike(@RequestBody Bike bike) {
		return bikeService.addNewBike(bike);
	}
	
	@DeleteMapping("{bikeId}")
	public void deleteBike(@PathVariable("bikeId") UUID bikeId) {
		bikeService.deleteBike(bikeId);
	}
	
	@PutMapping("")
	public void updateBike(@RequestBody Bike bike) {
		bikeService.updateBike(bike.getId(), bike.getName(), bike.getPrice(), bike.isAvailable(), bike.getType(), bike.getLocation());
	}
}
