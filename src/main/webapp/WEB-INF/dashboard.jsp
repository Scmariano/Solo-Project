<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page isErrorPage="true"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css" />
<title>Set List</title>
</head>
<body>

	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<header class="row justify-content-between align-items-center">
				<div class="text-start">
					<h4>Hello, ${user.name}!</h4>
				</div>
				<div class="col-5 text-start">
					<p>This is all the event!</p>
				</div>
				<div class="col-5 d-flex justify-content-end">
					<a href="/events/new" class="nav-link">Add Event</a><a href="/logout" class="nav-link">Logout</a>
				</div>
			</header>
		</div>
		<div class="row mx-auto mt-3">
			<table class="table table-striped table-bordered caption-top">
				<thead class="table-info">
					<tr class="align-middle">
						<th>Event Name</th>
						<th>Date</th>
						<th>Address</th>
						<th>Lead</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach var="set" items="${events}">
						<tr>
							<td><a href="/events/${set.id}" class="nav-link"><c:out value="${set.setTitle}"/></a></td>
							<td><fmt:formatDate value="${set.setDate}" pattern="MM/dd/yy"/>						
							<td><c:out value="${set.address}" /></td>
							<td><c:out value="${set.user.name}" /></td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>
	

</body>
</html>