package be.poshi.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

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
	private Player lender;

	// Constructeurs
	public Loan(Copy copy, Player borrower, Player lender) {
		this.copy = copy;
		this.borrower = borrower;
		this.lender = lender;
	}
	
	public Loan(Copy copy, Player borrower) {
		this.copy = copy;
		this.borrower = borrower;
	}

	public Loan(Copy copy) {
		this.copy = copy;
	}
	
	public Loan() {
		
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
	public boolean calculateBalance() {
		
		LocalDate today = LocalDate.now();
		int differenceInDays = (int)ChronoUnit.DAYS.between(endDate, today);
		int weeks = (differenceInDays / 7) + 1;
		int credit = copy.getVideoGame().getCreditCost();
		int costOfTheLoan = credit * weeks;
		int fine = 0;
		int newCreditLender = 0;
		int newCreditBorrower = 0;
		for (int i = 1; i<= weeks ; i++)
		{
			fine = 5 * differenceInDays;
		}
		
		newCreditLender = lender.getCredit() + costOfTheLoan + fine;
		newCreditBorrower = borrower.getCredit() - costOfTheLoan - fine;
		lender.setCredit(newCreditLender);
		borrower.setCredit(newCreditBorrower);
		
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> loanDAO = adf.getLoanDAO();
		return loanDAO.update(this);
	}

	public void endLoan() {
		setOngoing(false);
	}

	public boolean makeLoan() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Loan> loanDAO = adf.getLoanDAO();
		return loanDAO.create(this);
	}

}
