<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="generator"
	content="HTML Tidy for HTML5 (experimental) for Windows https://github.com/w3c/tidy-html5/tree/c63cc39" />
<!-- google fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com" />
<link rel="preconnect" href="https://fonts.gstatic.com" />
<link
	href="https://fonts.googleapis.com/css2?family=Oleo+Script:wght@700&amp;display=swap"
	rel="stylesheet" />
<link
	href="https://fonts.googleapis.com/css2?family=Anton&amp;display=swap"
	rel="stylesheet" />
<!-- bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="css/loginStyle.css" />
<script type="text/javascript" src="scripts/loginJS.js"></script>
<title>Create User</title>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" id="navbarBrand" href="#">Workout Tracker</a>
		<div class="collapse navbar-collapse" id="navbarNavDropdown">
			<ul class="navbar-nav">
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#"
					id="navbarDropdownMenuLink" role="button" data-toggle="dropdown"
					aria-haspopup="true" aria-expanded="false">Links</a>
					<div class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
						<a class="dropdown-item" href="login.html">Login</a> <a
							class="dropdown-item" href="log.html">Example Log</a> <a
							class="dropdown-item" href="set.html">Example Set</a>
					</div></li>
			</ul>
		</div>
	</nav>
	<div class="wrapper">
		<div id="formContent">
			<h1 id="loginHeader">Sign Up</h1>
			<!-- Login Form -->
			<form id="loginForm" action="/createUser" method="POST">
				<input type="text" id="login" class="" name="email"
					placeholder="email" value="${form.email}" />
				 <input type="password" id="password"
					class="" name="password" placeholder="password" value="${form.password}" /> 
				<input type="password" id="password"
					class="" name="confirmPassword" placeholder="confirm password" value="${form.confirmPassword}" />
				<input
					type="submit" class="" value="Confirm" onclick="" />
			</form>
			<!-- sign up-->
			<div id="formFooter">
				<a class="underlineHover" href="/login">Login</a>
			</div>
			<c:forEach items="${errors}" var="error">
				<span style='color: red'>${error}</span>
				<br>
			</c:forEach>
		</div>
		</div>
	</div>
</body>
</html>