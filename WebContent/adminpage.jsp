<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<!-- @Author: Alex -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/misslayout.css" rel="stylesheet" type="text/css">
<title>Admin page</title>
</head>
<body>
	<center>
		<table>
			<tr>
				<td>
					<div class="divbox">
						<form action="AdminServlet" name="form" method="post"
							onsubmit="return checkField(this)">
							<center>
								<h1>CHANGE USER PRIVILEGES</h1>
							</center>
							<c:if test="${not empty error}">
								<p style="color:red">${error}</p>
							</c:if>
							<c:if test="${empty error}">
								<br>
							</c:if>
							<table>
								<tr>
									<td>Username: <input type="text" name="username"></td>
								</tr>
								<tr>
									<td> Privileges: <input type="radio" name="admin" value="0" checked> User <input type="radio" name="admin" value="1"> Admin </td> <input type="radio" name="admin" value="2"> Sysadmin </td>
								</tr>
								<tr>
								<tr>
									<td><button type="submit" value="Submit">SUBMIT</button>&ensp;
										<button type="reset" value="Reset">RESET</button></td>
							</table>

						</form>
					</div>
				</td>
			</tr>
		</table>
	</center>
</body>
</html>