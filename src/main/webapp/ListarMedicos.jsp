<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.io.IOException"%>
<%@ page import="entidad.Medico"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Medicos</title>

<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>

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

	<%
	String error = (String) request.getAttribute("error");
	List<Medico> medicos;
	if (session.getAttribute("medicos") == null) {
		medicos = new ArrayList<Medico>();
		if (error == null)
			error = "Error al cargar el listado de pacientes"; //TODO -> Revisar mensaje
	} else {

		medicos = (List<Medico>) session.getAttribute("medicos");
	}
	%>


	<h1 class="title">Listado de medicos</h1>

	<a href="ServletMedico?op=add" class="p-2 bd-highlight">
		<input class="btn btn-outline-dark" type="submit" name="btnAgregarMedico" value="Agregar Medico">
	</a>

	<br>
	<br>
	<br>

	<table border="1" id="table_id">

		<thead>
			<tr>
				<td><b>DNI</b></td>
				<td><b>Nombre</b></td>
				<td><b>Apellido</b></td>
				<td><b>Sexo</b></td>
				<td><b>Fecha Nacimiento </b></td>
				<td><b>Correo Electronico</b></td>
				<td><b>Direccion</b></td>
				<td><b></b></td>
			</tr>
		</thead>
		<tbody>
			<%
			for (Medico medico : medicos) {
			%>
			<tr>
				<td><%=medico.getDni()%></td>
				<td><%=medico.getNombre()%></td>
				<td><%=medico.getApellido()%></td>
				<td><%=medico.getSexo()%></td>
				<td><%=medico.getFechaNacimiento()%></td>
				<td><%=medico.geteMail()%></td>
				<td><%=medico.getDireccion()%></td>
				<td><a href="ServletMedico?op=edit&dni=<%=medico.getDni()%>"><i
						class="fa fa-edit"></i></a></td>
			</tr>
			<%
			}
			%>
		</tbody>

	</table>

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
<%}else{
	response.sendRedirect("Login.jsp");
}
%>

</body>
</html>
