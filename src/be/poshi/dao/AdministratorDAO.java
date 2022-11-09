package be.poshi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.poshi.pojo.Administrator;

public class AdministratorDAO extends DAO<Administrator> {

	public AdministratorDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Administrator obj) {
		return false;
	}

	@Override
	public boolean delete(Administrator obj) {
		return false;
	}

	@Override
	public boolean update(Administrator obj) {
		return false;
	}

	@Override
	public Administrator find(int id) {
		Administrator administrator = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM User WHERE IdUser = " + id);
			if (result.first()) {
				administrator = new Administrator();
				administrator.setIdUser(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return administrator;
	}

	@Override
	public ArrayList<Administrator> GetAll() {
		return null;
	}

}
