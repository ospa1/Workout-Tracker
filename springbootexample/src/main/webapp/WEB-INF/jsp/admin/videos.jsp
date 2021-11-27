<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="../navbar.jsp" />

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<title>Admin</title>
</head>
<body>
	<h1>Videos Page</h1>

	<div class="container">
		<div class="d-inline p-2">Videos List</div>
		<div class="d-inline p-2">
			<form method="GET" class="d-inline p-2" action="/admin/videos/new">
				<button class="btn btn-primary" type="submit">Add Video</button>
			</form>
		</div>
		<hr>
		<table class="table table-striped">
			<thead>
				<tr>
					<th scope="col">Id</th>
					<th scope="col">Name</th>
					<th scope="col">Url</th>
					<th scope="col">Edit</th>
					<th scope="col">Delete</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${videos}" var="video">
					<tr>
						<td>${video.getId()}</td>
						<td>${video.getName()}</td>
						<td>${video.getUrl()}</td>
						<td>
							<form method="GET">
								<input type="hidden" name="id" value="${video.getId()}">
								<button class="btn btn-success" type="submit"
									formaction="/admin/videos/edit">Edit</button>
							</form>
						</td>
						<td>
							<form method="POST" action="/admin/videos/delete">
								<input type="hidden" name="id" value="${video.getId()}">
								<input type="hidden" name="name" value="${video.getName()}">
								<input type="hidden" name="url" value="${video.getUrl()}">
								<button type="submit" class="btn btn-danger">Delete</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>