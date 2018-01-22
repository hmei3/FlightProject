$(document).ready(function($) {
	

	var email_isValid = false;
	var password_isValid = false;
	var firstName_isValid = false;
	var lastName_isValid = false;
	var dateOfBirth_isValid = false;
	var street_isValid = false;
	var streetNo_isValid = false;
	var aptNo_isValid = true;
	var city_isValid = false;
	var state_isValid = false;
	var zip_isValid = false;
	var telHome_isValid = false;
	
	


	function checkValidation(){
		if(email_isValid&&password_isValid&& firstName_isValid&&lastName_isValid
			&&dateOfBirth_isValid&&streetNo_isValid&&street_isValid&&aptNo_isValid
			&&city_isValid&&state_isValid&&zip_isValid&&telHome_isValid === true)
		{
			$("button[name='registerSubmit']").prop("disabled",false);
			console.log($("button[name='registerSubmit']").attr("disabled"));
			console.log(email_isValid + " " + password_isValid + " " + firstName_isValid
					+ " " + lastName_isValid + " " + dateOfBirth_isValid + " " + street_isValid + " "
					+ streetNo_isValid + " " + aptNo_isValid + " " + city_isValid + " " + state_isValid
					+ " " + zip_isValid + " " + telHome_isValid);
		}
	}
	//Email
	$("input[name='email']").focus(function(event) {
		$("#emailTip").text('First character should be a letter, \
			and no special characters are allowed except "-", "_"').css({
			"color": "green"
		});
	});
	$("input[name='email']").blur(function(event) {
		var pattern = /^(?=.{5,30}$)([\w][\w\d-_]+)@([\w]+)(\.[\w]{2,3}){1,2}$/;
		var email = $("input[name='email']").val();
		if(pattern.test(email))
		{
			email_isValid = true;
			$("#emailTip").empty();
			$("input[name='email']").css("border","1px solid green");
			checkValidation();
		}
		else
		{
			email_isValid = false;
			$("#emailTip").text("Invalid Email Address").css("color","red");
			$("input[name='email']").css("border","1px solid red");
		}

	});
	//Password
	$("input[name='password']").focus(function(event) {
		$("#passwordTip").text('Length 5 - 20. It should contain \
			at least one lower case, one uppercase letter and one \
			number. No special characters are supported.').css({
			"color": "green"
		});
	});
	$("input[name='password']").blur(function(event) {
		var pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{5,20}$/;
		var password = $("input[name='password']").val();

		if(pattern.test(password))
		{
			password_isValid = true;
			$("#passwordTip").empty();
			$("input[name='password']").css("border","1px solid green");
			checkValidation();
		}
		else
		{	
			password_isValid = false;
			$("#passwordTip").text("Invalid Password").css("color","red");
			$("input[name='password']").css("border","1px solid red");
		}

	});

	//First Name
	$("input[name='firstName']").focus(function(event) {
		$("#firstNameTip").text("Only letters are supported.").css({
			"color": "green"
		});
	});
	$("input[name='firstName']").blur(function(event) {
		var pattern = /^[a-zA-Z]{2,30}$/;
		var firstName = $("input[name='firstName']").val();

		if(pattern.test(firstName))
		{
			firstName_isValid = true;
			$("#firstNameTip").empty();
			firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
			$("input[name='firstName']").val(firstName).css("border","1px solid green");
			checkValidation();
		}
		else
		{
			firstName_isValid = false;
			$("#firstNameTip").text("Invalid First Name").css("color","red");
			$("input[name='firstName']").css("border","1px solid red");
		}
	});

	//Last Name
	$("input[name='lastName']").focus(function(event) {
		$("#lastNameTip").text("Only letters are supported.").css({
			"color": "green"
		});
	});
	$("input[name='lastName']").blur(function(event) {
		var pattern = /^[a-zA-Z]{2,30}$/;
		var lastName = $("input[name='lastName']").val();

		if(pattern.test(lastName))
		{
			lastName_isValid = true;
			$("#lastNameTip").empty();
			lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
			$("input[name='lastName']").val(lastName).css("border","1px solid green");
			checkValidation();
		}
		else
		{
			lastName_isValid = false;
			$("#lastNameTip").text("Invalid Last Name").css("color","red");
			$("input[name='lastName']").css("border","1px solid red");
		}

	});
	//Date Of Birth
	$("input[name='dateOfBirth']").focus(function(event) {
		$("#dateOfBirthTip").text("Please input values using format \"MM/DD/YYYY\".").css({
			"color": "green"
		});
	});
	$("input[name='dateOfBirth']").blur(function(event) {
		var pattern1 = /^[0-1][0-9]\/[0-3][0-9]\/[0-9]{4,}$/;
		var pattern2 = /^[0-9]{4,}-[0-1][0-9]-[0-3][0-9]$/;
		var date = $("input[name='dateOfBirth']").val();
		console.log(date);
		if(pattern1.test(date) || pattern2.test(date))
		{
			date = new Date(date);
			if(date.toString() === "Invalid Date")
			{
				dateOfBirth_isValid = false;
				$("#dateOfBirthTip").text("Invalid Date Of Birth").css("color","red");
				$("input[name='dateOfBirth']").css("border","1px solid red");
			}
			else if(new Date().getFullYear() - date.getFullYear() < 18)
			{
				dateOfBirth_isValid = false;
				$("#dateOfBirthTip").text("You are under 18, so you are unable to register an account.").css("color","red");
				$("input[name='dateOfBirth']").css("border","1px solid red");
			}
			else
			{
				dateOfBirth_isValid = true;
				$("#dateOfBirthTip").empty();
				$("input[name='dateOfBirth']").css("border","1px solid green");
				checkValidation();
			}
		}
		else
		{
			dateOfBirth_isValid = false;
			$("#dateOfBirthTip").text("Invalid Date Of Birth").css("color","red");
			$("input[name='dateOfBirth']").css("border","1px solid red");
		}
		

	});

	
	//Street Number
	$("input[name='streetNo']").focus(function(event) {
		$("#streetNoTip").text("Only numbers are supported.").css({
			"color": "green"
		});
	});
	$("input[name='streetNo']").blur(function(event) {		
		var pattern = /^[0-9]{1,10}$/;
		var streetNo = $("input[name='streetNo']").val();
		if(pattern.test(streetNo))
		{
			streetNo_isValid = true;
			$("#streetNoTip").empty();
			$("input[name='streetNo']").css("border","1px solid green");
			checkValidation();
		}
		else
		{
			streetNo_isValid = false;
			$("#streetNoTip").text("Invalid Street Number").css("color","red");
			$("input[name='streetNo']").css("border","1px solid red");
		}
	});

	//Street Name
	$("input[name='streetName']").focus(function(event) {
		$("#streetTip").text("Only letters and whitespases are supported.").css({
			"color": "green"
		});
	});
	$("input[name='streetName']").blur(function(event) {
		var pattern = /^(?=.{1,50}$)[a-zA-Z]+(\s[a-zA-Z]+)*$/;
		var street = $("input[name='streetName']").val();
		if(pattern.test(street))
		{
			street_isValid = true;
			$("#streetTip").empty();
			
		     street = street.toLowerCase().split(' ');
		     for(var i = 0; i < street.length; i++){
		          street[i] = street[i].charAt(0).toUpperCase() + street[i].substring(1);
		     }
		     street = street.join(' ');
			$("input[name='streetName']").val(street).css("border","1px solid green");
			checkValidation();
		}
		else
		{
			street_isValid = false;
			$("#streetTip").text("Invalid Street Name").css("color","red");
			$("input[name='streetName']").css("border","1px solid red");
		}		

	});
	
	//Apartment No
	$("input[name='aptNo']").focus(function(event) {
		$("#aptNoTip").text("Optional. Only numbers are supported.").css({
			"color": "green"
		});
	});
	$("input[name='aptNo']").blur(function(event) {
		var pattern = /^$|^[1-9][0-9]{0,9}$/;
		var aptNo = $("input[name='aptNo']").val();
		if(pattern.test(aptNo))
		{
			aptNo_isValid = true;
			$("#aptNoTip").empty();
			$("input[name='aptNo']").css("border","1px solid green");
			checkValidation();
		}
		else
		{
			aptNo_isValid = false;
			$("#aptNoTip").text("Invalid Apt Number").css("color","red");
			$("input[name='aptNo']").css("border","1px solid red");
		}		

	});
	//City
	$("input[name='city']").focus(function(event) {
		$("#cityTip").text("Only letters and whitespaces are supported.").css({
			"color": "green"
		});
	});
	$("input[name='city']").blur(function(event) {
		var pattern = /^(?=.{1,50}$)[a-zA-Z]+(\s[a-zA-Z]+)*$/;
		var city = $("input[name='city']").val();
		console.log(city);
		if(pattern.test(city))
		{
			
			city_isValid = true;
			$("#cityTip").empty();
			city = city.toLowerCase().split(' ');
			console.log(city);
		    for(var i = 0; i < city.length; i++){
		    		city[i] = city[i].charAt(0).toUpperCase() + city[i].substring(1);
		    }
		     city = city.join(' ');
		     console.log(city);
			$("input[name='city']").val(city).css("border","1px solid green");
			checkValidation();
		}
		else
		{
			city_isValid = false;
			$("#cityTip").text("Invalid City Name").css("color","red");
			$("input[name='city']").css("border","1px solid red");
		}			

	});
	//State
	$("input[name='state']").focus(function(event) {
		$("#stateTip").text("Only letters are supported.").css({
			"color": "green"
		});
	});
	$("input[name='state']").blur(function(event) {
		var pattern = /^[a-zA-Z]{2,10}$/;
		var state = $("input[name='state']").val();
		if(pattern.test(state))
		{
			state_isValid = true;
			$("#stateTip").empty();
			state = state.toUpperCase();
			$("input[name='state']").val(state).css("border","1px solid green");
			checkValidation();
		}
		else
		{
			state_isValid = false;
			$("#stateTip").text("Invalid State Name").css("color","red");
			$("input[name='state']").css("border","1px solid red");
		}
	});
	
	//Zip
	$("input[name='zip']").focus(function(event) {
		$("#zipTip").text("Only numbers are supported.").css({
			"color": "green"
		});
	});
	$("input[name='zip']").blur(function(event) {
		var pattern = /^[0-9]{1,10}$/;
		var zip = $("input[name='zip']").val();
		if(pattern.test(zip))
		{
			zip_isValid = true;
			$("#zipTip").empty();
			$("input[name='zip']").css("border","1px solid green");
			checkValidation();
		}
		else
		{
			zip_isValid = false;
			$("#zipTip").text("Invalid Zip Number").css("color","red");
			$("input[name='zip']").css("border","1px solid red");
		}		

	});

	//Telephone Home
	$("input[name='telHome']").focus(function(event) {
		$("#telHomeTip").text("Only numbers are supported.").css({
			"color": "green"
		});
	});
	$("input[name='telHome']").blur(function(event) {
		var pattern = /^[0-9]{1,10}$/;
		var telHome = $("input[name='telHome']").val();
		if(pattern.test(telHome))
		{
			telHome_isValid = true;
			$("#telHomeTip").empty();
			$("input[name='telHome']").css("border","1px solid green");
			checkValidation();
		}
		else
		{
			telHome_isValid = false;
			$("#telHomeTip").text("Invalid Tel-Home Number").css("color","red");
			$("input[name='telHome']").css("border","1px solid red");
		}		

	});

	
});