
function validate(){
	
	let validWeight = validateField("weight");
	let validReps = validateField("reps");
	return validWeight && validReps;
}

$(document).ready(function(){
	$("#delete").click(function(){
		$('.toast').toast('show');
	});
});

function validateField(field){
	
	let value = document.getElementById(field).value;
	
	if(Number.isInteger(parseInt(value))){
		return true;
	}
	else{
		alert("the input field is incorrect");
		return false;
	}
}