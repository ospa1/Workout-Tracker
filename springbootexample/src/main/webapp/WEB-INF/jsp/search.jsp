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
	<title>Tutorials</title>
</head>
<body>

	<div class=container>
		<form>
		    <div class="input-group md-form form-sm form-2 pl-0">
		      <input class="form-control my-0 py-1 red-border" type="text" name="search" value="${search}">
		      <div class="input-group-append">
		       <button class="btn btn-primary btn-md my-0 ml-sm-2" type="submit">Search</button></div>
		    </div>
		</form>
	</div>
	<c:choose>
	<c:when test="${not empty search and empty videos}">
		<span style='color: black'>No Results Found</span>
		<br>
	</c:when>
	<c:when test="${not empty videos}">
		<c:forEach items="${videos}" var="video">
			<div class="container mt-3">
				<br>
				<span style='color: red'>name: ${video.getName()}</span>
				<br>
			</div>
			<div class="container mt-3">
				<!-- https://embedresponsively.com/ -->
				<div class='embed-container'>
					<iframe src='https://www.youtube.com/embed/nhoikoUEI8U' frameborder='0' allowfullscreen></iframe>
				</div>
				<hr>
			</div>
		</c:forEach>
	</c:when>
	</c:choose>
</body>
	<style>
		.input-group.md-form.form-sm.form-1 input{
		  border: 1px solid #bdbdbd;
		  border-top-right-radius: 0.25rem;
		  border-bottom-right-radius: 0.25rem;
		}
		.input-group.md-form.form-sm.form-2 input {
		  border: 1px solid #bdbdbd;
		  border-top-left-radius: 0.25rem;
		  border-bottom-left-radius: 0.25rem;
		}
		.input-group.md-form.form-sm.form-2 input.red-border {
		  border: 1px solid #ef9a9a;
		}
		.input-group.md-form.form-sm.form-2 input.lime-border {
		  border: 1px solid #cddc39;
		}
		.input-group.md-form.form-sm.form-2 input.amber-border {
		  border: 1px solid #ffca28;
		}

		 /* embeded youtube */
		.embed-container {
			position: relative;
			padding-bottom: 56.25%;
			height: 0;
			overflow: hidden;
			max-width: 100%;
		}
		.embed-container iframe, .embed-container object, .embed-container embed {
			position: absolute;
			top: 0;
			left: 0;
			width: 100%;
			height: 100%;
		}
	</style>
</html>
