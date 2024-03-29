package be.poshi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.poshi.pojo.Copy;
import be.poshi.pojo.Loan;
import be.poshi.pojo.Player;
import be.poshi.pojo.VideoGame;

public class LoanDAO extends DAO<Loan> {

	public LoanDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Loan obj) {
		boolean success = false;

		String query = "INSERT INTO Loan (StartDate, EndDate, OnGoing, IdUser, IdCopy) VALUES (?,?,?,?,?)";

		try {
			PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
			pstmt.setDate(1, Date.valueOf(obj.getStartDate()));
			pstmt.setDate(2, Date.valueOf(obj.getEndDate()));
			pstmt.setBoolean(3, obj.getOngoing());
			pstmt.setInt(4, obj.getBorrower().getIdUser());
			pstmt.setInt(5, obj.getCopy().getIdCopy());
			pstmt.execute();
			pstmt.close();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public boolean delete(Loan obj) {
		return false;
	}

	@Override
	public boolean update(Loan obj) {
		boolean success = false;

		String query = "UPDATE Loan SET OnGoing = false WHERE IdLoan ='" + obj.getIdLoan() + "'";

		try {
			PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			PlayerDAO playerDAO = new PlayerDAO(this.connect);
			playerDAO.update(obj.getLender());
			playerDAO.update(obj.getBorrower());
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public Loan find(int id) {
		Loan loan = null;

		String query1 = "SELECT * FROM Loan INNER JOIN Copy ON Copy.IdCopy = Loan.IdCopy WHERE IdLoan = '" + id + "'";
		String query2 = "SELECT * FROM Loan INNER JOIN User ON Loan.IdUser = User.IdUser WHERE IdLoan = '" + id + "'";
		
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query1);
			if (result.first()) {
				int idCopy = result.getInt("IdCopy");

				CopyDAO copyDAO = new CopyDAO(this.connect);
				Copy copy = copyDAO.find(idCopy);

				loan = new Loan(copy);
				loan.setIdLoan(id);
				loan.setStartDate(result.getDate("StartDate").toLocalDate());
				loan.setEndDate(result.getDate("EndDate").toLocalDate());
				loan.setOngoing(result.getBoolean("OnGoing"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// recuperer borrower
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query2);
			if (result.first()) {
				Player borrower = new Player();
				borrower.setIdUser(result.getInt("IdUser"));
				borrower.setCredit(result.getInt("Credit"));
				borrower.setDateOfBirth(result.getDate("DateOfBirth").toLocalDate());
				loan.setBorrower(borrower);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loan;
	}

	@Override
	public ArrayList<Loan> findAll(int id) {
		return null;
	}

}
