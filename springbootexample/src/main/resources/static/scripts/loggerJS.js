//for logger.html

//add dummy values to table
$(document).ready(function() {
	let rows = 1;
	let names = ["Squat", "Deadlift", "Squat"];
	let dates = ["2021 Sept 10", "2021 Sept 12", "2021 Sept 14"];
	let volumes = ["5000","4500","5100"];
	
	for(let i = 0; i < names.length; i++){
		$('#exerciseTable').append('\
		<tr>\
			<th scope="row">'+ rows +'</th>\
			<td>'+ names[i] +'</td>\
			<td>'+ dates[i] +'</td>\
			<td>'+ volumes[i] +'</td>\
		</tr>');
		rows++;
	}
});