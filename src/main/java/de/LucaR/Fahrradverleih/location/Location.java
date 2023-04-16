package de.LucaR.Fahrradverleih.location;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.jetbrains.annotations.NotNull;

@Entity
@Table(name = "location")
public class Location {
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	/*@Id
	@Column
	private Long id;*/
	@Column
	@NotNull
	private String name;
	@Column
	@NotNull
	private String street;
	@Column
	@NotNull
	private String city;
	@Column
	@NotNull
	private int postalCode;
	@Column
	private double longitude; //wäre cool wenn die aus google maps iwie extrahiert werden könnten
	@Column
	private double latitude;
	@Column
	private String pictureLink;
	
	public Location() {
		
	}

	public Location(String name, String city, String street, int postalCode, double longitude, double latitude, String pictureLink) {
		super();
		this.name = name;
		this.city = city;
		this.street = street;
		this.postalCode = postalCode;
		this.longitude = longitude;
		this.latitude = latitude;
		this.pictureLink = pictureLink;
	}

	/*public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}*/
	
	public UUID getId() {
		return id;
	}
	
	public void setId(UUID id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}
	
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public int getPostalCode() {
		return postalCode;
	}

	public void setPostalCode(int postalCode) {
		this.postalCode = postalCode;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public String getPictureLink() {
		return pictureLink;
	}

	public void setPictureLink(String pictureLink) {
		this.pictureLink = pictureLink;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
}
