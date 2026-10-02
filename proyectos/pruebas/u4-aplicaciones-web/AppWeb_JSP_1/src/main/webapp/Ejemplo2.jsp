<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<form method="get" action="Ejemplo2.jsp" >
	Ingrese su nombre: <input type="text" name="txtNombre"/>
	<input type="submit" name="btnSaludar" value ="saludar" />
	<input type="submit" name="btnDespedir" value ="Despedir" />
	
</form>

<%
	String texto="";
	if(request.getParameter("btnDespedir") != null)
	{
		//se hizo click sobre el boton despedir
		texto="Adios " + request.getParameter("txtNombre");
	}
	if(request.getParameter("btnSaludar")!=null)
	{
		//se hizo click sobre el boton saludar
		texto="Hola " + request.getParameter("txtNombre");
	}
	
 %>
 
 <%=texto %>


<!-- Otra alternativa  -->
<br><br>
<% 
	if(request.getParameter("btnDespedir")!=null) 
	{
%>	
<b>Adios <%=request.getParameter("txtNombre") %> </b>

<% 
	}
	
%>


</body>
</html>

