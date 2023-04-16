package de.LucaR.Fahrradverleih.user;

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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




@RestController
@RequestMapping("api/v1/user")
public class UserController {

	private final UserService userService;
	
	@Autowired
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("")
	public List<User> getUsers() {
		return userService.getUsers();
	}
	
	/*@PostMapping("")
	public void registerNewUser(@RequestBody User user) {
		userService.addNewUser(user);
	}*/
	
	@PostMapping("")
	public User register(@RequestBody UserRegistrationConfig user) {
		return userService.registerNewUser(user);
	}
	
	@PostMapping("/login") 
	public User login(@RequestBody UserLoginConfig user) {
		return userService.loginUser(user);
	}
	
	@DeleteMapping("{userId}")
	public void deleteUser(@PathVariable("userId") UUID userId) {
		userService.deleteUser(userId);
	}
	
	@PutMapping("")
	public void updateUser(
			/*@PathVariable("userId")Long userId, 
			@RequestParam(required = false)String name, 
			@RequestParam(required = false)String firstName, 
			@RequestParam(required = false)String email*/
			@RequestBody User user) {
		userService.updateUser(user.getId(), user.getName(), user.getFirstName(), user.getEmail(), user.getPassword(), user.getPictureLink());
	}
}
