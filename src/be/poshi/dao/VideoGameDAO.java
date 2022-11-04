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
		return null;
	}

	@Override
	public ArrayList<VideoGame> GetAll(){
		ArrayList<VideoGame> videoGames = new ArrayList<VideoGame>();
		
		String query = "SELECT * FROM VideoGame AS vg INNER JOIN Version AS v ON vg.IdVersion = v.IdVersion ";
		try {
			ResultSet result = this.connect.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			while (result.next()) {
				VideoGame vg = new VideoGame();
				vg.setIdVideoGame(result.getInt("IdVideoGame"));
				vg.setName(result.getString("VideoGameName"));
				vg.setCreditCost(result.getInt("CreditCost"));
				//vg.setConsole(result.getString("ConsoleName"));
				vg.setVersion(result.getString("VersionName"));
				videoGames.add(vg);
			} 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return videoGames;
	}

}
