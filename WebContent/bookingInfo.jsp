<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.hmei.bean.FullInfo"%>
<%@ page import="com.hmei.bean.SeatRemaining"%>
<%@ include file="adminHead.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
<div class="container">
	<div>
		<form action="checkBooking" method="Get" id="search-flight-form">
			
			<div style="display:inline-block">
				<p id="search-flightname-valid-msg"></p>
				<label>Flight Name*</label><br> 
				<input type="text" placeholder="Flight Name" id="search-flightname" name="fName" required><br> 
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
			<input type="button" id="search-flight-btn" value="Check"><br>
		</form>
	</div>
	<hr>
		<%	
		if(session == null)
		{
			response.sendRedirect("login.jsp");
		}
		
		if(request.getAttribute("adminBookingView") != null)
		{
			
			if (String.class == request.getAttribute("adminBookingView").getClass()) 
			{
				System.out.println(1);
				out.println("<p>" + (String) request.getAttribute("adminBookingView") + "</p>");
			} 
			else	
			{
				List<FullInfo> list = (ArrayList<FullInfo>) request.getAttribute("adminBookingView");
				SeatRemaining seatRem = (SeatRemaining)request.getAttribute("seatRemaining");
		%>
				<div>
					<h5>Remaining Seat:</h5>
					<ul>
						<li>Class A: <%=seatRem.getRemSeatA() %></li>
						<li>Class B: <%=seatRem.getRemSeatB() %></li>
						<li>Class C: <%=seatRem.getRemSeatC() %></li>
					</ul>
					
				</div>

					<div>				
						<table class="table table-hover">
				
							<tr>
								<th>Customer</th>
								<th>Flight Name</th>
								<th>Passenger</th>
								<th>Dep. Airport</th>
								<th>Dep. Time</th>
								<th>Arr. Airport</th>
								<th>Arr. Time</th>
								<th>Seat Class</th>
								<th>Booking Date</th>
								<th>Booking Status</th>
								<th>Ticket Status</th>
							</tr>
		<%
				for (int i = list.size() - 1; i >= 0; i--) {
		%>				
							<tr>
								<td><%=list.get(i).getCustomer().getFirstName()+" " + list.get(i).getCustomer().getLastName() %></td>
								<td><%=list.get(i).getFlight().getFlightName() %></td>
								<td><%=list.get(i).getTicket().getPassengerName() %></td>
								<td><%=list.get(i).getFlight().getDepAirportCode()%></td>
								<td><%=list.get(i).getFlight().getDepTime() %></td>
								<td><%=list.get(i).getFlight().getArrAirportCode()%></td>
								<td><%=list.get(i).getFlight().getArrTime()%></td>
								<td><%=list.get(i).getTicket().getSeatClass() %></td>
								<td><%=list.get(i).getBooking().getBookingDate() %></td>
								<td><%=list.get(i).getBooking().getBookingStatus() %></td>
								<td><%=list.get(i).getTicket().getTicketStatus() %></td>
							</tr>
							<tr>
							</tr>
				<%
				}
				%>
						</table>
					</div>

		<%
			}
		}
		%>

</div>
<script src="js/admin-search-flight.js"></script>
</body>
</html>