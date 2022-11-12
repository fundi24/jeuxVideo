package be.poshi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.poshi.pojo.Booking;
import be.poshi.pojo.Copy;
import be.poshi.pojo.VideoGame;

public class BookingDAO extends DAO<Booking> {

	public BookingDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Booking obj) {
		return false;
	}

	@Override
	public boolean delete(Booking obj) {
		return false;
	}

	@Override
	public boolean update(Booking obj) {
		return false;
	}

	@Override
	public Booking find(int id) {
		Booking booking = null;
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> VideoGameDAO = adf.getVideoGameDAO();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Booking WHERE IdBooking = " + id);
			if (result.first()) {
				int idVideoGame = result.getInt("IdVideoGame");
				VideoGame vg = VideoGameDAO.find(idVideoGame);
				booking = new Booking(vg);
				booking.setIdBooking(id);
				booking.setBookingDate(result.getDate("BookingDate").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return booking;
	}

	@Override
	public ArrayList<Booking> GetAll() {
		return null;
	}

}
