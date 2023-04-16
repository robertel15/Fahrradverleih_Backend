package de.LucaR.Fahrradverleih.booking;

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
import org.springframework.web.bind.annotation.RestController;

import de.LucaR.Fahrradverleih.bike.Bike;
import de.LucaR.Fahrradverleih.location.Location;
import de.LucaR.Fahrradverleih.user.User;

@RestController
@RequestMapping("api/v1/booking")
public class BookingController {

	private final BookingService bookingService;
	
	@Autowired
	public BookingController(BookingService bookingService) {
		this.bookingService = bookingService;
	}
	
	@GetMapping("")
	public List<Booking> getBookings() {
		return bookingService.getBookings();
	}
	
	@GetMapping("/return/{id}/{returnLocationId}")
	public Booking returnBike(@PathVariable UUID id, @PathVariable UUID returnLocationId) {
		
		return bookingService.getBookingsReturned(id, returnLocationId);
	}
	
	/*@PostMapping("")
	public void registerNewBooking(@RequestBody Booking booking) {
		bookingService.addNewBooking(booking);
	}*/
	
	@PostMapping("")
	public Booking addBooking(@RequestBody BookingConfig booking){
		return bookingService.addNewBooking(booking);
	}
	
	@DeleteMapping("{bookingId}")
	public void deleteBooking(@PathVariable("bookingId") UUID bookingId) {
		bookingService.deleteBooking(bookingId);
	}
	
	@PutMapping("")
	public void updateBooking(@RequestBody Booking booking) {
		bookingService.updateBooking(booking.getId(), booking.getBookingDate(), 
				booking.getReturnDate(), booking.isBookingDone(), booking.getUser(), booking.getBike());
	}
}
