package be.poshi.pojo;

import java.io.Serializable;

public class Copy implements Serializable {

	// Attributs
	private static final long serialVersionUID = 8982261286529829239L;
	private int idCopy;
	private VideoGame videoGame;
	private Loan loan;
	private Player owner;

	// Constructeurs
	public Copy(int idCopy, VideoGame videoGame, Loan loan, Player owner) {
		this.idCopy = idCopy;
		this.videoGame = videoGame;
		this.loan = loan;
		this.owner = owner;
	}

	public Copy(int idCopy, VideoGame videoGame, Player owner) {
		this.idCopy = idCopy;
		this.videoGame = videoGame;
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

	// Methode de base
	@Override
	public String toString() {
		return "Copy [videoGame=" + videoGame + ", loan=" + loan + ", owner=" + owner + "]";
	}

}
