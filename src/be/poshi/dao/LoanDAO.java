package be.poshi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import be.poshi.pojo.Copy;
import be.poshi.pojo.Loan;
import be.poshi.pojo.Player;

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
		return false;
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
	public ArrayList<Loan> GetAll() {
		return null;
	}

}
