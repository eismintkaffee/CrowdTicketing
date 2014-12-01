<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<div align="right"><sec:authorize access="isAuthenticated()">
Hello, ${myuser.name}&nbsp;&nbsp;&nbsp;<img  height=50px width=50px align="right" src="${path}"/>
</sec:authorize></div>
<br/><br/><br/>
<table id="myTable" class="offers">
<thead>
<tr>
 
    <th>User</th> 
    <th>Contact</th> 
    <th>Event</th> 
    <th>Type</th> 
    <th>Vanue</th> 
    <th>Date/Time</th> 
    <th>Tkts Available</th> 
    <th>Total tkts</th> 
    <th>No of Tkts</th> 
    <th></th>
</tr> 
</thead>
<tbody>
	<c:forEach var="offer" items="${offers}">
		<tr class="offerrow">

			<td class="name"><c:out value="${offer.user.name}"></c:out></td>

			<td class="contact"><a
				href="<c:url value='/message?uid=${offer.username}'/>">contact</a></td>

			<td class="offer"><c:out value="${offer.title}"></c:out></td>
			<td class="offer"><c:out value="${offer.type}"></c:out></td>
			<td class="offer"><c:out value="${offer.vanue}"></c:out></td>
			<td class="offer"><c:out value="${offer.date}"></c:out></td>
			<td class="offer"><c:out value="${offer.available}"></c:out></td>
			<td class="offer"><c:out value="${offer.total}"></c:out></td>
<td><sf:form method="post" action="${pageContext.request.contextPath}/buy" commandName="offer"> 
	<sf:input type="hidden" path="id" name="id" id="id" value="${offer.id}"/>
	<sf:input type="text" path="total" name="total" id="total" />
	<input type="submit" class="control" name="submit" value="Buy"/>
</sf:form></td>

		</tr>
	</c:forEach>
	</tbody>
</table>

<script type="text/javascript">

	function updateMessageLink(data) {
		$("#numberMessages").text(data.number);}

	function onLoad() {
		updatePage();
		window.setInterval(updatePage, 5000);
	}
	
	function updatePage() {
		$.getJSON("<c:url value="/getmessages"/>", updateMessageLink);
	}
	function table() 
	    { 
	        $("#myTable").tablesorter(); 
	    } 
	$(document).ready(onLoad);

</script>

