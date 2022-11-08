package be.poshi.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import be.poshi.dao.AbstractDAOFactory;
import be.poshi.dao.DAO;
import be.poshi.dao.VideoGameDAO;

public class VideoGame implements Serializable {

	// Attributs
	private static final long serialVersionUID = 5171965949662273923L;
	private int idVideoGame;
	private String name;
	private int creditCost;
	private String console;
	private String version;
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;

	// Constructeur
	public VideoGame() {
		bookings = new ArrayList<Booking>();
		copies = new ArrayList<Copy>();
	}

	// Accesseurs

	public int getIdVideoGame() {
		return idVideoGame;
	}

	public void setIdVideoGame(int idVideoGame) {
		this.idVideoGame = idVideoGame;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCreditCost() {
		return creditCost;
	}

	public void setCreditCost(int creditCost) {
		this.creditCost = creditCost;
	}

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
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

	// Methodes supplementaires
	public Copy CopyAvailable() {
		return null;
	}

	public void SelectBooking() {
	}

	public static ArrayList<VideoGame> GetAllVideoGame() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> VideoGameDAO = adf.getVideoGameDAO();
		return VideoGameDAO.GetAll();
	}

	public int GetIdFromDb() {
		return VideoGameDAO.GetId(this);
	}

	public boolean UpdateCredit() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> VideoGameDAO = adf.getVideoGameDAO();
		return VideoGameDAO.update(this);
	}

	public boolean Delete() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> VideoGameDAO = adf.getVideoGameDAO();
		return VideoGameDAO.delete(this);
	}

}
