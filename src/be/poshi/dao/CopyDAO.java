package be.poshi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import be.poshi.pojo.Copy;

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
