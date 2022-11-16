package be.poshi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.poshi.pojo.VideoGame;

public class VideoGameDAO extends DAO<VideoGame> {

	public VideoGameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(VideoGame obj) {
		boolean success = false;
		
		String query = "INSERT INTO VideoGame (VideoGameName, CreditCost, ConsoleName, VersionName) VALUES (?,?,?,?)";
		
		boolean isValid = CheckIfGameExist(obj);
		
		if(isValid==true)
		{
			try {
				PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
				pstmt.setString(1, obj.getName());
				pstmt.setInt(2, obj.getCreditCost());
				pstmt.setString(3, obj.getConsole());
				pstmt.setString(4, obj.getVersion());
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
	public boolean delete(VideoGame obj) {
		boolean success = false;
		
		String query = "DELETE FROM VideoGame WHERE IdVideoGame='" + obj.getIdVideoGame()+"'";
		
		boolean isValid = CheckForCopies(obj.getIdVideoGame());
		boolean isValid2 = CheckForBookings(obj.getIdVideoGame());
		if(isValid==true && isValid2 == true)
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
	public boolean update(VideoGame obj) {
		boolean success = false;

		String query = "UPDATE VideoGame SET CreditCost='" + obj.getCreditCost() + "'WHERE IdVideoGame='" + obj.getIdVideoGame()+"'";

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
	public VideoGame find(int id) {
		VideoGame videoGame = null;

		String query = "SELECT * FROM VideoGame WHERE VideoGame.IdVideoGame = '"
				+ id + "'";
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			if (result.first()) {
				videoGame = new VideoGame();
				videoGame.setIdVideoGame(result.getInt("IdVideoGame"));
				videoGame.setName(result.getString("VideoGameName"));
				videoGame.setCreditCost(result.getInt("CreditCost"));
				videoGame.setConsole(result.getString("ConsoleName"));
				videoGame.setVersion(result.getString("VersionName"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videoGame;

	}

	@Override
	public ArrayList<VideoGame> findAll(int id) {
		ArrayList<VideoGame> videoGames = new ArrayList<VideoGame>();

		String query = "SELECT * FROM VideoGame";
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while (result.next()) {
				int idVG = result.getInt("IdVideoGame");
				VideoGame vg = find(idVG);
				videoGames.add(vg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videoGames;
	}

	
	public boolean CheckForCopies(int id)
	{
		boolean isValid = true;
		String query = "SELECT * FROM Copy WHERE IdVideoGame='" + id + "'";
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				isValid = false;
				JOptionPane.showMessageDialog(null, "There is at least one copy of the game !");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}
	
	public boolean CheckForBookings(int id)
	{
		boolean isValid = true;
		String query = "SELECT * FROM Booking WHERE IdVideoGame='" + id + "'";
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				isValid = false;
				JOptionPane.showMessageDialog(null, "There is at least one booking of the game !");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}
	
	public boolean CheckIfGameExist(VideoGame vg)
	{
		boolean isValid = true;
		String query = "SELECT * FROM VideoGame WHERE VideoGameName ='" + vg.getName() + "' AND CreditCost = '" + vg.getCreditCost()
				+ "' AND ConsoleName = '" + vg.getConsole() + "' AND VersionName = '" + vg.getVersion() + "'" ;
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				isValid = false;
				JOptionPane.showMessageDialog(null, "There is already this game in the catalogue !");
				}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}

}
