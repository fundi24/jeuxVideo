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
	private Loan loan;
	private Player owner;

	// Constructeurs
	public Copy(VideoGame videoGame, Loan loan, Player owner) {
		this.videoGame = videoGame;
		this.loan = loan;
		this.owner = owner;
	}

	public Copy(VideoGame videoGame, Player owner) {
		this.videoGame = videoGame;
		this.owner = owner;
	}
	
	public Copy(VideoGame videoGame) //A verifier !!!
	{
		this.videoGame = videoGame;
	}

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
	public void ReleaseCopy() {
	}

	public void Borrow() {
	}

	public Boolean IsAvailable() {
		return false;
	}

	public boolean CreateACopy() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> CopyDAO = adf.getCopyDAO();
		return CopyDAO.create(this);
	}
	
	public boolean DeleteACopy(){
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> CopyDAO = adf.getCopyDAO();
		return CopyDAO.delete(this);
	}
	
	public static ArrayList<Copy> GetAllCopy(int id) {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> CopyDAO = adf.getCopyDAO();
		return CopyDAO.findAll(id);
	}

}
