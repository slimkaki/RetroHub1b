<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RetroHub - Cadastro</title>
<link rel="stylesheet" href="style.css"/>
</head>
<body>

<h1>Bem-vindo ao RetroHub!</h1>
<hr/><br/><br/>

Cadastre-se abaixo: <br/><br/>
<form action='efetuaCadastro' method='post' enctype="multipart/form-data">
Usuário: 
<br/><input type='text' name='name'required='required'/> <br/>
Senha: 
<br/><input type='password' name='password'required='required'/> <br/>
Confirmar senha: 
<br/><input type='password' name='passwordConf'required='required'/> <br/>
Foto: 
<br/><input type="file" name="pic"/> <br/>
<input type='submit'/>
</form>
<br/>
<hr/>
<form action='backToLogin'>
	<input type='submit' value='Voltar para o login'/>
</form>

<span style="color:red"> ${erro} </span>

</body>
</html>