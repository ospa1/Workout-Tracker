<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" href="css/loginStyle.css" />
<script type="text/javascript" src="scripts/loginJS.js"></script>
<title>Create User</title>
</head>
<body>
	<div class="wrapper">
		<div id="formContent">
			<h1 id="loginHeader">Sign Up</h1>
			<!-- Login Form -->
			<form id="loginForm" action="/createUser" method="POST">
				<input type="text" id="login" class="" name="email"
					placeholder="email" value="${form.email}" /> <input
					type="password" id="password" class="" name="password"
					placeholder="password" value="${form.password}" /> <input
					type="password" id="password" class="" name="confirmPassword"
					placeholder="confirm password" value="${form.confirmPassword}" />
				<input type="submit" class="" value="Confirm" onclick="" />
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
</body>
</html>