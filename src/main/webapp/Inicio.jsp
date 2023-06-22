<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="ISO-8859-1">
	<title>Inicio</title>
</head>
<body>
<%
	if(request.getSession().getAttribute("Usuario")!=null){
%>
	<%@include file="Nav.html"%>
	<div class="inicio">
		<h1 class="inicio-content ">Portal de inicio</h1>
	</div>
<%}else{
	response.sendRedirect("Login.jsp");
}
%>
</body>
</html>
