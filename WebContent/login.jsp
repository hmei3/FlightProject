<%@ include file="head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<!-- CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link href="./css/login.css" rel="stylesheet" type="text/css">


<!-- Script -->
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.17.0/jquery.validate.min.js"></script>
<script src="http://ajax.microsoft.com/ajax/jquery.validate/1.7/additional-methods.js"></script>
<title>Login</title>
</head>


<body>
	<div id="content">
	<% if(session != null && session.getAttribute("msg") !=null) {
		out.println("<div style='text-align:center; color:red;'><p>" + (String)session.getAttribute("msg") + "</p></div>");
		session.removeAttribute("msg");
	} 
	%>
		<div id="login_containter">
			<form class="form-horizontal" action="login" method="post">
				<div class="form-group">
					<label class="col-sm-2 control-label" id="userNameLabel">Email</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" id="userNameInput"
							placeholder="Email Address" name="userName" autofocus>
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" name="password" placeholder="Password">
					</div>
				</div>
				<div class="bs-docs-example toright">
					<label class="control-label" style="margin-right: 10px">User
						Type</label> <select class="selectpicker" data-style="btn-primary"
						name="userType" id="userTypeSelect">
						<option value="customer">Customer</option>
						<option value="admin">Administrator</option>
					</select>
				</div>
				<div class="form-group" style="margin-top:10px">
					<div class="col-sm-offset-2 col-sm-10 toright">
						<button type="submit" class="btn btn-default">Sign in</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="./js/login.js"></script>
</body>
</html>