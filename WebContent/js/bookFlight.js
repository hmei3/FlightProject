$(document).ready(function($) {

	$(function hideBtn(){
		$("button[name='btn-buy']").css("visibility","hidden");
		$("button[name='btn-cancel']").css("visibility","hidden");

	});
	
	$(function hideHead(){
		$("#h5-classA").hide();
		$("#h5-classB").hide();
		$("#h5-classC").hide();

	});
	//Ticket No A Validation
	$("input[name='ticketNoA']").keyup(function(event) {
		$("input[name='ticketNoA']").val($("input[name='ticketNoA']").val().replace(/[^0-9]/g, ''));
		$("input[name='ticketNoA']").val($("input[name='ticketNoA']").val().replace(/^0+/, ''));
	});
	$("input[name='ticketNoA']").blur(function(event) {
		if($("input[name='ticketNoA']").val() === "")
		{
			$("#Amsg").empty();
			return;
		}

		console.log(111);
		var upperBound = parseInt($("#seatAp").text().substring(8));
		var ticketNoA = parseInt($("input[name='ticketNoA']").val());
		if(ticketNoA > upperBound)
		{
			$("#Amsg").text("Value is larger than totoal ticket remaining").css("color","red");
		}
		else
		{
			$("#Amsg").empty();

		}
	});
	//Ticket No B Validation

	$("input[name='ticketNoB']").keyup(function(event) {
		$("input[name='ticketNoB']").val($("input[name='ticketNoB']").val().replace(/[^0-9]/g, ''));
		$("input[name='ticketNoB']").val($("input[name='ticketNoB']").val().replace(/^0+/, ''));
	});
	$("input[name='ticketNoB']").blur(function(event) {
		if($("input[name='ticketNoB']").val() === "")
		{
			$("#Bmsg").empty();
			return;
		}
		var upperBound = parseInt($("#seatBp").text().substring(8));
		var ticketNoB = parseInt($("input[name='ticketNoB']").val());
		if(ticketNoB > upperBound)
		{
			$("#Bmsg").text("Value is larger than totoal ticket remaining").css("color","red");
		}
		else
		{
			$("#Bmsg").empty();

		}
	});
	//Ticket No C Validation

	$("input[name='ticketNoC']").keyup(function(event) {
		$("input[name='ticketNoC']").val($("input[name='ticketNoC']").val().replace(/[^0-9]/g, ''));
		$("input[name='ticketNoC']").val($("input[name='ticketNoC']").val().replace(/^0+/, ''));
	});
	$("input[name='ticketNoC']").blur(function(event) {
		if($("input[name='ticketNoC']").val() === "")
		{
			$("#Cmsg").empty();
			return;
		}
		var upperBound = parseInt($("#seatCp").text().substring(8));
		var ticketNoC = parseInt($("input[name='ticketNoC']").val());
		if(ticketNoC > upperBound)
		{
			$("#Cmsg").text("Value is larger than totoal ticket remaining").css("color","red");
		}
		else
		{
			$("#Cmsg").empty();

		}
	});
	$("button[name='btn-cancel']").click(function(event) {
		location.reload();
	});
	
	$("input[name='btn-confirm']").click(function(event) {
		if(($("input[name='ticketNoA']").val() === "")&&
				($("input[name='ticketNoB']").val() === "")&&
				($("input[name='ticketNoC']").val() === ""))
		{
			return;
		}
		else if(($("#Amsg").text() === "")&&($("#Bmsg").text() === "")&&($("#Cmsg").text() === ""))
		{
			var ticketNoA = $("input[name='ticketNoA']").val();
			var ticketNoB = $("input[name='ticketNoB']").val();
			var ticketNoC = $("input[name='ticketNoC']").val();
			
			$("input[name='ticketNoA']").attr('readonly', true);
			$("input[name='ticketNoB']").attr('readonly', true);
			$("input[name='ticketNoC']").attr('readonly', true);
			
			if($("input[name='ticketNoA']").val() !== ""){
				$("#h5-classA").show();
			}
			for (var i = 0; i < ticketNoA; i++) {
				$("<div style='display:inline-block'><label>Passenger Name " + (i + 1) + ":  </label><br>" +
						"<input type= 'text' class='checkvalue' name='classAname" + i +"'></div>").appendTo("#classAnameField");
			}
			
			if($("input[name='ticketNoB']").val() !== ""){
				$("#h5-classB").show();
			}
			for (var j = 0; j < ticketNoB; j++) {
				$("<div style='display:inline-block'><label>Passenger Name " + (j + 1) + ":  </label><br>" +
						"<input type= 'text' class='checkvalue' name='classBname" + j +"'></div>").appendTo("#classBnameField");
			}
			
			if($("input[name='ticketNoC']").val() !== ""){
				$("#h5-classC").show();
			}
			for (let k = 0; k < ticketNoC; k++) {
				$("<div style='display:inline-block'><label>Passenger Name " + (k + 1) + ":  </label><br>" +
						"<input type= 'text' class='checkvalue' name='classCname" + k +"'></div>").appendTo("#classCnameField");
			}
			$("input[name='btn-confirm']").hide();
			$("button[name='btn-buy']").css("visibility","visible");
			$("button[name='btn-cancel']").css("visibility","visible");
		}
	});
	
	$("button[name='btn-buy']").click(function(event) {
		var pattern =/^(?=.{1,50}$)[a-zA-Z]+(\s[a-zA-Z]+)*$/;
		console.log("buy button clicked");
		$(".checkvalue").each(function(){
			if(pattern.test(this.value) === false)
			{
				return;
			}
			$("#submit-names").submit();
		});
	});
});