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
	private boolean ongoing;
	private Copy copy;
	private Player borrower;

	// Constructeurs
	public Loan(Copy copy, Player borrower) {
		this.copy = copy;
		this.borrower = borrower;
	}

	public Loan(Copy copy) {
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

	public boolean getOngoing() {
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

	public Player getBorrower() {
		return borrower;
	}

	public void setBorrower(Player borrower) {
		this.borrower = borrower;
	}

	// Methodes supplementaires
	public void calculateBalance() {
	}

	public void endLoan() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> loanDAO = adf.getLoanDAO();
		loanDAO.update(this);
	}

	public boolean makeLoan() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> loanDAO = adf.getLoanDAO();
		return loanDAO.create(this);
	}

}
