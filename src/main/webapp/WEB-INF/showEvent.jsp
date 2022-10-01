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
<title>View Set List</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<header class="row justify-content-between align-items-center">
				<div class="text-start">
					<h3>${event.setTitle} Set List</h3>
				</div>
				<div class=" d-flex justify-content-end">
					<a href="/dashboard" class="nav-link mb-3">Home</a>
					<c:if test="${event.user.id == user.id}">
						<a href="/songs/new/${event.id}" class="nav-link">Add Song</a>
					</c:if>
					<c:if test="${event.user.id == user.id}">
						<a href="/musicians/new/${event.id}" class="nav-link">Add Musician</a>
					</c:if>
					<a href="/logout" class="nav-link">Logout</a>
				</div>
			</header>
			<div class=" d-flex justify-content-between m-5">
				<main class="col-5 px-4 py-3 border border-1 border-pirmary rounded bg-light">
				<div>
					<ul>
						<c:forEach var="song" items="${event.songs}">
							<li><a href="/songs/${song.id}" class="nav-link"><c:out value="${song.songTitle}" /></a> </li>
						</c:forEach>
					</ul>
				</div>
			</main>
			<main class="col-4 px-4 py-3 border border-1 border-pirmary rounded bg-light">
				<div>
					<ul>
						<c:forEach var="musician" items="${event.musicians}">
							<li><c:out value="${musician.name}" /> - <c:out value="${musician.instrument}" /></li>
						</c:forEach>
					</ul>
				</div>
			</main>
			</div>
			
		</div>
	</div>
</body>
</html>