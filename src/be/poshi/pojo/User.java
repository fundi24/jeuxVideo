package be.poshi.pojo;

import java.io.Serializable;

import be.poshi.dao.UserDAO;

public abstract class User implements Serializable {

	// Attributs
	private static final long serialVersionUID = 8701479018979360310L;
	private int idUser;
	private String username;
	private String password;

	// Constructeur
	public User() {
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
	public static User login(String username, String password) throws Exception {
		return UserDAO.login(username, password);
	}

}
