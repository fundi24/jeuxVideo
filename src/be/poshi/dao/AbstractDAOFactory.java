package be.poshi.dao;

import be.poshi.pojo.*;

public abstract class AbstractDAOFactory {

	public abstract DAO<Administrator> getAdministratorDAO();

	public abstract DAO<Booking> getBookingDAO();

	public abstract DAO<Copy> getCopyDAO();

	public abstract DAO<Loan> getLoanDAO();

	public abstract DAO<Player> getPlayerDAO();

	public abstract DAO<User> getUserDAO();

	public abstract DAO<VideoGame> getVideoGameDAO();
	
	public abstract DAO<PriceHistory> getPriceHistoryDAO();

	public static AbstractDAOFactory getFactory() {
		return new DAOFactory();

	}
}
