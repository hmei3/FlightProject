package com.hmei.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hmei.bean.Flight;
import com.hmei.dao.FlightDao;


@WebServlet("/admin_search_flight")
public class AdminSearchFlight extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
			session.setAttribute("result", flight);
		}
		else
		{
			String result = "No such flight existing in DataBase, please make sure you entered the correct information.";
			session.setAttribute("result",result);
		}
		if(request.getParameter("action").equals("update")) {
			request.getRequestDispatcher("updateFlight.jsp").forward(request, response);
		}
		else if(request.getParameter("action").equals("delete"))
		{
			request.getRequestDispatcher("deleteFlight.jsp").forward(request, response);
		}
		else if(request.getParameter("action").equals("checkBooking"))
		{
			request.getRequestDispatcher("bookingInfo.jsp").forward(request, response);
		}
	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request, response);
	}

}
