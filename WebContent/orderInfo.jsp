<%@page import="java.util.Arrays"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.hmei.bean.FlightInfo"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Order Receipt</title>
</head>
<body>
	<%
		if (session == null || session.getAttribute("customer") == null) {
			response.sendRedirect("login.jsp");
		} else {
			Customer customer = (Customer) session.getAttribute("customer");
			String custName = customer.getFirstName() + " " + customer.getLastName();
			String flightIndex = (String) session.getAttribute("flight_list_index");
			int index = Integer.valueOf(flightIndex);
			List<FlightInfo> list = (ArrayList<FlightInfo>) session.getAttribute("result");
			FlightInfo fi = list.get(index);
	%>

	<div class="panel panel-default">
		<!-- Default panel contents -->
		<div class="panel-heading">Order Information</div>
		<div class="panel-body">
			<p>
				Dear
				<%=custName%>,<br>
				This is an order you just placed. You could either
				print it out or request to send it to your email.<br>
				<%
				String[] ticketApassengers = (String[])request.getAttribute("ticketApassengers");
				String[] ticketBpassengers = (String[])request.getAttribute("ticketBpassengers");
				String[] ticketCpassengers = (String[])request.getAttribute("ticketCpassengers");
				
				if(ticketApassengers.length != 0)
				{
					String passengersA = Arrays.toString(ticketApassengers);
					passengersA = passengersA.substring(1, passengersA.length() - 1);
				%>
				<h5>Class A:</h5>
				<p><%=passengersA %></p>
				<%
				}
				
				if(ticketBpassengers.length != 0)
				{
					String passengersB = Arrays.toString(ticketBpassengers);
					passengersB = passengersB.substring(1, passengersB.length() - 1);
				%>
				<h5>Class B:</h5>
				<p><%=passengersB %></p>
				<%
				}
				
				if(ticketCpassengers.length != 0)
				{
					String passengersC = Arrays.toString(ticketCpassengers);
					passengersC = passengersC.substring(1, passengersC.length() - 1);
				%>
				<h5>Class C:</h5>
				<p><%=passengersC %></p>
				<%
				}
				%>
				
			</p>
		</div>
		<table class="table">
			<caption>
				Flight Name:
				<%=fi.getFlightName()%></caption>
			<thead>
				<tr>
					<th></th>
					<th scope="col">Departure</th>
					<th scope="col">Arrival</th>
				</tr>
			</thead>
			<tr>
				<th>Airport</th>
				<td><%=fi.getDepAirport()%></td>
				<td><%=fi.getArrAirport()%></td>
			</tr>
			<tr>
				<th>Airport Code</th>
				<td><%=fi.getDepAirportCode()%></td>
				<td><%=fi.getArrAirportCode()%></td>
			</tr>
			<tr>
				<th>City</th>
				<td><%=fi.getDepCity()%></td>
				<td><%=fi.getArrCity()%></td>
			</tr>
			<tr>
				<th>State</th>
				<td><%=fi.getDepState()%></td>
				<td><%=fi.getArrState()%></td>
			</tr>
			<tr>
				<th>Country</th>
				<td><%=fi.getDepCountry()%></td>
				<td><%=fi.getArrCountry()%></td>
			</tr>
			<tr>
				<th>Time</th>
				<td><%=fi.getDepTime()%></td>
				<td><%=fi.getArrTime()%></td>
			</tr>
		</table>
	</div>
	<%
		for (int i = 0; i < ticketApassengers.length; i++) {

			}
	%>


	<%
		}
	%>
</body>
</html>