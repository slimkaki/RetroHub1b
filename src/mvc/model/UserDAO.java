package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import mvc.model.User;

public class UserDAO {
	
	private Connection connection = null;
	
	public UserDAO() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost/RetroHub1b", "root", "");
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public void addUser(User user) {
		MultipartFile pic = user.getPic();
		String sql = "INSERT INTO users" + "(username,password,foto) VALUES (?, ?, ?)";
		try {
			PreparedStatement stmt;
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			stmt.execute();
			stmt.close();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public boolean checkIfUserExists(User user) {
		String sql = "SELECT * FROM users WHERE username=?";
		PreparedStatement stmt;
		String name = user.getName();
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if (rs == null) {
				return true;
			}
			rs.close();
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
	
	public int getUserId(User user) {
		
		int id = 0;
		try {
			
			String sql = "SELECT * FROM users WHERE username=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			
			ResultSet rs = stmt.executeQuery();
			
			if (rs.next()) {
				id = rs.getInt("userId");	
			}
			rs.close();
			stmt.close();
			
		} catch (SQLException e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return id;
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
	
	public List<User> getUsers(){
		
		List<User> users = (List) new ArrayList<User>();
		
		try {
			
			PreparedStatement stmt = connection.prepareStatement("SELECT * FROM users");
			ResultSet lista;
			
			lista = stmt.executeQuery();
			
			while (lista.next()) {
				User user = new User();
				user.setUserId(lista.getInt("id"));
				user.setName(lista.getString("name"));
				user.setPassword(lista.getString("password"));
				users.add(user);
				}
			lista.close();
			stmt.close();
					
		} catch (Exception e) {
			// TODO: handle exception
		}
		return users;
		
	}
	
	public boolean login(User user) {

		boolean status = false;
		
		try {

			String sql = "SELECT * FROM users WHERE username=? AND password=?";
			PreparedStatement stmt = connection.prepareStatement(sql);
			
			stmt.setString(1, user.getName());
			stmt.setString(2, user.getPassword());
			
			ResultSet check = stmt.executeQuery();
			status = check.next();

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return status;
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
