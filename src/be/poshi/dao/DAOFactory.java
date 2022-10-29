package be.poshi.dao;

import java.sql.Connection;

import be.poshi.connection.DatabaseConnection;
import be.poshi.pojo.*;

public class DAOFactory extends AbstractDAOFactory {

	protected static final Connection conn = DatabaseConnection.getInstance();

	@Override
	public DAO<Administrator> getAdministratorDAO() {
		return new AdministratorDAO(conn);
	}

	@Override
	public DAO<Booking> getBookingDAO() {
		return new BookingDAO(conn);
	}

	@Override
	public DAO<Copy> getCopyDAO() {
		return new CopyDAO(conn);
	}

	@Override
	public DAO<Loan> getLoanDAO() {
		return new LoanDAO(conn);
	}

	@Override
	public DAO<Player> getPlayerDAO() {
		return new PlayerDAO(conn);
	}

	@Override
	public DAO<User> getUserDAO() {
		return new UserDAO(conn);
	}

	@Override
	public DAO<VideoGame> getVideoGameDAO() {
		return new VideoGameDAO(conn);
	}
}
