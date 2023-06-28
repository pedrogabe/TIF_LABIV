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

 	<% double i =  77.3; %>
 	
	<form action="ServletRepMedicos" method=post>
		<label for="mesMedico">Seleccione un Mes:</label> 
		<input type="month" id="mesMedico" name="mesMedico"> 
		<input type="submit">
	</form>
	
	<br>		
	<br>		
	
 	<div class="div_center">
 		<h2>Mes</h2>
		<br>		
		<div class="progress">
  			<div class="progress-bar bg-success" role="progressbar" style="width: <%= i %>%" aria-valuenow="<%= i %>" aria-valuemin="0" aria-valuemax="100"><%= i %></div>
		</div>
		<br>
		<div class="progress">
  			<div class="progress-bar bg-info" role="progressbar" style="width: 50%" aria-valuenow="50" aria-valuemin="0" aria-valuemax="100"></div>
		</div>
		<br>
		<div class="progress">
  			<div class="progress-bar bg-warning" role="progressbar" style="width: 75%" aria-valuenow="75" aria-valuemin="0" aria-valuemax="100"></div>
		</div>
		<br>
		<div class="progress">
  			<div class="progress-bar bg-danger" role="progressbar" style="width: 100%" aria-valuenow="100" aria-valuemin="0" aria-valuemax="100"></div>
		</div>
	
	</div>

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