package be.poshi.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.poshi.connection.DatabaseConnection;
import be.poshi.pojo.VideoGame;

public class VideoGameDAO extends DAO<VideoGame> {

	public VideoGameDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(VideoGame obj) {
		return false;
	}

	@Override
	public boolean delete(VideoGame obj) {
		boolean success = false;

		String query = "DELETE FROM VideoGame WHERE IdVideoGame='" + obj.getIdVideoGame()+"'";

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

		String query = "SELECT * FROM ((VideoGame INNER JOIN Version ON VideoGame.IdVersion = Version.IdVersion) INNER JOIN Console on Version.IdConsole = Console.IdConsole) WHERE VideoGame.IdVideoGame = '"
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
	public ArrayList<VideoGame> GetAll() {
		ArrayList<VideoGame> videoGames = new ArrayList<VideoGame>();

		String query = "SELECT * FROM VideoGame";
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY).executeQuery(query);
			while (result.next()) {
				int id = result.getInt("IdVideoGame");
				VideoGame vg = find(id);
				videoGames.add(vg);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videoGames;
	}

	public static int GetId(VideoGame videoGame) {
		int id = 0;

		Connection conn = DatabaseConnection.getInstance();

		String query = "SELECT * FROM ((VideoGame INNER JOIN Version ON VideoGame.IdVersion = Version.IdVersion) INNER JOIN Console on Version.IdConsole = Console.IdConsole) WHERE VideoGame.VideoGameName ='"
				+ videoGame.getName() + "' AND CreditCost ='" + videoGame.getCreditCost() + "'AND ConsoleName = '"
				+ videoGame.getConsole() + "' AND VersionName ='" + videoGame.getVersion() + "'";
		try {
			ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				id = result.getInt("IdVideoGame");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return id;
	}

}
