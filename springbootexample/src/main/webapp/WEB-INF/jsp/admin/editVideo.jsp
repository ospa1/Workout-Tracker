<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../navbar.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/setStyle.css" />
<script type="text/javascript" src="scripts/setJS.js"></script>
<title>Edit Video</title>
</head>
<body>
	<div class="container">
		<h1>Edit Video </h1>
		<p>Modify video info here</p>
		<hr />
	</div>

	<div class="container d-flex justify-content-center" id="video">
		<form class="form-inline" id="form" method="POST"
			style="margin-top: 40px">
			<input type="hidden" name="id" value="${id}">
			<input class="form-control mb-2 mr-sm-2" id="name"
				name="name" placeholder="name"></input> 
			<input class="form-control mb-2 mr-sm-2" id="url" name="url"
				placeholder="url"></input>
			<button type="submit" class="btn btn-primary mb-2" onclick=""
				id="button">Save</button>
		</form>
	</div>
</body>
</html>