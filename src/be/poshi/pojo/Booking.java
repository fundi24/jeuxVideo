package be.poshi.pojo;

import java.time.LocalDate;

public class Booking {

	// Attributs
	private LocalDate bookingDate;
	private Player player;
	private VideoGame videoGame;

	// Constructeurs
	public Booking(Player player, VideoGame videoGame) {
		this.player = player;
		this.videoGame = videoGame;
	}

	public Booking(LocalDate bookingDate, Player player, VideoGame videoGame) {
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
		return "Booking [bookingDate=" + bookingDate + ", player=" + player + ", videoGame=" + videoGame + "]";
	}

}
