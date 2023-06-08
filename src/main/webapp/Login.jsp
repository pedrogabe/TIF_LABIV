<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>LOGON</title>
</head>
<body>
	<h1>Iniciar sesion</h1>
	<form method="post" action="Login.jsp">
		<label for="username">Nombre de usuario:</label><br>
		<input type="text" id="username" name="username"><br>
		<label for="password">Contraseña:</label><br>
		<input type="password" id="password" name="password"><br><br>
		<input type="submit" value="Iniciar sesion">
	</form>
</body>
</html>