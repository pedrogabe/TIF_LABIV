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
<link href="estilo.css" rel="stylesheet">
<link rel="stylesheet" type="text/css"
	href="https://cdn.datatables.net/1.13.4/css/jquery.dataTables.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.4/jquery.min.js"></script>
<script type="text/javascript" charset="utf8"
	src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>


<script type="text/javascript">
	$(document).ready(function() {
		$('#table_id').DataTable();
		$('#table_det').DataTable();
	});
</script>
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
	<%
	if (request.getAttribute("repEspecialidad") != null) {
		ReporteEspecialidad repEsp = (ReporteEspecialidad) request.getAttribute("repEspecialidad");
	%>
	<div class="div_center">
		<table border="1" id="table_id">

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

		</table>
	</div>
	<br>
	<div class="div_center">
		<table border="1" id="table_det">

			<thead>
				<tr>
					<%
					for (int numCol = 0; numCol < repEsp.getTotalColumnasDet(); numCol++) {
					%>
					<td><b> <%=repEsp.getColumnasDet().get(numCol)%></b></td>
					<%
					}
					%>
				</tr>
			</thead>
			<tbody>
				<%
				int indiceValorDet = 0;
				for (int numFila = 0; numFila < repEsp.getTotalFilasPorColumnasDet(); numFila++) {
				%>
				<tr>
					<%
					for (int numVal = 0; numVal < repEsp.getTotalColumnasDet(); numVal++) {
					%>
					<td><%=repEsp.getValorDetIndex(indiceValorDet)%></td>
					<%
					indiceValorDet++;
					}
					%>
				</tr>
				<%
				}
				%>
			</tbody>

		</table>
	</div>
	<%
	}
	%>

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