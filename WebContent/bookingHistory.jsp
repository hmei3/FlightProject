<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="head.jsp" %>
<%@ page import="java.util.*"%>
<%@ page import="com.hmei.bean.BookedFlightInfo"%>
<%@ page import="java.time.LocalDate" %>
<%@ page import="java.sql.Timestamp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>



<div class="container">		
	<%	
		if(session == null)
		{
			response.sendRedirect("login.jsp");
		}
		if(session.getAttribute("ticketCancelMsg") != null)
		{
			out.println("<div style='text-align:center'><p style='color:red'>" + (String) session.getAttribute("ticketCancelMsg") + "</p></div>");
			session.removeAttribute("ticketCancelMsg");
		}
		if (String.class == session.getAttribute("bookedFlights").getClass()) 
		{
			out.println("<p>" + (String) session.getAttribute("bookedFlights") + "</p>");
		} 
		else	
		{
			List<BookedFlightInfo> list = (ArrayList<BookedFlightInfo>) session.getAttribute("bookedFlights");

			//for (int i = list.size() - 1; i >= 0; i--) {
	%>
				<div>
					<table class="table table-hover">
			
						<tr class="">
							<th>Flight Name</th>
							<th>Passenger</th>
							<th>Dep. Airport</th>
							<th>Dep. Time</th>
							<th>Arr. Airport</th>
							<th>Arr. Time</th>
							<th>Seat Class</th>
							<th>Booking Time</th>
							<th>Booking Status</th>
							<th>Ticket Status</th>
							<th>Operation</th>
						</tr>
			<%
			for (int i = 0; i < list.size(); i++) {
			%>	
			
						<tr>
							<td><%=list.get(i).getFlightName()%></td>
							<td><%=list.get(i).getPassengerName() %></td>
							<td><%=list.get(i).getDepAirportCode() %></td>
							<td><%=list.get(i).getDepTime() %></td>
							<td><%=list.get(i).getArrAirportCode() %></td>
							<td><%=list.get(i).getArrTime() %></td>
							<td><%=list.get(i).getSeatClass() %></td>
							<td><%=list.get(i).getBookingDate() %></td>
							<td><%=list.get(i).getBookingStatus() %></td>
							<td><%=list.get(i).getTicketStatus() %></td>
							<td>
								<form action="cancelFlight" method="POST">
									<input type="hidden" name="index" value="<%=i%>">
									<%
									if(list.get(i).getTicketStatus().equals("Confirmed")
											&&Timestamp.valueOf(list.get(i).getDepTime()).after(java.sql.Date.valueOf(LocalDate.now())))
									{
									%>
										<input type="submit" name="CancelFlight" value = "Cancel Ticket">
									<%
									}
									%>

								</form>
							</td>
						</tr>
						<%
			}
			%>
					</table>
				</div>

	<%
		}
	%>
</div>	
</body>
</html>