
function validate(){
	
	let weight = document.getElementById("weight").value;
	let reps = document.getElementById("reps").value;
	
	if(Number.isInteger(parseInt(weight)) && Number.isInteger(parseInt(reps))){
		alert("volume: " + weight * reps + " lbs");
		return true;
	}
	else{
		alert("the input field is incorrect");
		return false;
	}
}

$(document).ready(function(){
	$("#delete").click(function(){
		$('.toast').toast('show');
	});
	
	
});