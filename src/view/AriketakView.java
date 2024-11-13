package view;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import model.cronometers.KronometroAriketak;
import model.cronometers.KronometroKontaketa;
import model.cronometers.KronometroNagusia;
import model.cronometers.KronometroSerie;
import model.dao.AriketakDAO;
import model.objects.Ariketa;
import model.objects.Workout;
import resources.GlobalVariables;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.SystemColor;
import javax.swing.border.LineBorder;

public class AriketakView extends JFrame {

	public static final long serialVersionUID = 1L;
	public AriketakDAO ariketakDAO = new AriketakDAO();
	public ArrayList<Ariketa> ariketaList = new ArrayList<Ariketa>();
	public Ariketa ariketaActual;
	public int posAriketa = 0;
	public int posSerie = 0;
	public JLabel lblAriketaHeader = new JLabel();
	public JLabel lblDeskrAriketa = new JLabel();
	public JLabel lblLandutakoMuskulua = new JLabel();
	JLabel lblKronometroNagusia = new JLabel();
	public JLabel lblKronometroAriketa = new JLabel();
	public JLabel lblSerieName = new JLabel();
	public JLabel lblSerieRepes = new JLabel();
	public JButton btnHurrengoSerie = new JButton("Hurrengo serie");

	public KronometroNagusia kronometroNagusia;

	/* ----------------- */

	/**
	 * Create the frame.
	 */
	public AriketakView(Workout selectedWorkout, KronometroNagusia paramKrono) {
		try {
			ariketaList = ariketakDAO.getAriketakByWorkoutId(selectedWorkout);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ezin izan dira ariketak kargatu. Barkatu eragozpenak.");
		}

		AriketakView thisClass = this;
		kronometroNagusia = paramKrono;

		setTitle(
				selectedWorkout.getIzena() + " - JEM Fit · Erabiltzailea: " + GlobalVariables.loggedUser.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH,
				GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		Image resizedIconImage = new ImageIcon(getClass().getResource("/resources/images/logo.png")).getImage()
				.getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		setIconImage(resizedIconImage);

		BackgroundImageView panel = new BackgroundImageView();
		setContentPane(panel);
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));

		JLabel lblKronoKontaketa = new JLabel("");
		lblKronoKontaketa.setForeground(Color.WHITE);
		lblKronoKontaketa.setFont(new Font("Tahoma", Font.PLAIN, 90));
		lblKronoKontaketa.setHorizontalAlignment(SwingConstants.CENTER);
		lblKronoKontaketa.setBounds(263, 173, 469, 100);
		lblKronoKontaketa.setVisible(false);
		panel.add(lblKronoKontaketa);

		JButton btnAmaitu = new JButton("⏹️ Amaitu");
		btnAmaitu.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAmaitu.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAmaitu.setForeground(Color.WHITE);
		btnAmaitu.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		btnAmaitu.setFocusPainted(false);
		btnAmaitu.setBackground(Color.RED);
		btnAmaitu.setBounds(308, 480, 170, 35);
		panel.add(btnAmaitu);

		lblKronometroNagusia.setHorizontalAlignment(SwingConstants.CENTER);
		lblKronometroNagusia.setBorder(new LineBorder(Color.WHITE, 2));
		lblKronometroNagusia.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKronometroNagusia.setBounds(65, 73, 128, 29);
		lblKronometroNagusia.setOpaque(true);
		lblKronometroNagusia.setBackground(Color.WHITE);
		lblKronometroNagusia.setVisible(false);
		panel.add(lblKronometroNagusia);

		JButton btnNireProfila = new JButton("");
		btnNireProfila.setToolTipText("Kaixo, " + GlobalVariables.loggedUser.getName() + "!");
		btnNireProfila.setFont(new Font("Tahoma", Font.PLAIN, 6));
		btnNireProfila.setFocusPainted(false);
		btnNireProfila.setBorder(new EmptyBorder(0, 0, 0, 0));
		btnNireProfila.setBackground(Color.WHITE);
		btnNireProfila.setBounds(896, 11, 47, 35);
		Image originalImage = GlobalVariables.loggedUser.getpPhotoIC().getImage();
		int buttonWidth = btnNireProfila.getWidth();
		int buttonHeight = btnNireProfila.getHeight();
		Image resizedImage = originalImage.getScaledInstance(buttonWidth, buttonHeight, java.awt.Image.SCALE_SMOOTH);
		btnNireProfila.setIcon(new ImageIcon(resizedImage));
		panel.add(btnNireProfila);

		JButton btnLogout = new JButton("");
		btnLogout.setToolTipText("Logout...");
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 6));
		btnLogout.setFocusPainted(false);
		btnLogout.setBackground(Color.RED);
		ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/resources/images/logout-negro.png"));
		Image originalImage1 = originalIcon1.getImage();
		Image resizedImage1 = originalImage1.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnLogout.setIcon(new ImageIcon(resizedImage1));
		btnLogout.setFocusPainted(false);
		btnLogout.setBounds(942, 11, 33, 35);
		panel.add(btnLogout);

		lblAriketaHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblAriketaHeader.setForeground(Color.WHITE);
		lblAriketaHeader.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblAriketaHeader.setBounds(0, 11, 984, 35);
		panel.add(lblAriketaHeader);

		lblDeskrAriketa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeskrAriketa.setForeground(Color.BLACK);
		lblDeskrAriketa.setText("Ez dago deskribapenik.");
		lblDeskrAriketa.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDeskrAriketa.setBounds(0, 51, 984, 29);
		panel.add(lblDeskrAriketa);

		if (!ariketaList.isEmpty()) {
			ariketaActual = ariketaList.get(posAriketa);
			lblAriketaHeader.setText("Ariketa: " + ariketaActual.getIzena());
			lblDeskrAriketa.setText("Deskribapena: " + ariketaActual.getDeskribapena());
			lblLandutakoMuskulua.setText("Landutako muskulua: " + ariketaActual.getLandutako_muskulua());
			lblSerieName.setText("Seriea: " + ariketaActual.getSeries().get(posSerie).getIzena());
			lblSerieRepes.setText(
					"Repetizioak: " + String.valueOf(ariketaActual.getSeries().get(posSerie).getRepetizioak()));
		}

		JLabel lblWorkouta = new JLabel("Workout-a: " + selectedWorkout.getIzena());
		lblWorkouta.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkouta.setForeground(Color.BLACK);
		lblWorkouta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblWorkouta.setBounds(0, 80, 984, 22);
		panel.add(lblWorkouta);

		JButton btnStart = new JButton("▶️ Hasi");
		btnStart.setVerticalAlignment(SwingConstants.BOTTOM);
		btnStart.setForeground(Color.WHITE);
		btnStart.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		btnStart.setFocusPainted(false);
		btnStart.setBackground(new Color(46, 139, 87));
		btnStart.setBounds(507, 480, 170, 35);
		panel.add(btnStart);

		JButton btnPause = new JButton("⏸️ Pause");
		btnPause.setVerticalAlignment(SwingConstants.BOTTOM);
		btnPause.setForeground(Color.WHITE);
		btnPause.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		btnPause.setFocusPainted(false);
		btnPause.setBackground(new Color(46, 139, 87));
		btnPause.setBounds(507, 480, 170, 35);
		btnPause.setVisible(false);
		panel.add(btnPause);

		lblLandutakoMuskulua.setHorizontalAlignment(SwingConstants.CENTER);
		lblLandutakoMuskulua.setForeground(Color.BLACK);
		lblLandutakoMuskulua.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblLandutakoMuskulua.setBounds(0, 111, 984, 22);
		panel.add(lblLandutakoMuskulua);
		lblKronometroAriketa.setHorizontalAlignment(SwingConstants.CENTER);

		lblKronometroAriketa.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblKronometroAriketa.setOpaque(true);
		lblKronometroAriketa.setVisible(false);
		lblKronometroAriketa.setBackground(Color.WHITE);
		lblKronometroAriketa.setBounds(426, 349, 134, 29);
		panel.add(lblKronometroAriketa);
		lblSerieName.setFont(new Font("Tw Cen MT", Font.BOLD, 25));

		lblSerieName.setHorizontalAlignment(SwingConstants.CENTER);
		lblSerieName.setBounds(0, 181, 984, 35);
		panel.add(lblSerieName);
		lblSerieRepes.setFont(new Font("Verdana", Font.PLAIN, 19));

		lblSerieRepes.setHorizontalAlignment(SwingConstants.CENTER);
		lblSerieRepes.setBounds(0, 227, 984, 35);
		panel.add(lblSerieRepes);

		JButton btnHurrengoSerie = new JButton("➡️ Hurrengo seriea");
		btnHurrengoSerie.setForeground(Color.WHITE);
		btnHurrengoSerie.setVerticalAlignment(SwingConstants.BOTTOM);
		btnHurrengoSerie.setHorizontalAlignment(SwingConstants.RIGHT);
		btnHurrengoSerie.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		btnHurrengoSerie.setBackground(SystemColor.textHighlight);
		btnHurrengoSerie.setFocusPainted(false);
		btnHurrengoSerie.setBounds(369, 434, 251, 35);
		btnHurrengoSerie.setVisible(false);
		panel.add(btnHurrengoSerie);

		JLabel lblKronoNagHeader = new JLabel("Workoutaren kronometroa");
		lblKronoNagHeader.setHorizontalAlignment(SwingConstants.LEFT);
		lblKronoNagHeader.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblKronoNagHeader.setBounds(65, 54, 186, 14);
		lblKronoNagHeader.setVisible(false);
		panel.add(lblKronoNagHeader);

		JLabel lblKronoArikHeader = new JLabel("Ariketaren kronometroa");
		lblKronoArikHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblKronoArikHeader.setFont(new Font("Tahoma", Font.PLAIN, 10));
		lblKronoArikHeader.setVisible(false);
		lblKronoArikHeader.setBounds(426, 330, 134, 14);
		panel.add(lblKronoArikHeader);

		KronometroAriketak ka = new KronometroAriketak(thisClass);
		KronometroSerie ks = new KronometroSerie();

		// LISTENERS

		// AMAITU BOTOIA
		btnAmaitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kronometroNagusia.stopCrono();
				dispose();
				WorkoutsView workoutsView = new WorkoutsView();
				workoutsView.setVisible(true);
			}
		});

		// START BOTOIA
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (posSerie == 0 && ka.getSegundoa() == 0) {
					KronometroKontaketa kk = new KronometroKontaketa(lblKronoKontaketa, lblSerieName, lblSerieRepes, ka,
							ks);
				}
				if (ka.isAlive()) {
					kronometroNagusia.startRunning();
					ka.startRunning();
				} else {
					
					ka.start();
					kronometroNagusia.updateLbl(lblKronometroNagusia);
					ks.start();
					btnHurrengoSerie.setVisible(true);
				}

				lblKronoNagHeader.setVisible(true);
				lblKronometroNagusia.setVisible(true);
				lblKronometroAriketa.setVisible(true);
				lblKronoArikHeader.setVisible(true);
				btnStart.setVisible(false);
				btnPause.setVisible(true);
				btnHurrengoSerie.setVisible(true);
			}
		});

		// PAUSE BOTOIA
		btnPause.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ka.stopRunning();
				kronometroNagusia.stopRunning();
				btnStart.setVisible(true);
				btnPause.setVisible(false);
			}
		});

		// HURRENGO SERIEA BOTOIA
		btnHurrengoSerie.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				posSerie++;
				if (posSerie < ariketaActual.getSeries().size()) {
					lblSerieName.setText("Seriea: " + ariketaActual.getSeries().get(posSerie).getIzena());
					lblSerieRepes.setText(
							"Repetizioak: " + String.valueOf(ariketaActual.getSeries().get(posSerie).getRepetizioak()));
					ks.resetCrono();
				} else {
					ks.stopRunning();
					ks.resetCrono();
					ka.resetCrono();
					ka.stopRunning();
					posAriketa++;
					posSerie = 0;
					JOptionPane.showMessageDialog(null, "Serie guztiak bukatu dira! Hurrengo ariketara pasatuko zara.", "Serieak bukatuta",
							JOptionPane.INFORMATION_MESSAGE);

					if (posAriketa < ariketaList.size()) {
						btnPause.setVisible(false);
						btnStart.setVisible(true);
						ariketaActual = ariketaList.get(posAriketa);
						lblKronometroAriketa.setText("");
						lblAriketaHeader.setText("Ariketa: " + ariketaActual.getIzena());
						lblDeskrAriketa.setText("Deskribapena: " + ariketaActual.getDeskribapena());
						lblLandutakoMuskulua.setText("Landutako muskulua: " + ariketaActual.getLandutako_muskulua());
						lblSerieName.setText("Seriea: " + ariketaActual.getSeries().get(posSerie).getIzena());
						lblSerieRepes.setText("Repetizioak: "
								+ String.valueOf(ariketaActual.getSeries().get(posSerie).getRepetizioak()));
						btnHurrengoSerie.setVisible(false);
					} else {
						
						kronometroNagusia.stopCrono();
						ka.stopCrono();
						ks.stopCrono();
						// call db
						JOptionPane.showMessageDialog(null, "'" + selectedWorkout.getIzena() + "' workout-aren ariketa guztiak bukatu dira! Workout menura bueltatuko zara.", "Ariketak bukatuta",
								JOptionPane.INFORMATION_MESSAGE);
						dispose();
						WorkoutsView workoutsView = new WorkoutsView();
						workoutsView.setVisible(true);
					}
				}
			}
		});
		
		// LOGOUT BOTOIA
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlobalVariables.loggedUser = null;
				dispose();
				LoginView login = new LoginView("");
				login.setVisible(true);
			}
		});
		
		// NIRE PROFILA BOTOIA
		btnNireProfila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ProfileView profile = new ProfileView();
				profile.setVisible(true);
			}
		});
	}
}
