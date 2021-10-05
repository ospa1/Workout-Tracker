<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:include page="navbar.jsp" />
<!DOCTYPE html>
<html>
<head>
	<title>Tutorials</title>
</head>
<body>

	<div class="title">
		<h1 class="text-center ">TUTORIALS</h1>
	</div>
	<div class="container mt-5">
		<form>
		    <div class="input-group md-form form-sm form-2 pl-0">
		      <input class="form-control my-0 py-1 blue-border" type="text" name="search" value="${search}">
		      <div class="input-group-append">
		       <button class="btn btn-primary btn-md my-0 ml-sm-2" type="submit" formaction="/search">Search</button></div>
		    </div>
		</form>
	</div>
	<div class="container mt-1 mb-1">
		<form><button type="submit" class="btn btn-primary btn-lg btn-block" formaction="/search/all">Show All</button></form>
	</div>
	<c:choose>
	<c:when test="${not empty search and empty videos}">
		<div class="container">
			<span class="badge " style='color: black'>No Results Found</span>
			<br>
		</div>
	</c:when>
	<c:when test="${not empty videos}">
		<c:forEach items="${videos}" var="video">			
			<div class="container">
				<div class="card">
					<div class="card-body">
					    <h4 class="card-title">${video.getName()}</h4>
					    <div class='embed-container'>
							<iframe src='https://www.youtube.com/embed/nhoikoUEI8U'></iframe>
						</div>
					</div>
				</div>
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
