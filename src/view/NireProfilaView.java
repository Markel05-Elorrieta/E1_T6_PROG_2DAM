package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resources.GlobalVariables;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.JPasswordField;
import com.toedter.calendar.JDateChooser;

import model.metodoak.GlobalButtons;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.SwingConstants;

public class NireProfilaView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtIzena;
	private JTextField txtAbizena;
	private JTextField txtErabiltzaile;
	private JTextField txtTelefono;
	private JTextField txtEmail;
	private GlobalButtons globalButtons = new GlobalButtons();

	/**
	 * Create the frame.
	 */
	public NireProfilaView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		setTitle("Nire profila - JEM Fit · Erabilzailea: " + GlobalVariables.loggedUser.getUsername());
		
		BackgroundImageView panel = new BackgroundImageView();
		setContentPane(panel);
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setForeground(Color.WHITE);
		lblIzena.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblIzena.setBounds(148, 119, 132, 22);
		panel.add(lblIzena);
		
		txtIzena = new JTextField();
		txtIzena.setColumns(10);
		txtIzena.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtIzena.setBounds(148, 152, 299, 29);
		txtIzena.setText(GlobalVariables.loggedUser.getName());
		panel.add(txtIzena);
		
		JLabel lblAbizenak = new JLabel("Abizenak");
		lblAbizenak.setForeground(Color.WHITE);
		lblAbizenak.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblAbizenak.setBounds(148, 203, 132, 22);
		panel.add(lblAbizenak);
		
		txtAbizena = new JTextField();
		txtAbizena.setColumns(10);
		txtAbizena.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtAbizena.setBounds(148, 236, 299, 29);
		txtAbizena.setText(GlobalVariables.loggedUser.getSubname());
		panel.add(txtAbizena);
		
		JLabel lblErabiltzaile = new JLabel("Erabiltzailea");
		lblErabiltzaile.setForeground(Color.WHITE);
		lblErabiltzaile.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblErabiltzaile.setBounds(148, 290, 132, 22);
		panel.add(lblErabiltzaile);
		
		txtErabiltzaile = new JTextField();
		txtErabiltzaile.setColumns(10);
		txtErabiltzaile.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtErabiltzaile.setBounds(148, 323, 299, 29);
		txtErabiltzaile.setText(GlobalVariables.loggedUser.getUsername());
		panel.add(txtErabiltzaile);
		
		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setForeground(Color.WHITE);
		lblPasahitza.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblPasahitza.setBounds(148, 375, 132, 22);
		panel.add(lblPasahitza);
		
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(GlobalVariables.loggedUser.getBirthdate());
		Date data = calendar.getTime();
		JDateChooser dateChooser = new JDateChooser(data);
		dateChooser.setLocale(new Locale("es", "ES"));
		dateChooser.setDateFormatString("yyyy-MM-dd");
		dateChooser.setBounds(522, 236, 299, 29);
		panel.add(dateChooser);
		
		JLabel lblJaiotzedata = new JLabel("Jaiotze-data");
		lblJaiotzedata.setForeground(Color.WHITE);
		lblJaiotzedata.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblJaiotzedata.setBounds(522, 203, 132, 22);
		panel.add(lblJaiotzedata);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtTelefono.setBounds(522, 152, 299, 29);
		String tlf = Integer.toString(GlobalVariables.loggedUser.getPhone());
		txtTelefono.setText(tlf);
		panel.add(txtTelefono);
		
		JLabel lblTelefonoa = new JLabel("Telefonoa");
		lblTelefonoa.setForeground(Color.WHITE);
		lblTelefonoa.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblTelefonoa.setBounds(522, 119, 132, 22);
		panel.add(lblTelefonoa);
		
		JLabel lblEmail = new JLabel("E-mail");
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblEmail.setBounds(522, 290, 132, 22);
		panel.add(lblEmail);
		
		txtEmail = new JTextField();
		txtEmail.setColumns(10);
		txtEmail.setBorder(new LineBorder(new Color(0, 0, 0)));
		txtEmail.setBounds(522, 323, 299, 29);
		txtEmail.setText(GlobalVariables.loggedUser.getEmail());
		panel.add(txtEmail);
		
		JButton btnAtzera = globalButtons.btnAtzera("NireProfilaView");
		panel.add(btnAtzera);
		
		JButton btnAldatuPasswd = new JButton("Aldatu pasahitza");
		btnAldatuPasswd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAldatuPasswd.setFocusPainted(false);
		btnAldatuPasswd.setBounds(290, 372, 156, 35);
		panel.add(btnAldatuPasswd);
		
		JLabel lblLogin_Header = new JLabel("NIRE PROFILA -> " + GlobalVariables.loggedUser.getUsername());
		lblLogin_Header.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_Header.setForeground(Color.WHITE);
		lblLogin_Header.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblLogin_Header.setBounds(0, 11, 984, 35);
		panel.add(lblLogin_Header);
		
		JLabel lblArgazkia = new JLabel("Argazkia");
		lblArgazkia.setForeground(Color.WHITE);
		lblArgazkia.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblArgazkia.setBounds(522, 370, 132, 32);
		panel.add(lblArgazkia);
		
		JLabel profilePhoto = new JLabel("");
		profilePhoto.setHorizontalAlignment(SwingConstants.CENTER);
		profilePhoto.setForeground(Color.WHITE);
		profilePhoto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		profilePhoto.setBounds(689, 375, 132, 121);
		
		Image originalImage = GlobalVariables.loggedUser.getpPhotoIC().getImage();
		Image resizedImage = originalImage.getScaledInstance(60, 60, java.awt.Image.SCALE_SMOOTH);
		
		profilePhoto.setIcon(new ImageIcon(resizedImage));
		panel.add(profilePhoto);
		
		JButton btnAldatuArgazkia = new JButton("Aldatu argazkia");
		btnAldatuArgazkia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAldatuArgazkia.setBounds(522, 413, 148, 35);
		btnAldatuArgazkia.setFocusPainted(false);
		panel.add(btnAldatuArgazkia);

		// LISTENERS
		
		// ATZERA BUTTON
		
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkoutsView workoutsView = new WorkoutsView();
				workoutsView.setVisible(true);
				dispose();
			}
		});
		
		// ALDATU ARGAZKIA BUTTON
		btnAldatuArgazkia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		
		// ALDATU PASAHITZA BUTTON
		btnAldatuPasswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePasswordView changePasswordView = new ChangePasswordView();
				changePasswordView.setVisible(true);
				dispose();
			}
		});
	}
}
