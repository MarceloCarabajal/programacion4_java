<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Alta de Cliente</title>
<style>
    body { font-family: Arial, sans-serif; background-color: #f4f6f9; margin: 0; padding: 20px; }
    .container { max-width: 600px; background: white; padding: 30px; border-radius: 8px; box-shadow: 0 4px 10px rgba(0,0,0,0.1); margin: auto; }
    h2 { color: #333; text-align: center; }
    .form-group { margin-bottom: 15px; }
    label { display: block; font-weight: bold; margin-bottom: 5px; color: #555; }
    input, select { width: 100%; padding: 8px; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box; }
    button { background-color: #007bff; color: white; border: none; padding: 10px 15px; font-size: 16px; border-radius: 4px; cursor: pointer; width: 100%; margin-top: 10px; }
    button:hover { background-color: #0056b3; }
    .mensaje { text-align: center; font-weight: bold; margin-bottom: 20px; color: #28a745; }
</style>
</head>
<body>

<div class="container">
    <h2>Registro de Nuevo Cliente</h2>

    <!-- Mensaje dinámico que manda el Servlet al guardar -->
    <% 
        String mensaje = (String) request.getAttribute("mensaje");
        if (mensaje != null) {
    %>
        <div class="mensaje"><%= mensaje %></div>
    <% 
        } 
    %>

    <form action="AltaClienteServlet" method="POST">
        <div class="form-group">
            <label>DNI:</label>
            <input type="text" name="txtDni" required>
        </div>
        
        <div class="form-group">
            <label>CUIL:</label>
            <input type="text" name="txtCuil" required>
        </div>
        
        <div class="form-group">
            <label>Nombre:</label>
            <input type="text" name="txtNombre" required>
        </div>
        
        <div class="form-group">
            <label>Apellido:</label>
            <input type="text" name="txtApellido" required>
        </div>
        
        <div class="form-group">
            <label>Sexo:</label>
            <select name="txtSexo">
                <option value="M">Masculino</option>
                <option value="F">Femenino</option>
                <option value="Otro">Otro</option>
            </select>
        </div>
        
        <div class="form-group">
            <label>Fecha de Nacimiento:</label>
            <input type="date" name="txtFechaNacimiento" required>
        </div>
        
        <div class="form-group">
            <label>Dirección:</label>
            <input type="text" name="txtDireccion">
        </div>
        
        <div class="form-group">
            <label>Nacionalidad:</label>
            <input type="text" name="txtNacionalidad">
        </div>
        
        <div class="form-group">
            <label>Localidad:</label>
            <input type="text" name="txtLocalidad">
        </div>
        
        <div class="form-group">
            <label>Provincia:</label>
            <input type="text" name="txtProvincia">
        </div>
        
        <div class="form-group">
            <label>Correo Electrónico:</label>
            <input type="email" name="txtCorreo">
        </div>
        
        <div class="form-group">
            <label>Teléfono:</label>
            <input type="text" name="txtTelefono">
        </div>
        
        <button type="submit">Guardar Cliente</button>
    </form>
</div>

</body>
</html>