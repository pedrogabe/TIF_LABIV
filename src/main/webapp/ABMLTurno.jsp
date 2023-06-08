<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.Turno"%>
<!DOCTYPE html>
<html>
<head>
<%
	String error = (String)request.getAttribute("error");
	ArrayList<Turno> turnos = (ArrayList<Turno>)request.getAttribute("turnos");
%>
<meta charset="ISO-8859-1">
<title>Turnos</title>

<%@include file="Datatable_init.html" %>

</head>
<body>
	<%@include file="Nav.html"%>
	<h2>Alta y Modificación de Turnos</h2>
	<form id="turno" method="post" action="ABMLTurno.jsp">
		<label for="paciente">Paciente:</label>
		<select id="paciente" name="paciente">
			<option value="1">Pepe Zalazar</option>
			<option value="2">Lucia Martinez</option>
			<option value="3">Mariano Benitez</option>
		</select>
		
		<label for="medico">Médico:</label>
		<select name="medico" id="medico">
			<option value="1">Andrés Petronella</option>
			<option value="2">Mariano García</option>
			<option value="3">Estefanía Torres</option>
		</select>
		
		<label for="especialidad">Especialidad:</label>
		<select name="especialidad" id="especialidad">
			<option value="1">Clínico</option>
			<option value="2">Otorrino</option>
			<option value="3">Cardiólogo</option>
		</select>
		
		<label for="fecha">Fecha:</label>
		<input id="fecha" name="fecha" type="date" value="<%= java.time.LocalDate.now().toString() %>"/>
		
		<label for="hora">Hora:</label>
		<select name="hora" id="hora">
			<%for(int i=0; i<24; i++){ %>
				<option value="<%=i%>" <%= i==12 ? "selected" : "" %>><%=i%></option>
			<%} %>
		</select>
		
		
		<input type="submit" form="turno" value="Solicitar turno"/>
	</form>
	<br><br>
	<table datatable="true">
		<thead>
			<tr>
				<td>Id</td>
				<td>Especialidad</td>
				<td>Médico</td>
				<td>Paciente</td>
				<td>Fecha</td>
				<td>Hora</td>
				<td>Estado</td>
				<td>Editar</td>
			</tr>
		</thead>
		<tbody>
			<tr>
				<td>1</td>
				<td>Clínico</td>
				<td>Estefanía Torres</td>
				<td>Mariano Benitez</td>
				<td>2023-07-10</td>
				<td>15</td>
				<td>OCUPADO</td>
				<td><input type="button" value="Editar" name="editar"></input></td>
			</tr>
			<tr>
				<td>2</td>
				<td>Clínico</td>
				<td>Estefanía Torres</td>
				<td>Lucia Martinez</td>
				<td>2023-07-08</td>
				<td>14</td>
				<td>OCUPADO</td>
				<td><input type="button" value="Editar" name="editar"></input></td>
			</tr>
			<tr>
				<td>3</td>
				<td>Cardiólogo</td>
				<td>Andrés Petronella</td>
				<td>Mariano Benitez</td>
				<td>2023-07-10</td>
				<td>16</td>
				<td>OCUPADO</td>
				<td><input type="button" value="Editar" name="editar"></input></td>
			</tr>
			<tr>
				<td>3</td>
				<td>Cardiólogo</td>
				<td>Andrés Petronella</td>
				<td>Mariano Benitez</td>
				<td>2023-06-01</td>
				<td>16</td>
				<td>AUSENTE</td>
				<td><input type="button" value="Editar" name="editar"></input></td>
			</tr>
		</tbody>
	</table>
</body>
</html>