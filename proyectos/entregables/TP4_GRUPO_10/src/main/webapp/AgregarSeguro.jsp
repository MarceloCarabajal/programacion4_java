<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="entidad.TipoSeguro" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Agregar Seguro</title>
</head>
<body>

<a href="Inicio.jsp">Inicio</a>
<a href="servletAgregarSeguro">Agregar Seguros</a>
<a href="ServletListarSeguros">Listar Seguros</a>

<h1>Agregar Seguros</h1>

<%
	String mensaje = (String) request.getAttribute("mensaje");
	if (mensaje != null) {
%>
	<p><%= mensaje %></p>
<%
	}
%>

<%
	// Si se entra directo a la JSP sin pasar por el servlet no hay atributos, por eso los null
	Object proximoId = request.getAttribute("proximoId");
	List<TipoSeguro> listaTipos = (List<TipoSeguro>) request.getAttribute("listaTipos");
%>

<form method="post" action="servletAgregarSeguro">
<table>
	<tr>
		<td>Id Seguro:</td>
		<td><%= proximoId != null ? proximoId : "" %></td>
	</tr>
	<tr>
		<td>Descripción:</td>
		<td><input type="text" name="txtDescripcion"></td>
	</tr>
	<tr>
		<td>Tipo de Seguro:</td>
		<td>
			<select name="ddlTipoSeguro">
			<%
				if (listaTipos != null) {
					for (TipoSeguro tipo : listaTipos) {
			%>
				<option value="<%= tipo.getIdTipo() %>"><%= tipo.getDescripcion() %></option>
			<%
					}
				}
			%>
			</select>
		</td>
	</tr>
	<tr>
		<td>Costo contratación:</td>
		<td><input type="text" name="txtCostoContratacion"></td>
	</tr>
	<tr>
		<td>Costo Máximo Asegurado:</td>
		<td><input type="text" name="txtCostoAsegurado"></td>
	</tr>
	<tr>
		<td></td>
		<td><input type="submit" name="btnAceptar" value="Aceptar"></td>
	</tr>
</table>
</form>

</body>
</html>
