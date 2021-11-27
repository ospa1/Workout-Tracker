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
<title>New Video</title>
</head>
<body>
	<div class="container">
		<h1>New Video </h1>
		<p>Enter video info here</p>
		<hr />
	</div>

	<div class="container d-flex justify-content-center" id="video">
		<form class="form-inline" id="form" method="POST"
			style="margin-top: 40px">
			<input type="hidden" name="id" value="${video.getId()}">
			<input class="form-control mb-2 mr-sm-2" id="name"
				name="name" placeholder="name" value="${video.getName()}"></input> 
			<input class="form-control mb-2 mr-sm-2" id="url" name="url"
				placeholder="url" value="${video.getUrl()}"></input>
			<button type="submit" class="btn btn-primary mb-2" onclick=""
				id="button">Save</button>
		</form>
	</div>
</body>
</html>