package be.poshi.pojo;

import java.time.LocalDate;
import java.util.ArrayList;

import be.poshi.dao.AbstractDAOFactory;
import be.poshi.dao.DAO;
import be.poshi.dao.PlayerDAO;

public class Player extends User {

	// Attributs
	private static final long serialVersionUID = 1049137847898033097L;
	private int credit;
	private String pseudo;
	private LocalDate registrationDate;
	private LocalDate dateOfBirth;
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;
	private ArrayList<Loan> loans;
	private ArrayList<Loan> borrowings;

	// Constructeur
	public Player() {
		super();
		bookings = new ArrayList<Booking>();
		copies = new ArrayList<Copy>();
		loans = new ArrayList<Loan>();
		borrowings = new ArrayList<Loan>();
	}

	// Accesseurs
	public int getCredit() {
		return credit;
	}

	public void setCredit(int credit) {
		this.credit = credit;
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public LocalDate getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDate registrationDate) {
		this.registrationDate = registrationDate;
	}

	public LocalDate getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(LocalDate dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public ArrayList<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(ArrayList<Booking> bookings) {
		this.bookings = bookings;
	}

	public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}

	public ArrayList<Loan> getLoans() {
		return loans;
	}

	public void setLoans(ArrayList<Loan> loans) {
		this.loans = loans;
	}

	public ArrayList<Loan> getBorrowings() {
		return borrowings;
	}

	public void setBorrowings(ArrayList<Loan> borrowings) {
		this.borrowings = borrowings;
	}

	// Methodes supplementaires
	public boolean LoanAllowend() {
		return false;
	}

	public boolean AddBirthdayBonus() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Player> PlayerDAO = adf.getPlayerDAO();
		return PlayerDAO.update(this);
	}

	public static boolean CheckIfUsernameIsAvailable(String username) {
		return PlayerDAO.CheckIfUsernameIsAvailable(username);
	}

	public boolean Register() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Player> PlayerDAO = adf.getPlayerDAO();
		return PlayerDAO.create(this);
	}

}
