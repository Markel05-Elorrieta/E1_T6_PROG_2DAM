package view;

import java.awt.Font;
import model.dao.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import resources.GlobalVariables;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;


public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldErabiltzailea;
	private JPasswordField passwordField;
	UserDAO userDAO = new UserDAO();

	/**
	 * Create the frame.
	 */
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin_Header = new JLabel("LOGIN");
		lblLogin_Header.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_Header.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblLogin_Header.setBounds(0, 11, 984, 35);
		contentPane.add(lblLogin_Header);
		
		JLabel lblErabiltzaile = new JLabel("Erabiltzailea");
		lblErabiltzaile.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblErabiltzaile.setBounds(341, 144, 132, 22);
		contentPane.add(lblErabiltzaile);
		
		textFieldErabiltzailea = new JTextField();
		textFieldErabiltzailea.setBounds(341, 177, 299, 29);
		textFieldErabiltzailea.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(textFieldErabiltzailea);
		textFieldErabiltzailea.setColumns(10);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblPasahitza.setBounds(341, 229, 132, 22);
		contentPane.add(lblPasahitza);
		
		JButton btnErregistroa = new JButton("🆕 Erregistratu");
		btnErregistroa.setVerticalAlignment(SwingConstants.BOTTOM);
		btnErregistroa.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnErregistroa.setBounds(114, 463, 196, 35);
		btnErregistroa.setFocusPainted(false);
		contentPane.add(btnErregistroa);
		
		JButton btnLoginEgin = new JButton("☑️ Login egin");
		btnLoginEgin.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLoginEgin.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLoginEgin.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnLoginEgin.setBounds(654, 463, 206, 35);
		btnLoginEgin.setFocusPainted(false);
		contentPane.add(btnLoginEgin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(341, 262, 299, 29);
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(passwordField);
		
		JLabel lblOngiEtorriJem = new JLabel("Ongi etorri JEM Fit aplikaziora! Sartzeko, login egin edo erregistratu.");
		lblOngiEtorriJem.setHorizontalAlignment(SwingConstants.CENTER);
		lblOngiEtorriJem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblOngiEtorriJem.setBounds(0, 57, 984, 22);
		contentPane.add(lblOngiEtorriJem);
		
		// LISTENERS
		
		// Login button listener
		btnLoginEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

					boolean isUser;
					try {
						// Check the login
						isUser = userDAO.checkLogin(textFieldErabiltzailea.getText(), passwordField.getText());
						if (isUser) {
							// If the login is correct
							JOptionPane.showMessageDialog(null, "Loged", "Login", JOptionPane.INFORMATION_MESSAGE);
							
						} else {
							// If the login is incorrect
							JOptionPane.showMessageDialog(null, "Log false", "Login", JOptionPane.ERROR_MESSAGE);
						}
			
					} catch (Exception e1) {
						e1.printStackTrace();
					}

			}
		});
		
		// Register button listener
		btnErregistroa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	dispose();
            	ErregistroaView erregistroa = new ErregistroaView();
            	erregistroa.setVisible(true);
            }
		});
	}
}
