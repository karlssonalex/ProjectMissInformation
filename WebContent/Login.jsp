<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="css/misslayout.css" rel="stylesheet" type="text/css" />
<title>Login</title>

<script type="text/javascript">
function checks(form){
	checkCookie();
	checkField(form);
}

function checkCookie() {
    var cookieEnabled = (navigator.cookieEnabled) ? true : false;
    if (typeof navigator.cookieEnabled == "undefined" && !cookieEnabled){ 
        document.cookie = "testcookie";
        cookieEnabled = (document.cookie.indexOf("testcookie") != -1) ? true : false;
    }
    return (cookieEnabled) ? true : showCookieFail();
}

function showCookieFail() {
	alert("You have to enable cookies to use this site!");
}

function checkField(form) {
    if (form.user.value == "") {
        alert("Field 'Username' is empty");
        return false;
    }
    else if (form.pwrd.value == "") {
        alert("Field 'Password' is empty");
        return false;
    }
    return true;
}

</script>
</head>
<body>
	<form name="login" action="LoginServlet" method="post" onsubmit="checks(this)">
	<center><h1>LOGIN</h1></center>
		<table>
	<tr>
		<td>Username: <input type="text" name="user"></td>
		</tr>
		<tr>
		<td>Password: <input type="password" name="password"></td>
		</tr>
		<tr>
		<td><button type="submit" value="Submit">SUBMIT</button>
		<button type="reset" value="Reset">RESET</button></td>	
		</tr>
		<tr>
		<td><p>Not a member yet? Go to <a href="registration.jsp">registration</a>.</p></td>
		</tr>
		</table>
	</form>
</body>
</html>