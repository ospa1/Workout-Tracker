<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<c:if test="${not empty message}">
	<span style='color: red'>${message}</span>
</c:if>

<h1>Main Page</h1>
<div>
	<c:if test="${not empty email}">
		<span style='color: blue'>Signed in as, ${email}</span>
	</c:if>
</div>
<div>
	<a href="/logout">Logout</a>
</div>