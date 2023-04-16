package de.LucaR.Fahrradverleih.bike;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.jetbrains.annotations.NotNull;

import de.LucaR.Fahrradverleih.location.Location;

@Entity
@Table(name = "bike")
public class Bike {
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	//private Long id;
	@Column
	@NotNull
	private String name;
	@Column
	@NotNull
	private double price;
	@Column
	private boolean isAvailable = true; //standardmäßig auf true wenn die Fahrräder hochgeladen werden sind sie ausleihbar -> müssen beim Ausleihprozess auf false gesetzt werden
	@Column//(nullable=false)
	@NotNull
	private BikeType type;

	@ManyToOne(fetch=FetchType.LAZY,cascade = CascadeType.MERGE)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "id_location", referencedColumnName = "id") //hier weiß die Klasse Bike welche Location gemeint ist und welche verknüpft werden soll
	private Location location;

	public Bike() {
		
	}

	public Bike(String name, double price, boolean isAvailable, Location location, BikeType type) {
		super();
		this.name = name;
		this.price = price;
		this.isAvailable = isAvailable;
		this.location = location;
		this.type = type;
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

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public boolean isAvailable() {
		return isAvailable;
	}

	public void setAvailable(boolean isAvailable) {
		this.isAvailable = isAvailable;
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
	}
	
	public BikeType getType() {
		return type;
	}

	public void setType(BikeType type) {
		this.type = type;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
}
