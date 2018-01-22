package com.hmei.bean;

import java.util.List;

import com.hmei.dao.FlightDao;

public class ThreadBuy extends Thread {
	private int flightId;
	private String[] ticketApassengers;
	private String[] ticketBpassengers;
	private String[] ticketCpassengers;
	private int custId;

	public ThreadBuy(int flightId, int custId, String[] ticketApassengers, 
			String[] ticketBpassengers, String[] ticketCpassengers) {
		this.flightId = flightId;
		this.ticketApassengers = ticketApassengers;
		this.ticketBpassengers = ticketBpassengers;
		this.ticketCpassengers = ticketCpassengers;
		this.custId = custId;
	}

	@Override
	public void run() {
		FlightDao.buyTickets(flightId, custId, ticketApassengers, ticketBpassengers, ticketCpassengers);

	}
}
