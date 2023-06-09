package de.LucaR.Fahrradverleih.bike;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import de.LucaR.Fahrradverleih.location.Location;

public interface BikeRepository extends JpaRepository<Bike, UUID>{

	@Query("select b from Bike b where b.isAvailable =true")
	Optional<List<Bike>> findBikeByAvailability();
	
	Optional<List<Bike>> findAllByLocation(Location location);
}
