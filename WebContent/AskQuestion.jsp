<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link href="css/misslayout.css" rel="stylesheet" type="text/css">
</head>

<title>Ask for Advice</title>

<body>
<form action="AskServlet" name="form" method="post">
<table>
<tr>
<td><p>Got a problem? We'll help you out!</p></td>
<tr>
<td><textarea cols="60" rows="10" name="question">
Ask yo damn question!
</textarea></td>
</tr>

<tr>
<td><button type="submit" value="Submit">SUBMIT</button>
<button type="reset" value="Reset">RESET</button></td>
</tr>
</table>
</form>
</body>

</html>