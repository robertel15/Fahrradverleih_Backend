package de.LucaR.Fahrradverleih.booking;

import java.sql.Date;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.LucaR.Fahrradverleih.bike.Bike;
import de.LucaR.Fahrradverleih.bike.BikeRepository;
import de.LucaR.Fahrradverleih.location.Location;
import de.LucaR.Fahrradverleih.location.LocationRepository;
import de.LucaR.Fahrradverleih.user.User;
import de.LucaR.Fahrradverleih.user.UserRepository;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	private final UserRepository userRepository;
	private final BikeRepository bikeRepository;
	private final LocationRepository locationRepository;
	
	@Autowired
	public BookingService(BookingRepository bookingRepository, UserRepository userRepository, BikeRepository bikeRepository, LocationRepository locationRepository) {
		this.bookingRepository = bookingRepository;
		this.userRepository = userRepository;
		this.bikeRepository = bikeRepository;
		this.locationRepository = locationRepository;
	}
	
	public List<Booking> getBookings() {
		return bookingRepository.findAll();
	}

	/*public void addNewBooking(Booking booking) {
		// TODO Auto-generated method stub
		bookingRepository.save(booking);
	}*/
	
	public Booking addNewBooking(BookingConfig booking) {
		
		User user = userRepository.findById(booking.userID).get();
		Bike bike = bikeRepository.findById(booking.bikeID).get();
		Location start = locationRepository.findById(booking.locationID_start).get();
				
				bike.setLocation(null);
				bikeRepository.save(bike);
				Booking toAdd = new Booking();
				
				toAdd.setReturnDate(booking.returnDate);
				toAdd.setBookingDate(booking.bookingDate);
				toAdd.setBookingDone(booking.bookingDone);
				toAdd.setUser(user);
				toAdd.setBike(bike);
				toAdd.setLocation_start(start);
				
				return bookingRepository.save(toAdd);
				
	}

	public void deleteBooking(UUID bookingId) {
		// TODO Auto-generated method stub
		bookingRepository.deleteById(bookingId);
	}

	@Transactional
	public void updateBooking(UUID id, Date bookingDate, Date returnDate, boolean bookingDone, User user, Bike bike) {
		// TODO Auto-generated method stub
		Booking booking = bookingRepository.findById(id).orElseThrow(()
				-> new IllegalStateException("Booking mit der ID "+ id + " existiert nicht!"));
		
		booking.setId(id);
		booking.setBookingDate(bookingDate);
		booking.setReturnDate(returnDate);
		booking.setBookingDone(bookingDone);
		booking.setUser(user);
		booking.setBike(bike);
	}

	public Booking getBookingsReturned(UUID id, UUID returnLocationId) {
		
		try {
			Booking booking = bookingRepository.findById(id).get();
			Bike bike = booking.getBike();
			Location location = locationRepository.findById(returnLocationId).get();
			
			bike.setLocation(location);
			bike.setAvailable(true);
			bikeRepository.save(bike);
			booking.setLocation_end(location);
			booking.setBookingDone(true);
			
			return bookingRepository.save(booking);
		}
		catch(NoSuchElementException e) {
			throw new IllegalStateException("Fehler bei der Rückgabe, bitte überprüfe die Eingabe der Parameter sorgfältig.");
		}
	}

	
}
