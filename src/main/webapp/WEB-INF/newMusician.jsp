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
<title>Insert title here</title>
</head>
<body>
	<div class="container-fluid">
		<div class="container mx-auto mt-4">
			<div class="text-end">
				<a href="/dashboard" class="nav-link mb-3">Home</a>
			</div>
			<h1 class="display-4">Add a Musician</h1>
			<form:form action="/musicians/create" modelAttribute="musician" method="POST" class="col-5 mt-4 p-3">
				<div>
					<form:errors path="*" class="text-danger"/>
				</div>
				<div class="mb-3">
					<form:label path="name" >Name:</form:label>
					<form:input path="name" class="form-control" />
				</div>
				<div class="mb-3">
					<form:label path="instrument" >Instrument:</form:label>
					<form:input path="instrument" class="form-control" />
				</div>
				<div class="form-row">
					<form:input type="hidden" path="set" value="${set.id}" class="form-control"/>
				</div>
				<button>Submit</button>
			</form:form>
		</div>
	</div>
</body>
</html>