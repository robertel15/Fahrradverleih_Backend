package de.LucaR.Fahrradverleih.dao;

public class UserRegistrationsObject {
	
		
	
		public String firstName;
		public String name;
		public String email;
		public String passwordHash;
		public String passwordConfirmHash;
		public String street;
		public String number;
		public int plz;
		public String city;
		public byte[] Signatur;
		
		public UserRegistrationsObject(String firstName, String name, String email, String passwordHash,
				String passwordConfirmHash, String street, String number, int plz, String city,byte[] Signatur) {
			super();
			
			this.firstName = firstName;
			this.name = name;
			this.email = email;
			this.passwordHash = passwordHash;
			this.passwordConfirmHash = passwordConfirmHash;
			this.street = street;
			this.number = number;
			this.plz = plz;
			this.city = city;
			this.Signatur = Signatur;
			
		}
		
	}
