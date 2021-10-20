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
			<c:forEach items="${sets}" var="set">
				<div class="container">
					<div class="card bg-light border-dark">
						<div class="card-body">
							<div class="row">
								<div class="col-xs-12 col-md-8 font-weight-bolder h4">Weight:
									${set.getWeight()} Reps: ${set.getReps()}</div>
								<div class="col-xs-6 col-md-4 text-right font-weight-bolder h4">${set.getDate()}</div>
							</div>
						</div>
						<div class="card-footer">
							<div class="row">
								<div class="col-xs-12 col-md-8 font-weight-bolder h4">
									<form method="GET">
										<input type="hidden" name="id" value="${set.getId()}">
										<input type="hidden" name="exerciseId" value="${exerciseId}">
										<button class="btn btn-primary" type="submit"
											formaction="/updateSet">modify</button>
									</form>
								</div>
								<div class="col-xs-6 col-md-4 text-right font-weight-bolder h4">
									<form method="POST">
										<input type="hidden" name="id" value="${set.getId()}">
										<input type="hidden" name="exerciseId" value="${exerciseId}">
										<button class="btn btn-danger" type="submit"
											formaction="/deleteSet">delete</button>
									</form>
								</div>
							</div>
						</div>
					</div>

					<br>
				</div>
			</c:forEach>
		</c:when>
	</c:choose>
</body>
</html>