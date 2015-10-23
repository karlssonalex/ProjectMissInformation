<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" session="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- @Autor: Alex -->
<head>
<link href="css/misslayout.css" rel="stylesheet" type="text/css">
<title>Payment</title>
</head>
<body>
<div id="payment-div"></div>
	<script charset="UTF-8"
		src="https://ssl.ditonlinebetalingssystem.dk/integration/ewindow/paymentwindow.js"
		type="text/javascript"></script>
	<div id="payment-div"></div>
	<script type="text/javascript">
		paymentwindow = new PaymentWindow({
			'merchantnumber' : "8021018",
			'amount' : "1000",
			'currency' : "SEK",
			'windowstate' : "4",
			'orderid' : "q10",
			'paymentcollection' : "1",
			'iframeheight' : "500",
			'iframewidth' : "890",
			'accepturl' : "https://white-falls.se/test/target.jsp"
		});
		paymentwindow.append('payment-div');
		paymentwindow.open();
	</script>
</body>
</html>