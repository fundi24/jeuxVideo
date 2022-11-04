package be.poshi.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.poshi.pojo.Copy;

public class CopyDAO extends DAO<Copy> {

	public CopyDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Copy obj) {
		return false;
	}

	@Override
	public boolean delete(Copy obj) {
		return false;
	}

	@Override
	public boolean update(Copy obj) {
		return false;
	}

	@Override
	public Copy find(int id) {
		return null;
	}

	@Override
	public ArrayList<Copy> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
