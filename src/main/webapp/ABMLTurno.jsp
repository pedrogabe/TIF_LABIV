<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.util.ArrayList"%>

<%@ page import="entidad.Turno"%>
<%@ page import="entidad.Especialidad"%>
<%@page import="entidad.Medico"%>
<%@page import="entidad.Paciente"%>
<%@page import="entidad.EstadoTurno"%>


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
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

</head>

<%
if (request.getSession().getAttribute("Usuario") != null) {
%>

<body>
	<%@include file="Nav.html"%>

<%
        String op = request.getAttribute("op") != null ? request.getAttribute("op").toString() : "add";

        String dniPaciente, nombrePaciente, apellidoPaciente, fechaReserva, observacion;
        dniPaciente = nombrePaciente = apellidoPaciente = fechaReserva = observacion = "";

        int med, pac, esp, estadoT, hora;
         med = esp = estadoT = hora = 0;

        Turno turno = null;
        Especialidad especialidad_aux = null;
        Paciente paciente_aux = null;

        if(request.getAttribute("turno") != null || !op.equals("add")){
            try{
                turno = (Turno)request.getAttribute("turno");
                
                dniPaciente = String.format("%s", turno.getPaciente().getDni());

                nombrePaciente = paciente_aux.getNombre();
                apellidoPaciente = paciente_aux.getApellido();
                
                med = turno.getMedico().getDni();
                esp = especialidad_aux.getIdEspecialidad();
                fechaReserva = turno.getFechaReserva().toString().substring(0,10);
                observacion = turno.getObservacion();
                estadoT = turno.getEstadoTurno().getIdEstadoTurno();
                hora = turno.getHora();
            }catch(Exception e){
            	if(request.getAttribute("error")==null)
                	request.setAttribute("error", "Hubo inconvenientes al procesar los datos");
            }
        }

    %>
    
<form action="ServletTurno" method="post">
	<h2 class="title">Alta y Modificacion de Turnos</h2>
	
		<div class="formulario">
		
		<div>
		<table>
			<tr>
                <td><label>Dni Paciente</label></td>
                <td><input type="text" name="txtDniPaciente" value="<%= dniPaciente %>" required <%= op.equals("add") ? "" : "disabled" %>><input type="hidden" name="txtDniHide" value="<%= dniPaciente %>" ></td>
                 <td><a><i class="fa fa-search"></i></a></td>            
            </tr>
            
			<tr>
                <td><label>Nombre </label></td>
                <td><input type="text" name="txtNombrePaciente" value="<%= nombrePaciente %>" required <%= op.equals("add") ? "" : "disabled" %>><input type="hidden" name="txtDniHide" value="<%= nombrePaciente %>" ></td>
            </tr>
            
            <tr>
                <td><label>Apellido </label></td>
                <td><input type="text" name="txtApellidoPaciente" value="<%= apellidoPaciente %>" required <%= op.equals("add") ? "" : "disabled" %>><input type="hidden" name="txtDniHide" value="<%= apellidoPaciente %>" ></td>
            </tr>
			
			<tr>
				<td><label>Especialidad</label></td>
				<td>
					<select name="selEspecialidad">
						<%
						ArrayList<Especialidad> especialidades = null;

						if (request.getAttribute("especialidades") != null) {
							especialidades = (ArrayList<Especialidad>) request.getAttribute("especialidades");
						
						for (Especialidad especialidad : especialidades) {
						%>
						<option  value="<%=especialidad.getIdEspecialidad()%>"  <%= esp == especialidad.getIdEspecialidad() ? "selected" : "" %>>
							<%=especialidad.getEspecialidad()%>
						</option>
						
						<%
						}}
						%>
					</select>
				</td>
			</tr>
			
					
			<tr>
				<td><label>Medico</label></td>
				<td>
					<select name="selMedico">
						<%
						ArrayList<Medico> medicos = null;

						if (request.getAttribute("medicos") != null) {
							medicos = (ArrayList<Medico>) request.getAttribute("medicos");
						
						for (Medico medico : medicos) {
						%>
						<option  value="<%=medico.getDni()%>"  <%= med == medico.getDni() ? "selected" : "" %>>
							<%=medico.getDni()%>
						</option>
						
						<%
						}}
						%>
					</select>
				</td>
			</tr>
						
			<tr>
				<td><label>Fecha de Reserva</label></td>
				<td><input type="date" name="txtFechaReserva" value="<%= fechaReserva %>" required></td>
			</tr>
			
			<tr>
				<td><label>Hora</label></td>
				<td><input type="int" name="txtHora" value="<%= hora %>" required></td>
			</tr>
			
			<tr>
				<td><label>Observaciones</label></td>
				<td><input type="text" name="txtObservacion" value="<%= observacion %>" required></td>
			</tr>

			<tr>
				<td><label>Estado</label></td>
				<td>
					<select name="selEstadoTurno">
						<%
						ArrayList<EstadoTurno> estadoTurnos = null;

						if (request.getAttribute("estadoTurnos") != null) {
							estadoTurnos = (ArrayList<EstadoTurno>) request.getAttribute("estadoTurnos");
						
						for (EstadoTurno estadoTurno : estadoTurnos) {
						%>
						<option  value="<%=estadoTurno.getIdEstadoTurno()%>"  <%= estadoT == estadoTurno.getIdEstadoTurno() ? "selected" : "" %>>
							<%=estadoTurno.getIdEstadoTurno()%>
						</option>
						
						<%
						}}
						%>
					</select>
				</td>
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
	</script>
	
	<%
	} else {
	response.sendRedirect("Login.jsp");
	}
	%>
</body>
</html>
