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
		<a href="main">RetroHub</a>
	</h1>
	<a href="login">log out</a>
	<hr />
	<%
		SubjectDAO daoSub = new SubjectDAO();
		UserDAO daoUser = new UserDAO();
		String myURL = request.getParameter("myURL");
		String chatName = request.getParameter("subjectName");
	%>
	<h1>
		Assunto do chat: <i><%=chatName%></i>
	</h1>

	<table border='1'>
		<%
			List<Message> messages = daoSub.getChatMessages(myURL);
			for (Message message : messages) {
		%>
		<tr>
			<%
				String user = daoUser.getUserById(message.getIdUser());
			%>
			<td><%=user%></td>
			<td><%=message.getMsg()%></td>
			<td><%=message.getTime()%></td>
		</tr>
		<%
			}
		%>
	</table>
	<form method='post'>
		<input placeholder="Digite aqui sua mensagem" type='text'
			name='newMsg' required='required' /> <input type='submit'
			placeholder='Enviar' />
	</form>

</body>
</html>