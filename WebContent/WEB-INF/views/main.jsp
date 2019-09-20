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
	<a href="logout">log out</a>
	
	<hr />
	<%@ page import="java.util.*,mvc.controller.*,mvc.model.*"%>
	<br />
	
	<b>Lista de assuntos:</b>
	
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
 			--%>
 			
			<td><a href="chat?myURL=<%=subject.getURL()%>&subjectName=<%=subject.getSubject()%>&fromMain=true"><%=subject.getSubject()%></a></td>
		</tr>
		<%
			}
		%>
	</table>
	
	<form method='post'>
		<br /> <b>Crie uma nova conversa:</b>
		<br /> <input placeholder='Digite o nome da nova conversa aqui' type='text' name='newSubject' required='required' /> 
		<br /> <input type='submit' />
	</form>
	
</body>
</html>