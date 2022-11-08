package be.poshi.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import be.poshi.dao.AbstractDAOFactory;
import be.poshi.dao.DAO;
import be.poshi.dao.VideoGameDAO;

public class VideoGame implements Serializable{

	// Attributs
	private static final long serialVersionUID = 5171965949662273923L;
	private int idVideoGame;
	private String name;
	private int creditCost;
	private String console;
	private String version;
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;

	// Constructeurs
	public VideoGame() {
		bookings = new ArrayList<Booking>();
		copies = new ArrayList<Copy>();
	}

	public VideoGame(int idVideoGame, String name, int creditCost, String version, String console) {
		this.idVideoGame = idVideoGame;
		this.name = name;
		this.creditCost = creditCost;
		this.version = version;
		this.console = console;
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

	// Methodes supplementaires
	public Copy CopyAvailable() {
		return null;
	}

	public void SelectBooking() {
	}
	
	public static ArrayList<VideoGame> GetAllVideoGame()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> VideoGameDAO = adf.getVideoGameDAO();
		return VideoGameDAO.GetAll();
	}
	
	public int GetId()
	{
		return VideoGameDAO.GetId(this);
	}
	
	public boolean UpdateCredit()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> VideoGameDAO = adf.getVideoGameDAO();
		return VideoGameDAO.update(this);
	}

	// Methode de base
	@Override
	public String toString() {
		return "VideoGame [idVideoGame=" + idVideoGame + ", name=" + name + ", creditCost=" + creditCost + ", console="
				+ console + ", version=" + version + ", bookings=" + bookings + ", copies=" + copies + "]";
	}
	
	
}
