$(document).ready(function() {

	var flightname_valid_result = false;
	var deptime_valid_result = false;
	var depcode_valid_result = false;

	var arrcode_valid_result = false;
	var arrtime_valid_result = false;

	var seatA_valid_result = false;
	var seatB_valid_result = false;
	var seatC_valid_result = false;


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
		console.log(min);
		$("#deptime").attr({
			"min": min,
			"max": max
		});
		$("#arrtime").attr({
			"min": min,
			"max": max
		});
		$("#arrtime").attr("readonly","true");
	});
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
			$("#arrtime").val("");
			$("#arrtime").attr("readonly","true");
			$("#arrtime-valid-msg").empty();
			
		}
		else
		{	
			$("#arrtime").prop("readonly",false);
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
//Class A Seat
	$("#seatAamount").focus(function(event) {
		$("#seatAamount").attr({
			"title": 'Only accept no more than digits. Seat amount should be fewer than 200.'
		});
	});

	$("#seatAamount").keyup(function(event) {
		$("#seatAamount").val($("#seatAamount").val().replace(/[^0-9]/g, ''));
		$("#seatAamount").val($("#seatAamount").val().replace(/^0+/, ''));
	});

	$("#seatAamount").blur(function(event) {
		var seatAamount = $("#seatAamount").val();
		if(seatAamount > 200)
		{
			$("#seatA-valid-msg").text("Give a number less than 200").css("color","red");
			seatA_valid_result = false;
		}
		else
		{
			$("#seatA-valid-msg").empty();
			seatA_valid_result = true;
		}
	});
//Class B Seat

	$("#seatBamount").focus(function(event) {
		$("#seatBamount").attr({
			"title": 'Only accept no more than digits. Seat amount should be fewer than 200.'
		});
	});

	$("#seatBamount").keyup(function(event) {
		$("#seatBamount").val($("#seatBamount").val().replace(/[^0-9]/g, ''));
		$("#seatBamount").val($("#seatBamount").val().replace(/^0+/, ''));
	});

	$("#seatBamount").blur(function(event) {
		var seatBamount = $("#seatBamount").val();
		if(seatBamount > 200)
		{
			$("#seatB-valid-msg").text("Give a number less than 200.").css("color","red");
			seatB_valid_result = false;
		}
		else
		{
			$("#seatB-valid-msg").empty();
			seatB_valid_result = true;
		}
	});
//Class C Seat

	$("#seatCamount").focus(function(event) {
		$("#seatCamount").attr({
			"title": 'Only accept no more than digits. Seat amount should be fewer than 200.'
		});
	});

	$("#seatCamount").keyup(function(event) {
		$("#seatCamount").val($("#seatCamount").val().replace(/[^0-9]/g, ''));
		$("#seatCamount").val($("#seatCamount").val().replace(/^0+/, ''));
	});

	$("#seatCamount").blur(function(event) {
		var seatCamount = $("#seatCamount").val();
		if(seatCamount > 200)
		{
			$("#seatC-valid-msg").text("Give a number less than 200.").css("color","red");
			seatC_valid_result = false;
		}
		else
		{
			$("#seatC-valid-msg").empty();
			seatC_valid_result = true;
		}
	});

	$("#add-flight-btn").click(function(event) {
		if(flightname_valid_result&&deptime_valid_result&&depcode_valid_result
			&&arrtime_valid_result&&arrcode_valid_result&&seatA_valid_result
			&&seatB_valid_result&&seatC_valid_result === true)
		{
			$("#add-flight-form").submit();
		}
		else
		{
			$("#add-flight-msg").text("Please correct invalid fields.").css("color","red");
		}
	});



});