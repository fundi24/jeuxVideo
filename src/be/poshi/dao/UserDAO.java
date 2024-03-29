package be.poshi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.poshi.connection.DatabaseConnection;
import be.poshi.pojo.Administrator;
import be.poshi.pojo.Player;
import be.poshi.pojo.User;

public class UserDAO extends DAO<User> {

	public UserDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(User obj) {
		return false;
	}

	@Override
	public boolean delete(User obj) {
		return false;
	}

	@Override
	public boolean update(User obj) {
		return false;
	}

	@Override
	public User find(int id) {
		return null;
	}

	public static User login(String username, String password) throws Exception {
		User user = null;
		Connection conn = DatabaseConnection.getInstance();
		
		String query = "SELECT * FROM User WHERE Username='" + username + "' AND Password='" + password + "'";
		
		try {
			ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				int id = result.getInt("IdUser");
				boolean isAdmin = result.getBoolean("Administrator");
				if (isAdmin == true) {
					AdministratorDAO administratorDAO = new AdministratorDAO(conn);
					user = administratorDAO.find(id);
				} else {
					PlayerDAO playerDAO = new PlayerDAO(conn);
					user = playerDAO.find(id);
				}
			} else {

				throw new Exception("Incorrect username or password !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}

	@Override
	public ArrayList<User> findAll(int id) {
		return null;
	}
}
