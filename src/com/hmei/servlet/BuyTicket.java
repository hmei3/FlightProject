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

import com.hmei.bean.Customer;
import com.hmei.bean.FullInfo;
import com.hmei.bean.ThreadBuy;


@WebServlet("/buyTicket")
public class BuyTicket extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int ticketNoA = request.getParameter("ticketNoA").equals("") ? 0 : Integer.parseInt(request.getParameter("ticketNoA"));
		int ticketNoB = request.getParameter("ticketNoB").equals("") ? 0 : Integer.parseInt(request.getParameter("ticketNoB"));
		int ticketNoC = request.getParameter("ticketNoC").equals("") ? 0 : Integer.parseInt(request.getParameter("ticketNoC"));
	
		
		HttpSession session = request.getSession(false);
		if(session == null || session.getAttribute("customer") == null)
		{
			session = request.getSession();
			session.setAttribute("msg", "Time out, please sign in again!");
			response.sendRedirect("login.jps");
		}
		Customer customer = (Customer)session.getAttribute("customer");
		int custId = Integer.valueOf(customer.getCustomerId());
		
		
		String[] ticketApassengers = new String[ticketNoA];
		String[] ticketBpassengers = new String[ticketNoB];
		String[] ticketCpassengers = new String[ticketNoC];
		System.out.println(ticketBpassengers.length);
		
		
		int index = 0;
		while(index < ticketNoA)
		{
			ticketApassengers[index] = request.getParameter("classAname" + index++);
		}
		index = 0;
		while(index < ticketNoB)
		{

			ticketBpassengers[index] = request.getParameter("classBname" + index++);
		}
		index = 0;
		while(index < ticketNoC)
		{
			ticketCpassengers[index] = request.getParameter("classCname" + index++);
		}
		int flightId = Integer.valueOf(request.getParameter("flightId"));

		String bookMsg = null;
		ThreadBuy buy = new ThreadBuy(flightId, custId, ticketApassengers, 
				ticketBpassengers, ticketCpassengers);
		buy.start();
		
//		if(result.booleanValue() == true)
//		{
//			page = "orderInfo.jsp";
		request.setAttribute("ticketApassengers", ticketApassengers);
		request.setAttribute("ticketBpassengers", ticketBpassengers);
		request.setAttribute("ticketCpassengers", ticketCpassengers);
//		}
//		else
//		{
//			page="orderInfo.jsp";
//			session.setAttribute("orderError", "Placing order failed, please try again!");
//		}
		
		request.getRequestDispatcher("orderInfo.jsp").forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request,response);
	}

}
