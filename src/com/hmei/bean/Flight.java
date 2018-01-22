package com.hmei.bean;

public class Flight {
	private String flightId;
	private int SeatAAmt;
	private int SeatBAmt;
	private int SeatCAmt;
	private String flightName;
	private String DepTime;
	private String DepAirportCode;
	private String ArrTime;
	private String ArrAirportCode;
	public int getSeatAAmt() {
		return SeatAAmt;
	}
	public String getFlightId() {
		return flightId;
	}
	public void setFlightId(String flightId) {
		this.flightId = flightId;
	}
	public void setSeatAAmt(int seatAAmt) {
		SeatAAmt = seatAAmt;
	}
	public int getSeatBAmt() {
		return SeatBAmt;
	}
	public void setSeatBAmt(int seatBAmt) {
		SeatBAmt = seatBAmt;
	}
	public int getSeatCAmt() {
		return SeatCAmt;
	}
	public void setSeatCAmt(int seatCAmt) {
		SeatCAmt = seatCAmt;
	}
	public String getFlightName() {
		return flightName;
	}
	public void setFlightName(String flightName) {
		this.flightName = flightName;
	}
	public String getDepTime() {
		return DepTime;
	}
	public void setDepTime(String depTime) {
		DepTime = depTime.replace('T', ' ');
	}
	public String getDepAirportCode() {
		return DepAirportCode;
	}
	public void setDepAirportCode(String depAirportCode) {
		DepAirportCode = depAirportCode;
	}
	public String getArrTime() {
		return ArrTime;
	}
	public void setArrTime(String arrTime) {
		ArrTime = arrTime.replace('T', ' ');
	}
	public String getArrAirportCode() {
		return ArrAirportCode;
	}
	public void setArrAirportCode(String arrAirportCode) {
		ArrAirportCode = arrAirportCode;
	}
	
	
}
