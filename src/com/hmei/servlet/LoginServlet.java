package com.hmei.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hmei.bean.Customer;
import com.hmei.bean.Manager;
import com.hmei.bean.Person;
import com.hmei.dao.PersonDao;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();

		String userType = request.getParameter("userType");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String msg = null;
		
		String page = "login.jsp";
		RequestDispatcher rd = null;
		Person person = PersonDao.isValid(userType, userName, password);

		if(person != null)
		{
			if(person.getClass() == Customer.class)
			{
				page = "index.jsp";
				session.setAttribute("customer", person);
				if(session.getAttribute("curUrl")!= null)
				{
					String curUrl = (String)session.getAttribute("curUrl");
					response.sendRedirect(curUrl);
					session.removeAttribute("curUrl");
					return;
				}
			}
			else if(person.getClass() == Manager.class) {
				page = "managerIndex.jsp";
				session.setAttribute("manager", person);
			}
		}
		else
		{
			msg = "Invalid UserName or Password, please try again!";
			session.setAttribute("msg", msg);
		}
		
		rd = request.getRequestDispatcher(page);
		rd.forward(request, response);
		
	}

}
