package be.poshi.pojo;

public class Copy {

	// Attributs
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
