package view;

import java.awt.Font;
import model.dao.*;
import model.exceptions.LostDbConnection;
import model.metodoak.GlobalButtons;

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
import javax.swing.JSeparator;


public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textFieldErabiltzailea;
	private JPasswordField passwordField;
	private JLabel lblConnecStatus;
	private GlobalButtons globalButtons = new GlobalButtons();
	UserDAO userDAO = new UserDAO();

	/**
	 * Create the frame.
	 */
	public LoginView(String erabiltzaileaParam) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		setTitle("Login - JEM Fit");
		
		BackgroundImageView panel = new BackgroundImageView();
		setContentPane(panel);
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblLogin_Header = new JLabel("LOGIN");
		lblLogin_Header.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_Header.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblLogin_Header.setBounds(0, 11, 984, 35);
		panel.add(lblLogin_Header);
		
		JLabel lblErabiltzaile = new JLabel("Erabiltzailea");
		lblErabiltzaile.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblErabiltzaile.setBounds(341, 144, 132, 22);
		panel.add(lblErabiltzaile);
		
		textFieldErabiltzailea = new JTextField();
		textFieldErabiltzailea.setBounds(341, 177, 299, 29);
		textFieldErabiltzailea.setBorder(new LineBorder(new Color(0, 0, 0)));
		textFieldErabiltzailea.setText(erabiltzaileaParam);
		panel.add(textFieldErabiltzailea);
		textFieldErabiltzailea.setColumns(10);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblPasahitza.setBounds(341, 229, 132, 22);
		panel.add(lblPasahitza);
		
		JButton btnErregistroa = new JButton("🆕 Erregistratu");
		btnErregistroa.setVerticalAlignment(SwingConstants.BOTTOM);
		btnErregistroa.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnErregistroa.setBounds(378, 468, 206, 35);
		btnErregistroa.setFocusPainted(false);
		panel.add(btnErregistroa);
		
		JButton btnLoginEgin = new JButton("☑️ Login");
		btnLoginEgin.setHorizontalAlignment(SwingConstants.RIGHT);
		btnLoginEgin.setVerticalAlignment(SwingConstants.BOTTOM);
		btnLoginEgin.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnLoginEgin.setBounds(402, 330, 161, 35);
		btnLoginEgin.setFocusPainted(false);
		panel.add(btnLoginEgin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(341, 262, 299, 29);
		passwordField.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.add(passwordField);
		
		JLabel lblOngiEtorriJem = new JLabel("Ongi etorri JEM Fit aplikaziora! Sartzeko, login egin edo erregistratu.");
		lblOngiEtorriJem.setHorizontalAlignment(SwingConstants.CENTER);
		lblOngiEtorriJem.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblOngiEtorriJem.setBounds(0, 57, 984, 22);
		panel.add(lblOngiEtorriJem);
		
		JLabel lblBerria = new JLabel("Berria zara? Erregistratu hemen.");
		lblBerria.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblBerria.setHorizontalAlignment(SwingConstants.CENTER);
		lblBerria.setBounds(337, 426, 299, 22);
		panel.add(lblBerria);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(261, 401, 447, 14);
		panel.add(separator);
		
		// LISTENERS
		
		// Enter key listener
		passwordField.addKeyListener(new java.awt.event.KeyAdapter() {
		    public void keyPressed(java.awt.event.KeyEvent evt) {
		        if (evt.getKeyCode() == java.awt.event.KeyEvent.VK_ENTER) {
		            btnLoginEgin.doClick(); // Trigger the login button's action listener
		        }
		    }
		});
		
		// Login button listener
		btnLoginEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					boolean isUser = false;
					try {
						try {
							isUser = userDAO.checkLogin(textFieldErabiltzailea.getText(), passwordField.getText());
						} catch (LostDbConnection ldbc) {
							isUser = userDAO.checkLogin(textFieldErabiltzailea.getText(), passwordField.getText());
						}
						if (isUser) {
		
							dispose();
                            WorkoutsView workouts = new WorkoutsView();
                            workouts.setVisible(true);
						} else {
							JOptionPane.showMessageDialog(null, "Erabiltzailea edo pasahitza txarto dago!", "Login errorea", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

			}
		});
		
		// Register button listener
		btnErregistroa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	if(GlobalVariables.isConnexion) {
            		dispose();
                	ErregistroaView erregistroa = new ErregistroaView();
                	erregistroa.setVisible(true);
            	}else {
            		JOptionPane.showMessageDialog(null, "Ezin zara erristratu konexio gabe!", "Errorea", JOptionPane.ERROR_MESSAGE);
            	}
            	
            }
		});
	}
}
