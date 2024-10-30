package view;

import java.awt.EventQueue;
import java.awt.Image;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
import javax.swing.JTabbedPane;

public class AriketakView extends JFrame {

	private static final long serialVersionUID = 1L;
	private AriketakDAO ariketakDAO = new AriketakDAO();
	private ArrayList<Ariketa> ariketaList = new ArrayList<Ariketa>();

	/**
	 * Create the frame.
	 */
	public AriketakView(Workout selectedWorkout) {
		setTitle(selectedWorkout.getIzena() + " - JEM Fit · Erabiltzailea: " + GlobalVariables.loggedUser.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		Image resizedIconImage = new ImageIcon(getClass().getResource("/resources/images/logo.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		setIconImage(resizedIconImage);
		
		try {
			ariketaList = ariketakDAO.getAriketak(selectedWorkout);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Ezin izan dira ariketak kargatu. Barkatu eragozpenak.");
		}
		
		BackgroundImageView panel = new BackgroundImageView();
		setContentPane(panel);
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		JButton btnAtera = new JButton("⏹️ Amaitu");
		btnAtera.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAtera.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAtera.setForeground(Color.WHITE);
		btnAtera.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		btnAtera.setFocusPainted(false);
		btnAtera.setBackground(Color.RED);
		btnAtera.setBounds(327, 480, 170, 35);
		panel.add(btnAtera);
		
		JLabel lblKronometroNagusia = new JLabel("Crono princip");
		lblKronometroNagusia.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblKronometroNagusia.setBounds(90, 91, 107, 29);
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
		
		JLabel lblHeader = new JLabel("");
		lblHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblHeader.setForeground(Color.WHITE);
		lblHeader.setText("ARIKETA");
		lblHeader.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblHeader.setBounds(0, 11, 984, 35);
		panel.add(lblHeader);
		
		JLabel lblDeskrAriketa = new JLabel("");
		lblDeskrAriketa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDeskrAriketa.setForeground(Color.BLACK);
		lblDeskrAriketa.setText("Deskribapena: " );
		lblDeskrAriketa.setFont(new Font("Tahoma", Font.PLAIN, 13));
		lblDeskrAriketa.setBounds(0, 51, 984, 29);
		panel.add(lblDeskrAriketa);
		
		JLabel lblWorkouta = new JLabel("Workout-a: " + selectedWorkout.getIzena());
		lblWorkouta.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkouta.setForeground(Color.BLACK);
		lblWorkouta.setFont(new Font("Tahoma", Font.PLAIN, 17));
		lblWorkouta.setBounds(0, 80, 984, 22);
		panel.add(lblWorkouta);
		
		JButton btnStart = new JButton("▶️ Hasi");
		btnStart.setVerticalAlignment(SwingConstants.BOTTOM);
		btnStart.setHorizontalAlignment(SwingConstants.RIGHT);
		btnStart.setForeground(Color.WHITE);
		btnStart.setFont(new Font("Segoe UI Emoji", Font.BOLD, 20));
		btnStart.setFocusPainted(false);
		btnStart.setBackground(new Color(46, 139, 87));
		btnStart.setBounds(526, 480, 170, 35);
		panel.add(btnStart);
		
		
	}
}
