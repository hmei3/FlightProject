<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="adminHead.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<%
			if (session != null && session.getAttribute("result") != null) {
				out.println("<div style='text-align=center;'");
				out.println("<p style='color: red;'>" + session.getAttribute("result") + "</p>");
				out.println("</div>");
				session.removeAttribute("result");

			}
		%>
		<div style="text-align: center"><p id="add-flight-msg"></p></div>
		<form action="addFlight" method="POST" id="add-flight-form">
			<div class="panel panel-default">
				<div class="panel-heading">Add Flight</div>
				<table class="table">
					<tr>
						<th><label>Flight Name:</label></th>
						<td><input type="text" id="flightname" placeholder="Flight Name" name="fName">
							<p id="flightname-valid-msg"></p>
						</td>
					</tr>

					<tr>
						<th><label>Departure Time:</label></th>
						<td><input type="datetime-local" id="deptime" placeholder="Departure Time"
							name="depTime" title="YYYY/MM/DD/HH/MM">
							<p id="deptime-valid-msg"></p>
						<td>
					</tr>

					<tr>
						<th><label>Departure Airport Code:</label></th>
						<td><input type="text" id="depcode" placeholder="Dep. Airport Code"
							name="depAirportCode">
							<p id="depcode-valid-msg"></p>
						</td>
					</tr>

					<tr>
						<th><label>Arrival Time:</label></th>
						<td><input type="datetime-local" id="arrtime" placeholder="Arrival Time"
							name="arrTime" title="YYYY/MM/DD/HH/MM">
							<p id="arrtime-valid-msg"></p>
						</td>
					</tr>

					<tr>
						<th><label>Arrival Airport Code:</label></th>
						<td><input type="text" id="arrcode" placeholder="Arr. Airport Code"
							name="arrAirportCode">
							<p id="arrcode-valid-msg"></p>
						</td>
					</tr>

					<tr>
						<th><label>Class A Seat Amount:</label></th>
						<td><input type="text" id="seatAamount" placeholder="Class A Seat Amount"
							name="AseatAmt">
							<p id="seatA-valid-msg"></p>
						</td>
					</tr>

					<tr>
						<th><label>Class B Seat Amount:</label></th>
						<td><input type="text" id="seatBamount" placeholder="Class B Seat Amount"
							name="BseatAmt">
							<p id="seatB-valid-msg"></p>
						</td>
					</tr>

					<tr>
						<th><label>Class C Seat Amount:</label></th>
						<td><input type="text" id="seatCamount" placeholder="Class C Seat Amount"
							name="CseatAmt">
							<p id="seatC-valid-msg"></p>
						</td>
					</tr>
				</table>
			</div>
			<input type="button" value="Add" id="add-flight-btn"><br>
		</form>
	</div>
	<script src="js/flight-validation.js"></script>
</body>
</html>