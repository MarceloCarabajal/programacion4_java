<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.Seguro" %>
<%@ page import="entidad.TipoSeguro" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Listar Seguros</title>
</head>
<body>

<a href="Inicio.jsp">Inicio</a>
<a href="servletAgregarSeguro">Agregar Seguros</a>
<a href="ServletListarSeguros">Listar Seguros</a>

<h1>"Tipo de seguros en la base de datos"</h1>

<%
	List<Seguro> listaSeguros = (List<Seguro>) request.getAttribute("listaSeguros");
	List<TipoSeguro> listaTipos = (List<TipoSeguro>) request.getAttribute("listaTipos");
	Object tipoSeleccionado = request.getAttribute("tipoSeleccionado");
	int idSeleccionado = tipoSeleccionado != null ? (Integer) tipoSeleccionado : 0;
%>

<form method="get" action="ServletListarSeguros">
	Busqueda por tipo de seguros:
	<select name="ddlTipoSeguro">
		<option value="0">Todos</option>
		<%
			if (listaTipos != null) {
				for (TipoSeguro tipo : listaTipos) {
		%>
			<option value="<%= tipo.getIdTipo() %>" <%= tipo.getIdTipo() == idSeleccionado ? "selected" : "" %>><%= tipo.getDescripcion() %></option>
		<%
				}
			}
		%>
	</select>
	<input type="submit" name="btnFiltrar" value="Filtrar">
</form>

<br>

<table border="1">
	<tr>
		<th>ID Seguro</th>
		<th>Descripción Seguro</th>
		<th>Descripción Tipo Seguro</th>
		<th>Costo Contratación</th>
		<th>Costo Máximo Asegurado</th>
	</tr>
	<%
		if (listaSeguros != null) {
			for (Seguro seguro : listaSeguros) {
	%>
	<tr>
		<td><%= seguro.getIdSeguro() %></td>
		<td><%= seguro.getDescripcion() %></td>
		<td><%= seguro.getTipoSeguro().getDescripcion() %></td>
		<td><%= seguro.getCostoContratacion() %></td>
		<td><%= seguro.getCostoAsegurado() %></td>
	</tr>
	<%
			}
		}
	%>
</table>

</body>
</html>
