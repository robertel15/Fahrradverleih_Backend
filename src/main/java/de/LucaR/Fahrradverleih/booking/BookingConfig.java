package de.LucaR.Fahrradverleih.booking;

import java.sql.Date;
import java.util.UUID;

public class BookingConfig {

	public Date bookingDate;
	public Date returnDate;
	public UUID userID; 
	public UUID bikeID; 
	public UUID locationID_start;
	public UUID locationID_end;
	public boolean bookingDone;
	
	public BookingConfig(Date bookingDate, Date returnDate, UUID userId, UUID bikeId, UUID locationId_start,
			UUID locationId_end, boolean bookingDone) {
		super();
		this.bookingDate = bookingDate;
		this.returnDate = returnDate;
		this.userID = userId;
		this.bikeID = bikeId;
		this.locationID_start = locationId_start;
		this.locationID_end = locationId_end;
		this.bookingDone = bookingDone;
	}
	
	
}
