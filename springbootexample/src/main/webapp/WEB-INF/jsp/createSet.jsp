<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/setStyle.css" />
<script type="text/javascript" src="scripts/setJS.js"></script>
<title>Create Set</title>
</head>
<body>
	<div class="container">
		<h1>Create Set</h1>
		<p>You can enter your set information here</p>
		<hr />
	</div>

	<div class="container d-flex justify-content-center" id="set">
		<form class="form-inline" id="form" method="POST"
			style="margin-top: 40px">
			<input type="hidden" name="exerciseId" value="${exerciseId}">
			<input type="number" class="form-control mb-2 mr-sm-2" id="weight"
				name="weight" placeholder="Lbs"></input> <input type="number"
				class="form-control mb-2 mr-sm-2" id="reps" name="reps"
				placeholder="Reps"></input>
			<button type="submit" class="btn btn-primary mb-2" onclick=""
				id="button">Save</button>
		</form>
	</div>
</body>
</html>