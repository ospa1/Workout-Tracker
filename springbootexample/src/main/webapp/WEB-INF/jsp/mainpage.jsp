<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<jsp:include page="navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Main</title>
</head>
<body>
	<c:if test="${not empty message}">
		<span style='color: red'>${message}</span>
	</c:if>

	<h1 class="text-center">Main</h1>
	<div>
		<c:if test="${not empty email}">
			<p style='color: blue' class="text-center">Signed in as, ${email}</p>
		</c:if>
	</div>
	<div class="container">
		<div class="card text-white bg-dark mb-3">
			<div class="card-header text-center">
				<h2>Exercises</h2>
			</div>
			<div class="card-body">
				<a href="/exercise" class="stretched-link"></a>
				<h5 class="card-text text-center">Log your workouts.</h5>
			</div>
		</div>
		<div class="card text-white bg-dark mb-3">
			<div class="card-header text-center">
				<h2>Tutorials</h2>
			</div>
			<div class="card-body">
				<a href="/search" class="stretched-link"></a>
				<h5 class="card-text text-center">Search and watch exercise
					tutorials from Youtube.</h5>
			</div>
		</div>
		<div class="card text-white bg-dark mb-3">
			<div class="card-header text-center">
				<h2>History</h2>
			</div>
			<div class="card-body">
				<a href="/history" class="stretched-link"></a>
				<h5 class="card-text text-center">Shows your current workout
					history sorted by most recent.</h5>
			</div>
		</div>
		<div class="card text-white bg-dark mb-3">
			<div class="card-header text-center">
				<h2>Stats</h2>
			</div>
			<div class="card-body">
				<a href="/stats" class="stretched-link"></a>
				<h5 class="card-text text-center">Displays your lifetime stats
					as well as current workout averages.</h5>
			</div>
		</div>
	</div>
	<div>
		<sec:authorize access="hasAnyAuthority('ADMIN')">
			<div class="container">
				<div class="card text-white bg-danger mb-3">
					<div class="card-header text-center">
						<h2>Admin Page</h2>
					</div>
					<div class="card-body">
						<a href="/admin/protected" class="stretched-link"></a>
						<h5 class="card-text text-center">Admins can modify, add or
							remove tutorials.</h5>
					</div>
				</div>
			</div>
		</sec:authorize>
	</div>
	<div class="container">
		<form>
			<button class="btn btn-lg btn-block border-danger" type="submit"
				formaction="/logout">Logout</button>
		</form>
	</div>
</body>
</html>