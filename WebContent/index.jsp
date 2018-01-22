<%@include file="head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hmei.bean.Customer"%>
<!DOCTYPE html>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>HaoFlight</title>
<link href="./css/index.css" type="text/css" rel="stylesheet">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">


<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>

<body>

<div class="container">
	<div id="content">
		<p id="error-p"></p>
		<h3 style="margin-bottom: 30px; color:white">Flight Search</h3>
		<div id="searchbox">
			
			<form class="form-inline" id="search-flight-form" action="searchflights" method="get">
				
				<div class="form-group">
					<label style="color:white">From</label> 
					<input type="text" id="from-input" class="form-control" name="from" placeholder="From City/Airport">
				</div>
				
				<div class="form-group">
					<label style="color:white">To</label> 
					<input type="text" id="to-input" class="form-control" name="to" placeholder="To City/Airport">
				</div>
				
				<div class="form-group">
					<label style="color:white">Departure Date</label>
					<input type="date" id="depdate-input" class="form-control" name="depdate" placeholder="Date">
				</div>
				
				<input type="button" class="btn btn-default" name="search-btn" id="search-btn" value="Search Flight">
			</form>
		</div>

	</div>
</div>
</body>
<script src="https://code.jquery.com/jquery-3.2.1.js"
	integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
	integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
	crossorigin="anonymous"></script>
	
<!-- Old -->	
<script src="js/searchbox-validation.js"></script>

<!-- Using JQuery Plug-in -->
 <script
        src="https://code.jquery.com/jquery-3.1.1.min.js"
        integrity="sha256-hVVnYaiADRTO2PzUGmuLJr8BLUSjGIZsDYGmIJLv2b8="
        crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
<script src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/additional-methods.js"></script>
<!-- <script src="js/validation.js"></script> -->
</html>