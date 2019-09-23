<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>RetroHub - Chat</title>
</head>
<body>
	<%@ page
		import="java.util.*,mvc.controller.*,mvc.model.*"%>
	<%-- <% session.setAttribute("backToMain", "true"); %> --%>
	<h1>
		RetroHub
	</h1>
	<form action='backToMenu'>
		<input type='submit' value='Voltar para o menu'>
	</form>
	<form action='logout'>
		<input type='submit' value='logout'>
	</form>
	<hr />
	<%
	
	String chatURL = (String) request.getSession().getAttribute("chatURL");
	SubjectDAO daoSub = new SubjectDAO();
	String name = daoSub.getSubjectNameFromURL(chatURL);
	UserDAO daoUser = new UserDAO();
	%>
	<h1>
		Assunto do chat: <i><%=name%></i>
	</h1>

	<table border='1'>
		<%
			List<Message> messages = daoSub.getChatMessages(chatURL);
			for (Message message : messages) {
		%>
		<tr>
			<%
				String user = daoUser.getUserById(message.getIdUser());
				
			%>
			<td><img width=50px height=50px src="getImage?login=<%=user%>"/></td>
			<td><%=user%></td>
			<td><%=message.getMsg()%></td>
			<td><%=message.getTime()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<form action='enviaMsg' method='post'>
		<input type='hidden' name='myURL' value=<%=chatURL%>/>
		<input placeholder="Digite aqui sua mensagem" type='text'
			name='msg' required='required' /> <input type='submit'
			placeholder='Enviar' />
	</form>

</body>
</html>