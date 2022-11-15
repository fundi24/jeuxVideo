package be.poshi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.poshi.pojo.Copy;
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
		String query = "DELETE FROM Copy WHERE IdCopy ='" + obj.getIdCopy()+"'";
		
		boolean isValid = CheckForLoan(obj.getIdCopy());
		
		if(isValid==true)
		{
			try {
				PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
				pstmt.executeUpdate();
				pstmt.close();
				success = true;
			} 
			catch (SQLException e) {
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
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> VideoGameDAO = adf.getVideoGameDAO();

		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM Copy WHERE IdCopy = " + id);
			if (result.first()) {
				int idVideoGame = result.getInt("IdVideoGame");
				VideoGame vg = VideoGameDAO.find(idVideoGame);
				copy = new Copy(vg);
				copy.setIdCopy(id);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return copy;
	}

	@Override
	public ArrayList<Copy> findAll(int id) {
		return null;
	}
	
	public boolean CheckForLoan(int id)
	{
		boolean isValid = true;
		String query = "SELECT * FROM Loan WHERE IdCopy='" + id + "'";
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				isValid = false;
				JOptionPane.showMessageDialog(null, "There is a loan in progress !");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}

}
