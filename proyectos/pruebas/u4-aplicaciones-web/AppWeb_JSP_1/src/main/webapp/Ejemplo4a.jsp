<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>


<form action="Ejemplo4b.jsp" method="get">
	<b>Seleccione un pais:</b>
		<select name="pais">
			<option value="1">Mexico</option>
			<option value="2">Colombia</option>
			<option value="3">Chile</option>
			<option value="4">Peru</option>
			<option value="5">Argentina</option>
			<option value="6">España</option>
		</select>
		
	<br/>
	<br/>
	
	<b>Seleccione un idioma</b>
		Java <input type="radio" name="tecnologias" value="Java">
		PHP <input type="radio" name="tecnologias" value="PHP">
		JavaScript <input type="radio" name="tecnologias" value="JavaScript">
		
	<br/>
	<br/>
	
	<b>Seleccione experiencia:</b>
	Programador <input type="checkbox" name="chk1" value="Programdor" id="ed1">
	Soporte Tecnico <input type="checkbox" name="chk2" value="Soporte Tecnico" id="ed2">
	
	<input type="submit" value="Aceptar" name="btnAceptar">
	
	
</form>

</body>
</html>