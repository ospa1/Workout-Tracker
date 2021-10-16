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
	<h1>Sets</h1>
	<c:choose>
	<c:when test="${empty sets}">
		<div class="container">
			<span class="badge " style='color: black'>No Results Found</span>
			<br>
		</div>
	</c:when>
	<c:when test="${not empty sets}">
		<c:forEach items="${sets}" var="set">			
			<div class="container">
				<div class="card ">
					<div class="card-body">
					    <h4 class="card-title">weight ${set.getWeight()} reps ${set.getReps()}</h4>
					    <input type ="hidden" name="id" value="${set.getId()}">			
					</div>
				</div>
				<form method="POST">
					<input type ="hidden" name="id" value="${set.getId()}">	
					<input type ="hidden" name="exerciseId" value="${exerciseId}">	
					<button class="btn btn-primary" type="submit" formaction="/deleteSet">delete</button>
				</form>
				<form method="GET">
					<input type ="hidden" name="id" value="${set.getId()}">	
					<input type ="hidden" name="exerciseId" value="${exerciseId}">
					<button class="btn btn-primary" type="submit" formaction="/updateSet">modify</button>
				</form>
			</div>
		</c:forEach>
	</c:when>
	</c:choose>
	<form class="form-inline" method="GET">
  		<button type="submit" class="btn btn-primary mb-2" formaction="/createSet">+ Add New Set</button>
	</form>
</body>
</html>