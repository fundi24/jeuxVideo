package be.poshi.pojo;

import java.io.Serializable;
import java.util.ArrayList;

public class VideoGame implements Serializable{

	// Attributs
	private static final long serialVersionUID = 5171965949662273923L;
	private int idVideoGame;
	private String name;
	private String creditCost;
	private String console;
	private String version;
	private ArrayList<Booking> bookings;
	private ArrayList<Copy> copies;

	// Constructeurs
	public VideoGame() {
		bookings = new ArrayList<Booking>();
		copies = new ArrayList<Copy>();
	}

	public VideoGame(int idVideoGame, String name, String creditCost, String version, String console) {
		this.idVideoGame = idVideoGame;
		this.name = name;
		this.creditCost = creditCost;
		this.version = version;
		this.console = console;
		bookings = new ArrayList<Booking>();
		copies = new ArrayList<Copy>();
	}

	// Accesseurs
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCreditCost() {
		return creditCost;
	}

	public void setCreditCost(String creditCost) {
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

	// Methode de base
	@Override
	public String toString() {
		return "VideoGame [name=" + name + ", creditCost=" + creditCost + ", console=" + console + ", bookings="
				+ bookings + ", copies=" + copies + "]";
	}

}
