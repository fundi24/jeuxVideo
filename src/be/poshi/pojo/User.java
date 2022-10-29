package be.poshi.pojo;

public abstract class User {

	// Attributs
	private String username;
	private String password;

	// Constructeurs
	public User() {
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	// Accesseurs
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	// Methodes supplementaires
	public void Login() {
	}

	// Methode de base
	@Override
	public String toString() {
		return "User [username=" + username + ", password=" + password + "]";
	}

}
