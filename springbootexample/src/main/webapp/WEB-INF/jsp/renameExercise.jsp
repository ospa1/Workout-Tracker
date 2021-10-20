<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>rename exercise</title>
</head>
<body>
	<h1>Change Exercise Name</h1>
	<c:choose>
		<c:when test="${empty exercise}">
			<div class="container">
				<span class="badge " style='color: black'>this exercise does
					not exist</span> <br>
			</div>
		</c:when>
		<c:when test="${not empty exercise}">
			<div class="container">
				<div class="card">
					<div class="card-body">
						<h4 class="card-title">${exercise.getName()}</h4>
						<form method="POST">
							<input type="hidden" name="id" value="${id}"> <input
								type="text" name="name" placeholder="Enter a new name"></input>
							<button type="submit" formaction="/renameExercise">rename</button>
						</form>
					</div>
				</div>
			</div>
		</c:when>
	</c:choose>
</body>