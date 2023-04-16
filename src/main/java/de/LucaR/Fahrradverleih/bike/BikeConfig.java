package de.LucaR.Fahrradverleih.bike;

import java.util.UUID;

public class BikeConfig {

	public String name;
	public double price;
	public boolean isAvailable;
	public BikeType type;
	public String pictureLink;
	public String bikeDescription;
	public UUID locationID;
	
	public BikeConfig(String name, double price, boolean isAvailable, BikeType type, String pictureLink,
			String bikeDescription, UUID locationID) {
		super();
		this.name = name;
		this.price = price;
		this.isAvailable = isAvailable;
		this.type = type;
		this.pictureLink = pictureLink;
		this.bikeDescription = bikeDescription;
		this.locationID = locationID;
	}
	
	
}
