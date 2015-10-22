<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	session ="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- @Autor: Charlotte -->
<head>
<link href="css/misslayout.css" rel="stylesheet" type="text/css">
</head>

<title>Index</title>

<body>
	<%
		String userName = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("username")) {
					userName = c.getValue();
					pageContext.setAttribute("username", userName);
				}
			}
		}
		if (userName == null) {
			response.sendRedirect("login.jsp");
		}
	%>

	<div class="top">
		<center>
			<h1>MISS INFORMATION</h1>
		</center>
		<br />

		<ul class="navbar">
			<li><a href="mainbox.jsp" target="main">Home</a></li>
			<li><a href="askquestion.jsp" target="main">Ask for Advice</a></li>
			<li><a href="QuestionServlet" target="main">Q&A</a></li>
			<li><a href="mainbox.jsp" target="main">Contact</a></li>
			<form action="LoginServlet" name="logoutform" method="get">
				<input type="hidden" name="logout">
				<li><a href="#" onclick="document.forms['logoutform'].submit(); return false;">
						Logout </a></li>
			</form>
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