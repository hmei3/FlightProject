package com.hmei.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hmei.bean.Flight;
import com.hmei.bean.Manager;
import com.hmei.dao.FlightDao;


@WebServlet("/addFlight")
public class AddFlightServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		HttpSession session  = request.getSession(false);
		if(session == null || session.getAttribute("manager") == null)
		{
			session = request.getSession();
			session.setAttribute("msg", "Time out, please sign in again!");
			response.sendRedirect("login.jps");
		}
		Manager manager = (Manager)session.getAttribute("manager");
		
		String flightName = request.getParameter("fName");
		String depTime = request.getParameter("depTime");
		String depAirportCode = request.getParameter("depAirportCode");
		String arrTime = request.getParameter("arrTime");
		String arrAirportCode = request.getParameter("arrAirportCode");
		String classASeatAmt = request.getParameter("AseatAmt");
		String classBSeatAmt = request.getParameter("BseatAmt");
		String classCSeatAmt = request.getParameter("CseatAmt");
		Flight flight = new Flight();

		String result = "";
		String page = "addFlight.jsp";
		flight.setSeatAAmt((Integer.parseInt(classASeatAmt)));
		flight.setSeatBAmt((Integer.parseInt(classBSeatAmt)));
		flight.setSeatCAmt((Integer.parseInt(classCSeatAmt)));
		flight.setFlightName(flightName);
		flight.setDepTime(depTime);
		flight.setDepAirportCode(depAirportCode);
		flight.setArrTime(arrTime);
		flight.setArrAirportCode(arrAirportCode);

		if(FlightDao.addFlight(flight , manager))
		{
			page = "managerIndex.jsp";
			result = "Add Flight successfully!";
			
		}
		else
		{
			result = "Adding flight failed, please try again.";
			
		}
		session.setAttribute("result", result);
		request.getRequestDispatcher(page).forward(request, response);

	}

}
