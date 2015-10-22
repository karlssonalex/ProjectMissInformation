<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	session ="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<!-- @Autor: Alex -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/misslayout.css" rel="stylesheet" type="text/css">
<title>QOA</title>
</head>
<body>
	<table id="t01">
		<tr>
			<th>Ticket ID</th>
			<th>Question</th>
			<th>Answer</th>
			<th>Name</th>
		</tr>
		<c:forEach items="${questionList}" var="question">
			<tr>
				<td>${question.getTicketid()}</td>
				<td>${question.getQuestion()}</td>
				<td>${question.getAnswer()}</td>
				<td>${question.getName()}</td>
			</tr>

		</c:forEach>
	</table>
</body>
</html>