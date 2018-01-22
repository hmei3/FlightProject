<%@include file="head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/register.css" type="text/css">
<script src="https://code.jquery.com/jquery-3.2.1.min.js"
	integrity="sha256-hwg4gsxgFZhOsEEamdOYGBf13FyQuiTwlAQgxVSNgt4="
	crossorigin="anonymous"></script>
<script src="http://cdn.jsdelivr.net/qtip2/2.2.1/jquery.qtip.js"></script>

<link href="http://cdn.jsdelivr.net/qtip2/2.2.1/jquery.qtip.css" type="text/css">

<title>Register</title>
</head>
<body>
	<div id="content">
		<div id="register_containter">
			<%
			if(session != null && session.getAttribute("msg") != null)
			{
				String msg = (String)session.getAttribute("msg");
				out.println("<p style='text-align:center; color:red'>" + msg + "</p>");
				session.removeAttribute("msg");
			}
			%>
			<form class="form-horizontal" action="register" method="post">
				<!-- Email -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Email</label>
					<div class="col-sm-10">
						<input 
						type="email" 
						class="form-control"
						placeholder="Email"
						name="email">
						<p id="emailTip"></p>
					</div>
					
				</div>
				<!-- Password -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Password</label>
					<div class="col-sm-10">
						<input type="password" class="form-control" placeholder="Password"
							name="password">
						<p id="passwordTip"></p>
					</div>
				</div>
				<!-- First Name -->
				<div class="form-group">
					<label class="col-sm-2 control-label">First Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="First Name"
							name="firstName">
						<p id="firstNameTip"></p>
					</div>
				</div>
				<!-- Last Name -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Last Name</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="Last Name"
							name="lastName">
						<p id="lastNameTip"></p>
					</div>
				</div>
				<!-- Gender -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Gender</label>
					<div class="col-sm-10">
						<select class="form-control" name="gender">
							<option value="Male">Male</option>
							<option value="Female">Female</option>
							<option value="Other">Other</option>
						</select>
					</div>
				</div>

				<!-- DateofBirth -->
				<div class="form-group">
					<label class="col-sm-2 control-label">DateofBirth</label>
					<div class="col-sm-10">
						<input type="date" class="form-control" placeholder="mm/dd/yyyy"
							name="dateOfBirth">
						<p id="dateOfBirthTip"></p>
					</div>
				</div>
				<!-- Street No -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Street No</label>
					<div class="col-sm-10">
						<input type="text" class="form-control"
							placeholder="Street Number" name="streetNo">
						<p id="streetNoTip"></p>
					</div>
				</div>
				<!-- Street -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Street</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="Street"
							name="streetName">
						<p id="streetTip"></p>
					</div>
				</div>
				<!-- Apt# -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Apt</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="Apartment No"
							name="aptNo">
						<p id="aptNoTip"></p>
					</div>
				</div>
				<!-- City -->
				<div class="form-group">
					<label class="col-sm-2 control-label">City</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="City"
							name="city">
						<p id="cityTip"></p>
					</div>
				</div>
				<!-- State -->
				<div class="form-group">
					<label class="col-sm-2 control-label">State</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="State"
							name="state">
						<p id="stateTip"></p>
					</div>
				</div>
				<!-- Zip -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Zip</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="Zip"
							name="zip">
						<p id="zipTip"></p>
					</div>
				</div>
				<!-- Tel-home -->
				<div class="form-group">
					<label class="col-sm-2 control-label">Tel-Home</label>
					<div class="col-sm-10">
						<input type="text" class="form-control" placeholder="Tel-home"
							name="telHome">
						<p id="telHomeTip"></p>
					</div>
				</div>

				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-10">
						<button type="submit" name="registerSubmit" class="btn btn-default" disabled>Sign in</button>
					</div>
				</div>
			</form>
		</div>
	</div>
	<script src="./js/regValidation.js"></script>
</body>
</html>