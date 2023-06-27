<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.ReporteEspecialidad"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte Medicos</title>
<link href="estilo.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

</head>
<body>
	<%@include file="Nav.html"%>
	<h1 class="title">Reporte Medicos</h1>


	<%
	if (request.getAttribute("success") != null) {
	%>
	<div class="success"><%=request.getAttribute("success")%></div>
	<%
	}
	%>
	<%
	if (request.getAttribute("error") != null) {
	%>
	<div class="error"><%=request.getAttribute("error")%></div>
	<%
	}
	%>

</body>
</html>