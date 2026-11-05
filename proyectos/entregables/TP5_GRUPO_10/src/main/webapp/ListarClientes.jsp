<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List,entidades.Cliente,java.time.LocalDate,java.time.format.DateTimeFormatter"%>
<%!
    private String textoHtml(String valor) {
        if (valor == null) return "";
        return valor.replace("&", "&amp;").replace("<", "&lt;")
                .replace(">", "&gt;").replace("\"", "&quot;").replace("'", "&#39;");
    }

    private String fechaVisible(String fecha) {
        if (fecha == null || fecha.isEmpty()) return "";
        return LocalDate.parse(fecha).format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }
%>
<!DOCTYPE html>
<html lang="es">
<head>
<meta charset="UTF-8">
<title>Listado de clientes</title>
<style>
    body { font-family: Arial, sans-serif; margin: 20px; }
    .tabla { overflow-x: auto; }
    table { border-collapse: collapse; width: 100%; }
    th, td { border: 1px solid #ccc; padding: 8px; text-align: left; }
    th { background-color: #f4f6f9; }
</style>
</head>
<body>
<h1>Listado de clientes</h1>
<%
    List<Cliente> listaClientes = (List<Cliente>) request.getAttribute("listaClientes");
    if (listaClientes == null || listaClientes.isEmpty()) {
%>
<p>No hay clientes para mostrar.</p>
<% } %>
<div class="tabla">
<table id="tablaClientes">
    <thead>
        <tr>
            <th scope="col">ID</th>
            <th scope="col">DNI</th>
            <th scope="col">CUIL</th>
            <th scope="col">Nombre</th>
            <th scope="col">Apellido</th>
            <th scope="col">Sexo</th>
            <th scope="col">Fecha de nacimiento</th>
            <th scope="col">Dirección</th>
            <th scope="col">Nacionalidad</th>
            <th scope="col">Localidad</th>
            <th scope="col">Provincia</th>
            <th scope="col">Correo electrónico</th>
            <th scope="col">Teléfono</th>
        </tr>
    </thead>
    <tbody>
    <%
        if (listaClientes != null) {
            for (Cliente cliente : listaClientes) {
    %>
        <tr>
            <td><%= cliente.getIdCliente() %></td>
            <td><%= textoHtml(cliente.getDni()) %></td>
            <td><%= textoHtml(cliente.getCuil()) %></td>
            <td><%= textoHtml(cliente.getNombre()) %></td>
            <td><%= textoHtml(cliente.getApellido()) %></td>
            <td><%= textoHtml(cliente.getSexo()) %></td>
            <td><%= fechaVisible(cliente.getFechaNacimiento()) %></td>
            <td><%= textoHtml(cliente.getDireccion()) %></td>
            <td><%= textoHtml(cliente.getNacionalidad()) %></td>
            <td><%= textoHtml(cliente.getLocalidad()) %></td>
            <td><%= textoHtml(cliente.getProvincia()) %></td>
            <td><%= textoHtml(cliente.getCorreoElectronico()) %></td>
            <td><%= textoHtml(cliente.getTelefono()) %></td>
        </tr>
    <%
            }
        }
    %>
    </tbody>
</table>
</div>
</body>
</html>
