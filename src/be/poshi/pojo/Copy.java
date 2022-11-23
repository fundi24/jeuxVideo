package be.poshi.pojo;

import java.io.Serializable;
import java.util.ArrayList;

import be.poshi.dao.AbstractDAOFactory;
import be.poshi.dao.CopyDAO;
import be.poshi.dao.DAO;

public class Copy implements Serializable {

	// Attributs
	private static final long serialVersionUID = 8982261286529829239L;
	private int idCopy;
	private VideoGame videoGame;
	private Player owner;

	// Constructeurs
	public Copy(VideoGame videoGame, Player owner) {
		this.videoGame = videoGame;
		this.owner = owner;
	}

	public Copy(VideoGame videoGame) {
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

	public Player getOwner() {
		return owner;
	}

	public void setOwner(Player owner) {
		this.owner = owner;
	}

	// Methodes supplementaires
	public void releaseCopy() {
	}

	public void borrow() {
	}

	public boolean isAvailable() {
		AbstractDAOFactory adf = AbstractDAOFactory.getFactory();
		DAO<Copy> copyDAO = adf.getCopyDAO();
		return  ((CopyDAO)copyDAO).checkForLoan(this.getIdCopy());
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
	
	/*public static ArrayList<Copy> getAllCopyOfAGame(User user, VideoGame videogame)
	{
		ArrayList<Copy> copiesOfAGame = new ArrayList<Copy>();
		ArrayList<Copy> copies = getAllCopy(user);
		
		for(Copy c : copies)
		{
			if(c.videoGame.equals(videogame))
			copiesOfAGame.add(c);
		}
		
		return copiesOfAGame;
		
	}*/

}
