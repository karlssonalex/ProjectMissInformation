<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link href="css/misslayout.css" rel="stylesheet" type="text/css">
</head>

<title>Herrow</title>

<body>
	<%
		String userName = null;

		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				if (c.getName().equals("user")) {
					userName = c.getValue();
					pageContext.setAttribute("user", userName);
				}
			}
		}
		if (userName == null) {
			response.sendRedirect("login.jsp");
		}
	%>

	<center>
		<p>You're logged in as: ${user}</p>
	</center>
	
	<br>
	
	<img src="http://i.imgur.com/FcgG2S2.gif" width="890"height="500" alt="csgologo" />
</body>

</html>