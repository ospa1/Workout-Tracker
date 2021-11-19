
function validate(){
	
	let validWeight = validateField("weight");
	let validReps = validateField("reps");
	return validWeight && validReps;
}

function validateField(field){
	
	let element = document.getElementById(field);
	let value = element.value;
	
	if(Number.isInteger(parseInt(value))){
		return true;
	}
	else{
		alert("the field is incorrect");
		return false;
	}
}