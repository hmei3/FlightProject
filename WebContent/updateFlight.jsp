<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hmei.bean.Flight"%>
<%@ include file="adminHead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Update Flight</title>
</head>
<body>
	<div class="container">
		<div>
			<form action="admin_search_flight" method="Get" id="search-flight-form">
				<div style="display: inline-block">
					<p id="search-flightname-valid-msg"></p>
					<label>Flight Name*</label><br> <input type="text"
						placeholder="Flight Name" name="fName" id="search-flightname" required><br>
				</div>

				<div style="display: inline-block">
					<p id="search-deptime-valid-msg"></p>
					<label>Departure Time*</label><br> <input
						type="datetime-local" placeholder="Departure Time" id="search-deptime" name="depTime"
						title="YYYY/MM/DD/HH/MI" required><br>
				</div>

				<div style="display: inline-block">
				<p id="search-depcode-valid-msg"></p>
					<label>Dep. Airport Code*</label><br> <input type="text"
						placeholder="Dep. Airport Code" id="search-depcode" name="depAirportCode" required><br>
				</div>
				<input type="hidden" name="action" value="update"> 
				<input type="button" id="search-flight-btn" value="Search"><br>
			</form>
		</div>
		<hr>
		<div>
			<%
				if (session != null && session.getAttribute("result") != null) {
					Object result = session.getAttribute("result");
					if (result.getClass() == String.class) {
						out.println("<p>" + result + "</p>");
					} else if (result.getClass() == Flight.class) {
						Flight flight = (Flight) result;
			%>
			<form action="updateFlight" method="POST" id="update-form">
				<div style="text-align:center"><p id="add-flight-msg"></p></div>
				<div class="panel panel-default">
					<div class="panel-heading">Update Flight</div>
					<input type="hidden" name="id" value="<%=flight.getFlightId()%>">
					<table class="table">
						<tr>
							<th><label>Flight Name:</label></th>
							<td><input type="text" placeholder="Flight Name" id="flightname"
								name="fName" value="<%=flight.getFlightName()%>">
								<p id="flightname-valid-msg"></p>
								</td>
						</tr>

						<tr>
							<th><label>Departure Time:</label></th>
							<td><input type="datetime-local" id="deptime"
								placeholder="Departure Time" name="depTime"
								value="<%=flight.getDepTime().replace(' ', 'T')%>"
								title="YYYY/MM/DD/HH/MM">
								<p id="deptime-valid-msg"></p>
								</td>
						</tr>

						<tr>
							<th><label>Departure Airport Code:</label></th>
							<td><input type="text" placeholder="Dep. Airport Code"
								name="depAirportCode" id="depcode" value="<%=flight.getDepAirportCode()%>">
								<p id="depcode-valid-msg"></p>
							</td>
						</tr>

						<tr>
							<th><label>Arrival Time:</label></th>
							<td><input type="datetime-local" placeholder="Arrival Time"
								name="arrTime"
								id="arrtime"
								value="<%=flight.getArrTime().replace(' ', 'T')%>"
								title="YYYY/MM/DD/HH/MM">
								<p id="arrtime-valid-msg"></p>
								</td>
						</tr>

						<tr>
							<th><label>Arrival Airport Code:</label></th>
							<td><input type="text" placeholder="Arr. Airport Code"
								name="arrAirportCode" id="arrcode" value="<%=flight.getArrAirportCode()%>">
								<p id="arrcode-valid-msg"></p>
						</tr>


					</table>
				</div>
				<input type="button" id="update-flight-btn" value="Update"><br>
			</form>
			<%
				}
					session.removeAttribute("result");
				}
			%>
		</div>
	</div>
	<script src="js/update-flight.js"></script>
</body>
</html>