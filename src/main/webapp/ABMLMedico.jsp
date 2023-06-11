
<%@ page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Medico</title>

</head>
<body>
	<%@include file="Nav.html"%>
	
	<form action="servletMedico" method="post">
		
		<h2>Alta y Modificación de Medicos</h2>
		<br>
		<table>
			<tr>
				<td><label>Id Medico</label></td>
				<%
				String numId = "0";
				if (request.getAttribute("MaxIdPaciente") != null) {
					numId = request.getAttribute("MaxIdPaciente").toString();
				%>
				<td><input disabled type="number" value="<%=numId%>"
					name="txtIdPaciente"></td>
				<%
				} else {
				%>
				<td><input disabled type="number" value="<%=numId%>"
					name="txtIdPaciente"></td>
				<%
				}
				%>
			</tr>
			<tr>
				<td><label>DNI</label></td>
				<td><input type="text" name="txtApellido" value="" required></td>
			</tr>
			<tr>
				<td><label>Nombre</label></td>
				<td><input type="text" name="txtNombre" value="" required></td>
			</tr>
			<tr>
				<td><label>Apellido</label></td>
				<td><input type="text" name="txtApellido" value="" required></td>
			</tr>			
			<tr>
				<td><label>Nacionalidad</label></td>
				<td><input type="text" name="txtNacionalidad" value="" required></td>
			</tr>
			<tr>
				<td><label>Sexo</label></td>
				<td><select name="selSexo">
						<option value="0">No indica</option>
						<option value="1">Femenino</option>
						<option value="2">Masculino</option>
				</select></td>
			</tr>
			<tr>
				<td><label>Fecha Nacimiento</label></td>
				<td><input type="date" name="txtFecNacimiento" value="" required></td>
			</tr>
			<tr>
				<td><label>Direccion</label></td>
				<td><input type="text" name="txtDireccion" value="" required></td>
			</tr>
			<tr>
				<td><label>Localidad</label></td>
				<td><input type="text" name="txtLocalidad" value="" required></td>
			</tr>
			<tr>
				<td><label>Provincia</label></td>
				<td><input type="text" name="txtProvincia" value="" required></td>
			</tr>
			<tr>
				<td><label>Corro Electronico</label></td>
				<td><input type="email" name="txtEmail" value="" required></td>
			</tr>
			<tr>
				<td><label>Telefono</label></td>
				<td><input type="text" name="txtTelefono" value="" required></td>
			</tr>
			<tr>
				<td><label>Especialidad</label></td>
				<td><select name="selEspecialidad">
						<option value="0">No indica</option>
				</select></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="btnBuscar" value="Buscar"></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="btnAgregar" value="Agregar"></td>
			</tr>
		</table>
		
		<% if(request.getAttribute("success")!=null) {%>
			<div class="success"><%= request.getAttribute("success") %></div>
		<% } %>
		<% if(request.getAttribute("error")!=null) {%>
			<div class="error"><%= request.getAttribute("error") %></div>
		<% } %>
	</form>
</body>
</html>