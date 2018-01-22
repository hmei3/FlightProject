<%@ include file="head.jsp"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hmei.bean.Customer"%>
<%@ page import="java.util.Arrays"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
	integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u"
	crossorigin="anonymous">
<link rel="stylesheet" href="./css/profile.css">
<script
	src="//ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script
	src="//netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
</head>
<body>
	<%
		if (session == null || session.getAttribute("customer") == null) 
		{
			session.setAttribute("msg", "Time out! please log in again!");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		} 
		else 
		{
			Customer customer = (Customer) session.getAttribute("customer");
			String updateMsg = "";
			if(request.getAttribute("updateMsg") != null)
			{
				updateMsg = (String)	request.getAttribute("updateMsg");
			}
	%>

	<div class="container">
	<div style="text-align: center">
		<p id="message"><%=updateMsg %></p>
	</div>
		<div class="row">
			<h2>Profile</h2>

			<form method="post" action="update_profile" name="form-prof">
				<div class="col-md-7">

					<div class="panel panel-default">
						<div class="panel-heading">
							<h4>User Profile</h4>
						</div>
						<div class="panel-body">

							<div class="box box-info">

								<div class="box-body">
									<div class="col-sm-6">
										<div align="center">
											<img alt="User Pic"
												src="https://x1.xingassets.com/assets/frontend_minified/img/users/nobody_m.original.jpg"
												id="profile-image1" class="img-circle img-responsive">
										</div>

										<br>

										<!-- /input-group -->
									</div>
									<div class="col-sm-6">
										<h4 style="color: #00b1b1;"></h4>
									</div>
									<div class="clearfix"></div>
									<hr style="margin: 5px 0 5px 0;">

									<div class="col-sm-5 col-xs-6 tital ">First Name*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="firstNameP"><%=customer.getFirstName()%></p>
										<input name="firstNameInput" type="hidden" required>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Last Name*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="lastNameP"><%=customer.getLastName()%></p>
										<input name="lastNameInput" type="hidden" required>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Gender*</div>
									<div class="col-sm-7">
										<%
											String gender = customer.getGender();
										%>
										<select id="genderSelect" name="gender">
											<%
												if (gender.equals("Male")) {
											%>
											<option value="Male" selected>Male</option>
											<option value="Female">Female</option>
											<option value="Other">Other</option>
											<%
												} else if (gender.equals("Female")) {
											%>
											<option value="Male">Male</option>
											<option value="Female" selected>Female</option>
											<option value="Other">Other</option>
											<%
												} else {
											%>
											<option value="Male">Male</option>
											<option value="Female">Female</option>
											<option value="Other" selected>Other</option>
											<%
												}
											%>
										</select>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Email</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="emailP" title="unable to change"><%=customer.getEmail()%><img src="./img/not_allowed.png"
											 title="unable to edit"
											 style="width:14px; height:auto;"></p>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Password*</div>
									<div class="col-sm-7">
									<%
									String password = customer.getPassword();
									int passwordLen = password.length();
									String pwdInParagraph = "";
									for(int i = 0; i < passwordLen; i++)
									{
										pwdInParagraph += "*";
									}
									%>
										<p style="margin-bottom: 0px; display:inline-block" id="passwordP"><%=pwdInParagraph %></p>
										<input name="passwordInput" type="hidden" value="<%=customer.getPassword()%>" required>
				
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Tel-Home*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="telHomeP"><%=customer.getTelHome()%></p>
										<input name="telHomeInput" type="hidden" required>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Date of Birth*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="dateOfBirthP"><%=customer.getDateOfBirth()%></p>
										<input name="dateOfBirthInput" type="hidden" required>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>
									<%
										String fullAddress = customer.getAddress();
											String[] addrArray = fullAddress.split(",");
											String streetNo = null;
											String streetName = null;
											String aptNo = null;
											String city = addrArray[1].trim();
											String state = addrArray[2].trim().split(" ")[0];
											String zip = addrArray[2].trim().split(" ")[1];
											String[] localAddrArray = addrArray[0].split(" ");
											int length = localAddrArray.length;
											streetNo = localAddrArray[0];
											if (length == 2) {
												streetName = localAddrArray[1];
												aptNo = "";
											} else {
												if (localAddrArray[length - 2].equals("Apt")) {
													aptNo = localAddrArray[length - 1];
													streetName = addrArray[0].substring(streetNo.length() + 1,
															addrArray[0].length() - 4 - aptNo.length());
												} else {
													aptNo = "";
													streetName = addrArray[0].substring(streetNo.length() + 1);
												}

											}
									%>
									<div class="col-sm-5 col-xs-6 tital ">Street No*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="streetNoP"><%=streetNo%></p>
										<input name="streetNoInput" type="hidden" required>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Street Name*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="streetNameP"><%=streetName%></p>
										<input name="streetNameInput" type="hidden" required>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Apt No</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="aptNoP"><%=aptNo.equals("") ? "Double click to edit" : aptNo%></p>
										<input name="aptNoInput" type="hidden">
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">City*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="cityP"><%=city%></p>
										<input name="cityInput" type="hidden" required>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">State*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="stateP"><%=state%></p>
										<input name="stateInput" type="hidden" required>
									</div>
									<div class="clearfix"></div>
									<div class="bot-border"></div>

									<div class="col-sm-5 col-xs-6 tital ">Zip*</div>
									<div class="col-sm-7">
										<p style="margin-bottom: 0px" id="zipP"><%=zip%></p>
										<input name="zipInput" type="hidden" required>
									</div>
									<!-- /.box-body -->
								</div>
								<!-- /.box -->
							</div>
						</div>
					</div>
					<button type="button" class="btn btn-primary" id="updateBtn" disabled>Update</button>
					<button type="button" class="btn btn-warning" id="cancelBtn" title="Cancel will not commit your changes">Cancel</button>
			</form>
		</div>
	</div>
	<%
		}
	%>
	<script src="./js/profile.js"></script>
	<script>
		if ($("#aptNoP").text().trim() === "Double click to edit") {

			$("#aptNoP").css({
				"font-style" : "italic",
				"font-size" : "14px",
				"color" : "gray"
			});
		}
	</script>
		<script src="https://code.jquery.com/jquery-3.2.1.js"
		integrity="sha256-DZAnKJ/6XZ9si04Hgrsxu/8s717jcIzLy3oi35EouyE="
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
		integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa"
		crossorigin="anonymous"></script>
</body>
</html>