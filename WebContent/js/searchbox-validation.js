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
		var min = years + "-" + month + "-" + date;
		var max = years + 2 + "-" + month + "-" + date;
		console.log(min);
		$("#depdate-input").attr({
			"min": min,
			"max": max
		});
	});
	$("#depdate-input").blur(function(){
		var depdate = $("#depdate-input").val();
		var array = depdate.split("-");
		var now = new Date();

		if(new Date(array[0], array[1] - 1, array[2]) < now)
		{
			let value = "";
			value = value + now.getFullYear() + "-";
			if(now.getMonth() + 1 < 10)
			{
				value = value + "0" + (now.getMonth() + 1) + "-";
			}
			else
			{
				value = value + (now.getMonth() + 1) + "-";
			}
			if(now.getDate() < 10)
			{
				value = value + "0" + now.getDate();
			}
			else
			{
				value = value + now.getDate();
			}
			$("#depdate-input").val(value);
			
		}
	});
	
	$("#search-btn").click(function(event) {
		var fromValue = $("#from-input").val();
		var toValue = $("#to-input").val();
		var pattern = /^(?=.{1,50}$)[a-zA-Z]+(\s[a-zA-Z]+)*$/;
		
		if(pattern.test(fromValue)&&pattern.test(toValue)&&(fromValue.toUpperCase() !== toValue.toUpperCase()))
		{
			$("#search-flight-form").submit();
		}
		else
		{
			$("#error-p").text("Invalid input, please check!").css("color","red");
		}
	});
	
	
	
});