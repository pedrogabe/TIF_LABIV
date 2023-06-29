<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.ReporteMedico"%>
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
 	
	<form action="ServletRepMedicos" method=post>
		<label for="mesMedico">Seleccione un Mes:</label> 
		<input type="month" id="mesMedico" name="mesMedico" required> 
		<input type="submit">
	</form>
	
	<br>		
	<br>	
	
 
 		<%
 		String anioMes = "";

		if (request.getAttribute("anioMes") != null){
			  anioMes =(request.getAttribute("anioMes").toString()).substring(5);		 
		}

		%>
			
		<%					
			ArrayList<ReporteMedico> reporte = null;

			if (request.getAttribute("reporte") != null) {
			
				%>
				<div class="div_center">
				
				<%	
			
		
			reporte = (ArrayList<ReporteMedico>) request.getAttribute("reporte");
			
				if(reporte.isEmpty()) {
			%>
				<br>
				<text><center>No hay registros para mostrar</center></text>
				<br>
				
			<%			
				
				
				} else {
			%>		
				<text><center>Mes <%= anioMes %></center></text>
				<br>	
			<%					
				for (ReporteMedico registro : reporte) {
					
				%>
	
					<h3> <%= registro.getMedico().getNombre() %> <%= registro.getMedico().getApellido() %></h3>	
					<h6> <%= registro.getMedico().getEspecialidad().getEspecialidad() %></h6>
					<div class="progress">
		  				<div class="progress-bar bg-success" role="progressbar" style="width: <%= registro.getProcentaje_por_medico() %>%" aria-valuenow="<%= registro.getTotal_por_medico() %>" aria-valuemin="0" aria-valuemax="100">
		  				<%= registro.getTotal_por_medico() %>
		  				</div>
					</div>
					<br>			
				<%
			    }
			  }
			%>				
			</div>
			<%
			} 
		%>

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