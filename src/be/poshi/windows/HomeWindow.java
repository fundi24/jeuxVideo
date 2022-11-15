package be.poshi.windows;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import be.poshi.pojo.Administrator;
import be.poshi.pojo.Player;
import be.poshi.pojo.User;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class HomeWindow extends JFrame {

	private JPanel contentPane;
	private User connectedUser;
	private JLabel LblPseudo;
	private JLabel LblHomePage;
	private JLabel LblCredit;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					HomeWindow frame = new HomeWindow();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public HomeWindow() {

	}

	public HomeWindow(User user) {
		this.connectedUser = user;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		getContentPane().setLayout(null);

		LblHomePage = new JLabel("Home Page");
		LblHomePage.setBounds(173, 10, 83, 13);
		getContentPane().add(LblHomePage);

		LblPseudo = new JLabel("Pseudo");
		LblPseudo.setBounds(352, 10, 45, 13);
		contentPane.add(LblPseudo);

		JButton BtnLogout = new JButton("Logout");
		BtnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				connectedUser = null;
				LoginWindow loginWindow = new LoginWindow();
				JOptionPane.showMessageDialog(null, "You are log out");
				loginWindow.setVisible(true);
				dispose();
			}
		});
		BtnLogout.setBounds(270, 150, 139, 21);
		getContentPane().add(BtnLogout);
		
		JButton BtnGetGameCatalogue = new JButton("See the catalogue");
		BtnGetGameCatalogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GetCatalogueWindow GCW = new GetCatalogueWindow(connectedUser);
				GCW.setVisible(true);
				dispose();
			}
		});
		BtnGetGameCatalogue.setBounds(270, 100, 139, 21);
		contentPane.add(BtnGetGameCatalogue);

		if (connectedUser instanceof Administrator) {			
			LblPseudo.setText("Admin");
			
		}
		if (connectedUser instanceof Player) {
			Player player = (Player) user;
						
			LblCredit = new JLabel("Credit");
			LblCredit.setBounds(325, 24, 72, 13);
			contentPane.add(LblCredit);

			LblPseudo.setText(player.getPseudo());
			LblCredit.setText(player.getCredit() + " credit(s)");
			
			JButton BtnMyLoans = new JButton("My loans");
			BtnMyLoans.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MyLoanWindow MLW = new MyLoanWindow(connectedUser);
					MLW.setVisible(true);
					dispose();
				}
			});
			BtnMyLoans.setBounds(10, 200, 139, 21);
			contentPane.add(BtnMyLoans);
			
			JButton BtnMyGames = new JButton("My games");
			BtnMyGames.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MyGamesWindow MGW = new MyGamesWindow(connectedUser);
					MGW.setVisible(true);
					dispose();
				}
			});
			BtnMyGames.setBounds(270, 200, 139, 21);
			contentPane.add(BtnMyGames);
			
			JButton BtnMybookings = new JButton("My bookings");
			BtnMybookings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MyBookingWindow MBW = new MyBookingWindow(connectedUser);
					MBW.setVisible(true);
					dispose();
				}
			});
			BtnMybookings.setBounds(10, 150, 139, 21);
			contentPane.add(BtnMybookings);
			
			JButton BtnMyborrowings = new JButton("My borrowing");
			BtnMyborrowings.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MyBorrowingWindow MBW = new MyBorrowingWindow(connectedUser);
					MBW.setVisible(true);
					dispose();
				}
			});
			BtnMyborrowings.setBounds(10, 100, 139, 21);
			contentPane.add(BtnMyborrowings);
			
			if(player.LoanAllowend() == true)
			{
				JButton BtnBorrow = new JButton("Borrow");
				BtnBorrow.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						BorrowWindow BW = new BorrowWindow(connectedUser);
						BW.setVisible(true);
						dispose();
					}
				});
				BtnBorrow.setBounds(10, 50, 139, 21);
				contentPane.add(BtnBorrow);
			}
			else
			{
				JOptionPane.showMessageDialog(this, "You can only lend games  !");
			}
	
			
			boolean success = player.AddBirthdayBonus();
			if(success == true)
			{
				JOptionPane.showMessageDialog(this, "You received 2 credits for your birthday. Please log in again !");
			}
			
		}

	}
}
