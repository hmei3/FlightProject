package com.hmei.servlet;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hmei.bean.Customer;
import com.hmei.dao.PersonDao;
import com.hmei.util.DateConverter;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String dateOfBirth = request.getParameter("dateOfBirth");
		String telHome = request.getParameter("telHome");
		String gender = request.getParameter("gender");
		String streetNo = request.getParameter("streetNo");
		String streetName = request.getParameter("streetName");
		String aptNo = request.getParameter("aptNo");
		String city = request.getParameter("city");
		String state = request.getParameter("state");
		String zip = request.getParameter("zip");

		StringBuilder addrStr = new StringBuilder();

		addrStr.append(streetNo + " ");
		addrStr.append(streetName);

		if(aptNo != null && !aptNo.equals("")) {
			addrStr.append(" Apt " + aptNo);
		}
		addrStr.append(", ");
		addrStr.append(city + ", ");
		addrStr.append(state + " ");
		addrStr.append(zip);
		
		
		String msg = null;
		RequestDispatcher rd = null;
		String page = "login.jsp";
		HttpSession session = request.getSession();

		Customer customer = new Customer();
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPassword(password);
		customer.setEmail(email);
		customer.setGender(gender);
		customer.setAddress(addrStr.toString());
		customer.setTelHome(telHome);
		LocalDate birthDate = DateConverter.stringToLocalDate(dateOfBirth);
		customer.setDateOfBirth(birthDate);



		if (PersonDao.addRecord(customer)) {
			msg = "Sign up sucessfully! Please log in!";
		} else {
			msg = "Something went wrong. Sign-up fails :(";
			page = "register.jsp";
		}
		session.setAttribute("msg", msg);

		rd = request.getRequestDispatcher(page);
		rd.forward(request, response);

	}

}
