<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.Turno"%>
<%@ page import="entidad.Especialidad"%>
<!DOCTYPE html>
<html>
<head>

<%
String error = (String) request.getAttribute("error");
ArrayList<Turno> turnos = (ArrayList<Turno>) request.getAttribute("turnos");
%>
<meta charset="ISO-8859-1">
<title>Turnos</title>

<%@include file="Datatable_init.html"%>

</head>

<%
if (request.getSession().getAttribute("Usuario") != null) {
%>

<body>
	<%@include file="Nav.html"%>

	<%
	int especia = 0;
	%>
<form action="ServletMedico" method="post">
	<h2 class="title">Alta y Modificacion de Turnos</h2>
	
		<label for="paciente">Paciente:</label> <select id="paciente"
			name="paciente">
			<option value="1">Pepe Zalazar</option>
			<option value="2">Lucia Martinez</option>
			<option value="3">Mariano Benitez</option>
		</select> <label for="medico">Medico:</label> <select name="medico" id="medico">
			<option value="1">Andres Petronella</option>
			<option value="2">Mariano García</option>
			<option value="3">Estefanía Torres</option>

		</select> <label>Especialidad</label> <select name="selEspecialidad">
			
			<%
			ArrayList<Especialidad> especialidades = null;

			if (request.getAttribute("especialidades") != null) {
				especialidades = (ArrayList<Especialidad>) request.getAttribute("especialidades");

				for (Especialidad especialidad : especialidades) {
			%>
			<option value="<%=especialidad.getIdEspecialidad()%>"
				<%=especia == especialidad.getIdEspecialidad() ? "selected" : ""%>>
				<%=especialidad.getEspecialidad()%>
			</option>
			<%
			}
			%>
			<%
			}
			%>
		</select> <label for="fecha">Fecha:</label> <input id="fecha" name="fecha"
			type="date" value="<%=java.time.LocalDate.now().toString()%>" /> <label
			for="hora">Hora:</label> <select name="hora" id="hora">
			<%
			for (int i = 0; i < 24; i++) {
			%>
			<option value="<%=i%>" <%=i == 12 ? "selected" : ""%>><%=i%></option>
			<%
			}
			%>
		</select> <input type="submit" form="turno" value="Solicitar turno" />
	</form>
	<br>
	<br>
	<table datatable="true">
		<thead>
			<tr>
				<td>Id</td>
				<td>Especialidad</td>
				<td>Medico</td>
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
				<td>Clinico</td>
				<td>Estefania Torres</td>
				<td>Mariano Benitez</td>
				<td>2023-07-10</td>
				<td>15</td>
				<td>OCUPADO</td>
				<td><input type="button" value="Editar" name="editar"></input></td>
			</tr>
			<tr>
				<td>2</td>
				<td>Clinico</td>
				<td>Estefania Torres</td>
				<td>Lucia Martinez</td>
				<td>2023-07-08</td>
				<td>14</td>
				<td>OCUPADO</td>
				<td><input type="button" value="Editar" name="editar"></input></td>
			</tr>
			<tr>
				<td>3</td>
				<td>Cardiologo</td>
				<td>Andrés Petronella</td>
				<td>Mariano Benitez</td>
				<td>2023-07-10</td>
				<td>16</td>
				<td>OCUPADO</td>
				<td><input type="button" value="Editar" name="editar"></input></td>
			</tr>
			<tr>
				<td>3</td>
				<td>Cardiologo</td>
				<td>Andres Petronella</td>
				<td>Mariano Benitez</td>
				<td>2023-06-01</td>
				<td>16</td>
				<td>AUSENTE</td>
				<td><input type="button" value="Editar" name="editar"></input></td>
			</tr>
		</tbody>
	</table>
	<%
	} else {
	response.sendRedirect("Login.jsp");
	}
	%>
</body>
</html>
