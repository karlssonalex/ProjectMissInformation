<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- @Autor: Charlotte -->
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/misslayout.css" rel="stylesheet" type="text/css" />
<title>Registration</title>
</head>
<body>
<center>
		<table>
			<tr>
				<td>
					<div class="divbox">
						<form action="RegisterServlet" name="form" method="post">

							<center>
								<h1>REGISTRATION</h1>
							</center>
							<br>
							<table>
								<tr>
									<td>Username:</td>
								</tr>
								<tr>
									<td><input type="text" name="username"></td>
								</tr>
								<tr>
									<td>Password:</td>
								</tr>
								<tr>
									<td><input type="password" name="password"></td>
								</tr>
								<tr>
									<td><br></td>
								</tr>
								<tr>
									<td><button type="submit" value="Submit">SUBMIT</button>&ensp;
										<button type="reset" value="Reset">RESET</button></td>
							</table>

						</form>
						</div>
				</td>
</tr></table></center>
</body>
</html>