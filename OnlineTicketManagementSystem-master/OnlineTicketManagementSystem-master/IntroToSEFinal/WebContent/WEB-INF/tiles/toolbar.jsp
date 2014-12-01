<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<c:choose>
	<c:when test="${hasOffer}">
		<a href="${pageContext.request.contextPath}/createoffer">Edit or
			delete your current Event</a>
	</c:when>
	<c:otherwise>
		<a href="${pageContext.request.contextPath}/createoffer">Add a new
			Event</a>
	</c:otherwise>

</c:choose>
&nbsp;
<sec:authorize access="hasRole('ROLE_ADMIN')">
	<a href="<c:url value='/admin'/>">Admin</a>
</sec:authorize>
&nbsp;
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/messages'/>">Messages (<span id="numberMessages">0</span>)</a>
</sec:authorize>
&nbsp;&nbsp;
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/events'/>">My Events </a>
</sec:authorize>

&nbsp;&nbsp;
<sec:authorize access="isAuthenticated()">
	<a href="<c:url value='/settings'/>">Settings </a>
</sec:authorize>

<%-- <sec:authorize access="isAuthenticated()">
	< href="<c:url value='/settings'/>">Settings </a>
</sec:authorize> --%>