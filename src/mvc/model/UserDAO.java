package mvc.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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

}
