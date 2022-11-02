package be.poshi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	public static User Login(String username, String password) throws Exception {
		User user = null;
		Connection conn = DatabaseConnection.getInstance();
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Administrator> AdministratorDAO = adf.getAdministratorDAO();
		DAO<Player> PlayerDAO = adf.getPlayerDAO();

		String query = "SELECT * FROM User WHERE Username='" + username + "' AND Password='" + password + "'";
		try {
			ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				int id = result.getInt("IdUser");
				boolean isAdmin = result.getBoolean("Administrator");
				if (isAdmin == true) {
					user = AdministratorDAO.find(id);
				}
				user = PlayerDAO.find(id);
			} else {
				throw new Exception("Utilisateur non trouvé !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
}
