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
		int fine = 0;
		int newCreditLender = 0;
		int newCreditBorrower = 0;
		int loanDuration = (int) ChronoUnit.DAYS.between(startDate, today);
		int loanDurantionInWeeks;
		if (loanDuration % 7 == 0) {
			loanDurantionInWeeks = loanDuration / 7;
		} else {
			loanDurantionInWeeks = (loanDuration / 7) + 1;
		}
		int dayLate = (int) ChronoUnit.DAYS.between(endDate, today);
		int weeksLate;
		if (dayLate % 7 == 0) {
			weeksLate = dayLate / 7;

		} else {
			weeksLate = (dayLate / 7) + 1;

		}
		int credit = copy.getVideoGame().getCreditCost();
		int costOfTheLoan = 0;

		int oldCredit = 0;
		int newCredit = 0;
		int tempCredit = 0;
		LocalDate tempDate = this.startDate;

		//calcul si la liste est vide
		if (copy.getVideoGame().getPriceHistory().isEmpty() ) {
			for (int i = 1; i <= weeksLate; i++) {
				fine = 5 * dayLate;
			}

			costOfTheLoan = credit * loanDurantionInWeeks;
			newCreditLender = lender.getCredit() + costOfTheLoan + fine;
			newCreditBorrower = borrower.getCredit() - costOfTheLoan - fine;
			lender.setCredit(newCreditLender);
			borrower.setCredit(newCreditBorrower);

			AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
			DAO<Loan> loanDAO = adf.getLoanDAO();
			return loanDAO.update(this);
		} 
		else {
			//boucle jusqu'au dernier changement
			for (int i = 0; i <= copy.getVideoGame().getPriceHistory().size() - 1; i++) {
				if (copy.getVideoGame().getPriceHistory().get(i).getDateOfChange().isAfter(startDate)
						&& copy.getVideoGame().getPriceHistory().get(i).getDateOfChange().isBefore(today)) {
					LocalDate dateOfChange = copy.getVideoGame().getPriceHistory().get(i).getDateOfChange();
					int dayDifferenceBetweenTheFirst = (int) ChronoUnit.DAYS.between(tempDate, dateOfChange);
					int newWeeks;
					if (dayDifferenceBetweenTheFirst % 7 == 0) {
						newWeeks = dayDifferenceBetweenTheFirst / 7;
					} else {
						newWeeks = (dayDifferenceBetweenTheFirst / 7) + 1;
					}

					oldCredit = copy.getVideoGame().getPriceHistory().get(i).getOldCredit() * newWeeks;
					tempCredit = oldCredit + tempCredit;
					tempDate = dateOfChange;
				}
			}
			//dernier changement 
			int sizeMax = copy.getVideoGame().getPriceHistory().size() - 1;
			if (copy.getVideoGame().getPriceHistory().get(sizeMax).getDateOfChange().isAfter(startDate)
					&& copy.getVideoGame().getPriceHistory().get(sizeMax).getDateOfChange().isBefore(today)) {
				int dayDifferenceBetweenTheEnd = (int) ChronoUnit.DAYS
						.between(copy.getVideoGame().getPriceHistory().get(sizeMax).getDateOfChange(), today);
				int newWeeks1;
				if (dayDifferenceBetweenTheEnd % 7 == 0) {
					newWeeks1 = dayDifferenceBetweenTheEnd / 7;
				} else {
					newWeeks1 = (dayDifferenceBetweenTheEnd / 7) + 1;
				}
				newCredit = credit * newWeeks1;
				tempCredit = tempCredit + newCredit;
			}//si aucune date ne se trouve entre les changements
			else
			{
				costOfTheLoan = credit * loanDurantionInWeeks;
			}

			for (int y = 1; y <= weeksLate; y++) {
				fine = 5 * dayLate;
			}

			newCreditLender = lender.getCredit() + tempCredit + fine;
			newCreditBorrower = borrower.getCredit() - tempCredit - fine;
			lender.setCredit(newCreditLender);
			borrower.setCredit(newCreditBorrower);

			AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
			DAO<Loan> loanDAO = adf.getLoanDAO();
			return loanDAO.update(this);
		}
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
