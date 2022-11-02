package be.poshi.pojo;

import java.io.Serializable;

public abstract class User implements Serializable {

	// Attributs
	private static final long serialVersionUID = 8701479018979360310L;
	private int idUser;
	private String username;
	private String password;

	// Constructeurs
	public User() {
	}

	public User(int idUser, String username, String password) {
		this.idUser = idUser;
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
