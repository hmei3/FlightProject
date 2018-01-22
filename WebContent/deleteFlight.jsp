<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.hmei.bean.Flight" %>
<%@ include file="adminHead.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Delete Flight</title>
</head>
<body>
<div class="container">

	<div>
		<form action="admin_search_flight" method="Get" id="search-flight-form">
			<div style="display:inline-block">
			<p id="search-flightname-valid-msg"></p>
			<label>Flight Name*</label><br> 
			<input type="text" placeholder="Flight Name" name="fName" id="search-flightname" required><br> 
			</div>
			
			<div style="display:inline-block">
			<p id="search-deptime-valid-msg"></p>
			<label>Departure Time*</label><br> 
			<input type="datetime-local" placeholder="Departure Time" id="search-deptime" name="depTime" title="YYYY/MM/DD/HH/MI" required><br>
			</div>
			
			<div style="display:inline-block">
			<p id="search-depcode-valid-msg"></p>
			<label>Dep. Airport Code*</label><br>
			<input type="text" placeholder="Dep. Airport Code" id="search-depcode" name="depAirportCode" required><br>	
			</div>
					
			<input type="hidden" name="action" value="delete">
			<input type="button" id="search-flight-btn" value="Search"><br>
		</form>
	</div>
	<hr>
	<div>
		<%
		if(session != null && session.getAttribute("result") != null)
		{
			Object result = session.getAttribute("result");
			if(result.getClass() == String.class)
			{
				out.println("<p>" + result + "</p>");
			}
			else if(result.getClass() == Flight.class)
			{
				Flight flight = (Flight)result;
		%>
		<input type="hidden" name="id" value="<%=flight.getFlightId() %>">

		<form action="deleteFlight" method="POST">
			<input type="hidden" name="id" value="<%=flight.getFlightId() %>" readonly>
			<label>Flight Name:</label><br>
			<input type="text" placeholder="Flight Name" name="fName" value="<%=flight.getFlightName()%>" readonly><br>
			
			<label>Departure Time:</label><br>
			<input type="text" placeholder="Departure Time" name="depTime" value="<%=flight.getDepTime().replace(' ','T') %>" title="YYYY/MM/DD/HH/MM" readonly><br>
			
			<label>Departure City:</label><br>
			<input type="text" placeholder="Dep. Airport Code" name="depAirportCode" value="<%=flight.getDepAirportCode() %>" readonly><br>
			
			<label>Arrival Time:</label><br>
			<input type="text" placeholder="Arrival Time" name="arrTime" value="<%=flight.getArrTime().replace(' ','T') %>" title="YYYY/MM/DD/HH/MM" readonly><br>
			
			<label>Arrival City:</label><br>
			<input type="text" placeholder="Arr. Airport Code" name="arrAirportCode" value="<%=flight.getArrAirportCode() %>" readonly><br>
			
			<label>Class A Seat Amount:</label><br>
			<input type="text" placeholder="Class A Seat Amount" name="AseatAmt" value="<%=flight.getSeatAAmt() %>" readonly><br>
			
			<label>Class B Seat Amount:</label><br>
			<input type="text" placeholder="Class B Seat Amount" name="BseatAmt" value="<%=flight.getSeatBAmt() %>" readonly><br>
			
			<label>Class C Seat Amount:</label><br>
			<input type="text" placeholder="Class C Seat Amount" name="CseatAmt" value="<%=flight.getSeatCAmt() %>" readonly><br>
			
			<input type="submit" value="Delete"><br>
		</form>
		<%
			}
			session.removeAttribute("result");
		}
		%>
	</div>
</div>
<script src="js/admin-search-flight.js"></script>
</body>
</html>