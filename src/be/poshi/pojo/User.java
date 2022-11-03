package be.poshi.pojo;

import java.io.Serializable;

import be.poshi.dao.UserDAO;

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
	
	public int getIdUser() {
		return idUser;
	}

	public void setIdUser(int idUser) {
		this.idUser = idUser;
	}
	
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
	public static User Login(String username, String password) throws Exception {
		return UserDAO.Login(username, password);
	}

	// Methode de base
	@Override
	public String toString() {
		return "User [idUser=" + idUser + ", username=" + username + ", password=" + password + "]";
	}

}
