package be.poshi.pojo;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Random;

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
		for (Copy c : copies) {
			if (c.isAvailable() == true) {
				return c;
			}
		}
		return null;
	}

	public Booking selectBooking() {

		// Recuperer chacun des points de comparaison du premier element de la liste
		int maxCredit = bookings.get(0).getPlayer().getCredit();
		LocalDate olderBooking = bookings.get(0).getBookingDate();
		LocalDate olderRegistration = bookings.get(0).getPlayer().getRegistrationDate();
		LocalDate today = LocalDate.now();
		int oldestPlayer = Period.between(bookings.get(0).getPlayer().getDateOfBirth(), today).getYears();
		Random random = new Random();
		int ran = random.nextInt(bookings.size());

		// Valeurs temporaires de chaque comparaison
		int temp1 = 0;
		int indexCredit = 0;
		int tempResult1 = 0;
		int nbrResult1 = 0;

		LocalDate temp2 = LocalDate.of(1922, 11, 29);
		int indexOlderBooking = 0;
		LocalDate temp2Result = LocalDate.of(1922, 11, 29);
		int nbrResult2 = 0;

		LocalDate temp3 = LocalDate.of(1922, 11, 29);
		int indexOlderRegistration = 0;
		LocalDate temp3Result = LocalDate.of(1922, 11, 29);
		int nbrResult3 = 0;

		int temp4 = 0;
		int indexAge = 0;
		int tempResult4 = 0;
		int nbrResult4 = 0;

		// Comparaison sur les credits
		// comparer au premier element et recuperer le maximum
		for (int i = 1; i < bookings.size(); i++) {
			temp1 = bookings.get(i).getPlayer().getCredit();
			if (temp1 > maxCredit) {
				maxCredit = temp1;
				indexCredit = i;
			}
		}
		// verifier s'il y a un ou plusieurs valeurs maximums identiques
		for (int i = 0; i < bookings.size(); i++) {
			tempResult1 = bookings.get(indexCredit).getPlayer().getCredit();
			if (bookings.get(i).getPlayer().getCredit() == tempResult1) {
				nbrResult1++;
			}
		}
		// si result = 1 retourne l'objet sinon on continue
		if (nbrResult1 == 1) {
			return bookings.get(indexCredit);
		} else {
			// comparaison sur la date de reservation
			for (int i = 1; i < bookings.size(); i++) {
				temp2 = bookings.get(i).getBookingDate();
				if (temp2.isBefore(olderBooking)) {
					olderBooking = temp2;
					indexOlderBooking = i;
				}
			}
			for (int i = 0; i < bookings.size(); i++) {
				temp2Result = bookings.get(indexOlderBooking).getBookingDate();
				if (bookings.get(i).getBookingDate().isEqual(temp2Result)) {
					nbrResult2++;
				}
			}
			if (nbrResult2 == 1) {
				return bookings.get(indexOlderBooking);
			} else {
				// comparaison sur la date d'inscription
				for (int i = 1; i < bookings.size(); i++) {
					temp3 = bookings.get(i).getPlayer().getRegistrationDate();
					if (temp3.isBefore(olderRegistration)) {
						olderRegistration = temp3;
						indexOlderRegistration = i;
					}
				}
				for (int i = 0; i < bookings.size(); i++) {
					temp3Result = bookings.get(indexOlderRegistration).getPlayer().getRegistrationDate();
					if (bookings.get(i).getPlayer().getRegistrationDate().equals(temp3Result)) {
						nbrResult3++;
					}
				}
				if (nbrResult3 == 1) {
					return bookings.get(indexOlderRegistration);
				}
				// comparaison sur l'age
				else {
					for (int i = 1; i < bookings.size(); i++) {
						temp4 = Period.between(bookings.get(i).getPlayer().getDateOfBirth(), today).getYears();
						if (temp4 > oldestPlayer) {
							oldestPlayer = temp4;
							indexAge = i;
						}
					}
					for (int i = 0; i < bookings.size(); i++) {
						tempResult4 = Period.between(bookings.get(indexAge).getPlayer().getDateOfBirth(), today)
								.getYears();
						if (Period.between(bookings.get(i).getPlayer().getDateOfBirth(), today)
								.getYears() == tempResult4) {
							nbrResult4++;
						}
					}
					if (nbrResult4 == 1) {
						return bookings.get(indexAge);
					}
					// aleatoire
					else {
						return bookings.get(ran);
					}
				}
			}
		}

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
