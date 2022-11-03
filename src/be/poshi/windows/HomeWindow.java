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
				loginWindow.setVisible(true);
				dispose();
			}
		});
		BtnLogout.setBounds(10, 163, 85, 21);
		getContentPane().add(BtnLogout);

		JButton BtnGetGameCatalogue = new JButton("See the game catalogue");
		BtnGetGameCatalogue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		BtnGetGameCatalogue.setBounds(10, 114, 139, 21);
		contentPane.add(BtnGetGameCatalogue);

		if (connectedUser instanceof Administrator) {
			LblPseudo.setText("Admin");
			JButton BtnAddVideoGame = new JButton("Add a video game");
			BtnAddVideoGame.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					AddAVideoGameWindow AAVGW = new AddAVideoGameWindow(connectedUser);
					AAVGW.setVisible(true);
					dispose();
				}
			});
			BtnAddVideoGame.setBounds(10, 65, 139, 21);
			contentPane.add(BtnAddVideoGame);

		}
		if (connectedUser instanceof Player) {
			Player player = (Player) user;
			LblPseudo.setText(player.getPseudo());
		}

	}
}
