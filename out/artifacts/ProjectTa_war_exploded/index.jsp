<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<meta charset="utf-8">
	<link rel="stylesheet" type="text/css" href="style.css">
	<title>hotel</title>
</head>
<body>
	<div id="frame">
		<div id="header">
			<button class="button"><a href="${pageContext.request.contextPath}/addCustomer"><h2>Add Customer</h2></a></button>
			<button class="button"><a href="${pageContext.request.contextPath}/rooms"><h2>Rooms</h2></a></button>
		</div>
		<table border="1" id="table">
			<tr class="tr">
				<th>ROOM</th>
				<th>NAME</th>
				<th>DATE</th>
				<th></th>
			</tr>
			<c:forEach var="customer" items="${customers}">
				<tr class="tr">
					<td>${customer.getCustomerRoom()}</td>
					<td>${customer.getCustomerName()}</td>
					<td>${customer.getCustomerDepDate()}</td>
					<td><button class="button"><a href='<c:url value="/deleteCustomer?room=${customer.getCustomerRoom()}" />'><h3>Delete</h3></a></button></td>
				</tr>
			</c:forEach>
		</table>
		<div id="footer"></div>
	</div>

</body>
</html>