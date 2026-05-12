package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dTO.BookingCustomerIdReturn;
import com.example.demo.dTO.CustomerDto;
import com.example.demo.model.Booking;
import com.example.demo.service.bookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin(origins = "http://localhost:5173")
public class bookingController {
	
	@Autowired
	private bookingService bookingservice;
	
	@PostMapping("/insertWithCustId")
	public Booking InsertWithCust(@RequestBody Booking b) {
		return bookingservice.InsertWithCust(b);
	}
	@PutMapping("/update/{id}")
	public Booking updateBooking(@PathVariable int id, @RequestBody Booking booking) {
			return bookingservice.updateBooking(id, booking);
	}
	@DeleteMapping("/delete/{BookingId}")
	public String Delete(@PathVariable int BookingId) {
		return bookingservice.Delete(BookingId);
	}
	@GetMapping("/single/{BookingId}")
	public Booking Single(@PathVariable int BookingId) {
			return bookingservice.Single(BookingId);
	}
	
	@GetMapping("/custWithBooking/{id}")
	public BookingCustomerIdReturn custwithbook(@PathVariable int id) {
		return bookingservice.custwithooking(id);
	}
	
	@GetMapping("/allBooking")
	public List<Booking> All() {
		return bookingservice.All();
	}
	
	@GetMapping("/Allcustwithbooking")
	public List<BookingCustomerIdReturn> Allcustwithbooking() {
		return bookingservice.AllWithCustomers();
	}
	
	@GetMapping("/Cust/{id}")
	public CustomerDto SingleCust(@PathVariable int id) {
		return bookingservice.SingleCust(id);
	}
	
	@GetMapping("/allCust")
	public List<CustomerDto> allCust() {
		return bookingservice.allCust();
	}

}
