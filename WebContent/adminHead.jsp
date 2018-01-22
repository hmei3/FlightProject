<%@page import="com.hmei.bean.Manager"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link href="./css/admin-head.css" rel="stylesheet">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
</head>
<body>
	<%
	if(session == null || session.getAttribute("manager") == null)
	{
		response.sendRedirect("login.jsp");
	}
	else
	{
		Manager manager = (Manager)session.getAttribute("manager");
	%>

	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="managerIndex.jsp">Hao Flight</a>
		</div>

		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<li><a href="addFlight.jsp">Add Flight
						<span class="sr-only">(current)</span>
				</a></li>
				<li><a href="updateFlight.jsp">Update Flight</a></li>
				<li><a href="deleteFlight.jsp">Delete Flight</a></li>
				<li><a href="bookingInfo.jsp">Check Booking</a></li>

			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li class="dropdown"><a href="#" class="dropdown-toggle"
					data-toggle="dropdown" role="button" aria-haspopup="true"
					aria-expanded="false"><%=manager.getUserName() %> <span class="caret"></span></a>
					<ul class="dropdown-menu">
						<li><a href="logout.jsp">Logout</a></li>
					</ul>
				</li>
			</ul>
		</div>
	</div>
	</nav>
	<%
	}
	%>
	<script src="https://code.jquery.com/jquery-3.2.1.js"
		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
	<!-- 	<script type="text/javascript"> -->
	<!--  		$(document).ready(function() { -->
	<!--  			$("li").click(function() { -->
	<!--  				if ($(this).hasClass("active") === false) { -->
	<!--  					$("li").removeClass("active"); -->
	<!--  					$(this).addClass("active"); -->
	<!--  				} -->
	<!--  			}); -->
	<!--  		}); -->
	<!-- 	</script> -->
</body>
</html>