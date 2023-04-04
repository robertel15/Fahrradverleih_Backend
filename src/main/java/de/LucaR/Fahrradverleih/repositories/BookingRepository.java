package de.LucaR.Fahrradverleih.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.LucaR.Fahrradverleih.entities.Booking;
import de.LucaR.Fahrradverleih.entities.User;


public interface BookingRepository extends JpaRepository<Booking, UUID>{


	Optional<List<Booking>> findAllByUser(User user);
	
	
	
}