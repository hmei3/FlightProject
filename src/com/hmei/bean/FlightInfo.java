package com.hmei.bean;

import java.io.Serializable;
import java.sql.Timestamp;

public class FlightInfo implements Serializable{
	private int FlightId;
	private String FlightName;
	private String DepAirport;
	private String DepAirportCode;
	
	
	private Timestamp DepTime;
	private String DepCity;
	private String DepState;
	private String DepCountry;
	
	private String ArrAirport;
	private String ArrAirportCode;
	
	
	private Timestamp ArrTime;
	private String ArrCity;
	private String ArrState;
	private String ArrCountry;
	public int getFlightId() {
		return FlightId;
	}
	public void setFlightId(int flightId) {
		FlightId = flightId;
	}
	public String getFlightName() {
		return FlightName;
	}
	public void setFlightName(String flightName) {
		FlightName = flightName;
	}
	public String getDepAirport() {
		return DepAirport;
	}
	public void setDepAirport(String depAirport) {
		DepAirport = depAirport;
	}
	public String getDepAirportCode() {
		return DepAirportCode;
	}
	public void setDepAirportCode(String depAirportCode) {
		DepAirportCode = depAirportCode;
	}
	public Timestamp getDepTime() {
		return DepTime;
	}
	public void setDepTime(Timestamp depTime) {
		DepTime = depTime;
	}
	public String getDepCity() {
		return DepCity;
	}
	public void setDepCity(String depCity) {
		DepCity = depCity;
	}
	public String getDepState() {
		return DepState;
	}
	public void setDepState(String depState) {
		DepState = depState;
	}
	public String getDepCountry() {
		return DepCountry;
	}
	public void setDepCountry(String DepCountry) {
		this.DepCountry = DepCountry;
	}
	public String getArrAirport() {
		return ArrAirport;
	}
	public void setArrAirport(String arrAirport) {
		ArrAirport = arrAirport;
	}
	public String getArrAirportCode() {
		return ArrAirportCode;
	}
	public void setArrAirportCode(String arrAirportCode) {
		ArrAirportCode = arrAirportCode;
	}
	public Timestamp getArrTime() {
		return ArrTime;
	}
	public void setArrTime(Timestamp arrTime) {
		ArrTime = arrTime;
	}
	public String getArrCity() {
		return ArrCity;
	}
	public void setArrCity(String arrCity) {
		ArrCity = arrCity;
	}
	public String getArrState() {
		return ArrState;
	}
	public void setArrState(String arrState) {
		ArrState = arrState;
	}
	public String getArrCountry() {
		return ArrCountry;
	}
	public void setArrCountry(String ArrCountry) {
		this.ArrCountry = ArrCountry;
	}
	
}
