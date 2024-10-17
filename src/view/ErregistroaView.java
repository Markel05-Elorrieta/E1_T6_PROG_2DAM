package view;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import com.toedter.calendar.JDateChooser;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import resources.GlobalVariables;

public class ErregistroaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JPasswordField passwordField_1;
	private JTextField textField_4;

	/**
	 * Create the frame.
	 */
	public ErregistroaView() {
		setTitle("Erregistroa - JEM Fit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblLogin_Header = new JLabel("ERREGISTROA");
		lblLogin_Header.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_Header.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblLogin_Header.setBounds(0, 11, 984, 35);
		contentPane.add(lblLogin_Header);
		
		JLabel lblErabiltzaile = new JLabel("Erabiltzailea");
		lblErabiltzaile.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblErabiltzaile.setBounds(135, 287, 132, 22);
		contentPane.add(lblErabiltzaile);
		
		textField = new JTextField();
		textField.setColumns(10);
		textField.setBounds(135, 320, 299, 29);
		contentPane.add(textField);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblPasahitza.setBounds(135, 372, 132, 22);
		contentPane.add(lblPasahitza);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(135, 405, 299, 29);
		contentPane.add(passwordField);
		
		JLabel lblAbizenak = new JLabel("Abizenak");
		lblAbizenak.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblAbizenak.setBounds(135, 200, 132, 22);
		contentPane.add(lblAbizenak);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(135, 233, 299, 29);
		contentPane.add(textField_1);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblIzena.setBounds(135, 116, 132, 22);
		contentPane.add(lblIzena);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(135, 149, 299, 29);
		contentPane.add(textField_2);
		
		JLabel lblTelefonoa = new JLabel("Telefonoa");
		lblTelefonoa.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblTelefonoa.setBounds(509, 116, 132, 22);
		contentPane.add(lblTelefonoa);
		
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(509, 149, 299, 29);
		contentPane.add(textField_3);
		
		JLabel lblJaiotzedata = new JLabel("Jaiotze-data");
		lblJaiotzedata.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblJaiotzedata.setBounds(509, 200, 132, 22);
		contentPane.add(lblJaiotzedata);
		
		JLabel lblPasahitzaKonfirmatu = new JLabel("Pasahitza konfirmatu");
		lblPasahitzaKonfirmatu.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblPasahitzaKonfirmatu.setBounds(509, 372, 299, 22);
		contentPane.add(lblPasahitzaKonfirmatu);
		
		passwordField_1 = new JPasswordField();
		passwordField_1.setBounds(509, 405, 299, 29);
		contentPane.add(passwordField_1);
		
		JLabel lblMessHello = new JLabel("Ongi etorri JEM Fit aplikaziora. Berria bazara, hemen erregistratu dezakezu!");
		lblMessHello.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMessHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessHello.setBounds(0, 56, 984, 22);
		contentPane.add(lblMessHello);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblEmail.setBounds(509, 287, 132, 22);
		contentPane.add(lblEmail);
		
		textField_4 = new JTextField();
		textField_4.setColumns(10);
		textField_4.setBounds(509, 320, 299, 29);
		contentPane.add(textField_4);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2010, 0, 1);
		Date maxdata = calendar.getTime();
		JDateChooser dateChooser = new JDateChooser(maxdata);
		dateChooser.setBounds(509, 233, 299, 29);
		dateChooser.setLocale(Locale.forLanguageTag("es"));
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setMaxSelectableDate(maxdata);
		calendar.set(1900, 0, 1);
		Date mindata = calendar.getTime();
		dateChooser.getJCalendar().setMinSelectableDate(mindata);
		dateChooser.getDateEditor().getUiComponent().setFocusable(false);
		contentPane.add(dateChooser);
		
		JButton btnErregistratu = new JButton("☑️ Erregistratu");
		btnErregistratu.setHorizontalAlignment(SwingConstants.RIGHT);
		btnErregistratu.setVerticalAlignment(SwingConstants.BOTTOM);
		btnErregistratu.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnErregistratu.setFocusPainted(false);
		btnErregistratu.setBounds(711, 476, 209, 35);
		contentPane.add(btnErregistratu);
		
		JButton btnAtzera = new JButton("⬅️ Atzera");
		btnAtzera.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAtzera.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAtzera.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnAtzera.setFocusPainted(false);
		btnAtzera.setBounds(81, 476, 157, 35);
		contentPane.add(btnAtzera);
		
		// LISTENERS
		
		// Erregistratu botoia
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// Atzera botoia
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				LoginView login = new LoginView();
				login.setVisible(true);
				dispose();
			}
		});
	}
}
