package de.LucaR.Fahrradverleih.repositories;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import de.LucaR.Fahrradverleih.entities.Fahrrad;
import de.LucaR.Fahrradverleih.entities.Standort;

public interface FahrradRepository extends JpaRepository<Fahrrad, UUID>{
	
	
	Optional<List<Fahrrad>> findAllByStandort(Standort standort);


}