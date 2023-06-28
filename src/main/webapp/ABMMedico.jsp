<%@page import="java.time.LocalDate"%>
<%@ page import="java.util.ArrayList"%>
<%@page import="entidad.Nacionalidad"%>
<%@page import="entidad.Provincia"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Usuario"%>
<%@page import="entidad.Especialidad"%>
<%@page import="entidad.Localidad"%>
<%@page import="entidad.Jornada"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Medico</title>

</head>
<body>

<%
	if(request.getSession().getAttribute("Usuario")!=null){
%>
	<%@include file="Nav.html"%>
	<%
	//TODO -> Validar error / success
	String op = request.getAttribute("op") != null ? request.getAttribute("op").toString() : "add";
	String apellido, nombre, dni, eMail, fechaNacimiento, telefono, direccion, sexo, userLogin, password, confPassword;
	apellido = nombre = dni = eMail = fechaNacimiento = telefono = direccion = sexo = userLogin = password = confPassword = "";

	int maxId, loc, nacion, prov, espe, jor;
	maxId = loc = nacion = prov = espe = jor = 0;

	Medico medico = null;
	Usuario usuario = null;

	if (request.getAttribute("medico") != null && request.getAttribute("usuario") != null && !op.equals("add")) {
		try {
			medico = (Medico) request.getAttribute("medico");
			usuario = (Usuario) request.getAttribute("usuario");
			apellido = medico.getApellido();
			nombre = medico.getNombre();
			dni = String.format("%s", medico.getDni());
			eMail = medico.geteMail();
			fechaNacimiento = medico.getFechaNacimiento().toString().substring(0, 10);
			loc = medico.getLocalidad().getIdLocalidad();
			espe = medico.getEspecialidad().getIdEspecialidad();
			nacion = medico.getNacionalidad().getIdNacionalidad();
			prov = medico.getProvincia().getIdProvincia();
			sexo = medico.getSexo();
			telefono = medico.getTelefono();
			direccion = medico.getDireccion();
			userLogin = usuario.getUserLogin();
			password = usuario.getPassword();
			jor = medico.getJornada().getId();
		} catch (Exception e) {
			if (request.getAttribute("error") == null)
		request.setAttribute("error", "Hubo inconvenientes al procesar los datos");
		}
	}
	%>
	<form id="formPpal" action="ServletMedico" method="post">

		<h2 class="title">Alta y Modificacion de Medicos</h2>
		<br>
		
		<div class="formulario">
		
		<div>
		<table>
			<tr>
                <td><label>DNI</label></td>
                <td><input type="number" min=1000000 min=0 name="txtDni" value="<%= dni %>" required <%= op.equals("add") ? "" : "disabled" %>><input type="hidden" name="txtDniHide" value="<%= dni %>" ></td>
            </tr>			
			<tr>
				<td><label>Nombre</label></td>
				<td><input type="text" name="txtNombre" pattern="[a-z A-Z]+" value="<%= nombre %>" required></td>
			</tr>
			<tr>
				<td><label>Apellido</label></td>
				<td><input type="text" name="txtApellido"  pattern="[a-z A-Z]+" value="<%= apellido %>" required></td>
			</tr>
			<tr>
				<td><label>Especialidad</label></td>
				<td><select name="selEspecialidad">
						<%
						ArrayList<Especialidad> especialidades = null;

						if (request.getAttribute("especialidades") != null) {
							especialidades = (ArrayList<Especialidad>) request.getAttribute("especialidades");
						
						for (Especialidad espec : especialidades) {
						%>
						<option  value="<%=espec.getIdEspecialidad()%>"  <%= espe == espec.getIdEspecialidad() ? "selected" : "" %>>
							<%=espec.getEspecialidad()%>
						</option>
						
						<%
						}}
						%>
				</select></td>
			</tr>
			<tr>
				<td><label>Nacionalidad</label></td>
				<td><select name="selNacionalidad">
						<%
						ArrayList<Nacionalidad> nacionalidades = null;

						if (request.getAttribute("nacionalidades") != null) {
							nacionalidades = (ArrayList<Nacionalidad>) request.getAttribute("nacionalidades");
						
						for (Nacionalidad nacionalidad : nacionalidades) {
						%>
						<option  value="<%=nacionalidad.getIdNacionalidad()%>"  <%= nacion == nacionalidad.getIdNacionalidad() ? "selected" : "" %>>
							<%=nacionalidad.getNacionalidad()%>
						</option>
						
						<%
						}}
						%>
				</select></td>
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
				<td><label>Jornada</label></td>
				<td><select name="selJornada">
						<%
						ArrayList<Jornada> jornadas = null;

						if (request.getAttribute("jornadas") != null) {
							jornadas = (ArrayList<Jornada>) request.getAttribute("jornadas");
						
						for (Jornada jornada : jornadas) {
						%>
							<option value="<%=jornada.getId()%>" <%= jor == jornada.getId() ? "selected" : "" %>>
								<%=jornada.getDescripcion() %>
							</option>
						<%
						}}
						%>
				</select></td>
			</tr>
			<tr>
				<td><label>Fecha Nacimiento</label></td>
				<td><input type="date" name="txtFecNacimiento" value="<%= fechaNacimiento %>"
					max="<%= LocalDate.now().toString() %>" required></td>
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
							<option value="<%=provincia.getIdProvincia()%>" <%= prov == provincia.getIdProvincia() ? "selected" : "" %>>
								<%=provincia.getProvincia()%>
							</option>
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
                        <option value="<%=localidad.getIdLocalidad()%>" provincias="<%=localidad.getProvincia().getIdProvincia() %>" <%= loc == localidad.getIdLocalidad() ? "selected" : "" %>>
                        	<%=localidad.getLocalidad()%>
                        </option>
						
                        <%
                        }}
                        %>
                </select></td>
            </tr>
			<tr>
				<td><label>Direccion</label></td>
				<td><input type="text" name="txtDireccion" value="<%= direccion %>" required></td>
			</tr>
			<tr>
				<td><label>Correo Electronico</label></td>
				<td><input type="email" name="txtEmail" pattern=".{1,}@.{1,}\.com.{0,}$" value="<%= eMail %>" required></td>
			</tr>
			<tr>
				<td><label>Telefono</label></td>
				<td><input type="number" name="txtTelefono" min=10000000 value="<%= telefono %>" required></td>
			</tr>
			<tr>
				<td><label>UsuarioLogin</label></td>
				<td><input type="text" name="txtUserLogin" value="<%= userLogin %>" required></td>
			</tr>
			<tr>
				<td><label>Password</label></td>
				<td><input type="password" name="txtPassword" id="txtPassword" value="<%= password %>" required></td>
			</tr>
			<tr>
				<td><label>Confirmar Password</label></td>
				<td><input type="password" name="txtConfPassword" id="txtConfPassword" placeholder="Confirmar Password" value="<%= confPassword %>" required></td>
			</tr>
		</table>
		</div>
		
		<div  class="pt-4 w-25 d-flex justify-content-around" >

				<% if(op.equals("add")) {%>
					<input class="btn btn-outline-success" type="submit" name="btnGrabar" value="Grabar">
				<% } else { %>
					<input class="btn btn-outline-primary" type="submit" name="btnActualizar" value="Grabar">
					<input class="btn btn-outline-danger" type="submit" name="btnEliminar" value="Eliminar">
				<% } %>

		</div>

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
		</div>
	</form>
	<script type="text/javascript">
	
	window.onload = function () {
        var txtPassword = document.getElementById("txtPassword");
        var txtConfirmPassword = document.getElementById("txtConfPassword");
        txtPassword.onchange = ConfirmPassword;
        txtConfirmPassword.onkeyup = ConfirmPassword;
        function ConfirmPassword() {
            txtConfirmPassword.setCustomValidity("");
            if (txtPassword.value != txtConfirmPassword.value) {
                txtConfirmPassword.setCustomValidity("Passwords do not match.");
            }
        }
    }
	
	const desplegableA = document.getElementById('selProvincia');
	desplegableA.addEventListener('change', filtrarDesplegableB);

	function filtrarDesplegableB(forzarSeleccion = true) {
	 
	  const valorSeleccionado = desplegableA.value;

	  const desplegableB = document.getElementById('selLocalidad');
	  Array.from(desplegableB.options).forEach(option => {
	    if (option.getAttribute('provincias') === valorSeleccionado) {
	      option.style.display = 'block';
	      if(forzarSeleccion)
		      	option.setAttribute('selected',true);
	    } else {
	      	option.style.display = 'none';
	    	if(forzarSeleccion)
		    	  option.removeAttribute('selected');
	    }
	  });
	}

	
	filtrarDesplegableB(false);
	
	
	
	document.getElementById('formPpal').addEventListener('submit', function(event) {
  		
		var msg = "¿Seguro que desea continuar?";
  		
 		if (event.submitter.name === 'btnGrabar') 
	    	msg = "¿Seguro que desea crear el nuevo medico con los datos ingresados?";
 		if (event.submitter.name === 'btnEliminar') 
 			msg = "¿Seguro que desea eliminar el medico seleccionado?";
 		if (event.submitter.name === 'btnActualizar') 
 			msg = "¿Seguro que desea actualizar los datos del medico seleccionado?"
 			
 		if(!confirm(msg))
 			event.preventDefault();
	});


	</script>

<%}else{
	response.sendRedirect("Login.jsp");
}
%>

</body>
</html>
