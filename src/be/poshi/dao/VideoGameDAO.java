package be.poshi.dao;

import java.sql.Connection;

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

}
