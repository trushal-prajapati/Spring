package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.dTO.BookingCustomerIdReturn;
import com.example.demo.dTO.CustomerDto;
import com.example.demo.model.Booking;
import com.example.demo.repo.BookingRepo;

@Service
public class bookingService {

	@Autowired
	private BookingRepo bookingRepo;
	
	@Autowired
	private WebClient webclient;
	
	// Fetch a single customer by id (expects the customer service to return a JSON object)
	public CustomerDto SingleCust(int id) {
		CustomerDto custDto = webclient.get()
									.uri("http://localhost:8089/customer/single/" + id)
									.retrieve()
									.bodyToMono(CustomerDto.class)
									.block();
		if (custDto == null) {
			throw new RuntimeException("Customer not found with id: " + id);
		}
		return custDto;
	}

	// Fetch all customers (customer service returns a JSON array -> map to List<CustomerDto>)
	public List<CustomerDto> allCust() {
		List<CustomerDto> custList = webclient.get()
									.uri("http://localhost:8089/customer/all")
									.retrieve()
									.bodyToFlux(CustomerDto.class)
									.collectList()
									.block();
		return custList;
	}
	
	public Booking InsertWithCust(Booking booking) {

	    if (booking == null) {
	        throw new RuntimeException("Booking data is null");
	    }

	    int customerId = booking.getCustomer_id();

	    boolean exists = customerExists(customerId);

	    if (!exists) {
	        throw new RuntimeException(
	                "Customer ID " + customerId + " does not exist. Cannot book flight.");
	    }

	    return bookingRepo.save(booking);
	}
	public Booking updateBooking(int id, Booking updatedBooking) {

	    Booking existingBooking = bookingRepo.findById(id)
	            .orElseThrow(() -> new RuntimeException("Booking not found with id " + id));
	    
	    existingBooking.setFlightname(updatedBooking.getFlightname());
	    existingBooking.setCustomer_id(updatedBooking.getCustomer_id());

	    return bookingRepo.save(existingBooking);
	}
	public String Delete(int BookingId) {
		bookingRepo.deleteById(BookingId);
		return "Record Gayo!";
	}
	
	public Booking Single(int BookingId) {
			return bookingRepo.findById(BookingId).orElse(null);
	}
	public List<Booking> All() {
		List<Booking> list = new ArrayList<>();
		
		bookingRepo.findAll().forEach(list :: add);
		
		return list;
	}
	
	public BookingCustomerIdReturn custwithooking(int id) {
		Booking b = bookingRepo.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
		
		CustomerDto custDto = webclient.get()
										.uri("http://localhost:8089/customer/single/" + b.getCustomer_id())
										.retrieve()
										.bodyToMono(CustomerDto.class)
										.block();
		
		BookingCustomerIdReturn custReturn = new BookingCustomerIdReturn();
		
		custReturn.setBookingId(b.getBooking_id());
		custReturn.setFlightname(b.getFlightname());
		custReturn.setCustomerDto(custDto);
		custReturn.setMessage( "Booking and Customer details retrieved successfully" );
		
		return custReturn;
	}
    
	/**
	 * Check whether a customer exists by calling the customer service.
	 * Returns true if the customer service returns a non-null CustomerDto, false otherwise.
	 */
	public boolean customerExists(int id) {
		try {
			CustomerDto custDto = webclient.get()
										.uri("http://localhost:8089/customer/single/" + id)
										.retrieve()
										.bodyToMono(CustomerDto.class)
										.block();
			return custDto != null;
		} catch (Exception ex) {
			// If the customer service is down or returns an error (e.g., 404), treat as not found
			return false;
		}
	}
    
	// Return all bookings along with their customer details by calling the customer service
	public List<BookingCustomerIdReturn> AllWithCustomers() {
		
		List<Booking> bookings = All();
		List<BookingCustomerIdReturn> results = new ArrayList<>();

		for (Booking b : bookings) {
			CustomerDto custDto = null;
			try {
					custDto = webclient.get()
									.uri("http://localhost:8089/customer/single/" + b.getCustomer_id())
									.retrieve()
									.bodyToMono(CustomerDto.class)
									.block();
			} catch (Exception ex) {
				// If customer service is unavailable or returns an error, leave custDto as null
			}

			BookingCustomerIdReturn custReturn = new BookingCustomerIdReturn();
			custReturn.setBookingId(b.getBooking_id());
			custReturn.setFlightname(b.getFlightname());
			custReturn.setCustomerDto(custDto);
			custReturn.setMessage(custDto != null ? "Booking and Customer details retrieved successfully" : "Booking retrieved; customer details unavailable");

			results.add(custReturn);
		}

		return results;
	}
	
}
