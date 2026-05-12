package com.example.demo.dTO;

public class BookingCustomerIdReturn {
	
	private int BookingId;
	private String flightname;
	private CustomerDto customerDto;
	
	public int getBookingId() {
		return BookingId;
	}
	public void setBookingId(int bookingId) {
		BookingId = bookingId;
	}
	public String getFlightname() {
		return flightname;
	}
	public void setFlightname(String flightname) {
		this.flightname = flightname;
	}
	public CustomerDto getCustomerDto() {
		return customerDto;
	}
	public void setCustomerDto(CustomerDto customerDto) {
		this.customerDto = customerDto;
	}
	public void setMessage(String string) {
		// TODO Auto-generated method stub
		
	}
}
