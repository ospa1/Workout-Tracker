<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Stats</title>
</head>
<body>
	<div class="container" id="header">
		<h1 style="font-family: 'Anton', sans-serif;">Workout Stats</h1>
		<hr />
	</div>

	<div class="container">
		<div class="card text-center bg-dark text-white">
			<div class="card-body">
				<h1 class="card-title">Total Volume</h1>
				<h3 class="card-text">${total}Lbs</h3>
			</div>
		</div>
		<br>
		<div class="card text-center bg-dark text-white">
			<div class="card-body">
				<h1 class="card-title">Days Lifted</h1>
				<h3 class="card-text">${days}Days</h3>
			</div>
		</div>
	</div>
	<br>

	<c:choose>
		<c:when test="${empty exercises}">
			<div class="container">
				<span class="badge " style='color: black'>No Results Found</span> <br>
			</div>
		</c:when>
		<c:when test="${not empty exercises}">
			<div class="container" id="tableContainer">
				<table class="table table-hover" id="exerciseTable">
					<thead class="thead-dark">
						<tr>
							<th scope="col">Id</th>
							<th scope="col">Exercise</th>
							<th scope="col">Volume</th>
							<th scope="col">Average</th>
						</tr>
					</thead>
					<c:set var="count" value="0" scope="page" />
					<tbody>
						<c:forEach items="${exercises}" var="exercise">
							<tr>
								<th scope="row">${exercise.getId()}</th>
								<td>${exercise.getName()}</td>
								<td>${totals.get(Integer.parseInt(count))}</td>
								<td>${averages.get(Integer.parseInt(count))}</td>
							</tr>
							<c:set var="count" value="${count + 1}" scope="page" />
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