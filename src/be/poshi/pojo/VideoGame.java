package be.poshi.pojo;

import java.io.Serializable;
import java.util.ArrayList;
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
	//private ArrayList<Copy> copies;

	// Constructeur
	public VideoGame() {
		//copies = new ArrayList<Copy>();
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
	

	/*public ArrayList<Copy> getCopies() {
		return copies;
	}

	public void setCopies(ArrayList<Copy> copies) {
		this.copies = copies;
	}*/

	public String getConsole() {
		return console;
	}

	public void setConsole(String console) {
		this.console = console;
	}

	// Methodes supplementaires
	public Copy copyAvailable() {
		return null;
	}

	public void selectBooking() {
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

	// Methode de base
	@Override
	public int hashCode() {
		return Objects.hash(console, creditCost, idVideoGame, name, version);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		VideoGame other = (VideoGame) obj;
		return Objects.equals(console, other.console) && creditCost == other.creditCost
				&& idVideoGame == other.idVideoGame && Objects.equals(name, other.name)
				&& Objects.equals(version, other.version);
	}
	
	

}
