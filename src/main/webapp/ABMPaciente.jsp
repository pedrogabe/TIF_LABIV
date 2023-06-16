
<%@ page import="java.util.ArrayList"%>

<%@ page import="java.util.List"%>


<%@page import="entidad.Provincia"%>
<%@page import="entidad.Paciente"%>
<%@page import="entidad.Localidad"%>

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
<%
        //TODO -> Validar error / success
        String op = request.getAttribute("op") != null ? request.getAttribute("op").toString() : "add";
        String apellido, nombre, dni, eMail, fechaNacimiento,  telefono, direccion, sexo;
        apellido = nombre = dni = eMail = fechaNacimiento = telefono = direccion = sexo = "";

        int maxId , loc, nacion, prov;
        maxId = loc = nacion = prov = 0;

        Paciente paciente = null;

        if(request.getAttribute("paciente") != null || !op.equals("add")){
            try{
                paciente = (Paciente)request.getAttribute("paciente");                
                apellido = paciente.getApellido();
                nombre = paciente.getNombre();
                dni = String.format("%s", paciente.getDni());
                eMail = paciente.geteMail();
                fechaNacimiento = paciente.getFechaNacimiento().toString();
                loc = paciente.getLocalidad().getIdLocalidad();
                nacion = paciente.getNacionalidad().getIdNacionalidad();
                prov = paciente.getProvincia().getIdProvincia();
                sexo = paciente.getSexo();
                telefono = paciente.getTelefono();
                direccion = paciente.getDireccion();
            }catch(Exception e){
                request.setAttribute("error", "Hubo inconvenientes al procesar los datos");
            }
        }

    %>
	<form action="ServletPaciente" method="post">

		<h2>Alta y Modificación de Pacientes</h2>
		<br>
		<table>
			<tr>
                <td><label>DNI</label></td>
                <td><input type="text" name="txtDni" value="<%= dni %>" required <%= op.equals("add") ? "" : "disabled" %>></td>
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
				<td><select name="selProvincia" Id="selProvincia">
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
                <td><select name="selLocalidad" Id="selLocalidad">
                        <%
                        ArrayList<Localidad> localidades = null;

                        if (request.getAttribute("localidades") != null) {
                            localidades = (ArrayList<Localidad>) request.getAttribute("localidades");

                        for (Localidad localidad : localidades) {
                        %>
                        <option value="<%=localidad.getIdLocalidad()%>" provincias="<%=localidad.getProvincia().getIdProvincia() %>"><%=localidad.getLocalidad()%></option>
						
                        <%
                        }}
                        %>
                </select></td>
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
	<script type="text/javascript">
	
	const desplegableA = document.getElementById('selProvincia');
	desplegableA.addEventListener('change', filtrarDesplegableB);
	
	function filtrarDesplegableB() {
	 
	  const valorSeleccionado = desplegableA.value;

	  const desplegableB = document.getElementById('selLocalidad');
	  Array.from(desplegableB.options).forEach(option => {
	    if (option.getAttribute('provincias') === valorSeleccionado) {
	      option.style.display = 'block';
	      option.setAttribute('selected',true);
	    } else {
	      option.style.display = 'none';
	    }
	  });
	}
	
	</script>
</body>
</html>