<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.Turno"%>
<%@ page import="entidad.Usuario" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@include file="estilo.css" %>
</style>
<title>Listado de turnos</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<%@include file="Datatable_init.html" %>

</head>
<body>
<%
	if(request.getSession().getAttribute("Usuario")!=null){
%>

	<%@include file="Nav.html"%>
	
	<%
		String error = (String)request.getAttribute("error");
		List<Turno> turnos;
		if(session.getAttribute("turnos") == null){
			turnos = new ArrayList<Turno>();
			if(error==null)
				error = "Error al cargar el listado de pacientes";
		}
		else{
			turnos = (List<Turno>)session.getAttribute("turnos");
		}
	%>

	<h1 class="title">Turnos otorgados</h1>
 	<%
		Usuario usuario = (Usuario) request.getSession().getAttribute("Usuario");
	
			if (usuario.getIdPerfil() != Usuario.ROL_MEDICO) {
	%>
	<a href="ServletTurno?op=add" class="p-2 bd-highlight">
		<input class="btn btn-outline-dark" type="submit" name="btnAgregarTurno" value="Agregar Turno">
	</a>
	<%
		}
	%>
	
	<br><br><br>

	<table border="1" id="table_id" datatable="true">
	
	<thead>	
		<tr>
			<td><b>Id</b></td>
			<td><b>Paciente</b></td>
			<td><b>Medico</b></td>			
			<td><b>Fecha</b></td>
			<td><b>Hora</b></td>
			<td><b>Estado</b></td>			
			<td><b></b></td>
		</tr>
	</thead>	
	<tbody>
		<% for(Turno turno : turnos) { %>
	         <tr>
	            <td><%= turno.getIdTurno() %></td>
	           	<td><%= turno.getPaciente().getNombre() %> <%= turno.getPaciente().getApellido() %></td>              
	            <td><%= turno.getMedico().getNombre() %> <%= turno.getMedico().getApellido() %></td>  
                <td><%= turno.getFechaReserva() %></td>                
                <td><%= turno.getHora() %>hs</td>
                <td><%= turno.getEstadoTurno().getDescripcion() %></td>
                <td><a href="Turnos?op=edit&id=<%= turno.getIdTurno() %>"><i class="fa fa-edit"></i></a></td>            
            </tr>
          <%} %>
	</tbody>

	</table>
	
			<% if(request.getAttribute("success")!=null) {%>
			<div class="success"><%= request.getAttribute("success") %></div>
		<% } %>
		<% if(request.getAttribute("error")!=null) {%>
			<div class="error"><%= request.getAttribute("error") %></div>
		<% } %>
<%}else{
	response.sendRedirect("Login.jsp");
}
%>
</body>
</html>
