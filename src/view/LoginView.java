package view;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import resources.GlobalVariables;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textFieldErabiltzailea;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		// Set the look and feel to the system's look and feel
		try {
			javax.swing.UIManager.setLookAndFeel(javax.swing.UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Run the application
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginView frame = new LoginView();
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
	public LoginView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(280, 150, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
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
		contentPane.add(textFieldErabiltzailea);
		textFieldErabiltzailea.setColumns(10);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblPasahitza.setBounds(341, 229, 132, 22);
		contentPane.add(lblPasahitza);
		
		JButton btnErregistroa = new JButton("Erregistratu");
		btnErregistroa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnErregistroa.setBounds(125, 463, 157, 35);
		contentPane.add(btnErregistroa);
		
		JButton btnLoginEgin = new JButton("Login egin");
		btnLoginEgin.setFont(new Font("Tahoma", Font.PLAIN, 20));
		btnLoginEgin.setBounds(716, 463, 157, 35);
		contentPane.add(btnLoginEgin);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(341, 262, 299, 29);
		contentPane.add(passwordField);
		
		// LISTENERS
		
		// Login button listener
		btnLoginEgin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// Register button listener
		btnErregistroa.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	
            }
		});
	}
}
