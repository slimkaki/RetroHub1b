package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mvc.model.Message;
import mvc.model.Subject;

public class SubjectDAO {
	
	private Connection connection = null;
	
	String url = System.getenv("mysql_url");
	String user = System.getenv("mysql_user");
	String password = System.getenv("mysql_password");
	
	public SubjectDAO() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(url, user, password);
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addSubject(Subject subject) {
		String sql1 = "INSERT INTO subjects" + "(subject,url) VALUES (?,?)";
		
		String sql2 = "CREATE TABLE " + subject.getURL() + 
				"(id INT(255) NOT NULL AUTO_INCREMENT PRIMARY KEY, msg TEXT NOT NULL, idUser INT(32) NOT NULL, time TIMESTAMP NOT NULL)";
		try {
			PreparedStatement stmt1 = connection.prepareStatement(sql1);
			PreparedStatement stmt2 = connection.prepareStatement(sql2);
			stmt1.setString(1, subject.getSubject());
			stmt1.setString(2, subject.getURL());
			stmt1.execute();
			stmt2.execute();
			stmt1.close();
			stmt2.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public int messageCount(Subject subject) {
		String sql = "SELECT COUNT(*) FROM " + subject.getSubject();
		int count = 0;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				count = rs.getInt(1);
			}
			rs.close();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}
	
	public List<Subject> getSubjectList() {
		List<Subject> assuntos = (List) new ArrayList<Subject>();
		String sql = "SELECT * FROM subjects";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet lista;
			
			lista = stmt.executeQuery();
			
			while (lista.next()) {
				Subject subject = new Subject();
				subject.setId(lista.getInt("id"));
				subject.setSubject(lista.getString("subject"));
				subject.setURL(lista.getString("subject"));
				assuntos.add(subject);
			}
			lista.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return assuntos;
	}
	
	public List<Message> getChatMessages(String url) {
		List<Message> messages = (List) new ArrayList<Message>();
		url = url.replace("/", "");
		String sql = "SELECT * FROM " + url;
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			ResultSet msgList;
			
			msgList = stmt.executeQuery();
			
			while (msgList.next()) {
				Message message = new Message();
				message.setIdUser(msgList.getInt("idUser"));
				message.setMsg(msgList.getString("msg"));
				message.setTime(msgList.getTimestamp("time"));
				messages.add(message);
			}
			msgList.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return messages;
	}
	
	public String getUserById(int id) {
		String username = "";
		String sql = "SELECT * FROM users WHERE userId=?";
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				username = rs.getString("username");
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return username;
	}
	
	public void addMessage(Message message, String url) {
		String sql = "INSERT INTO " + url + " (msg, idUser, time) VALUES (?,?,?)";
		//System.out.println("Adicionando msg a URL: " + url);
		try {
			PreparedStatement stmt = connection.prepareStatement(sql);
			stmt.setString(1, message.getMsg());
			stmt.setInt(2, message.getIdUser());
			stmt.setTimestamp(3, message.getTime());
			stmt.execute();
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getSubjectNameFromURL(String URL) {
		String name = URL;
		if (URL.contains("_")) {
			name = URL.replace("_", " ");
		}
		name = name.replace("/", "");
		return name;
	}
	
	public void close() {
		// TODO Auto-generated method stub
		try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
