
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="entidad.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paciente</title>
<style>
	<%@include file="estilo.css" %>
</style>
</head>
<body>
	<%@include file="Nav.html"%>
	
	<%
		//TODO -> Validar error / success
		String op = request.getAttribute("op") != null ? request.getAttribute("op").toString() : "add";
		String apellido, nombre, dni, eMail, fechaNacimiento,  telefono, direccion, sexo;
		apellido = nombre = dni = eMail = fechaNacimiento = telefono = direccion = sexo = "";
		
		int maxId , localidad, nacionalidad, provincia;
		maxId = localidad = nacionalidad = provincia = 0;
		
		Paciente paciente = null;
		if(op.equals("add") && request.getAttribute("maxIdPaciente")!=null){
			try{
				maxId = (int)request.getAttribute("maxIdPaciente");
				maxId++;
			}catch(Exception e){
				request.setAttribute("error", "400"); //TODO -> Cambiar mensaje
			}
		}
		if(request.getAttribute("paciente") != null || !op.equals("add")){
			try{
				paciente = (Paciente)request.getAttribute("paciente");
				maxId = paciente.getId();
				apellido = paciente.getApellido();
				nombre = paciente.getNombre();
				dni = String.format("%s", paciente.getDni());
				eMail = paciente.geteMail();
				fechaNacimiento = paciente.getFechaNacimiento().toString();
				localidad = paciente.getLocalidad().getIdLocalidad();
				nacionalidad = paciente.getNacionalidad().getIdNacionalidad();
				provincia = paciente.getProvincia();
				sexo = paciente.getSexo();
				telefono = paciente.getTelefono();
				direccion = paciente.getDireccion();
			}catch(Exception e){
				request.setAttribute("error", "Hubo inconvenientes al procesar los datos");
			}
		}
			
	%>
	
	<form action="Pacientes" method="post">
		<input type="hidden" name="hfId" value="<%= maxId %>"/>
		<h2>Alta y Modificación de Pacientes</h2>
		<br>
		<table>
			<tr>
				<td><label>DNI</label></td>
				<td><input type="text" name="txtDni" value="<%= dni %>" required <%= op.equals("add") ? "" : "disabled" %>></td>
			</tr>
			<tr>
				<td><label>Nombre</label></td>
				<td><input type="text" name="txtNombre" value="<%= nombre %>" required></td>
			</tr>
			<tr>
				<td><label>Apellido</label></td>
				<td><input type="text" name="txtApellido" value="<%= apellido %>" required></td>
			</tr>
			<tr>
				<td><label>Fecha Nacimiento</label></td>
				<td><input type="date" name="txtFecNacimiento" value="<%= fechaNacimiento %>" required></td>
			</tr>
			<tr>
				<td><label>Sexo</label></td>
				<td><select name="selSexo">
						<option value="No indica" <%= sexo.equals("No indica") ? "selected" : "" %>>No indica</option>
						<option value="Femenino" <%= sexo.equals("Femenino") ? "selected" : "" %>>Femenino</option>
						<option value="Masculino" <%= sexo.equals("Masculino") ? "selected" : "" %>>Masculino</option>
				</select></td>
			</tr>
			<tr>
				<td><label>Direccion</label></td>
				<td><input type="text" name="txtDireccion" value="<%= paciente.getDireccion() %>" required></td>
			</tr>	
			<tr>
				<td><label>Nacionalidad</label></td>
				<td><select name="txtNacionalidad" required>
				<% if(request.getAttribute("nacionalidades")!=null) for(Nacionalidad nac : (List<Nacionalidad>)request.getAttribute("nacionalidades")) { %>
						<option value="<%= nac.getIdNacionalidad() %>" <%= nacionalidad == nac.getIdNacionalidad() ? "selected" : "" %>>
							<%= nac.getNacionalidad() %>
						</option>
				<% } %>
				</select></td>
			</tr>
			<tr>
				<td><label>Provincia</label></td>
				<td><select name="txtProvincia" required>
				<% if(request.getAttribute("provincias")!=null) for(Provincia prov : (List<Provincia>)request.getAttribute("provincias")) { %>
						<option value="<%= prov.getIdProvincia()() %>" <%= provincia == prov.getIdProvincia() ? "selected" : "" %>>
							<%= prov.getProvincia() %>
						</option>
				<% } %>
				</select></td>
			</tr>
			<tr>
				<td><label>Localidad</label></td>
				<td><select name="txtLocalidad" required>
				<% if(request.getAttribute("localidades")!=null) for(Localidad loc : (List<Localidad>)request.getAttribute("localidades")) { %>
						<option value="<%= loc.getIdNacionalidad() %>" <%= localidad == loc.getIdLocalidad() ? "selected" : "" %>>
							<%= loc.getLocalidad() %>
						</option>
				<% } %>
				</select></td>
			</tr>
			<tr>
				<td><label>Correo Electrónico</label></td>
				<td><input type="email" name="txtEmail" value="<%= eMail %>" required></td>
			</tr>
			<tr>
				<td><label>Telefono</label></td>
				<td><input type="text" name="txtTelefono" value="<%= telefono %>" required></td>
			</tr>
			<tr>
				<% if(op.equals("add")) {%>
					<td><input type="submit" name="btnGrabar" value="Grabar"></td>
				<% } else { %>
					<td><input type="submit" name="btnActualizar" value="Grabar"></td>
					<td><input type="submit" name="btnEliminar" value="Eliminar"></td>
				<% } %>
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