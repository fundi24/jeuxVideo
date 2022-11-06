package be.poshi.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
		return false;
	}

	@Override
	public boolean update(VideoGame obj) {
		return false;
	}

	@Override
	public VideoGame find(int id) {
		VideoGame videoGame = null;
		
		String query = "SELECT * FROM ((VideoGame INNER JOIN Version ON VideoGame.IdVersion = Version.IdVersion) INNER JOIN Console on Version.IdConsole = Console.IdConsole) WHERE VideoGame.IdVideoGame = '" + id + "'";
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
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
	public ArrayList<VideoGame> GetAll(){
		ArrayList<VideoGame> videoGames = new ArrayList<VideoGame>();
		
		String query = "SELECT * FROM VideoGame";
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
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

}
