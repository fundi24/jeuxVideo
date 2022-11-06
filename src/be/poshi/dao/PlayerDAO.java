package be.poshi.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import be.poshi.connection.DatabaseConnection;
import be.poshi.pojo.Player;

public class PlayerDAO extends DAO<Player> {

	public PlayerDAO(Connection conn) {
		super(conn);
	}

	@Override
	public boolean create(Player obj){
		boolean success = false;
		LocalDate today = LocalDate.now();
		
		String query = "INSERT INTO User (Username, Password, RegistrationDate, Pseudo, Credit, DateOfBirth, Administrator) VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
			pstmt.setString(1, obj.getUsername());
			pstmt.setString(2, obj.getPassword());
			pstmt.setDate(3, Date.valueOf(today));
			pstmt.setString(4, obj.getPseudo());
			pstmt.setInt(5, 10);
			pstmt.setDate(6,Date.valueOf(obj.getDateOfBirth()));
			pstmt.setBoolean(7, false);
			pstmt.execute();
			pstmt.close();
			success = true;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return success;
	}

	@Override
	public boolean delete(Player obj) {
		return false;
	}

	@Override
	public boolean update(Player obj) {
		boolean success=false;

		LocalDate birthday = obj.getDateOfBirth();
		int dayBirthday = birthday.getDayOfMonth();
		Month monthBirthday = birthday.getMonth();
		LocalDate today = LocalDate.now();
		int day = today.getDayOfMonth();
		Month month = today.getMonth();
		
		String query="UPDATE User SET Credit='"+obj.getCredit()+"', ReceivedBirthdayGift = true WHERE IdUser='"+obj.getIdUser()+"'";
		String query2="UPDATE User SET ReceivedBirthdayGift = false WHERE IdUser='"+obj.getIdUser()+"'";
		
		if(day == dayBirthday && month == monthBirthday)
		{
			try {
				boolean ReceivedBirthdayGift = PlayerDAO.HasReceivedBirthdayGift(obj);
				if(ReceivedBirthdayGift == false)
				{
					PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query);
			        pstmt.executeUpdate();
			        pstmt.close();
			        success=true;
					JOptionPane.showMessageDialog(null, "You have received your credits, happy birthday !");
				}
			}
			catch(SQLException e){
				e.printStackTrace();
			}
			
		}
		else
		{
			try {
				PreparedStatement pstmt = (PreparedStatement) this.connect.prepareStatement(query2);
		        pstmt.executeUpdate();
		        pstmt.close();
		        success=true;
				JOptionPane.showMessageDialog(null, "It's not your birthday !");
			}
			catch(SQLException e){
				e.printStackTrace();
			}
		}
		
		return success; 
	}

	@Override
	public Player find(int id) {
		Player player = null;
		try {
			ResultSet result = this.connect
					.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery("SELECT * FROM User WHERE IdUser = " + id);
			if (result.first()) {
				player = new Player();
				player.setIdUser(id);
				player.setPseudo(result.getString("Pseudo"));
				player.setCredit(result.getInt("Credit"));
				player.setDateOfBirth(result.getDate("DateOfBirth").toLocalDate());
				player.setRegistrationDate(result.getDate("RegistrationDate").toLocalDate());
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return player;
	}
	
	@Override
	public ArrayList<Player> GetAll() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public static boolean CheckIfUsernameIsAvailable(String username) throws Exception
	{
		boolean isValid = true;
		Connection conn = DatabaseConnection.getInstance();
		String query = "SELECT * FROM User WHERE Username='" + username + "'";
		try {
			ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				isValid = false;
				throw new Exception("Username is already used !");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return isValid;
	}
	
	public static boolean HasReceivedBirthdayGift(Player player)
	{
		boolean received = false;
		int id = player.getIdUser();
		
		Connection conn = DatabaseConnection.getInstance();
		String query = "SELECT * FROM User WHERE IdUser='" + id + "'";
		try {
			ResultSet result = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)
					.executeQuery(query);
			if (result.first()) {
				boolean isReceived = result.getBoolean("ReceivedBirthdayGift");
				if(isReceived == true)
				{
					received = true;
					JOptionPane.showMessageDialog(null, "The gift has already been given !");				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return received;
	}

}
