package com.hmei.servlet;

import java.io.IOException;
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


@WebServlet("/update_profile")
public class UpdateProfileServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(false);
		if(session == null)
		{	
			session = request.getSession();
			session.setAttribute("msg", "Time out, please sign in again!");
			response.sendRedirect("login.jsp");
		}
		String customerId = ((Customer)session.getAttribute("customer")).getCustomerId();
		String customerEmail = ((Customer)session.getAttribute("customer")).getEmail();
		
		String firstName = request.getParameter("firstNameInput");
		String lastName = request.getParameter("lastNameInput");
		String password = request.getParameter("passwordInput");
		String dateOfBirth = request.getParameter("dateOfBirthInput");
		String telHome = request.getParameter("telHomeInput");
		String gender = request.getParameter("gender");
		String streetNo = request.getParameter("streetNoInput");
		String streetName = request.getParameter("streetNameInput");
		String aptNo = request.getParameter("aptNoInput");
		String city = request.getParameter("cityInput");
		String state = request.getParameter("stateInput");
		String zip = request.getParameter("zipInput");
		StringBuilder addrStr = new StringBuilder();

		addrStr.append(streetNo + " ");
		addrStr.append(streetName);
		if(aptNo != null && !(aptNo.equals(""))) {
			addrStr.append(" Apt " + aptNo);
		}
		addrStr.append(", ");
		addrStr.append(city + ", ");
		addrStr.append(state + " ");
		addrStr.append(zip);
		
		
		String updateMsg = null;
		RequestDispatcher rd = null;

		Customer customer = new Customer();
		customer.setCustomerId(customerId);
		customer.setFirstName(firstName);
		customer.setLastName(lastName);
		customer.setPassword(password);
		customer.setGender(gender);
		customer.setAddress(addrStr.toString());
		customer.setTelHome(telHome);
		LocalDate birthDate = null;
		if(dateOfBirth.contains("/"))
		{
			String[] date = dateOfBirth.split("/");
			birthDate = LocalDate.of(Integer.valueOf(date[2]), Integer.valueOf(date[0]),
					Integer.valueOf(date[1]));
		}
		else {
			String[] date = dateOfBirth.split("-");
			birthDate = LocalDate.of(Integer.valueOf(date[0]), Integer.valueOf(date[1]),
					Integer.valueOf(date[2]));
		}
		// LocalDate today = LocalDate.now();
		// age = Period.between(birthDate, today).getYears();
		customer.setDateOfBirth(birthDate);



		if (PersonDao.updateRecord(customer)) {
			updateMsg = "Profile updated sucessfully!";
			customer.setEmail(customerEmail);
			customer.setCustomerId(customerId);
			session.setAttribute("customer", customer);
		} else {
			updateMsg = "Something went wrong. Update failed.";
		}
		request.setAttribute("updateMsg", updateMsg);

		rd = request.getRequestDispatcher("profile.jsp");
		rd.forward(request, response);
	}

}
