package be.poshi.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Objects;

import be.poshi.dao.AbstractDAOFactory;
import be.poshi.dao.DAO;

public class VideoGame implements Serializable {

	// Attributs
	private static final long serialVersionUID = 5171965949662273923L;
	private int idVideoGame;
	private String name;
	private int creditCost;
	private String console;
	private String version;
	private ArrayList<Copy> copies;
	private ArrayList<Booking> bookings;

	
	// Constructeur
	public VideoGame() {
		copies = new ArrayList<Copy>();
		bookings = new ArrayList<Booking>();
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

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	// Methodes supplementaires
	public Copy copyAvailable() {
		for(Copy c : copies)
		{
			if(c.isAvailable() == true)
			{
				return c;
			}
		}
		return null;
	}

	public void selectBooking() {
				
		int maxCredit = bookings.get(0).getPlayer().getCredit();
		int temp =0;
		int index1=0;
		int index2=0;
		
		for(int i=1; i<bookings.size(); i++)
		{
			temp = bookings.get(i).getPlayer().getCredit();
			LocalDate date = LocalDate.now();
			if( temp > maxCredit)
			{
				maxCredit = temp;
				index1 = i;
			}
			if(temp == maxCredit)
			{
				index1=i;
				index2=i+1;
				LocalDate playerDate1 = bookings.get(index1).getBookingDate();
				LocalDate playerDate2 = bookings.get(index2).getBookingDate();
				if(playerDate1.isBefore(playerDate2))
				{
					//bookings.get(index1).
				}
			}
		}
		bookings.get(index1);
		
		 
	}

	public static ArrayList<VideoGame> getAllVideoGame() {
		int notUsed = 0;
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
		return videoGameDAO.findAll(notUsed);
	}

	public boolean updateCredit() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
		return videoGameDAO.update(this);
	}

	public boolean createAVideoGame() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
		return videoGameDAO.create(this);
	}

	public boolean delete() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<VideoGame> videoGameDAO = adf.getVideoGameDAO();
		return videoGameDAO.delete(this);
	}

}
