<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hmei.bean.Customer" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link href="./css/head.css" type="text/css" rel="stylesheet">
<title>Hao's Flight</title>

</head>
<body>
<div>
	<nav class="navbar navbar-inverse">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="index.jsp">Hao Flight</a>
		</div>
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			
		<%
		if(session != null && session.getAttribute("customer") != null)
		{
			Customer customer = (Customer)session.getAttribute("customer");
		%>
			<ul class="nav navbar-nav navbar-right">
			<li class="dropdown"><a href="#" class="dropdown-toggle"
				data-toggle="dropdown" role="button" aria-haspopup="true"
				aria-expanded="false"><%=customer.getEmail() %> <span class="caret"></span></a>
				<ul class="dropdown-menu">
					<li><a href="profile.jsp">Edit Profile</a></li>
					<li>
<!-- 						<form class="navbar-form navbar-left"> -->
<!-- 		          			<input type="hidden" class="form-control" placeholder="Search"> -->
<%-- 		          			<a href="CustBookingHistory?custid=<%=customer.getCustomerId() %>">Booking History</a> --%>
<!--       					</form> -->
						<a href="CustBookingHistory?custid=<%=customer.getCustomerId() %>">Booking History</a>
					</li>
					<li role="separator" class="divider"></li>
					<li><a href="logout.jsp">Logout</a></li>
				</ul>
			</li>
		</ul>
		<%
		}
		else
		{
		%>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="login.jsp">Login</a></li>
				<li><a href="register.jsp">Register</a></li>
			</ul>
		<%
		}
		%>
		</div>
	</div>
	</nav>
</div>
	<script src="https://code.jquery.com/jquery-3.2.1.js"
		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>