<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<sec:authorize access="isAuthenticated()">
	<h1>User is authenticated!!!!!!</h1>
	<sec:authentication property="principal.username" />
	<br>
</sec:authorize>
<sec:authorize access="hasAnyAuthority('ADMIN')">
	<a href="/admin/protected">Protected admin url</a>
	<br>
</sec:authorize>
<sec:authorize access="hasAnyAuthority('MANAGER')">
	<a href="/admin/manager">Protected manager url</a>
	<br>
</sec:authorize>

<sec:authorize access="!isAuthenticated()">
	<a href="/login/login">Login</a>
</sec:authorize>