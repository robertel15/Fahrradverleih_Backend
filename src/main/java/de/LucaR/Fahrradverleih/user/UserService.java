package de.LucaR.Fahrradverleih.user;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	private final UserRepository userRepository;

	@Autowired
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public List<User> getUsers() {
		return userRepository.findAll();
	}

	public void addNewUser(User user) {
		// TODO Auto-generated method stub
		Optional<User> userOptional = userRepository.findUserByEmail(user.getEmail());
		if(userOptional.isPresent()) {
			throw new IllegalStateException("email vergeben");
		}
		
		userRepository.save(user);
	}
	//
	public User registerNewUser(UserRegistrationConfig userConfig){
		
		boolean alreadyExists = false;
		
		if(!(userConfig.passwordHash.equals(userConfig.passwordConfirmHash))) {
			throw new IllegalStateException("Incorrect password!");
		}
	
		try {
			@SuppressWarnings("unused")
			User user = userRepository.findUserByEmail(userConfig.email).get();
			alreadyExists = true;
		}catch(Exception e) {};
		
		if(alreadyExists) {
			throw new IllegalStateException("Diese Email ist bereits vergeben");
		}
		
		User toAdd = new User();
		
		
		toAdd.setEmail(userConfig.email);
		toAdd.setFirstName(userConfig.firstName);
		toAdd.setName(userConfig.name);
		toAdd.setPassword(userConfig.passwordHash);
		toAdd.setDob(userConfig.dob);
	
		return userRepository.save(toAdd);
	}
	
	public User loginUser(UserLoginConfig userConfig) {
		
		Optional<User> userSearch = userRepository.findUserByEmail(userConfig.email);
		
		User u = null;
		try {
			u = userSearch.get();
			if(!(u.getPassword().equals(userConfig.password))) {
				throw new IllegalStateException("Falsches Passwort");
			}
		} catch(NoSuchElementException e) {
			throw new IllegalStateException("Kein Benutzer mit dieser Email gefunden");
		}
		
		return u;
	}
	
	//
	public void deleteUser(UUID userId) {
		// TODO Auto-generated method stub
		boolean exists = userRepository.existsById(userId);
		if(!exists) {
			throw new IllegalStateException("User mit der Id: " +userId +" existiert nicht");
		}
		
		userRepository.deleteById(userId);
	}
	
	@Transactional
	public void updateUser(UUID userId, String name, String firstName, String email, String password, String pictureLink) {
		User user = userRepository.findById(userId).orElseThrow(() 
				-> new IllegalStateException("User mit der Id "+ userId + " existiert nicht"));
		
		if (name != null && name.length() > 0 && !Objects.equals(user.getEmail(), email)) {
			user.setName(name);
		}
		
		if (firstName != null && firstName.length() > 0 && !Objects.equals(user.getEmail(), email)) {
			user.setFirstName(firstName);
		}
		
		if(email != null && email.length() > 0 && !Objects.equals(user.getEmail(), email)) {
			Optional<User> userOptional = userRepository.findUserByEmail(email);
			if(userOptional.isPresent()) {
				throw new IllegalStateException("email vergeben");
			}
			
			user.setEmail(email);
		}
			
			user.setPictureLink(pictureLink);
	}
}