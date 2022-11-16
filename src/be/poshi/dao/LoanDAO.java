package be.poshi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.poshi.pojo.Copy;
import be.poshi.pojo.Loan;

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

		String query = "UPDATE Loan SET OnGoing = false WHERE IdLoan ='" + obj.getIdLoan()+"'";

		try {
			PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
			pstmt.executeUpdate();
			pstmt.close();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return success;
	}

	@Override
	public Loan find(int id) {
		Loan loan = null;
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> CopyDAO = adf.getCopyDAO();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Loan INNER JOIN Copy ON Copy.IdCopy = Loan.IdCopy WHERE IdLoan = " + id);
			if (result.first()) {
				int idCopy = result.getInt("IdCopy");

				Copy copy = CopyDAO.find(idCopy);
				
				loan = new Loan(copy);
				loan.setIdLoan(id);
				loan.setStartDate(result.getDate("StartDate").toLocalDate());
				loan.setEndDate(result.getDate("EndDate").toLocalDate());
				loan.setOngoing(result.getBoolean("OnGoing"));
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
