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

	// Constructeurs
	public Player() {
		super();
		bookings = new ArrayList<Booking>();
		copies = new ArrayList<Copy>();
		loans = new ArrayList<Loan>();
		borrowings = new ArrayList<Loan>();
	}

	public Player(int idUser, String username, String password, int credit, String pseudo, LocalDate registrationDate,
			LocalDate dateOfBirth) {
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

	// Methodes supplementaires
	
	
	public boolean LoanAllowend() { 
		return false;
	}

	public void AddBirthdayBonus() {
			credit = credit + 2;
			AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
			DAO<Player> PlayerDAO = adf.getPlayerDAO();
			PlayerDAO.update(this);
	}
	
	public static boolean CheckIfUsernameIsAvailable(String username) throws Exception 
	{
		return PlayerDAO.CheckIfUsernameIsAvailable(username);
	}
	
	public boolean Register()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Player> PlayerDAO = adf.getPlayerDAO();
		return PlayerDAO.create(this);
	}
	
	public boolean HasReceivedBirthdayGift() throws Exception
	{
		return PlayerDAO.HasReceivedBirthdayGift(this);
	}

	// Methode de base
	@Override
	public String toString() {
		return "Player [credit=" + credit + ", pseudo=" + pseudo + ", registrationDate=" + registrationDate
				+ ", dateOfBirth=" + dateOfBirth + ", bookings=" + bookings + ", copies=" + copies + ", loanss=" + loans
				+ ", borrowings=" + borrowings + "]";
	}

}
