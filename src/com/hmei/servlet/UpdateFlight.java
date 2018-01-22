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

/**
 * Servlet implementation class UpdateFlight
 */
@WebServlet("/updateFlight")
public class UpdateFlight extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Flight flight = new Flight();
		String result = null;
		String page = "managerIndex.jsp";
		HttpSession session = request.getSession();
		flight.setFlightId(request.getParameter("id"));
		flight.setFlightName(request.getParameter("fName"));
		flight.setDepTime(request.getParameter("depTime"));
		flight.setDepAirportCode(request.getParameter("depAirportCode"));
		flight.setArrTime(request.getParameter("arrTime"));
		flight.setArrAirportCode(request.getParameter("arrAirportCode"));
//		flight.setSeatAAmt(Integer.parseInt(request.getParameter("AseatAmt")));
//		flight.setSeatBAmt(Integer.parseInt(request.getParameter("BseatAmt")));
//		flight.setSeatCAmt(Integer.parseInt(request.getParameter("CseatAmt")));
		
		if(FlightDao.updateFlight(flight))
		{
			result = "Flight is updated successfully!";
			
		}
		else
		{
			result = "Update failed, please try again!";
			page = "updateFlight.jsp";
		}
		session.setAttribute("result", result);
		request.getRequestDispatcher(page).forward(request, response);
		
		
	}

}
