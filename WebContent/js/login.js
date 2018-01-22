$(document).ready(function() {
	$("#userTypeSelect").change(function (event) {
	  if ($(this).val() === 'customer')
	  {
	  	$("#userNameLabel").text("Email");
	  	$("#userNameInput").attr("placeholder", "Email");
	  }
	  else if($(this).val() === 'admin')
	  {
		$("#userNameLabel").text("UserName");
		$("#userNameInput").attr("placeholder", "User Name");
	  }
	});
});