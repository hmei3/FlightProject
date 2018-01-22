$(document).ready(function(){
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
});