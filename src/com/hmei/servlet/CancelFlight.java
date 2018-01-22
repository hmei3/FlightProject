package com.hmei.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hmei.bean.BookedFlightInfo;
import com.hmei.dao.FlightDao;


@WebServlet("/cancelFlight")
public class CancelFlight extends HttpServlet {


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("customer") == null)
		{
			session = request.getSession();
			session.setAttribute("msg", "Time out, please sign in again!");
			response.sendRedirect("login.jps");
		}
		int index = Integer.valueOf(request.getParameter("index"));
		System.out.println(index);
		List<BookedFlightInfo> list = (ArrayList<BookedFlightInfo>)session.getAttribute("bookedFlights");
		BookedFlightInfo bfi = list.get(index);
		String ticketCancelMsg = null;
		if(FlightDao.cancelTicket(bfi))
		{
			ticketCancelMsg = "Ticket is Cancelled successfully";
			session.setAttribute("bookedFlights", list);
		}
		else
		{
			ticketCancelMsg = "Cancel failed, please try again!";
		}
		session.setAttribute("ticketCancelMsg", ticketCancelMsg);
		request.getRequestDispatcher("bookingHistory.jsp").forward(request, response);;
	}

}
