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
					<h1 class="display-5 mb-4">Title: ${song.songTitle}</h1>
				</div>
				<div class=" d-flex justify-content-end">
					<a href="/dashboard" class="nav-link mb-3">Home</a>
					<c:if test="${song.creator.id == user.id}">
						<a href="/songs/edit/${song.id}" class="nav-link">Edit</a>
					</c:if>
					<a href="/logout" class="nav-link">Logout</a>
				</div>
				<div class="text-start">
					<h2 class="display-9 mb-4">Artist: <c:out value="${song.artist}" /></h2>
				</div>
			</header>
			<main class="col-5 px-4 py-3 border border-1 border-pirmary rounded bg-light">
				<div>
					<h3>Lyrics:</h3>
					<p class="mb-2"><c:out value="${song.lyrics}" /></p>
				</div>
			</main>
		</div>
	</div>
</body>
</html>