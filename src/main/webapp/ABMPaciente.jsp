
<%@ page import="java.util.ArrayList"%>

<%@ page import="java.util.List"%>


<%@page import="entidad.Provincia"%>
<%@page import="dao.ProvinciaDao"%>
<%@page import="daoImpl.ProvinciaDaoImpl"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Paciente</title>

</head>
<body>
	<%@include file="Nav.html"%>

	<form action="ServletPaciente" method="post">

		<h2>Alta y Modificación de Pacientes</h2>
		<br>
		<table>
			<tr>
				<td><label>Id Paciente</label></td>
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
				<td><input type="date" name="txtFecNacimiento" value=""
					required></td>
			</tr>

			<tr>
				<td><label>Provincia</label></td>
				<td><select name="selProvincia">
						<%
						ArrayList<Provincia> provincias = null;

						if (request.getAttribute("provincias") != null) {
							provincias = (ArrayList<Provincia>) request.getAttribute("provincias");
						
						for (Provincia provincia : provincias) {
						%>
						<option value="<%=provincia.getIdProvincia()%>"><%=provincia.getProvincia()%></option>

						<%
						}}
						%>
				</select></td>
			</tr>

			<tr>
				<td><label>Localidad</label></td>
				<td><input type="text" name="txtLocalidad" value="" required></td>
			</tr>
			<tr>
				<td><label>Direccion</label></td>
				<td><input type="text" name="txtDireccion" value="" required></td>
			</tr>
			<tr>
				<td><label>Correo Electronico</label></td>
				<td><input type="email" name="txtEmail" value="" required></td>
			</tr>
			<tr>
				<td><label>Telefono</label></td>
				<td><input type="text" name="txtTelefono" value="" required></td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" name="btnBuscar" value="Buscar"></td>
				<td><input type="submit" name="btnGrabar" value="Grabar"></td>
			</tr>
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
	</form>
</body>
</html>