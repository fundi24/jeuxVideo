package be.poshi.dao;

import java.sql.Connection;
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
		return false;
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
		ArrayList<Loan> loans = new ArrayList<Loan>();

		String query = "SELECT * FROM Loan INNER JOIN Copy ON Copy.IdCopy = Loan.IdCopy WHERE OnGoing = false AND NOT Copy.IdUser = '"+id+"'";
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while (result.next()) {
				int idLoan = result.getInt("IdLoan");
				Loan loan = find(idLoan);
				loans.add(loan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return loans;
	}

}
