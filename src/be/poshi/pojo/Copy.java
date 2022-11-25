package be.poshi.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import be.poshi.dao.AbstractDAOFactory;
import be.poshi.dao.DAO;

public class Copy implements Serializable {

	// Attributs
	private static final long serialVersionUID = 8982261286529829239L;
	private int idCopy;
	private VideoGame videoGame;
	private Player owner;
	private Loan loan;

	// Constructeurs
	public Copy(VideoGame videoGame, Player owner, Loan loan)
	{
		this.videoGame = videoGame;
		this.owner = owner;
		this.loan = loan;
	}
	
	public Copy(VideoGame videoGame, Player owner) {
		this.videoGame = videoGame;
		this.owner = owner;
	}

	public Copy(VideoGame videoGame) {
		this.videoGame = videoGame;
	}
	
	public Copy() {}

	// Accesseurs
	public int getIdCopy() {
		return idCopy;
	}

	public void setIdCopy(int idCopy) {
		this.idCopy = idCopy;
	}

	public VideoGame getVideoGame() {
		return videoGame;
	}

	public void setVideoGame(VideoGame videoGame) {
		this.videoGame = videoGame;
	}

	public Loan getLoan() {
		return loan;
	}

	public void setLoan(Loan loan) {
		this.loan = loan;
	}

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	// Methodes supplementaires
	public void releaseCopy() {
		this.loan = new Loan();
	}

	public boolean borrow() {
		loan.setCopy(this);
		return this.loan.makeLoan();
	}

	public boolean isAvailable() {
		boolean isValid = true;
		
		if(this.loan == null || this.loan.getOngoing() == true)
		{
			isValid = false;
		}
		return isValid;
	}

	public boolean createACopy() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> copyDAO = adf.getCopyDAO();
		return copyDAO.create(this);
	}

	public boolean deleteACopy() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> copyDAO = adf.getCopyDAO();
		return copyDAO.delete(this);
	}

	public static ArrayList<Copy> getAllCopy(User user) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> copyDAO = adf.getCopyDAO();
		return copyDAO.findAll(user.getIdUser());
	}
	
}
