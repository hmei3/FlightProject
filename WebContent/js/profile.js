$(document).ready(function() {
	
	
	var email_isValid = true;
	var password_isValid = true;
	var firstName_isValid = true;
	var lastName_isValid = true;
	var dateOfBirth_isValid = true;
	var street_isValid = true;
	var streetNo_isValid = true;
	var aptNo_isValid = true;
	var city_isValid = true;
	var state_isValid = true;
	var zip_isValid = true;
	var telHome_isValid = true;
	
	function checkValidation(){
		return email_isValid&&password_isValid&& firstName_isValid&&lastName_isValid
		&&dateOfBirth_isValid&&streetNo_isValid&&street_isValid&&aptNo_isValid
		&&city_isValid&&state_isValid&&zip_isValid&&telHome_isValid;
	}
	
//First Name	
	$("#firstNameP").dblclick(function(event) {
		$("#firstNameP").hide();
		$("input[name='firstNameInput']").attr({
				"type":"text",
				"title":"Only letters are supported. Length should be 2-30."
			})
		.val($("#firstNameP").text().trim()).focus();
	});

	$("input[name='firstNameInput']").blur(function(event) {
		
		if($("input[name='firstNameInput']").val() === "")
		{

			firstName_isValid = false;
		}
		else
		{
			$("input[name='firstNameInput']").attr("type","hidden");
			$("#firstNameP").show();
			var firstName = $("input[name='firstNameInput']").val().trim();
			firstName = firstName.substring(0,1).toUpperCase() + firstName.substring(1).toLowerCase();
			$("#firstNameP").text(firstName);
			
			var pattern = /^[a-zA-Z]{2,30}$/;

			if(pattern.test(firstName))
			{
				firstName_isValid = true;
				$("#firstNameP").css("color","black");
			}
			else
			{
				firstName_isValid = false;
				$("#firstNameP").css("color","red");
			}
		}
	});

//Last Name
	$("#lastNameP").dblclick(function(event) {
		$("#lastNameP").hide();
		$("input[name='lastNameInput']").attr({
			"type":"text",
			"title":"Only letters are supported. Length should be 2-30."
		})
		.val($("#lastNameP").text().trim()).focus();
	});

	$("input[name='lastNameInput']").blur(function(event) {
		
		if($("input[name='lastNameInput']").val() === "")
		{
			lastName_isValid = false;
		}
		else
		{
			$("input[name='lastNameInput']").attr("type","hidden");
			$("#lastNameP").show();
			var lastName = $("input[name='lastNameInput']").val().trim();
			lastName = lastName.substring(0,1).toUpperCase() + lastName.substring(1).toLowerCase();
			$("#lastNameP").text(lastName);
			
			var pattern = /^[a-zA-Z]{2,30}$/;

			if(pattern.test(lastName))
			{
				lastName_isValid = true;
				$("#lastNameP").css("color","black");
			}
			else
			{
				lastName_isValid = false;
				$("#lastNameP").css("color","red");
			}
		}
	});


//Email
//	$("#emailP").dblclick(function(event) {
//		$("#emailP").hide();
//		$("input[name='emailInput']")
//		.attr({
//			"type":"text",
//			"title":'First character should be a letter, ' + 'and no special characters are allowed except "-", "_"'
//			})
//		.val($("#emailP").text().trim()).focus();
//	});
//
//	$("input[name='emailInput']").blur(function(event) {
//		$("input[name='emailInput']").attr("type","hidden");
//		$("#emailP").show();
//		$("#emailP").text($("input[name='emailInput']").val().trim());
//		var pattern = /^(?=.{5,30}$)([\w][\w\d-_]+)@([\w]+)(\.[\w]{2,3}){1,2}$/;
//		var email = $("#emailP").text();
//		if(pattern.test(email))
//		{
//			email_isValid = true;
//			$("#emailP").css("color","black");
//		}
//		else
//		{
//			email_isValid = false;
//			$("#emailP").css("color","red");
//		}
//	});

//Password
	$("#passwordP").dblclick(function(event) {
		$("#passwordP").hide();
		$("#pwd-div").attr("display","inline-block");
		$("input[name='passwordInput']").attr({
			"type":"text",
			"title":'Length 5 - 20. It should contain ' + 
				'at least one lower case, one uppercase letter and one ' +
				'number. No special characters are supported.'
			}).focus();
	});

	$("input[name='passwordInput']").blur(function(event) {
		
		if($("input[name='passwordInput']").val() !== "")
		{
			$("input[name='passwordInput']").attr("type","hidden");
			$("#passwordP").show();
			var len = $("input[name='passwordInput']").val().length;
			var pwdInParagraph = "";
			for(let i = 0; i < len; i++)
			{
				pwdInParagraph += "*";
			}
			
			$("#passwordP").text(pwdInParagraph);
			
			var pattern = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])[a-zA-Z0-9]{5,20}$/;
			var password = $("input[name='passwordInput']").val();

			if(pattern.test(password))
			{
				password_isValid = true;
				$("#passwordP").css("color","black");
			}
			else
			{	
				password_isValid = false;
				$("#passwordP").css("color","red");
			}
		}
		else
		{
			password_isValid = false;
		}

	});


//Tel-Home
	$("#telHomeP").dblclick(function(event) {
		$("#telHomeP").hide();
		$("input[name='telHomeInput']").attr({
			"type":"text",
			"title":"Only numbers are supported. Length should be 1-20."}).val($("#telHomeP").text().trim()).focus();
	});

	$("input[name='telHomeInput']").blur(function(event) {
		if($("input[name='telHomeInput']").val() !== "")
		{
			$("input[name='telHomeInput']").attr("type","hidden");
			$("#telHomeP").show();
			$("#telHomeP").text($("input[name='telHomeInput']").val());
			
			var pattern = /^[0-9]{1,20}$/;
			var telHome = $("input[name='telHomeInput']").val();
			if(pattern.test(telHome))
			{
				telHome_isValid = true;
				$("#telHomeP").css("color","black");
			}
			else
			{
				telHome_isValid = false;
				$("#telHomeP").css("color","red");
			}
		}
		else
		{
			telHome_isValid = false;
		}
	});


//dateOfBirth
	$("#dateOfBirthP").dblclick(function(event) {
		$("#dateOfBirthP").hide();
		$("input[name='dateOfBirthInput']").attr("type","date").val($("#dateOfBirthP").text().trim()).focus();
	});

	$("input[name='dateOfBirthInput']").blur(function(event) {
		$("input[name='dateOfBirthInput']").attr("type","hidden");
		$("#dateOfBirthP").show();
		$("#dateOfBirthP").text($("input[name='dateOfBirthInput']").val());
	});


//streetNo
	$("#streetNoP").dblclick(function(event) {
		$("#streetNoP").hide();
		$("input[name='streetNoInput']")
		.attr({"type":"text",
			"title":"Only numbers are supported. Length should be 1-10."})
		.val($("#streetNoP").text().trim()).focus();
	});

	$("input[name='streetNoInput']").blur(function(event) {
		if($("input[name='streetNoInput']").val() !== "")
		{
			$("input[name='streetNoInput']").attr("type","hidden");
			$("#streetNoP").show();
			$("#streetNoP").text($("input[name='streetNoInput']").val());
			
			var pattern = /^[0-9]{1,10}$/;
			var streetNo = $("#streetNoP").text();
			if(pattern.test(streetNo))
			{
				streetNo_isValid = true;
				$("#streetNoP").css("color","black");
			}
			else
			{
				streetNo_isValid = false;
				$("#streetNoP").css("color","red");
			}
		}
		else
		{
			streetNo_isValid = false;
		}
	});
	
//streetName	
	$("#streetNameP").dblclick(function(event) {
		$("#streetNameP").hide();
		$("input[name='streetNameInput']")
		.attr({"type":"text",
			"title":"Only letters and whitespases are supported. Length should be 1-50."})
		.val($("#streetNameP").text().trim()).focus();
	});

	$("input[name='streetNameInput']").blur(function(event) {
		if($("input[name='streetNameInput']").val() !== "")
		{
			$("input[name='streetNameInput']").attr("type","hidden");
			$("#streetNameP").show();
			$("#streetNameP").text($("input[name='streetNameInput']").val().trim());
			
			var pattern = /^(?=.{1,50}$)[a-zA-Z]+(\s[a-zA-Z]+)*$/;
			var street = $("#streetNameP").text();
			if(pattern.test(street))
			{
				street_isValid = true;
				street = street.toLowerCase().split(' ');
			    for(var i = 0; i < street.length; i++){
			    	street[i] = street[i].charAt(0).toUpperCase() + street[i].substring(1);
			    }
			    street = street.join(' ');
				$("#streetNameP").css("color","black");
			}
			else
			{
				street_isValid = false;
				$("#streetNameP").css("color","red");
			}
		}
		else
		{
			street_isValid = false;
		}
	});
	
//aptNo	
	$("#aptNoP").dblclick(function(event) {
		$("#aptNoP").hide();
		var aptNo = $("#aptNoP").text().trim();
		var inputValue;
		if(aptNo === "Double click to edit")
		{
			inputValue = "";
		}
		else
		{
			inputValue = aptNo;
		}
		$("input[name='aptNoInput']").attr({"type":"text",
			"title":"Optional. Only numbers are supported and max length is 10."})
			.val(inputValue).focus();
	});

	$("input[name='aptNoInput']").blur(function(event) {
		$("input[name='aptNoInput']").attr("type","hidden");
		$("#aptNoP").show();
		var inputValue = $("input[name='aptNoInput']").val().trim();
		var aptNo;
		
		if(inputValue === "")
		{
			aptNo = "Double click to edit";
			$("#aptNoP").css({
				"color":"gray",
				"font-style":"Italic",
				"font-size":"14px"
			});
			aptNo_isValid = true;
		}
		else
		{
			var pattern = /^$|^[1-9][0-9]{0,9}$/;
			aptNo = inputValue;
			if(pattern.test(inputValue))
			{
				aptNo_isValid = true;
				$("#aptNoP").css({
					"color":"black",
					"font-style":"normal",
					"font-size":"14px"
				});
			}
			else
			{
				aptNo_isValid = false;
				$("#aptNoP").css({
					"color":"red",
					"font-style":"normal",
					"font-size":"14px"
				});
			}
		}
		$("#aptNoP").text(aptNo);
	});
	
//city	
	$("#cityP").dblclick(function(event) {
		$("#cityP").hide();
		$("input[name='cityInput']").attr({
			"type":"text",
			"title":"Only letters and spaces are supported! Length should be 1-50."
		}).val($("#cityP").text().trim()).focus();
	});

	$("input[name='cityInput']").blur(function(event) {
		
		if($("input[name='cityInput']").val() !== "")
		{
			$("input[name='cityInput']").attr("type","hidden");
			$("#cityP").show();
			$("#cityP").text($("input[name='cityInput']").val());
			
			var pattern = /^(?=.{1,50}$)[a-zA-Z]+(\s[a-zA-Z]+)*$/;
			var city = $("input[name='cityInput']").val();
			if(pattern.test(city))
			{
				
				city_isValid = true;
				city = city.toLowerCase().split(' ');
			    for(var i = 0; i < city.length; i++){
			    		city[i] = city[i].charAt(0).toUpperCase() + city[i].substring(1);
			    }
			     city = city.join(' ');
				$("#cityP").text(city).css("color","black");
			}
			else
			{
				city_isValid = false;
				$("#cityP").css("color","red");
			}			
		}
		else
		{
			city_isValid = false;
		}
	});
//state	
	$("#stateP").dblclick(function(event) {
		$("#stateP").hide();
		$("input[name='stateInput']").attr({
			"type":"text",
			"title":"Only letters are supported. Length should be 2-10."
				}).val($("#stateP").text().trim()).focus();
	});

	$("input[name='stateInput']").blur(function(event) {
		if($("input[name='stateInput']").val() !== "")
		{
			$("input[name='stateInput']").attr("type","hidden");
			$("#stateP").show();
			$("#stateP").text($("input[name='stateInput']").val().trim());
			
			
			var pattern = /^[a-zA-Z]{2,10}$/;
			var state = $("input[name='stateInput']").val().trim();
			if(pattern.test(state))
			{
				state_isValid = true;
				state = state.toUpperCase();
				$("#stateP").text(state).css("color","black");
			}
			else
			{
				state_isValid = false;
				$("#stateP").css("color","red");
			}
		}
		else
		{
			state_isValid = false;
		}
	});
	
	
	
	
//zip	
	$("#zipP").dblclick(function(event) {
		$("#zipP").hide();
		$("input[name='zipInput']").attr({
			"type":"text",
			"title":"Only numbers are supported. Length should be 1-10."
				}).val($("#zipP").text().trim()).focus();
	});

	$("input[name='zipInput']").blur(function(event) {
		if($("input[name='zipInput']").val() !== "")
		{
			$("input[name='zipInput']").attr("type","hidden");
			$("#zipP").show();
			$("#zipP").text($("input[name='zipInput']").val().trim());
			
			
			var pattern = /^[0-9]{1,10}$/;
			var zip = $("#zipP").text();
			if(pattern.test(zip))
			{
				zip_isValid = true;
				$("#zipP").css("color","black");
			}
			else
			{
				zip_isValid = false;
				$("#zipP").css("color","red");
			}
		}
		else
		{
			zip_isValid = false;
		}

	});
	
	
	
//Cancel Button	
	$("#cancelBtn").on("click", function(e){
	    e.preventDefault();
	    window.history.back();
	});
	
//Update Button
	$("form[name='form-prof']").change(function(event){
		$("#updateBtn").prop("disabled",false);
	});
	$("#updateBtn").click(function(event){
		console.log(email_isValid + " " + password_isValid + " " + firstName_isValid
				+ " " + lastName_isValid + " " + dateOfBirth_isValid + " " + street_isValid + " "
				+ streetNo_isValid + " " + aptNo_isValid + " " + city_isValid + " " + state_isValid
				+ " " + zip_isValid + " " + telHome_isValid);
		if(checkValidation())
		{
			$("form[name='form-prof']").submit();
		}
		else
		{
			$("#message").text("Please fill up the empty required fields and correct fields with red color first!")
			.css("color", "red");
		}
	});
	
//Load values to hidden inputs
	(function init(){
		$("input[name='firstNameInput']").val($("#firstNameP").text().trim());
		$("input[name='lastNameInput']").val($("#lastNameP").text().trim());
		$("input[name='telHomeInput']").val($("#telHomeP").text().trim());
		$("input[name='dateOfBirthInput']").val($("#dateOfBirthP").text().trim());
		$("input[name='streetNoInput']").val($("#streetNoP").text().trim());
		$("input[name='streetNameInput']").val($("#streetNameP").text().trim());
		console.log($("input[name='aptNoInput']").val());
		if($("#aptNoP").text().trim() !== "Double click to edit")
		{
			$("input[name='aptNoInput']").val($("#aptNoP").text().trim());
			console.log(1);
		}
		else
		{
			$("input[name='aptNoInput']").val("");
			console.log(2);
		}
		
		$("input[name='cityInput']").val($("#cityP").text().trim());
		$("input[name='stateInput']").val($("#stateP").text().trim());
		$("input[name='zipInput']").val($("#zipP").text().trim());
		
	})();

		
});