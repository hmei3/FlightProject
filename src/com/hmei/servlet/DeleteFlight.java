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


@WebServlet("/deleteFlight")
public class DeleteFlight extends HttpServlet {

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String result = null;
		String page = "managerIndex.jsp";
		HttpSession session = request.getSession();
		int id = Integer.valueOf(request.getParameter("id"));
		
		if(FlightDao.deleteFlight(id))
		{
			result = "Flight is delete successfully!";
			
		}
		else
		{
			result = "deletion failed, please try again!";
			page = "deleteFlight.jsp";
		}
		session.setAttribute("result", result);
		request.getRequestDispatcher(page).forward(request, response);
	}

}
