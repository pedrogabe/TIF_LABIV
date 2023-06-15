<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGIN</title>

</head>
<body>
<div align=left>
	<h1>Iniciar sesion</h1>
</div>
	<form action=servletLogin method=post>
		
		<table>
			<tr><td>Usuario: </td><td><input type=text name= txtUsuario></td></tr>
		    <tr><td>Contraseña: </td><td><input type=password name= txtContraseña></td></tr>
		    <tr><td><input type=submit value=Login></td><td><input type=reset></td></tr>
		</table>
	
	
	</form>
	
	
	
</body>
</html>
