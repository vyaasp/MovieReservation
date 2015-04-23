<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form"
    prefix="springForm"%>
<%@ taglib prefix="c" 
           uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Screens</title>
<style>
.error {
    color: #ff0000;
    font-style: italic;
    font-weight: bold;
}
</style>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/css/bootstrap-theme.min.css">
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
</head>
<body>
 <table>
<springForm:form method="POST" commandName="bs" action="bookticket">
<span class="glyphicon glyphicon-search" aria-hidden="true"></span>
<tr><td>Customer Name:</td>
<td><label>"${bs.customer.name}"</label></td></tr>
<tr><td>Customer Username:</td>
<td><label>"${bs.customer.username}"</label></td></tr>
<tr><td>Customer Phone:</td>
<td><label>"${bs.customer.phone}"</label></td></tr>
<tr><td>Movie Name:</td>
<td><label>"${bs.showtime.movie.name}"</label></td></tr>
<tr><td>Movie Cast:</td>
<td><label>"${bs.showtime.movie.cast}"</label></td></tr>
<tr><td>Cinema Name:</td>
<td><label>"${bs.showtime.cinema.name}"</label></td></tr>
<tr><td>Cinema Address:</td>
<td><label>"${bs.showtime.cinema.address}"</label></td></tr>
<tr><td>Cinema Phone:</td>
<td><label>"${bs.showtime.cinema.phone}"</label></td></tr>
<tr><td>Cinema e-Mail:</td>
<td><label>"${bs.showtime.cinema.email}"</label></td></tr>
<tr><td>Cinema Screen:</td>
<td><label>"${bs.showtime.screen.name}"</label></td></tr>
</springForm:form>
</table>
</body>
</html>

