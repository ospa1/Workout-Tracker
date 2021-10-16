<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

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
	<a href="/search">Tutorials</a>
	<a href="/exercise">Exercises</a>
	<a href="/history">History</a>
	<a href="/stats">Stats</a>
</div>
<div>
	<sec:authorize access="hasAnyAuthority('ADMIN')">
	<br>
	<span style="color: red;">ADMIN</span>
	<a href="/admin/protected">Protected</a>
	<br>
	</sec:authorize>
	
	<sec:authorize access="!hasAnyAuthority('ADMIN')">
	<br>
	<span style="color: red;">USER</span>
	<br>
	</sec:authorize>
</div>