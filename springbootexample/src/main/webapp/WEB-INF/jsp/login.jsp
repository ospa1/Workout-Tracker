<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />

<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet" href="css/loginStyle.css" />
<script type="text/javascript" src="scripts/loginJS.js"></script>
<title>Login</title>
</head>
<body>
	<div class="wrapper">
		<div id="formContent">
			<h1 id="loginHeader">LOGIN</h1>
			<!-- Login Form -->
			<form id="loginForm" action="/login" method="POST">
				<input type="text" id="login" class="" name="username" value="${form.username}"
					placeholder="email" /> 
				<input type="password" id="password" value="${form.password}"
					class="" name="password" placeholder="password" /> 
				<input
					type="submit" class="" value="Log In" onclick="" />
			</form>
			<!-- sign up-->
			<div id="formFooter">
				<a class="underlineHover" href="/createUser">Sign Up</a>
			</div>
			<c:forEach items="${errors}" var="error">
				<span style='color: red'>${error}</span>
				<br>
			</c:forEach>
		</div>
	</div>
</body>
</html>