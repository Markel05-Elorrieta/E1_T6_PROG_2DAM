package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.BcryptMethods;
import model.dao.UserDAO;
import resources.GlobalVariables;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ChangePasswordView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JPasswordField passwdOraingoa;
	private JPasswordField passwdBerria;
	private JPasswordField passwdBerriaErrepikatu;
	private UserDAO userDAO = new UserDAO();
	private BcryptMethods bc = new BcryptMethods();

	/**
	 * Create the frame.
	 */
	public ChangePasswordView() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(250, 250, 452, 277);
		setResizable(false);
		setTitle("Pasahitza aldatu - JEM Fit · Erabiltzailea: " + GlobalVariables.loggedUser.getUsername());
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblOraingoPasahitza = new JLabel("Oraingo pasahitza:");
		lblOraingoPasahitza.setBounds(43, 54, 108, 14);
		contentPane.add(lblOraingoPasahitza);
		
		passwdOraingoa = new JPasswordField();
		passwdOraingoa.setBounds(217, 54, 175, 20);
		contentPane.add(passwdOraingoa);
		
		JLabel lblNewLabel_1 = new JLabel("PASAHITZA ALDAKETA");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(0, 11, 434, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblPasahitzaBerria = new JLabel("Pasahitza berria:");
		lblPasahitzaBerria.setBounds(43, 97, 108, 14);
		contentPane.add(lblPasahitzaBerria);
		
		passwdBerria = new JPasswordField();
		passwdBerria.setBounds(217, 97, 175, 20);
		contentPane.add(passwdBerria);
		
		JLabel lblPasahitzaBerriaErrepikatu = new JLabel("Pasahitza berria errepikatu:");
		lblPasahitzaBerriaErrepikatu.setBounds(43, 140, 175, 14);
		contentPane.add(lblPasahitzaBerriaErrepikatu);
		
		passwdBerriaErrepikatu = new JPasswordField();
		passwdBerriaErrepikatu.setBounds(217, 140, 175, 20);
		contentPane.add(passwdBerriaErrepikatu);
		
		JButton btnGorde = new JButton("Gorde");
		btnGorde.setBounds(222, 201, 89, 23);
		contentPane.add(btnGorde);
		
		JButton btnAtzera = new JButton("Atzera");
		btnAtzera.setBounds(120, 201, 89, 23);
		contentPane.add(btnAtzera);
		
		// LISTENERS
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		
		btnGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String oldPasswd = passwdOraingoa.getText();
				String newPasswd = passwdBerria.getText();
				String confirmNewPasswd = passwdBerriaErrepikatu.getText();
				
				if (oldPasswd.isEmpty() || newPasswd.isEmpty() || confirmNewPasswd.isEmpty()) {
					JOptionPane.showMessageDialog(null, "Eremu guztiak bete behar dira.", "Errorea",
							JOptionPane.ERROR_MESSAGE);
				} else if (!bc.checkPassword(oldPasswd, GlobalVariables.loggedUser.getPassword())) {
					JOptionPane.showMessageDialog(null, "Oraingo pasahitza okerra da.", "Errorea",
							JOptionPane.ERROR_MESSAGE);
				} else if (newPasswd.equals(confirmNewPasswd)) {
					try {
						userDAO.changePassword(newPasswd);
						dispose();
						JOptionPane.showMessageDialog(null, "Pasahitza ondo aldatu da.", "Informazioa",
								JOptionPane.INFORMATION_MESSAGE);
					} catch (Exception e1) {
						JOptionPane.showMessageDialog(null, "Errore bat gertatu da pasahitza aldatzean.", "Errorea",
								JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
