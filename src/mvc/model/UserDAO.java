package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.web.multipart.MultipartFile;

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

}
