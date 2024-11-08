package view;

import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import resources.GlobalVariables;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import com.toedter.calendar.JDateChooser;

import model.dao.UserDAO;
import model.exceptions.DateException;
import model.exceptions.EmailException;
import model.exceptions.EmptyFieldException;
import model.exceptions.LostDbConnection;
import model.exceptions.PasswordsNotMatchException;
import model.exceptions.PhoneNumException;
import model.metodoak.GlobalButtons;
import model.metodoak.Images;
import model.metodoak.ValidateData;
import model.objects.User;

import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.SwingConstants;

public class ProfileView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txt_name;
	private JTextField txt_subname;
	private JTextField txt_tlf;
	private JTextField txt_mail;
	private GlobalButtons globalButtons = new GlobalButtons();

	private ValidateData methods = new ValidateData();
	private UserDAO userDAO = new UserDAO();
	private Images images = new Images();
	private String newPhoto = null;

	/**
	 * Create the frame.
	 */
	public ProfileView() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH,
				GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		setTitle("Nire profila - JEM Fit · Erabiltzailea: " + GlobalVariables.loggedUser.getUsername());
		Image resizedIconImage = new ImageIcon(getClass().getResource("/resources/images/logo.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		setIconImage(resizedIconImage);

		BackgroundImageView panel = new BackgroundImageView();
		setContentPane(panel);
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JButton btnSave = new JButton("☑️ Gorde aldaketak");
		btnSave.setHorizontalAlignment(SwingConstants.RIGHT);
		btnSave.setForeground(Color.WHITE);
		btnSave.setBackground(Color.ORANGE);
		btnSave.setFocusPainted(false);
		btnSave.setFont(new Font("Segoe UI Emoji", Font.BOLD, 18));
		btnSave.setBounds(355, 485, 240, 35);
		panel.add(btnSave);

		JLabel lblIzena = new JLabel("Izena");
		lblIzena.setForeground(Color.WHITE);
		lblIzena.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblIzena.setBounds(148, 119, 132, 22);
		panel.add(lblIzena);

		txt_name = new JTextField();
		txt_name.setHorizontalAlignment(SwingConstants.CENTER);
		txt_name.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_name.setColumns(10);
		txt_name.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_name.setBounds(148, 152, 299, 29);
		txt_name.setText(GlobalVariables.loggedUser.getName());
		panel.add(txt_name);

		JLabel lblAbizenak = new JLabel("Abizenak");
		lblAbizenak.setForeground(Color.WHITE);
		lblAbizenak.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblAbizenak.setBounds(148, 203, 132, 22);
		panel.add(lblAbizenak);

		txt_subname = new JTextField();
		txt_subname.setHorizontalAlignment(SwingConstants.CENTER);
		txt_subname.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_subname.setColumns(10);
		txt_subname.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_subname.setBounds(148, 236, 299, 29);
		txt_subname.setText(GlobalVariables.loggedUser.getSubname());
		panel.add(txt_subname);

		JLabel lblPasahitza = new JLabel("Pasahitza");
		lblPasahitza.setForeground(Color.WHITE);
		lblPasahitza.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblPasahitza.setBounds(148, 320, 132, 22);
		panel.add(lblPasahitza);

		Calendar calendar = Calendar.getInstance();
		calendar.setTime(GlobalVariables.loggedUser.getBirthdate());
		Date data = calendar.getTime();
		JDateChooser dateChooser = new JDateChooser(data);
		dateChooser.setFont(new Font("Tahoma", Font.PLAIN, 14));
		dateChooser.setLocale(new Locale("es", "ES"));
		dateChooser.setDateFormatString("yyyy-MM-dd");
		calendar.setTime(GlobalVariables.loggedUser.getBirthdate());
		dateChooser.setBounds(522, 236, 299, 29);
		panel.add(dateChooser);

		JLabel lblJaiotzedata = new JLabel("Jaiotze-data");
		lblJaiotzedata.setForeground(Color.WHITE);
		lblJaiotzedata.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		lblJaiotzedata.setBounds(522, 203, 132, 22);
		panel.add(lblJaiotzedata);

		txt_tlf = new JTextField();
		txt_tlf.setHorizontalAlignment(SwingConstants.CENTER);
		txt_tlf.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_tlf.setColumns(10);
		txt_tlf.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_tlf.setBounds(522, 152, 299, 29);
		String tlf = Integer.toString(GlobalVariables.loggedUser.getPhone());
		txt_tlf.setText(tlf);
		panel.add(txt_tlf);

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

		txt_mail = new JTextField();
		txt_mail.setHorizontalAlignment(SwingConstants.CENTER);
		txt_mail.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txt_mail.setColumns(10);
		txt_mail.setBorder(new LineBorder(new Color(0, 0, 0)));
		txt_mail.setBounds(522, 323, 299, 29);
		txt_mail.setText(GlobalVariables.loggedUser.getEmail());
		panel.add(txt_mail);

		JButton btnAtzera = globalButtons.btnAtzera("NireProfilaView");
		panel.add(btnAtzera);

		JButton btnAldatuPasswd = new JButton("Aldatu pasahitza");
		btnAldatuPasswd.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAldatuPasswd.setFocusPainted(false);
		btnAldatuPasswd.setBounds(290, 317, 156, 35);
		panel.add(btnAldatuPasswd);

		JLabel lblLogin_Header = new JLabel("Kaixo, " + GlobalVariables.loggedUser.getUsername() + "!");
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
		profilePhoto.setBorder(new LineBorder(new Color(0, 0, 0)));
		profilePhoto.setHorizontalAlignment(SwingConstants.CENTER);
		profilePhoto.setForeground(Color.WHITE);
		profilePhoto.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD, 21));
		profilePhoto.setBounds(689, 375, 132, 121);

		int widthLabel = profilePhoto.getWidth();
		int heightLabel = profilePhoto.getHeight();
		Image originalImage = GlobalVariables.loggedUser.getpPhotoIC().getImage();
		Image resizedImage = originalImage.getScaledInstance(widthLabel, heightLabel, java.awt.Image.SCALE_SMOOTH);
		profilePhoto.setIcon(new ImageIcon(resizedImage));
		panel.add(profilePhoto);

		JButton btnAldatuArgazkia = new JButton("Aldatu argazkia");
		btnAldatuArgazkia.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnAldatuArgazkia.setBounds(522, 413, 148, 35);
		btnAldatuArgazkia.setFocusPainted(false);
		panel.add(btnAldatuArgazkia);
		
		JLabel lblSubtitle = new JLabel("Hemen zure profilaren datuak daukazu. Aldatu nahi duzun datuak eta, gorderi eman gordetzeko aldaketak.");
		lblSubtitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblSubtitle.setForeground(Color.BLACK);
		lblSubtitle.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblSubtitle.setBounds(0, 57, 984, 22);
		panel.add(lblSubtitle);
		
		JButton btnWorkoutHistoriala = new JButton("Workout historiala");
		btnWorkoutHistoriala.setForeground(Color.WHITE);
		btnWorkoutHistoriala.setBackground(Color.RED);
		btnWorkoutHistoriala.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnWorkoutHistoriala.setFocusPainted(false);
		btnWorkoutHistoriala.setBounds(183, 413, 222, 35);
		panel.add(btnWorkoutHistoriala);

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
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Argazkiak", "png", "jpg");
				fileChooser.setFileFilter(filter);
				int returnValue = fileChooser.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = fileChooser.getSelectedFile();
					if (selectedFile.getName().endsWith("png") || selectedFile.getName().endsWith("jpg")) {
						newPhoto = images.encode(selectedFile);

						// Update img
						Image originalImage = images.decode(newPhoto).getImage();
						Image resizedImage = originalImage.getScaledInstance(widthLabel, heightLabel, java.awt.Image.SCALE_SMOOTH);
						profilePhoto.setIcon(new ImageIcon(resizedImage));
					} else {
						JOptionPane.showMessageDialog(null, "JPG edo PNG fitxategi bat igo!", "Error",
								JOptionPane.INFORMATION_MESSAGE);
					}
				}
			}
		});

		// ALDATU PASAHITZA BUTTON
		btnAldatuPasswd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ChangePasswordView changePasswordView = new ChangePasswordView();
				changePasswordView.setVisible(true);
			}
		});
		
		// SAVE BUTTON
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String name = txt_name.getText();
				String subname = txt_subname.getText();
				Date birthdate = dateChooser.getDate();
				String email = txt_mail.getText();
				String phoneStr = txt_tlf.getText();

				try {
					methods.checkDate(birthdate);
					int phoneNum = methods.checkPhoneNumber(phoneStr);

					methods.checkEmail(email);

					User updateUser = new User(GlobalVariables.loggedUser.getUsername(), name, subname,
							GlobalVariables.loggedUser.getPassword(), birthdate, email, phoneNum);
					if (newPhoto != null) {
						updateUser.setpPhoto(newPhoto);
					} else {
						updateUser.setpPhoto(GlobalVariables.loggedUser.getpPhoto());
					}
					methods.checkEmptyFields(updateUser);
					try {
						userDAO.updateUser(updateUser);
					}catch (LostDbConnection ldbc) {
						userDAO.updateUser(updateUser);
					}
					JOptionPane.showMessageDialog(null, "Erabiltzailea ondo modifikatu da!", "Erabiltzailea",
							JOptionPane.INFORMATION_MESSAGE);
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
					JOptionPane.showMessageDialog(null, "Errorea egon da aldatzean!", "Errorea",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		
		// WORKOUT HISTORIALA BUTTON
		btnWorkoutHistoriala.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				WorkoutHistorialaView workoutHistoriala = new WorkoutHistorialaView();
				workoutHistoriala.setVisible(true);
				dispose();
			}
		});
	}
}
