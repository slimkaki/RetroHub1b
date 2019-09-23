<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<title>RetroHub</title>
</head>

<body>
	
	<h1>RetroHub, Bem vindo ${usuarioLogado}!</h1>
	<img width=50px height=50px src="getImage?login=${usuarioLogado}" /><br/>
	<form action='logout'>
		<input type='submit' value='logout'>
	</form>
	
	<hr />
	<%@ page import="java.util.*,mvc.controller.*,mvc.model.*"%>
	<br />
	
	<b>Lista de assuntos:</b><br/>
	
	<table border='1'>
		<%
			SubjectDAO dao = new SubjectDAO();
			List<Subject> subjects = dao.getSubjectList();
			for (Subject subject : subjects) {
		%>
		<tr>
			<%--  
			<% session.setAttribute("myURL", subject.getURL()); %>
 			<% session.setAttribute("subjectName", subject.getSubject()); %> 
 			--%><% String name = subject.getSubject();
 				   String myURL = subject.getURL();%>
 			<th><%=name%></th>
 			<th>
 			<form action='entraConversa' method='get'>
 			<input type='hidden' name="myURL" value=<%=myURL%>/>
 			<input type='submit' value='Entrar na conversa'/><br/>
 			</form></th>
 		</tr>
		<%
			}
		%>
	</table>
	
	<form action="criaConversa" method='post'>
		<br /> <b>Crie uma nova conversa:</b>
		<br /> <input placeholder='Digite o nome da nova conversa aqui' type='text' name='subject' required='required' /> 
		<br /> <input type='submit' value = "Enviar assunto"/>
	</form>
	
</body>
</html>