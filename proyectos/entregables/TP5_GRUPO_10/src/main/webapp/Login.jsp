<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Iniciar Sesión</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/estilos.css">
</head>
<body>
    <div class="card">
        <h2>Iniciar sesión</h2>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        
        <form action="LoginServlet" method="post">
            <div class="form-group">
                <label>Usuario</label>
                <input type="text" name="txtUsuario" required>
            </div>
            
            <div class="form-group">
                <label>Contraseña</label>
                <input type="password" name="txtPass" required>
            </div>
            
            <button type="submit" class="btn">Ingresar</button>
        </form>
    </div>
</body>
</html>