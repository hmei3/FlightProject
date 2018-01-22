package com.hmei.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hmei.bean.BookedFlightInfo;
import com.hmei.bean.Customer;
import com.hmei.dao.FlightDao;


@WebServlet("/CustBookingHistory")
public class CustBookingHistory extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String custId = request.getParameter("custid");
		List<BookedFlightInfo> bookedFlights = FlightDao.getBookedFlight(custId);
		
		if(bookedFlights == null)
		{
			session.setAttribute("bookedFlights", "Something went wrong, please try again!");
		}
		else if(bookedFlights.size() == 0)
		{
			session.setAttribute("bookedFlights", "There is no booked flights under this account!");
		}
		else
		{
			session.setAttribute("bookedFlights", bookedFlights);
		}
		
		request.getRequestDispatcher("bookingHistory.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
