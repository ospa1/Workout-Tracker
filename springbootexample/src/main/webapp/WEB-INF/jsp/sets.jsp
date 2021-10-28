<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sets</title>
</head>
<body>
	<h1 class="text-center">Sets</h1>
	<div class="container">
		<form class="form-inline text-center" method="GET">
			<button type="submit" class="btn btn-primary" formaction="/createSet">+
				Add New Set</button>
		</form>
	</div>
	<c:choose>
		<c:when test="${empty sets}">
			<div class="container">
				<span class="badge " style='color: black'>No Results Found</span> <br>
			</div>
		</c:when>
		<c:when test="${not empty sets}">
			<div class="container">
			<c:set var="count" value="1" scope="page" />
				<div class="table-responsive">
				<table class="table table-hover">
					<thead class="thead-dark">
						<tr>
							<th scope="col">#</th>
							<th scope="col">Reps</th>
							<th scope="col">Lbs</th>
							<th scope="col">Date</th>
							<th scope="col">Edit</th>
							<th scope="col">Delete</th>
						</tr>
					</thead>
					<c:forEach items="${sets}" var="set">
					<tbody>
						<tr>
							<th scope="row">${count}</th>
							<td>${set.getReps()}</td>
							<td>${set.getWeight()}</td>
							<td>${set.getDate()}</td>
							<td>
								<form method="GET">
									<input type="hidden" name="id" value="${set.getId()}">
									<input type="hidden" name="exerciseId" value="${exerciseId}">
									<button class="btn btn-primary" type="submit"
										formaction="/updateSet">Edit</button>
								</form>
							</td>
							<td>
								<form method="POST" action="/deleteSet">
									<input type="hidden" name="id" value="${set.getId()}">
									<input type="hidden" name="exerciseId" value="${exerciseId}">
									<input type="hidden" name="_method" value="DELETE">
									<button type="submit" class="btn btn-danger">Delete</button>
								</form>
							</td>
						</tr>
					</tbody>
					<c:set var="count" value="${count + 1}" scope="page"/>
					</c:forEach>
				</table>
			</div>
			</div>
		</c:when>
	</c:choose>
</body>
</html>

<!-- <div class="row">
						<div class="col-md-1">
							<h3>${count}</h3>
						</div>
						<div class="col-md-3">
							<h3>${set.getReps()} reps</h3>
							<h3>${set.getWeight()} lbs</h3>
						</div>
						<div class="col-md-3">
							<h3>${set.getDate()}</h3>
						</div>
						<div class="col-md-4">
							<div class="row">
								<div class="col-md-4">
									<form method="GET">
										<input type="hidden" name="id" value="${set.getId()}">
										<input type="hidden" name="exerciseId" value="${exerciseId}">
										<button class="btn btn-primary" type="submit"
											formaction="/updateSet">Modify</button>
									</form>
								</div>
								<div class="col-md-4">
									<form method="POST" action="/deleteSet">
										<input type="hidden" name="id" value="${set.getId()}">
										<input type="hidden" name="exerciseId" value="${exerciseId}">
										<input type="hidden" name="_method" value="DELETE">
										<button type="submit" class="btn btn-danger">Delete</button>
									</form>
								</div>
							</div>
						</div>
					</div>
					<hr> -->