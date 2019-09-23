<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import='mvc.model.UserDAO, mvc.model.User, java.util.*'%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RetroHub</title>
</head>
<body>

	<h1>RetroHub</h1>
	<hr />
	<br /> Faça seu login caso já tenha um usuário!
	<br />
	<br />
	
	<form action='efetuaLogin' method='post'>
		Usuário: <br /> <input type='text' name='name' required='required' /> <br /> 
		Senha: <br /> <input type='password' name='password' required='required' /> <br /> 
		<input type='submit' value='Entrar'/>
	</form>
	
	<hr/>
	<br />
	<form method='post' action='cadastro'>
		Ainda não possui um usuário? <input type = "submit" value = "Cadastro"/>
	</form>
	<br />

</body>
</html>