package com.hmei.bean;

public class FullInfo {
	private Booking booking;
	private Ticket ticket;
	private Customer customer;
	private Flight flight;
	
	
	public FullInfo() {
		booking = new Booking();
		ticket = new Ticket();
		customer = new Customer();
		flight = new Flight();
		
	}
	public Booking getBooking() {
		return booking;
	}
	public void setBooking(Booking booking) {
		this.booking = booking;
	}
	public Ticket getTicket() {
		return ticket;
	}
	public void setTicket(Ticket ticket) {
		this.ticket = ticket;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	public Flight getFlight() {
		return flight;
	}
	public void setFlight(Flight flight) {
		this.flight = flight;
	}
	
	
}
