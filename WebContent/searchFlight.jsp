<%@ include file="head.jsp" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.hmei.bean.FlightInfo"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
	
	<div style="text-align:center"><p id="error-p"></p></div>
	<div>
		<form action="searchflights" id="search-flight-form" method="Get">
			<div style="display:inline">
			<label>From:</label>
			<input type="text" id="from-input" name="from" placeholder="From City/Airport" required>
			</div>
			
			<div style="display:inline">
			<label>To:</label>
			<input type="text" id="to-input" name="to" placeholder="To City/Airport" required>
			</div>
			
			<div style="display:inline">
			<label>Departure Date</label>
			<input type="date" id="depdate-input" name="depdate" required>
			</div>
		
			<input type="button" id="search-btn" value="Search">
		</form>
	</div>
	<hr>
	<div>
	<%
	if(session != null && session.getAttribute("result").getClass() == String.class)
	{
		out.println("<p>" + (String)session.getAttribute("result") + "</p");
		session.removeAttribute("result");
	}
	else if(session != null && session.getAttribute("result").getClass() == ArrayList.class)
	{
		ArrayList<FlightInfo> list = (ArrayList)session.getAttribute("result");
		for(int i = 0; i < list.size(); i++)
		{
			FlightInfo fInfo = list.get(i);
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
			
	%>
	<br>
	<div>
		<form action="bookFlight" method="Get">
			<input type="hidden" name="flight_list_index" value="<%=i%>">
			
			<div class="panel panel-default">
  			<div class="panel-heading"><h4>Flight Name: <%=fName %></h4></div>	
			<table class="table">
				<thead>
					<tr>
						<th></th>
						<th scope="col">Departure</th>
						<th scope="col">Arrival</th>
					</tr>
				</thead>
				<tr>
					<th> Airport</th>
					<td><%=fDepAirport %></td>
					<td><%=fArrAirport %></td>
				</tr>
				<tr>
					<th>Airport Code</th>
					<td><%=fDepAirportCode %></td>
					<td><%=fArrAirportCode %></td>
				</tr>
				<tr>
					<th>City</th>
					<td><%=fDepCity %></td>
					<td><%=fArrCity %></td>
				</tr>
				<tr>
					<th>State</th>
					<td><%=fDepState %></td>
					<td><%=fArrState %></td>
				</tr>
				<tr>
					<th>Nationality</th>
					<td><%=fDepNationality %></td>
					<td><%=fArrNationality %></td>
				</tr>
				<tr>
					<th>Time</th>
					<td><%=fDepTime %></td>
					<td><%=fArrTime %></td>
				</tr>
			</table>
			</div>
			<input type="submit" value="Book">
		</form>
	</div>
	
	
	
	<%
		}
	}
	%>
	</div>
</div>
<script src="js/searchbox-validation.js"></script>
</body>
</html>