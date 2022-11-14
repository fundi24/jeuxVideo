package be.poshi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
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
		boolean success = false;

		String query = "INSERT INTO Booking (BookingDate, IdUser, IdVideoGame) VALUES (?,?,?)";

		try {
			PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
			pstmt.setDate(1, Date.valueOf(obj.getBookingDate()));
			pstmt.setInt(2, obj.getPlayer().getIdUser());
			pstmt.setInt(3, obj.getVideoGame().getIdVideoGame());
			pstmt.execute();
			pstmt.close();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(Booking obj) {
		boolean success = false;
		
		String query = "DELETE FROM Booking WHERE IdBooking = '" + obj.getIdBooking()+"'";
		
		try {
				PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
				pstmt.executeUpdate();
				pstmt.close();
				success = true;
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		
		return success;
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
	public ArrayList<Booking> findAll(int id) {
		return null;
	}

}
