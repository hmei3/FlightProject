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

import com.hmei.bean.FlightInfo;
import com.hmei.dao.FlightDao;


@WebServlet("/searchflights")
public class SearchFlightsServlet extends HttpServlet {
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String from = request.getParameter("from");
		String to = request.getParameter("to");
		String depDate = request.getParameter("depdate");
		List<FlightInfo> result = new ArrayList<>();
		
		result = FlightDao.searchFlight(from, to, depDate);
		if(session.getAttribute("result")!= null)
			session.removeAttribute("result");
		if(result.size() == 0)
		{
			session.setAttribute("result","There is no such flight existing under this condition.");
		}
		else
		{
			session.setAttribute("result", result);
		}
		
		request.getRequestDispatcher("searchFlight.jsp").forward(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
