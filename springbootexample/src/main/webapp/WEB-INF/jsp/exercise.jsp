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
	<div class="container text-center">
		<h1>Exercises</h1>
		<br>
		<button class="btn btn-primary" onclick="showForm()">+ New
			Exercise</button>
		<div id="myDIV" style="display: none">
			<br>
			<form class="form-inline text-center" method="POST">
				<div class="form-group mx-sm-3 mb-2">
					<input type="text" class="form-control" id="newExercise"
						name="name" placeholder="Exercise Name">
				</div>
				<button type="submit" class="btn btn-primary mb-2">Submit</button>
			</form>
		</div>
		<hr>
	</div>
	<c:choose>
		<c:when test="${empty exercises}">
			<div class="container">
				<span class="badge " style='color: black'>No Results Found</span> <br>
			</div>
		</c:when>
		<c:when test="${not empty exercises}">
			<div class="container">
			<c:forEach items="${exercises}" var="exercise">
					<div class="card bg-dark border-light text-white">
						<div class="card-body d-inline">
							<div class="row">
								<div class="col-xs-12 col-md-8 font-weight-bolder h4">${exercise.getName()}</div>
								<div class="col-xs-6 col-md-4 text-right font-weight-bolder h4">Sets:
									${exercise.getSets().size()}</div>
							</div>
						</div>
						<div class="card-footer">
							<form class="form-inline" method="POST">
								<input type="hidden" name="id" value="${id}"> <a
									href="/getSets?exerciseId=${exercise.getId()}"
									class="card-link text-primary btn">View</a>
								<button class="btn btn-primary" type="submit"
									formaction="/updateExercise/${exercise.getId()}">Rename</button>
								<button class="btn btn-danger" type="submit"
									formaction="/deleteExercise/${exercise.getId()}">Delete</button>
							</form>
						</div>
					</div>
				<br>
			</c:forEach>
			</div>
		</c:when>
	</c:choose>
</body>
<script type="text/javascript">
	function showForm() {
		var x = document.getElementById("myDIV");
		if (x.style.display === "none") {
			x.style.display = "block";
		} else {
			x.style.display = "none";
		}
	}
</script>
</html>