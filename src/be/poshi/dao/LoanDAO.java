package be.poshi.dao;

import java.sql.Connection;
import java.util.ArrayList;

import be.poshi.pojo.Loan;

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
		return null;
	}

	@Override
	public ArrayList<Loan> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
