<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.Paciente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>
	<%@include file="estilo.css" %>
</style>
<title>Listado de Pacientes</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<%@include file="Datatable_init.html" %>

</head>
<body>
	<%@include file="Nav.html"%>
	
	<%
		String error = (String)request.getAttribute("error");
		List<Paciente> pacientes;
		if(session.getAttribute("pacientes") == null){
			pacientes = new ArrayList<Paciente>();
			if(error==null)
				error = "Error al cargar el listado de pacientes"; //TODO -> Revisar mensaje
		}
		else{
			pacientes = (List<Paciente>)session.getAttribute("pacientes");
		}
	%>

	<h1>Listado de pacientes</h1>
	
	<a href="servletPaciente?op=add"><input type="button" name="btnAgregarPaciente" value="Agregar Paciente"></a>
	
	<br><br><br>

	<table border="1" id="table_id" datatable="true">
	
	<thead>	
		<tr>
			<td><b>DNI</b></td>
			<td><b>Nombre</b></td>
			<td><b>Apellido</b></td>
			<td><b>Sexo</b></td>
			<td><b>Fecha Nacimiento	</b></td>
			<td><b>Correo Electronico</b></td>
			<td><b>Direccion</b></td>
			<td><b></b></td>
		</tr>
	</thead>	
	<tbody>
		<% for(Paciente paciente : pacientes) { %>
	         <tr>
	            <td><%= paciente.getDni() %></td>
                <td><%= paciente.getNombre() %></td>
                <td><%= paciente.getApellido() %></td>
              	<td><%= paciente.getSexo() %></td>
                <td><%= paciente.getFechaNacimiento() %></td>
                <td><%= paciente.geteMail() %></td>
                <td><%= paciente.getDireccion() %></td>
                <td><a href="servletPaciente?op=edit&dni=<%= paciente.getDni() %>"><i class="fa fa-edit"></i></a></td>            
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

</body>
</html>