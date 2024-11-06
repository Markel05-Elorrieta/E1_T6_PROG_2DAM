package view;

import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

import model.cronometers.KronometroAriketak;
import model.cronometers.KronometroNagusia;
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

public class AriketakView extends JFrame{

	public static final long serialVersionUID = 1L;
	public AriketakDAO ariketakDAO = new AriketakDAO();
	public ArrayList<Ariketa> ariketaList = new ArrayList<Ariketa>();
	public Ariketa ariketaActual;
	public int posAriketa = 0;
	public JLabel lblAriketaHeader = new JLabel();
	public JLabel lblDeskrAriketa = new JLabel();
	public JLabel lblLandutakoMuskulua = new JLabel();
	JLabel lblKronometroNagusia = new JLabel();
	public JLabel lblKronometroAriketa = new JLabel();
	
	
	
	/* ----------------- */

	
	
	/**
	 * Create the frame.
	 */
	public AriketakView(Workout selectedWorkout, KronometroNagusia kronometroNagusia) {
		try {
			ariketaList = ariketakDAO.getAriketak(selectedWorkout);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ezin izan dira ariketak kargatu. Barkatu eragozpenak.");
		}
		
		AriketakView thisClass = this;
		
		setTitle(selectedWorkout.getIzena() + " - JEM Fit · Erabiltzailea: " + GlobalVariables.loggedUser.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		Image resizedIconImage = new ImageIcon(getClass().getResource("/resources/images/logo.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		setIconImage(resizedIconImage);
		
		BackgroundImageView panel = new BackgroundImageView();
		setContentPane(panel);
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnAmaitu = new JButton("⏹️ Amaitu");
		btnAmaitu.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAmaitu.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAmaitu.setForeground(Color.WHITE);
		btnAmaitu.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		btnAmaitu.setFocusPainted(false);
		btnAmaitu.setBackground(Color.RED);
		btnAmaitu.setBounds(327, 480, 170, 35);
		panel.add(btnAmaitu);
		

		lblKronometroNagusia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblKronometroNagusia.setBounds(88, 152, 114, 29);
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
		btnStart.setBounds(526, 480, 170, 35);
		panel.add(btnStart);
		
		
		lblLandutakoMuskulua.setHorizontalAlignment(SwingConstants.CENTER);
		lblLandutakoMuskulua.setForeground(Color.BLACK);
		lblLandutakoMuskulua.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		lblLandutakoMuskulua.setBounds(0, 111, 984, 22);
		panel.add(lblLandutakoMuskulua);
		
	
		
	
		lblKronometroAriketa.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblKronometroAriketa.setBounds(88, 205, 114, 29);
		panel.add(lblKronometroAriketa);
		
		kronometroNagusia.updateLbl(lblKronometroNagusia);
		
		
		
		
		// LISTENERS
		
		btnAmaitu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				WorkoutsView workoutsView = new WorkoutsView();
				workoutsView.setVisible(true);
			}
		});
		
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		     
			KronometroAriketak ka = new KronometroAriketak(thisClass);
			
				
			}
		});
	}
	
   
}
