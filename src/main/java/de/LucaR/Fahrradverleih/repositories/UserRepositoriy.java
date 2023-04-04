package de.LucaR.Fahrradverleih.repositories;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.LucaR.Fahrradverleih.entities.User;

public interface UserRepository extends JpaRepository<User, UUID>{
	
	
	Optional<User> findByEmailEquals(String email);
	
	
	Optional<User> findByEmail(String email);


}