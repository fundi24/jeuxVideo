package be.poshi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.poshi.pojo.Copy;
import be.poshi.pojo.Loan;
import be.poshi.pojo.Player;
import be.poshi.pojo.VideoGame;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Copy obj) {
		boolean success = false;

		String query = "INSERT INTO Copy (IdVideoGame, IdUser) VALUES (?,?)";

		try {
			PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
			pstmt.setInt(1, obj.getVideoGame().getIdVideoGame());
			pstmt.setInt(2, obj.getOwner().getIdUser());
			pstmt.execute();
			pstmt.close();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(Copy obj) {
		boolean success = false;

		String query = "DELETE FROM Copy WHERE IdCopy ='" + obj.getIdCopy() + "'";

		boolean isValid = checkForLoan(obj.getIdCopy());

		if (isValid == true) {
			try {
				PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
				pstmt.executeUpdate();
				pstmt.close();
				success = true;
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return success;
	}

	@Override
	public boolean update(Copy obj) {
		return false;
	}

	@Override
	public Copy find(int id) {
		Copy copy = null;
		Loan loan = null;

		String query1 = "SELECT * FROM Copy WHERE IdCopy = '" + id + "'";
		String query2 = "SELECT TOP 1 * FROM Copy INNER JOIN Loan ON Copy.IdCopy = Loan.IdCopy WHERE Copy.IdCopy ='"
				+ id + "' ORDER BY Loan.EndDate DESC ";

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query1);
			if (result.first()) {
				int idVideoGame = result.getInt("IdVideoGame");
				VideoGameDAO videoGameDAO = new VideoGameDAO(this.connect);
				VideoGame vg = videoGameDAO.find(idVideoGame);
				copy = new Copy(vg);
				copy.setIdCopy(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// recuperer loan
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query2);
			if (result.first()) {
				loan = new Loan();
				loan.setIdLoan(result.getInt("IdLoan"));
				loan.setStartDate(result.getDate("StartDate").toLocalDate());
				loan.setEndDate(result.getDate("EndDate").toLocalDate());
				loan.setOngoing(result.getBoolean("OnGoing"));
				copy.setLoan(loan);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return copy;
	}

	@Override
	public ArrayList<Copy> findAll(int id) {
		ArrayList<Copy> copies = new ArrayList<Copy>();

		String query = "SELECT * FROM Copy WHERE NOT Copy.IdUser = '" + id + "'";
		
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while (result.next()) {
				int idCopy = result.getInt("IdCopy");
				Copy copy = find(idCopy);
				copies.add(copy);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return copies;
	}

	public boolean checkForLoan(int id) {
		boolean isValid = true;
		
		String query = "SELECT * FROM Loan WHERE IdCopy='" + id + "' AND OnGoing = True";
		
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if (result.first()) {
				isValid = false;
				JOptionPane.showMessageDialog(null, "This game is on loan !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return isValid;
	}

}
