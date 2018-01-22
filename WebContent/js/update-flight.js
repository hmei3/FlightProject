$(document).ready(function() {
	$(function(){
		var now = new Date();
		var years = now.getFullYear();
		var month = now.getMonth() + 1;
		var date = now.getDate();
		if(month < 10)
		{
			month = "0" + month;
		}
		if(date < 10)
		{
			date = "0" + date;
		}
		var min = years + "-" + month + "-" + date + "T00:00:00";
		var max = years + 5 + "-" + month + "-" + date + "T00:00:00";

		$("#search-deptime").attr({
			"min": min,
			"max": max
		});
		$("#deptime").attr({
			"min": min,
			"max": max
		});
		$("#arrtime").attr({
			"min": min,
			"max": max
		});
	});
	
	$("#search-flightname").focus(function(event) {
		$("#search-flightname").attr({
			"title": 'Start with more than 2 to 4 Capital letters and followed with 4 digits.'
		});
	});
	
	//DepCode
	$("#search-depcode").focus(function(event) {
		$("#search-depcode").attr({
			"title": '3 Capital letters'
		});
	});
	$("#search-depcode").focus(function(event) {
		$("#search-depcode").attr({
			"title": '3 Capital letters'
		});
	});

	
	$("#search-flight-btn").click(function(event) {
		var flightName_Valid = false;
		var depcode_Valid = false;
		var deptime_Valid = false;

		var flightName = $("#search-flightname").val();
		var depcode = $("#search-depcode").val();
		var deptime= $("#search-deptime").val();
		var flightNamePattern = /^[A-Z]{2,4}[0-9]{4}$/;
		var depcodePattern = /^[a-zA-Z]{3}$/;

		if(flightNamePattern.test(flightName) === false)
		{

			$("#search-flightname-valid-msg").text("Please input correct flight name.").css("color","red");
			flightName_Valid = false;
		}
		else
		{

			$("#search-flightname-valid-msg").empty();
			flightName_Valid = true;
		}
//deptime
		if(deptime === "")
		{
			$("#search-deptime-valid-msg").text("Please input correct departure time.").css("color","red");
			deptime_Valid = false;
		}
		else
		{
			let array = deptime.split("T");

			let date = array[0].split("-");

			let time = (array[1].split("."))[0].split(":");
			
			if(new Date(date[0], date[1] - 1, date[2], time[0], time[1], "0") < new Date())
			{
				$("#search-deptime-valid-msg").text("Please input correct departure time.").css("color","red");
				deptime_Valid = false;
				
			}
			else
			{	
				$("#search-deptime-valid-msg").empty();
				deptime_Valid = true;
			}
		}			
			
//depcode		
		if(depcodePattern.test(depcode) === false)
		{

			$("#search-depcode-valid-msg").text("Only 3 Letters Accepted.").css("color","red");
			depcode_Valid = false;
		}
		else
		{

			$("#search-depcode-valid-msg").empty();
			$("#search-depcode").val(depcode.toUpperCase());
			depcode_Valid = true;
		}
		
		if(depcode_Valid && deptime_Valid && flightName_Valid === true)
		{

			$("#search-flight-form").submit();
		}
	});
	
	
//-------------------------------
	var flightname_valid_result = true;
	var deptime_valid_result = true;
	var depcode_valid_result = true;

	var arrcode_valid_result = true;
	var arrtime_valid_result = true;

//Flight Name
	$("#flightname").focus(function(event) {
		$("#flightname").attr({
			"title": 'Start with more than 2 to 4 Capital letters and followed with 4 digits.'
		});
	});

	$("#flightname").blur(function(event) {
		/* Act on the event */
		var input = $("#flightname").val();
		var pattern = /^[A-Z]{2,4}[0-9]{4}$/;
		if(input === "")
		{
			$("#flightname-valid-msg").empty();
			flightname_valid_result = false;
			return;
		}
		if(pattern.test(input))
		{
			flightname_valid_result = true;
			$("#flightname-valid-msg").empty();
		}
		else
		{
			$("#flightname-valid-msg").text("Please input correct flight name.").css("color","red");
			flightname_valid_result = false;
		};
	});

//DepTime
		$("#deptime").blur(function(){
		var deptime = $("#deptime").val();
		if(deptime === "")
		{
			deptime_valid_result = false;
			return;
		}
		var array = deptime.split("T");
		var date = array[0].split("-");
		var time = (array[1].split("."))[0].split(":");
		
		if(new Date(date[0], date[1] - 1, date[2], time[0], time[1], "0") < new Date())
		{
			$("#deptime-valid-msg").text("Please input correct departure time.").css("color","red");
			deptime_valid_result = false;
			
		}
		else
		{	
			$("#deptime-valid-msg").empty();
			deptime_valid_result = true;
		}
	});
	
	$("#deptime").change(function(){
		$("#arrtime").val("");
		$("#arrtime-valid-msg").empty();
		arrtime_valid_result = false;
		
	});
//DepCode
	$("#depcode").focus(function(event) {
		$("#depcode").attr({
			"title": '3 Capital letters'
		});
	});

	$("#depcode").blur(function(event) {
		
		var input = $("#depcode").val();
		if(input === "")
		{
			$("#depcode-valid-msg").empty();
			depcode_valid_result = false;
			return;
		}
		var pattern = /^[a-zA-Z]{3}$/;
		if(pattern.test(input))
		{
			$("#depcode").val(input.toUpperCase());
			$("#depcode-valid-msg").empty();
			depcode_valid_result = true;
		}
		else
		{
			$("#depcode-valid-msg").text("Only 3 Letters Accepted.").css("color","red");
			depcode_valid_result = false;
		}
	});
//ArrTime
	
	$("#arrtime").blur(function(event) {
		var arrtime = $("#arrtime").val();
		if(arrtime === "")
		{
			arrtime_valid_result = false;
			return;
		}
		var deptime = $("#deptime").val();
		var darray = deptime.split("T");
		var ddate = darray[0].split("-");
		var dtime = (darray[1].split("."))[0].split(":");

		var arrtime = $("#arrtime").val();
		var aarray = arrtime.split("T");
		var adate = aarray[0].split("-");
		var atime = (aarray[1].split("."))[0].split(":");
		
		if(new Date(adate[0], adate[1] - 1, adate[2], atime[0], atime[1], "0") 
			<= new Date(ddate[0], ddate[1] - 1, ddate[2], dtime[0], dtime[1], "0"))
		{
			$("#arrtime-valid-msg").text("Invalid Arrival Time").css("color","red");
			arrtime_valid_result = false;
		}
		else
		{
			$("#arrtime-valid-msg").empty();
			arrtime_valid_result = true;
		}

	});

//ArrCode
	$("#arrcode").focus(function(event) {
		$("#arrcode").attr({
			"title": '3 Capital letters'
		});
	});

	$("#arrcode").blur(function(event) {
		
		var input = $("#arrcode").val();
		if(input === "")
		{
			$("#arrcode-valid-msg").empty();
			arrcode_valid_result = false;
			return;
		}
		var pattern = /^[a-zA-Z]{3}$/;
		if(pattern.test(input))
		{
			$("#arrcode").val(input.toUpperCase());
			$("#arrcode-valid-msg").empty();
			arrcode_valid_result = true;
		}
		else
		{
			$("#arrcode-valid-msg").text("Only 3 Letters Accepted.").css("color","red");
			arrcode_valid_result = false;
		}
	});
	
	$("#update-flight-btn").click(function(event) {
		if(flightname_valid_result&&deptime_valid_result&&depcode_valid_result
			&&arrtime_valid_result&&arrcode_valid_result === true)
		{
			console.log(1);
			$("#update-form").submit();
		}
		else
		{
			console.log(2);
			$("#add-flight-msg").text("Please correct invalid fields.").css("color","red");
		}
	});
	
	
	
});