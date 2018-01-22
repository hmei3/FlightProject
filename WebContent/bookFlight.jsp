<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<%@ page import="com.hmei.bean.Flight"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.hmei.bean.FlightInfo"%>
<%@ page import="com.hmei.bean.SeatRemaining"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
</head>
<body>
	<%
		//sysout
		System.out.println(session == null);
		String index = (String)session.getAttribute("flight_list_index");
		//sysout
		System.out.println(index);
		ArrayList<FlightInfo> flightInfos = (ArrayList<FlightInfo>) session.getAttribute("result");
		FlightInfo fInfo = flightInfos.get(Integer.valueOf(index));
		int fId = fInfo.getFlightId();
		String fName = fInfo.getFlightName();
		String fDepAirport = fInfo.getDepAirport();
		String fDepAirportCode = fInfo.getDepAirportCode();
		String fDepTime = fInfo.getDepTime().toLocaleString();
		String fDepCity = fInfo.getDepCity();
		String fDepState = fInfo.getDepState();
		String fDepNationality = fInfo.getDepCountry();

		String fArrAirport = fInfo.getArrAirport();
		String fArrAirportCode = fInfo.getArrAirportCode();
		String fArrTime = fInfo.getArrTime().toLocaleString();
		String fArrCity = fInfo.getArrCity();
		String fArrState = fInfo.getArrState();
		String fArrNationality = fInfo.getArrCountry();

		SeatRemaining seatRemInfo = (SeatRemaining) session.getAttribute("seatRemInfo");
		int remSeatA = seatRemInfo.getRemSeatA();
		int remSeatB = seatRemInfo.getRemSeatB();
		int remSeatC = seatRemInfo.getRemSeatC();
	%>
	<div>

		<table class="table">
			
			<caption>Flight Name: <%=fName%></caption>
			<thead>
				<tr>
					<th></th>
					<th scope="col">Departure</th>
					<th scope="col">Arrival</th>
				</tr>
			</thead>
			<tr>
				<th>Airport</th>
				<td><%=fDepAirport%></td>
				<td><%=fArrAirport%></td>
			</tr>
			<tr>
				<th>Airport Code</th>
				<td><%=fDepAirportCode%></td>
				<td><%=fArrAirportCode%></td>
			</tr>
			<tr>
				<th>City</th>
				<td><%=fDepCity%></td>
				<td><%=fArrCity%></td>
			</tr>
			<tr>
				<th>State</th>
				<td><%=fDepState%></td>
				<td><%=fArrState%></td>
			</tr>
			<tr>
				<th>Nationality</th>
				<td><%=fDepNationality%></td>
				<td><%=fArrNationality%></td>
			</tr>
			<tr>
				<th>Time</th>
				<td><%=fDepTime%></td>
				<td><%=fArrTime%></td>
			</tr>
		</table>
		<h4>Remaining Seats:</h4>
		
		<p id="seatAp" style="display:inline">Class A:<%=remSeatA%></p>
		<p id="seatBp" style="display:inline">Class B:<%=remSeatB%></p>
		<p id="seatCp" style="display:inline">Class C:<%=remSeatC%></p>
		<br>
		<br>
		<h4>Input the amount of tickets</h4>
		<form class="form-inline" id = "submit-names" action="buyTicket" method="Get">
			<input type="hidden" name="flightId" value="<%=fId%>">	
			<div class="form-group">
				<label>Class A:</label>
				<input type="text" name="ticketNoA"><span id="Amsg"></span><br>
			</div>
			
			<div class="form-group">
				<label>Class B:</label><input type="text" name="ticketNoB"><span id="Bmsg"></span><br>
			</div>
			
			<div class="form-group">
				<label>Class C:</label><input type="text" name="ticketNoC"><span id="Cmsg"></span><br>
			</div>
			<div>
				<div>
					<h5 id="h5-classA">Class A</h5>
					<div id="classAnameField"></div>
				</div>
				<div>
					<h5 id="h5-classB">Class B</h5>
					<div id="classBnameField"></div>
				</div>
				<div>
					<h5 id="h5-classC">Class C</h5>
					<div id="classCnameField"></div>
				</div>
				

			</div>
			<input type="button" value="Confirm" name="btn-confirm" 	>
		</form>
		<button name="btn-buy">Buy</button>
		<button name="btn-cancel">Cancel</button>
	</div>
	<script src="./js/bookFlight.js"></script>
</body>
</html>