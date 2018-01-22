$(document).ready(function(){
	
	$("#search-btn").click(function(){
		$.validator.addMethod('validatePlaceInput', function(value, element){
			return this.optional(element) ||/^(?=.{1,50}$)[a-zA-Z]+(\s[a-zA-Z]+)*$/.test(value)},
			`Only Letters and spaces. No continuous spaces are allowed!`);
			});
	$("#search-flight-form").validate();
	
	$("#search-flight-form").validate({
				
				rules:{
					from: 
					{
						required: true,
						validatePlaceInput: true
					},
					to: 
					{
						required: true,
						validatePlaceInput: true			
					},
					depdate: 
					{
						required: true
					}
				},
				
				messages:
				{
					
					from: 
					{
						required: "Please input this Field",
						validatePlaceInput: "Please input valid 3 letters Airport code or City name"
					},
					to: 
					{
						required: "Please input this Field",
						validatePlaceInput: "Please input valid 3 letters Airport code or City name"
					},
					depdate: 
					{
						required: "Please input the departure date"
					}
				},
		        submitHandler: function(form) {
		            alert('You have submitted the form successfully');
		        }
			});
});