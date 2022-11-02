package be.poshi.pojo;

import java.io.Serializable;
import java.time.LocalDate;

public class Booking implements Serializable{

	// Attributs
	private static final long serialVersionUID = -2547447650772941403L;
	private int idBooking;
	private LocalDate bookingDate;
	private Player player;
	private VideoGame videoGame;

	// Constructeurs
	public Booking(int idBooking, Player player, VideoGame videoGame) {
		this.idBooking = idBooking;
		this.player = player;
		this.videoGame = videoGame;
	}

	public Booking(int idBooking, LocalDate bookingDate, Player player, VideoGame videoGame) {
		this.idBooking = idBooking;
		this.bookingDate = bookingDate;
		this.player = player;
		this.videoGame = videoGame;
	}

	// Accesseurs
	public LocalDate getBookingDate() {
		return bookingDate;
	}

	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}

	// Methodes supplementaires
	public void Delete() {
	}

	// Methode de base
	@Override
	public String toString() {
		return "Booking [idBooking=" + idBooking+ "bookingDate=" + bookingDate + ", player=" + player + ", videoGame=" + videoGame + "]";
	}

}
