package be.poshi.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

import be.poshi.dao.AbstractDAOFactory;
import be.poshi.dao.DAO;

public class Loan implements Serializable {

	// Attributs
	private static final long serialVersionUID = 8365143624293081836L;
	private int idLoan;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean ongoing;
	private Copy copy;
	private Player lender;
	private Player borrower;

	// Constructeur
	public Loan(Copy copy, Player lender, Player borrower) {
		this.copy = copy;
		this.lender = lender;
		this.borrower = borrower;
	}
	
	public Loan(Copy copy)  //A verifier !!!
	{
		this.copy = copy;
	}

	// Accesseurs
	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public Boolean getOngoing() {
		return ongoing;
	}

	public void setOngoing(Boolean ongoing) {
		this.ongoing = ongoing;
	}

	public int getIdLoan() {
		return idLoan;
	}

	public void setIdLoan(int idLoan) {
		this.idLoan = idLoan;
	}

	public Copy getCopy() {
		return copy;
	}

	public void setCopy(Copy copy) {
		this.copy = copy;
	}

	public Player getLender() {
		return lender;
	}

	public void setLender(Player lender) {
		this.lender = lender;
	}

	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}

	// Methodes supplementaires
	public void CalculateBalance() {
	}

	public void EndLoan() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> LoanDAO = adf.getLoanDAO();
		LoanDAO.update(this);
	}
	
	public static ArrayList<Loan> GetAllLoansAvailable(int id) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> LoanDAO = adf.getLoanDAO();
		return LoanDAO.findAll(id);
	}

}
