<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"
	session ="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- @Autor: Charlotte -->
<head>
<link href="css/misslayout.css" rel="stylesheet" type="text/css">
<title>Contact</title>
</head>

<body>
<form action="http://lmgtfy.com/">
<p>Got more questions other than your problematic life? Mail us!</p>
            <table>
                <tr><td><p>To:</p>
                    </td>
                    <td><input type="text" name="mail" value="admin@gmail.com"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Subject:</p>
                    </td>
                    <td>
                        <input type="text" name="sub" value="Enter Subject Line">
                    </td>
                </tr>
                <tr>
                    <td>
                        <p>Message Text:</p>
                    </td>
                    <td>
                        <textarea rows="12" cols="80" name="mess"></textarea>
                    </td>
                </tr>
                <tr>
                    <td><button type="submit" value="Submit">SUBMIT</button><button type="reset" value="Reset">RESET</button></td>
                </tr>
            </table>
        </form>
</body>
</html>