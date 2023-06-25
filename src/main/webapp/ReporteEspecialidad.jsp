<%@page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="entidad.ReporteEspecialidad"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Reporte Especialidades</title>
</head>
<body>
<%@include file="Nav.html"%>
	<h1 class="title">Reporte Especialidades</h1>

	<form action="ServletRepEspecialidad" method=post>
		<label for="mesEspecialidad">Seleccione un Mes:</label> <input
			type="month" id="mesEspecialidad" name="mesEspecialidad"> <input
			type="submit">
	</form>

	<br>
	<br>
	<br>

	<table border="1" id="table_id">
		<%
		if (request.getAttribute("repEspecialidad") != null) {
			ReporteEspecialidad repEsp = (ReporteEspecialidad) request.getAttribute("repEspecialidad");
		%>
		<thead>
			<tr>
				<%
				for (int numCol = 0; numCol < repEsp.getTotalColumnas(); numCol++) {
				%>
				<td><b> <%=repEsp.getColumnas().get(numCol)%></b></td>
				<%
				}
				%>
			</tr>
		</thead>
		<tbody>
			<%
			int indiceValor = 0;
			for (int numFila = 0; numFila < repEsp.getTotalFilasPorColumnas(); numFila++) {
			%>
			<tr>
				<%
				for (int numVal = 0; numVal < repEsp.getTotalColumnas(); numVal++) {
				%>
				<td><%=repEsp.getValorIndex(indiceValor)%></td>
				<%
				indiceValor++;
				}
				%>
			</tr>
			<%
			}
			%>
		</tbody>
		<%
		}
		%>
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

</body>
</html>