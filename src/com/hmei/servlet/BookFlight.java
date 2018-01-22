package com.hmei.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hmei.bean.FlightInfo;
import com.hmei.bean.SeatRemaining;
import com.hmei.dao.FlightDao;



@WebServlet("/bookFlight")
public class BookFlight extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		String flightIndex = request.getParameter("flight_list_index");
		if(session.getAttribute("customer") == null)
		{
			session.setAttribute("flightIndex", flightIndex);
			session.setAttribute("curUrl", request.getRequestURL().toString());

			request.getRequestDispatcher("login.jsp").forward(request, response);
			return;

		}
		if(session!= null && session.getAttribute("result")!= null)
		{
			flightIndex = (flightIndex == null ? (String)session.getAttribute("flightIndex") : flightIndex);
			ArrayList<FlightInfo> flightInfos = (ArrayList<FlightInfo>)session.getAttribute("result");
			FlightInfo selectedFlight = flightInfos.get(Integer.valueOf(flightIndex));
			
			int flightId = selectedFlight.getFlightId();
			SeatRemaining seatRem = new SeatRemaining();
			seatRem = FlightDao.getSeatRem(flightId);
			session.setAttribute("seatRemInfo", seatRem);
			session.setAttribute("flight_list_index", flightIndex);
			request.getRequestDispatcher("bookFlight.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
