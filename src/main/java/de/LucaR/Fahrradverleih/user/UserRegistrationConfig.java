package de.LucaR.Fahrradverleih.user;

import java.sql.Date;

public class UserRegistrationConfig {

	public String name;
	public String firstName;
	public String email;
	public String passwordHash;
	public String passwordConfirmHash;
	public Date dob;

	
	public UserRegistrationConfig(String name, String firstName, String email, String passwordHash,
			String passwordConfirmHash, Date dob) {
		
		super();
		
		this.firstName = firstName;
		this.name = name;
		this.email = email;
		this.passwordHash = passwordHash;
		this.passwordConfirmHash = passwordConfirmHash;
		this.dob = dob;
	}
	
}
