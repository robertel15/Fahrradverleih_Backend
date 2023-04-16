package de.LucaR.Fahrradverleih.booking;

import java.sql.Date;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.LucaR.Fahrradverleih.bike.Bike;
import de.LucaR.Fahrradverleih.user.User;

@Service
public class BookingService {

	private final BookingRepository bookingRepository;
	
	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	public List<Booking> getBookings() {
		return bookingRepository.findAll();
	}

	public void addNewBooking(Booking booking) {
		// TODO Auto-generated method stub
		bookingRepository.save(booking);
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

	
}
