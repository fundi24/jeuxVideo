package be.poshi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.poshi.pojo.Player;

public class PlayerDAO extends DAO<Player> {

	public PlayerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Player obj) {
		boolean success = false;
		LocalDate today = LocalDate.now();

		String query = "INSERT INTO User (Username, Password, RegistrationDate, Pseudo, Credit, DateOfBirth, Administrator, ReceivedBirthdayGift) VALUES (?,?,?,?,?,?,?,?)";

		boolean isValid = checkIfUsernameIsAvailable(obj.getUsername());

		if (isValid == true) {
			try {
				PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
				pstmt.setString(1, obj.getUsername());
				pstmt.setString(2, obj.getPassword());
				pstmt.setDate(3, Date.valueOf(today));
				pstmt.setString(4, obj.getPseudo());
				pstmt.setInt(5, 10);
				pstmt.setDate(6, Date.valueOf(obj.getDateOfBirth()));
				pstmt.setBoolean(7, false);
				pstmt.setBoolean(8, false);
				pstmt.execute();
				pstmt.close();
				success = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return success;
	}

	@Override
	public boolean delete(Player obj) {
		return false;
	}

	@Override
	public boolean update(Player obj) {
		boolean success = false;

		LocalDate birthday = obj.getDateOfBirth();
		int dayBirthday = birthday.getDayOfMonth();
		Month monthBirthday = birthday.getMonth();
		LocalDate today = LocalDate.now();
		int day = today.getDayOfMonth();
		Month month = today.getMonth();

		String query = "UPDATE User SET Credit='" + obj.getCredit() + "', ReceivedBirthdayGift = true WHERE IdUser='"
				+ obj.getIdUser() + "'";
		String query2 = "UPDATE User SET ReceivedBirthdayGift = false WHERE IdUser='" + obj.getIdUser() + "'";

		if (day == dayBirthday && month == monthBirthday) {
			try {
				boolean ReceivedBirthdayGift = hasReceivedBirthdayGift(obj);
				if (ReceivedBirthdayGift == false) {
					PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
					pstmt.executeUpdate();
					pstmt.close();
					success = true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			try {
				PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query2);
				pstmt.executeUpdate();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return success;
	}

	@Override
	public Player find(int id) {

		Player player = null;

		// recuperer player
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM User WHERE IdUser = " + id);
			if (result.first()) {
				player = new Player();
				player.setIdUser(id);
				player.setPseudo(result.getString("Pseudo"));
				player.setCredit(result.getInt("Credit"));
				player.setDateOfBirth(result.getDate("DateOfBirth").toLocalDate());
				player.setRegistrationDate(result.getDate("RegistrationDate").toLocalDate());
				player.setReceivedBirthdayGift(result.getBoolean("ReceivedBirthdayGift"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Recuperer ses copies
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM User INNER JOIN Copy ON User.IdUser = Copy.IdUser WHERE User.IdUser = "
							+ id);
			CopyDAO copyDAO = new CopyDAO(this.connect);
			while (result.next()) {
				player.getCopies().add(copyDAO.find(result.getInt("IdCopy")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// recuperer ses bookings
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT * FROM User INNER JOIN Booking ON User.IdUser = Booking.IdUser WHERE User.IdUser = "
									+ id);
			BookingDAO bookingDAO = new BookingDAO(this.connect);
			while (result.next()) {
				player.getBookings().add(bookingDAO.find(result.getInt("IdBooking")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// recuperer ses emprunts
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM User INNER JOIN Loan ON User.IdUser = Loan.IdUser WHERE User.IdUser = "
							+ id);
			LoanDAO loanDAO = new LoanDAO(this.connect);
			while (result.next()) {
				player.getBorrowings().add(loanDAO.find(result.getInt("IdLoan")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// recuperer ses prets
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(
							"SELECT * FROM (User INNER JOIN Copy ON User.IdUser = Copy.IdUser) INNER JOIN Loan ON Copy.IdCopy = Loan.IdCopy WHERE Copy.IdUser = "
									+ id);
			LoanDAO loanDAO = new LoanDAO(this.connect);
			while (result.next()) {
				player.getLoans().add(loanDAO.find(result.getInt("IdLoan")));

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return player;
	}

	@Override
	public ArrayList<Player> findAll(int id) {
		return null;
	}

	public boolean checkIfUsernameIsAvailable(String username) {
		boolean isValid = true;
		String query = "SELECT * FROM User WHERE Username='" + username + "'";
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if (result.first()) {
				isValid = false;
				JOptionPane.showMessageDialog(null, "Username is already used !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}

	public boolean hasReceivedBirthdayGift(Player player) {
		boolean received = false;
		int id = player.getIdUser();

		String query = "SELECT * FROM User WHERE IdUser='" + id + "'";
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if (result.first()) {
				boolean isReceived = result.getBoolean("ReceivedBirthdayGift");
				if (isReceived == true) {
					received = true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return received;
	}

}
