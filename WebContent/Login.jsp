<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"
    session = "false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- @Autor: Charlotte -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/misslayout.css" rel="stylesheet" type="text/css" />
<title>Login</title>

</head>
<body>
<%
HttpSession session = request.getSession(false);
if(null != session) {
	session.invalidate();
}
%>
	<form name="login" action="LoginServlet" method="post" onsubmit="checkCookie()">
	<center><h1>LOGIN</h1></center>
	<br>
		<table>
	<tr>
		<td>Username: <input type="text" name="username"></td>
		</tr>
		<tr>
		<td>Password: <input type="password" name="password"></td>
		</tr>
		<tr>
		<td><button type="submit" value="Submit">LOGIN</button>
		<button type="reset" value="Reset">RESET</button></td>	
		</tr>
		<tr>
		<td><p>Not a member yet? Go to <a href="registration.jsp">registration</a>.</p></td>
		</tr>
		</table>
	</form>
</body>
</html>