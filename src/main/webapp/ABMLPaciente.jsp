
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.Paciente" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paciente</title>

</head>
<body>
	<%@include file="Nav.html"%>
	
	<%
		//TODO -> Validar error / success
		String op = request.getAttribute("op").toString();
		int maxId = 0;
		Paciente paciente;
		if(op.equals("add")){
			try{
				maxId = (int)request.getAttribute("maxIdPaciente");
			}catch(Exception e){
				request.setAttribute("error", "Hubo inconvenientes al procesar los datos");
			}
		}else{
			try{
				paciente = (Paciente)request.getAttribute("paciente");
				maxId = paciente.getDni();
			}catch(Exception e){
				request.setAttribute("error", "Hubo inconvenientes al procesar los datos");
			}
		}
			
	%>
	
	<form action="servletPaciente" method="post">
		
		<h2>Alta y Modificación de Pacientes</h2>
		<br>
		<table>
			<tr>
				<td><label>Id Paciente</label></td>
				<td><input disabled type="number" value="<%=maxId%>"
					name="txtIdPaciente"></td>
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
				<td><input type="submit" name="btnGrabar" value="Grabar"></td>
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