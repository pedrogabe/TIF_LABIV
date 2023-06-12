<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Listado de Pacientes</title>

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


<%@include file="Datatable_init.html" %>

</head>
<body>
	<%@include file="Nav.html"%>

	<h1>Listado de pacientes</h1>
	
	<a href="Pacientes?op=add"><input type="button" name="btnAgregarPaciente" value="Agregar Paciente"></a>
	
	<br><br><br>

	<table border="1" id="table_id" datatable="true">
	
	<thead>	
		<tr>
			<td><b>Id</b></td>
			<td><b>DNI</b></td>
			<td><b>Nombre</b></td>
			<td><b>Apellido</b></td>
			<td><b>Sexo</b></td>
			<td><b>Fecha Nacimiento	</b></td>
			<td><b>Correo Electronico</b></td>
			<td><b>Direccion</b></td>
			<td><b></b></td>
		</tr>
	</thead>	
	<tbody>
	         <tr>
	            <td>61</td>
	            <td>32.080.023</td>
                <td>Prescott</td>
                <td>Bartlett</td>
              	<td>F</td>
                <td>2011-04-25</td>
                <td>mail@email.com</td>
                <td>Calle 234</td>
                <td><i class="fa fa-trash"></i></td>            
            </tr>
            <tr>
                <td>443</td>
                <td>17.075.320</td>
                <td>Olivia </td>
                <td>Lang</td>
                <td>M</td>
                <td>2011-07-25</td>
                <td>mail@email.com</td>
                <td>Avenida 432</td> 
                <td><button class="fa fa-edit"></button><button class="fa fa-trash"></button></td>                     
            </tr>
	</tbody>

	</table>

</body>
</html>