package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import com.toedter.calendar.JDateChooser;

import model.BcryptMethods;
import model.User;
import model.dao.UserDAO;
import model.exceptions.DateException;
import model.exceptions.EmailException;
import model.exceptions.EmptyFieldException;
import model.exceptions.LostDbConnection;
import model.exceptions.PasswordsNotMatchException;
import model.exceptions.PhoneNumException;
import model.metodoak.GlobalButtons;
import model.metodoak.ValidateData;
import resources.GlobalVariables;

public class ErregistroaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txt_username;
	private JPasswordField txt_password;
	private JTextField txt_subname;
	private JTextField txt_name;
	private JTextField txt_tlf;
	private JPasswordField txt_repeatPassword;
	private JTextField txt_mail;
	
	private UserDAO userDAO = new UserDAO();
	private BcryptMethods bCrypt = new BcryptMethods();
	private ValidateData methods = new ValidateData();
	private JButton btnAtzera;

	/**
	 * Create the frame.
	 */
	public ErregistroaView() {
		setTitle("Erregistroa - JEM Fit");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
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
		
		txt_username = new JTextField();
		txt_username.setColumns(10);
		txt_username.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_username.setBounds(135, 320, 299, 29);
		contentPane.add(txt_username);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblPasahitza.setBounds(135, 372, 132, 22);
		contentPane.add(lblPasahitza);
		
		txt_password = new JPasswordField();
		txt_password.setBounds(135, 405, 299, 29);
		txt_password.setBorder(new LineBorder(new Color(0, 0, 0)));
		contentPane.add(txt_password);
		
		JLabel lblAbizenak = new JLabel("Abizenak");
		lblAbizenak.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblAbizenak.setBounds(135, 200, 132, 22);
		contentPane.add(lblAbizenak);
		
		txt_subname = new JTextField();
		txt_subname.setColumns(10);
		txt_subname.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_subname.setBounds(135, 233, 299, 29);
		contentPane.add(txt_subname);
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblIzena.setBounds(135, 116, 132, 22);
		contentPane.add(lblIzena);
		
		txt_name = new JTextField();
		txt_name.setColumns(10);
		txt_name.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_name.setBounds(135, 149, 299, 29);
		contentPane.add(txt_name);
		
		JLabel lblTelefonoa = new JLabel("Telefonoa");
		lblTelefonoa.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblTelefonoa.setBounds(509, 116, 132, 22);
		contentPane.add(lblTelefonoa);
		
		txt_tlf = new JTextField();
		txt_tlf.setColumns(10);
		txt_tlf.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_tlf.setBounds(509, 149, 299, 29);
		contentPane.add(txt_tlf);
		
		JLabel lblJaiotzedata = new JLabel("Jaiotze-data");
		lblJaiotzedata.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblJaiotzedata.setBounds(509, 200, 132, 22);
		contentPane.add(lblJaiotzedata);
		
		JLabel lblPasahitzaKonfirmatu = new JLabel("Pasahitza konfirmatu");
		lblPasahitzaKonfirmatu.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblPasahitzaKonfirmatu.setBounds(509, 372, 299, 22);
		contentPane.add(lblPasahitzaKonfirmatu);
		
		txt_repeatPassword = new JPasswordField();
		txt_repeatPassword.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_repeatPassword.setBounds(509, 405, 299, 29);
		contentPane.add(txt_repeatPassword);
		
		JLabel lblMessHello = new JLabel("Ongi etorri JEM Fit aplikaziora. Berria bazara, hemen erregistratu dezakezu!");
		lblMessHello.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblMessHello.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessHello.setBounds(0, 56, 984, 22);
		contentPane.add(lblMessHello);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 21));
		lblEmail.setBounds(509, 287, 132, 22);
		contentPane.add(lblEmail);
		
		txt_mail = new JTextField();
		txt_mail.setColumns(10);
		txt_mail.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_mail.setBounds(509, 320, 299, 29);
		contentPane.add(txt_mail);
		
		Calendar calendar = Calendar.getInstance();
		calendar.set(2020, 0, 1);
		Date maxdata = calendar.getTime();
		JDateChooser dateChooser = new JDateChooser(maxdata);
		dateChooser.setBounds(509, 233, 299, 29);
		dateChooser.setLocale(new Locale("es", "ES"));
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setMaxSelectableDate(maxdata);
		calendar.set(1900, 0, 1);
		Date mindata = calendar.getTime();
		dateChooser.getJCalendar().setMinSelectableDate(mindata);
		contentPane.add(dateChooser);
		
		JButton btnErregistratu = new JButton("☑️ Erregistratu");
		btnErregistratu.setHorizontalAlignment(SwingConstants.RIGHT);
		btnErregistratu.setVerticalAlignment(SwingConstants.BOTTOM);
		btnErregistratu.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnErregistratu.setFocusPainted(false);
		btnErregistratu.setBounds(711, 476, 209, 35);
		contentPane.add(btnErregistratu);
		
		btnAtzera = GlobalButtons.btnAtzera("LoginView");
		contentPane.add(btnAtzera);
		
		// LISTENERS
		
		// Erregistratu botoia
		btnErregistratu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txt_name.getText();
				String subname = txt_subname.getText();
				String username = txt_username.getText();
				String password = txt_password.getText();
				Date birthdate = dateChooser.getDate();
				String repeatPassword = txt_repeatPassword.getText();
				String email = txt_mail.getText();
				String phoneStr = txt_tlf.getText();
			
				try {
					methods.checkDate(birthdate);
					int phoneNum = methods.checkPhoneNumber(phoneStr);
					methods.checkPasswords(password, repeatPassword);
					methods.checkEmail(email);
					String hashedPwd = bCrypt.hashPassword(password);
					User newUser = new User(username, name, subname, hashedPwd, birthdate, email, phoneNum);
					methods.checkEmptyFields(newUser);
					try {
						userDAO.registerUser(newUser);
					} catch (LostDbConnection lbdc) {
						userDAO.registerUser(newUser);
					}
					JOptionPane.showMessageDialog(null, "REGISTRAU!", "Erregistratuta", JOptionPane.INFORMATION_MESSAGE);
				} catch (PasswordsNotMatchException pnme) {
					pnme.getMessage();
				} catch (PhoneNumException pne) {
					pne.getMessage();
				} catch (DateException de) {
					de.getMessage();
				} catch (EmptyFieldException efe) {
					efe.getMessage();
				} catch (EmailException ee) {
					ee.getMessage();
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, "Errorea egon da sortzean!", "Errorea", JOptionPane.ERROR_MESSAGE);
				}	
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
