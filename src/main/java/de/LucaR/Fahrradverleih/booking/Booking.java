package de.LucaR.Fahrradverleih.booking;

import java.sql.Date;
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

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.jetbrains.annotations.NotNull;

import de.LucaR.Fahrradverleih.bike.Bike;
import de.LucaR.Fahrradverleih.location.Location;
import de.LucaR.Fahrradverleih.user.User;

@Entity
@Table(name = "booking")
public class Booking {
	/*@Id
	@Column
	private Long id;*/
	@Id
	@Column(columnDefinition= "VARBINARY(16)")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private UUID id;
	@Column
	@NotNull
	private Date bookingDate;
	@Column
	@NotNull
	private Date returnDate;
	@Column
	private boolean bookingDone;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.MERGE) //This happens because you have a collection in your entity, and that collection has one or more items which are not present in the database. By specifying the above options you tell hibernate to save them to the database when saving their parent.
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "id_user", referencedColumnName = "id")
	private User user;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "id_bike", referencedColumnName = "id")
	@NotNull
	private Bike bike;
	
	@ManyToOne(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "id_location_start", referencedColumnName = "id")
	@NotNull
	private Location location_start;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	@JoinColumn(name = "id_location_end", referencedColumnName = "id")
	private Location location_end;
	
	public Booking() {
		
	}

	public Booking(@NotNull Date bookingDate, @NotNull Date returnDate, boolean bookingDone, User user, Bike bike, Location location_start, Location location_end) {
		super();
		this.bookingDate = bookingDate;
		this.returnDate = returnDate;
		this.bookingDone = bookingDone;
		this.user = user;
		this.bike = bike;
		this.location_start = location_start;
		this.location_end = location_end;
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
	
	public Date getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(Date bookingDate) {
		this.bookingDate = bookingDate;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}

	public boolean isBookingDone() {
		return bookingDone;
	}

	public void setBookingDone(boolean bookingDone) {
		this.bookingDone = bookingDone;
	}

	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public Bike getBike() {
		return bike;
	}

	public void setBike(Bike bike) {
		this.bike = bike;
	}

	public Location getLocation_start() {
		return location_start;
	}

	public void setLocation_start(Location location_start) {
		this.location_start = location_start;
	}

	public Location getLocation_end() {
		return location_end;
	}

	public void setLocation_end(Location location_end) {
		this.location_end = location_end;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return super.toString();
	}
	
	
	
	
}
