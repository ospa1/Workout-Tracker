<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Exercises</title>
</head>
<body>
	<h1>Exercises</h1>
	<c:choose>
	<c:when test="${empty exercises}">
		<div class="container">
			<span class="badge " style='color: black'>No Results Found</span>
			<br>
		</div>
	</c:when>
	<c:when test="${not empty exercises}">
		<c:forEach items="${exercises}" var="exercise">			
			<div class="container">
				<div class="card ">
					<div class="card-body">
					    <h4 class="card-title">${exercise.getName()}</h4>
					    <input type ="hidden" name="id" value="${id}">					   
					    <a href="/getSets?exerciseId=${exercise.getId()}" class="stretched-link"></a>
					</div>
				</div>
				<form method="POST"><button class="btn btn-primary" type="submit" formaction="/updateExercise/${exercise.getId()}">rename</button></form>
				<form method="POST"><button class="btn btn-primary" type="submit" formaction="/deleteExercise/${exercise.getId()}">delete</button></form>
			</div>
		</c:forEach>
	</c:when>
	</c:choose>
	<div id="form"></div>
	<button id="newExercise">New Exercise</button>
	
	 <button onclick="myFunction()">Click Me</button>

	<div id="myDIV" style="display: none">
	  	<form class="form-inline" method="POST">
			<div class="form-group mx-sm-3 mb-2">
		    <input type="text" class="form-control" id="newExercise" name="name" placeholder="Exercise Name">
		  	</div>
		 	 <button type="submit" class="btn btn-primary mb-2">Submit</button>
		 </form>
	</div> 
</body>
<script type="text/javascript">
	$(document).ready(function(){
		$("#newExercise").click(function(){
			$('#form').append('\
					<form class="form-inline" method="POST">\
					<div class="form-group mx-sm-3 mb-2">\
				    <input type="text" class="form-control" id="newExercise" name="name" placeholder="Exercise Name">\
				  </div>\
				  <button type="submit" class="btn btn-primary mb-2">Submit</button>\
				  </div>');
		});
	});
	
	function myFunction() {
		  var x = document.getElementById("myDIV");
		  if (x.style.display === "none") {
		    x.style.display = "block";
		  } else {
		    x.style.display = "none";
		  }
		} 
</script>
</html>