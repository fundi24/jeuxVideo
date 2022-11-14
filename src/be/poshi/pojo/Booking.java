package be.poshi.pojo;

import java.io.Serializable;
import java.time.LocalDate;

import be.poshi.dao.AbstractDAOFactory;
import be.poshi.dao.DAO;

public class Booking implements Serializable {

	// Attributs
	private static final long serialVersionUID = -2547447650772941403L;
	private int idBooking;
	private LocalDate bookingDate;
	private Player player;
	private VideoGame videoGame;

	// Constructeur
	public Booking(Player player, VideoGame videoGame) {
		this.player = player;
		this.videoGame = videoGame;
	}
	
	public Booking(VideoGame videoGame) //A verifier !!!
	{
		this.videoGame = videoGame;
	}

	// Accesseurs
	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	public int getIdBooking() {
		return idBooking;
	}

	public void setIdBooking(int idBooking) {
		this.idBooking = idBooking;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public VideoGame getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}

	// Methodes supplementaires
	public boolean MakeABooking()
	{
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Booking> BookingDAO = adf.getBookingDAO();
		return BookingDAO.create(this);
	}
	
	public void Delete() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Booking> BookingDAO = adf.getBookingDAO();
		BookingDAO.delete(this);
	}

}
