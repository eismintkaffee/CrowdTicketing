<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<table id="myTable" class="tablesorter">
<thead>
<tr>
 
    <th>Title</th> 
    <th>Type</th> 
    
    <th>Vanue</th> 
    <th>Date/Time</th> 
    
    <th>No of Tkts bought</th> 
    <th></th>
</tr> 
</thead>
<tbody>
	<c:forEach var="offer" items="${offers}">
		<tr class="offerrow">

			

			

			<td class="offer"><c:out value="${offer.title}"></c:out></td>
			<td class="offer"><c:out value="${offer.type}"></c:out></td>
			<td class="offer"><c:out value="${offer.venue}"></c:out></td>
			<td class="offer"><c:out value="${offer.date}"></c:out></td>
			
			<td class="offer"><c:out value="${offer.tnum}"></c:out></td>


		</tr>
	</c:forEach>
	</tbody>
</table>

</body>
</html>