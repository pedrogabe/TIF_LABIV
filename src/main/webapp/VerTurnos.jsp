<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Turnos</title>

<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
	});

</script>

</head>
<body>
<%
	if(request.getSession().getAttribute("Usuario")!=null){
%>
	<%@include file="Nav.html"%>

	<h1 class="title">Turnos otorgados</h1>
	
	<a href="ABMLTurno.jsp" class="p-2 bd-highlight">
		<input class="btn btn-outline-dark" type="submit" name="btnAgregarTurno" value="Agregar Turno">
	</a>
	    
	<br><br><br>
	
	<table border="1" id="table_id">
	
	<thead>	
		<tr>
			<td><b>Id</b></td>
			<td><b>Paciente</b></td>
			<td><b>Profesional</b></td>
			<td><b>Especialidad</b></td>
			<td><b>Fecha</b></td>
			<td><b>Hora</b></td>			
			<td><b>Estado</b></td>			
		</tr>
	</thead>	
	<tbody>
	        <tr>
	            <td>6132</td>
                <td>Olivia Lang</td>
                <td>Prescott Bartlett</td>
              	<td>Endocrinologia</td>
                <td>2011-04-25</td>
                <td>19hs</td>
                <td>Ocupado</td>
                <td><i class="fa fa-trash"></i></td>            
            </tr>
            <tr>
                <td>435</td>
                <td>Jonas Alexander</td>
                <td>Jennifer Chang</td>
                <td>Oftalmologia</td>
                <td>2011-07-25</td>
                <td>13hs</td>               
                <td>Ausente</td>
                <td><i class="fa fa-trash"></i></td>            
            </tr>
	</tbody>

	</table>
<%}else{
	response.sendRedirect("Login.jsp");
}
%>
</body>
</html>
