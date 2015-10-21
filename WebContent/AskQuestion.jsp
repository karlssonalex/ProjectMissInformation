<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link href="css/misslayout.css" rel="stylesheet" type="text/css">

</head>

<title>Ask for Advice</title>

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
	<form action="AskServlet" name="form" method="post">
		<input type="hidden" value="${user}" name="user" />
		<table>
			<tr>
				<td><p>Got a problem? We'll help you out!</p></td>
			<tr>
				<td><textarea cols="60" rows="10" name="question" onfocus="if(this.value==this.defaultValue)this.value=''" onblur="if(this.value=='')this.value=this.defaultValue">Ask yo damn question!</textarea></td>
			</tr>

			<tr>
				<td><button type="submit" value="Submit">SUBMIT</button>
					<button type="reset" value="Reset">RESET</button></td>
			</tr>
		</table>
	</form>
</body>

</html>