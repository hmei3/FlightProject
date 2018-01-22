package com.hmei.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hmei.bean.Flight;
import com.hmei.bean.FullInfo;
import com.hmei.dao.FlightDao;


@WebServlet("/checkBooking")
public class CheckBooking extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("manager") == null)
		{
			session = request.getSession();
			session.setAttribute("msg", "Time out, please sign in again!");
			response.sendRedirect("login.jps");
		}
		String flightName = request.getParameter("fName");
		String fDepAirportCode = request.getParameter("depAirportCode");
		String fDepTime = request.getParameter("depTime");

		
		Flight flight = new Flight();
		flight.setFlightName(flightName);
		flight.setDepAirportCode(fDepAirportCode);
		flight.setDepTime(fDepTime);
		
		if(FlightDao.searchFlight(flight))
		{

			List<FullInfo> adminBookingView = FlightDao.getBookingList(flight);
			request.setAttribute("adminBookingView", adminBookingView);
			request.setAttribute("seatRemaining", FlightDao.getSeatRem(Integer.valueOf(flight.getFlightId())));

		}
		else
		{
			System.out.println(2);
			request.setAttribute("adminBookingView", "It seems that no such flight exists");
		}

		request.getRequestDispatcher("bookingInfo.jsp").forward(request, response);
		
	}

}
