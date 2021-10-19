<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>History</title>
</head>
<body>
	<div class="container" id="header">
		<h1 style="font-family: 'Anton', sans-serif;">Workout History</h1>
		<hr />
	</div>

	<c:choose>
		<c:when test="${empty sets}">
			<div class="container">
				<span class="badge " style='color: black'>No Results Found</span> <br>
			</div>
		</c:when>
		<c:when test="${not empty sets}">
			<div class="container" id="tableContainer">
				<table class="table table-hover" id="exerciseTable">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Set ID</th>
							<th scope="col">Exercise</th>
							<th scope="col">Date</th>
							<th scope="col">Reps</th>
							<th scope="col">Weight</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${sets}" var="set">
							<tr>
								<th scope="row">${set.getId()}</th>
								<td>${set.getExercise().getName()}</td>
								<td>${set.getDate()}</td>
								<td>${set.getReps()}</td>
								<td>${set.getWeight()}</td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</c:when>
	</c:choose>
</body>
<style>
table, th, td {
	border: 1px solid black;
}

#header {
	margin-top: 20px;
}
</style>
</html>