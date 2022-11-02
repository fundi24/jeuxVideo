package be.poshi.pojo;

import java.util.ArrayList;
import java.util.Date;


public class Player extends User {

	// Attributs
	private static final long serialVersionUID = 1049137847898033097L;
	private int credit;
	private String pseudo;
	private Date registrationDate;
	private Date dateOfBirth;
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;
	private ArrayList<Loan> loans;
	private ArrayList<Loan> borrowings;

	// Constructeurs
	public Player() {
		super();
		bookings = new ArrayList<Booking>();
		copies = new ArrayList<Copy>();
		loans = new ArrayList<Loan>();
		borrowings = new ArrayList<Loan>();
	}

	public Player(int idUser, String username, String password, int credit, String pseudo, Date registrationDate,
			Date dateOfBirth) {
		super(idUser, username, password);
		this.credit = credit;
		this.pseudo = pseudo;
		this.registrationDate = registrationDate;
		this.dateOfBirth = dateOfBirth;
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

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	// Methodes supplementaires
	
	
	public boolean LoanAllowend() {
		return false;
	}

	public void AddBirthdayBonus() {
	}

	// Methode de base
	@Override
	public String toString() {
		return "Player [credit=" + credit + ", pseudo=" + pseudo + ", registrationDate=" + registrationDate
				+ ", dateOfBirth=" + dateOfBirth + ", bookings=" + bookings + ", copies=" + copies + ", loanss=" + loans
				+ ", borrowings=" + borrowings + "]";
	}

}
