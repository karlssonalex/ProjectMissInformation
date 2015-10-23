<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	session ="true"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<!-- @Autor: Charlotte -->
<head>
<link href="css/misslayout.css" rel="stylesheet" type="text/css">
</head>

<title>Index</title>

<body>
	<div class="top">
		<center>
			<h1>MISS INFORMATION</h1>
		</center>
		<br />

		<ul class="navbar">
			<li><a href="mainbox.jsp" target="main">Home</a></li>
			<li><a href="askquestion.jsp" target="main">Ask for Advice</a></li>
			<li><a href="QuestionServlet" target="main">Q&A</a></li>
			<li><a href="contact.jsp" target="main">Contact</a></li>
			<c:if test="${sessionScope.adminStatus == 1}">
				<li><a href="adminpage.jsp" target="main">Admin</a></li>
			</c:if>

				<li><a href="LogoutServlet">
						Logout </a></li>

		</ul>
	</div>

	<center>
		<div class="main" width="900">
			<iframe class="mainbox1" src="mainbox.jsp" name="main" width="900"
				height="550"></iframe>
		</div>
	</center>

</body>

</html>