package de.LucaR.Fahrradverleih.controller;


import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import de.LucaR.Fahrradverleih.dao.UserLoginObject;
import de.LucaR.Fahrradverleih.dao.UserRegistrationsObject;
import de.LucaR.Fahrradverleih.entities.User;
import de.LucaR.Fahrradverleih.repositories.BookingRepository;
import de.LucaR.Fahrradverleih.repositories.UserRepository;

@Controller
@RestController
@RequestMapping("/users")
public class UserInteractionController {
	
	@Autowired
	BookingRepository bookingRepository;
	
	@Autowired
	UserRepository userRepository;
	
	@GetMapping("/{id}/bookings")
	public ResponseEntity<Object> getBookings(@PathVariable UUID id){
		Optional<User> u = userRepository.findById(id);
		try {
			User user =u.get();
			return new ResponseEntity<Object>(bookingRepository.findAllByUser(user).get(), HttpStatus.OK);
		}
		catch(NoSuchElementException e) {
			return new ResponseEntity<Object>("Gibt keine Buchungen bro", HttpStatus.NOT_FOUND);
		}
	}
	
	@PostMapping("")
	public ResponseEntity<Object> register(@RequestBody UserRegistrationsObject uro){
		
		boolean alreadyExists = false;
		
		if(!(uro.passwordHash.equals(uro.passwordConfirmHash))) {
			return new ResponseEntity<Object>("Incorrect password!", HttpStatus.UNAUTHORIZED);
		}
		
		
	
		try {
			@SuppressWarnings("unused")
			User user = userRepository.findByEmail(uro.email).get();
			alreadyExists = true;
		}catch(Exception e) {};
		
		if(alreadyExists) {
			return new ResponseEntity<Object>("User gibts schon", HttpStatus.NOT_ACCEPTABLE);
		}
		
		byte[] signatur;
		if(uro.Signatur == null) signatur = null;
		else signatur = uro.Signatur;
		User toAdd = new User();
		
		
		toAdd.setEmail(uro.email);
		toAdd.setName(uro.name);
		toAdd.setFirstName(uro.firstName);
		toAdd.setPassword(uro.passwordHash);
		toAdd.setStreet(uro.street);
		toAdd.setNumber(uro.number);
		toAdd.setCity(uro.city);
		toAdd.setSignatur(signatur);
	
		return new ResponseEntity<Object>(userRepository.save(toAdd), HttpStatus.CREATED);
	}
	
	@PutMapping("/login")
	public ResponseEntity<Object> login(@RequestBody UserLoginObject ulo){
		
		Optional<User> userSearch = userRepository.findByEmail(ulo.email);
		User u = null;
		try {
			u = userSearch.get();
			if(!(u.getPassword().equals(ulo.password))) {
				return new ResponseEntity<Object>("Falsches Passwort", HttpStatus.UNAUTHORIZED);
			}
		} catch(NoSuchElementException e) {
			return new ResponseEntity<Object>("Kein Benutzer mit dieser Email gefunden", HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Object>(u, HttpStatus.OK);
		
	}

}