package be.poshi.pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class Loan implements Serializable{

	// Attributs
	private static final long serialVersionUID = 8365143624293081836L;
	private int idLoan;
	private LocalDate startDate;
	private LocalDate endDate;
	private Boolean ongoing;
	private Copy copy;
	private Player lender;
	private Player borrower;

	// Constructeurs
	public Loan(int idLoan, Copy copy, Player lender, Player borrower) {
		this.idLoan = idLoan;
		this.copy = copy;
		this.lender = lender;
		this.borrower = borrower;
	}

	public Loan(int idLoan, Copy copy, Player lender, Player borrower, LocalDate startDate, LocalDate endDate, Boolean ongoing) {
		this.idLoan = idLoan;
		this.copy = copy;
		this.lender = lender;
		this.borrower = borrower;
		this.startDate = startDate;
		this.endDate = endDate;
		this.ongoing = ongoing;
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

	// Methodes supplementaires
	public void CalculateBalance() {
	}

	public void EndLoan() {
	}

	// Methode de base
	@Override
	public String toString() {
		return "Loan [startDate=" + startDate + ", endDate=" + endDate + ", ongoing=" + ongoing + ", copy=" + copy
				+ ", lender=" + lender + ", borrower=" + borrower + "]";
	}
}
