<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<body>
	<H1>Exception Thrown Internal Server Error</H1>
	<c:if test="${not empty message}">
		<p>${message}</p>
	</c:if>
	<c:if test="${not empty stackTrace}">
		<br />
		<p>${stackTrace}</p>
	</c:if>
</body>
</html>