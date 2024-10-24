package view;



import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import model.dao.WorkoutsDAO;
import model.exceptions.LostDbConnection;
import model.objects.Workout;
import resources.GlobalVariables;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;

import javax.swing.ImageIcon;
import javax.swing.JSeparator;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import javax.swing.SpringLayout;

public class WorkoutsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JRadioButton rdbtnWorkout;
	
	private WorkoutsDAO workoutsDAO = new WorkoutsDAO();
	private ArrayList<Workout> workoutsList = new ArrayList<Workout>();

	/**
	 * Create the frame.
	 * 
	 * @throws Exception
	 */
	public WorkoutsView() {
		setTitle("Workouts - JEM Fit · Erabiltzailea: " + GlobalVariables.loggedUser.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH,
				GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		
		BackgroundImageView panel = new BackgroundImageView();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);

		JButton btnNireProfila = new JButton("");
		btnNireProfila.setBackground(Color.WHITE);
		btnNireProfila.setToolTipText("Kaixo, " + GlobalVariables.loggedUser.getName() + "!");
		btnNireProfila.setFont(new Font("Tahoma", Font.PLAIN, 6));
		btnNireProfila.setBounds(884, 11, 53, 35);
		btnNireProfila.setFocusPainted(false);
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/resources/user_icon.png"));
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnNireProfila.setIcon(new ImageIcon(resizedImage));
		panel.add(btnNireProfila);

		JButton btnLogout = new JButton("");
		btnLogout.setBackground(Color.RED);
		btnLogout.setForeground(Color.WHITE);
		btnLogout.setToolTipText("Logout...");
		btnLogout.setFont(new Font("Tahoma", Font.PLAIN, 6));
		ImageIcon originalIcon1 = new ImageIcon(getClass().getResource("/resources/logout-negro.png"));
		Image originalImage1 = originalIcon1.getImage();
		Image resizedImage1 = originalImage1.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		btnLogout.setIcon(new ImageIcon(resizedImage1));
		btnLogout.setFocusPainted(false);
		btnLogout.setBounds(935, 11, 33, 35);
		panel.add(btnLogout);
		
		JPanel panelWorkoutsInfo = new JPanel();
		panelWorkoutsInfo.setBackground(new Color(124, 252, 0));
		panelWorkoutsInfo.setBorder(null);
		panelWorkoutsInfo.setBackground(new Color(0, 0, 0, 0));
		panelWorkoutsInfo.setOpaque(false);
		panelWorkoutsInfo.setBounds(506, 57, 469, 476);
		panel.add(panelWorkoutsInfo);
		panelWorkoutsInfo.setLayout(null);
		
		JLabel lblWorkoutTitle = new JLabel("");
		lblWorkoutTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutTitle.setFont(new Font("Tahoma", Font.PLAIN, 36));
		lblWorkoutTitle.setBounds(0, 90, 468, 46);
		panelWorkoutsInfo.add(lblWorkoutTitle);
		
		JLabel lblWorkoutMaila = new JLabel("Aukeratu workout bat...");
		lblWorkoutMaila.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutMaila.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblWorkoutMaila.setBounds(1, 173, 467, 36);
		panelWorkoutsInfo.add(lblWorkoutMaila);
		
		JButton btnStart = new JButton("▶️ Hasi");
		btnStart.setHorizontalAlignment(SwingConstants.RIGHT);
		btnStart.setForeground(Color.WHITE);
		btnStart.setBackground(new Color(46, 139, 87));
		btnStart.setVerticalAlignment(SwingConstants.BOTTOM);
		btnStart.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnStart.setFocusPainted(false);
		btnStart.setBounds(159, 415, 147, 35);
		btnStart.setEnabled(false);
		panelWorkoutsInfo.add(btnStart);
		
		JLabel lblWorkoutInformazioa = new JLabel("Workout informazioa");
		lblWorkoutInformazioa.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutInformazioa.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 23));
		lblWorkoutInformazioa.setBounds(1, 11, 470, 36);
		panelWorkoutsInfo.add(lblWorkoutInformazioa);
		
		JLabel lblAriketaSize = new JLabel("");
		lblAriketaSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblAriketaSize.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAriketaSize.setBounds(1, 264, 468, 36);
		panelWorkoutsInfo.add(lblAriketaSize);

		JLabel lblLogin_Header = new JLabel("WORKOUTS");
		lblLogin_Header.setForeground(Color.WHITE);
		lblLogin_Header.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_Header.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblLogin_Header.setBounds(0, 11, 984, 35);
		panel.add(lblLogin_Header);

		JPanel panelWorkouts = new JPanel();
		panelWorkouts.setBackground(new Color(0, 0, 0, 0));
		panelWorkouts.setOpaque(false); 
		panelWorkouts.setBounds(3, 57, 490, 477);
		panel.add(panelWorkouts);
		panelWorkouts.setLayout(null);

		JLabel lblZureWorkoutak = new JLabel("Zure workout-ak");
		lblZureWorkoutak.setHorizontalAlignment(SwingConstants.CENTER);
		lblZureWorkoutak.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 23));
		lblZureWorkoutak.setBounds(0, 11, 490, 32);
		panelWorkouts.add(lblZureWorkoutak);

		JScrollPane scrollPane_Workouts = new JScrollPane();
		scrollPane_Workouts.setBorder(null);
		scrollPane_Workouts.setBackground(Color.ORANGE);
		scrollPane_Workouts.setBounds(25, 49, 455, 424);
		panelWorkouts.add(scrollPane_Workouts);

		ButtonGroup WorkoutsRBGroup = new ButtonGroup();
		try {
			try {
				workoutsList = workoutsDAO.getWorkouts();
			} catch (LostDbConnection ldbc) {
				workoutsList = workoutsDAO.getWorkouts();
			}
		}catch (Exception e) {
	        e.printStackTrace();
		}
		
		
		JPanel panelForRadioButtons = new JPanel();
		panelForRadioButtons.setFont(new Font("Tahoma", Font.PLAIN, 15));
		panelForRadioButtons.setBorder(null);
		panelForRadioButtons.setBackground(Color.ORANGE);
		panelForRadioButtons.setOpaque(false);
		//panelForRadioButtons.setLayout(new BoxLayout(panelForRadioButtons, BoxLayout.Y_AXIS));

		for (int i = 0; i < workoutsList.size(); i++) {
			rdbtnWorkout = new JRadioButton(workoutsList.get(i).getIzena());
			rdbtnWorkout.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			rdbtnWorkout.setActionCommand(i + "");
			rdbtnWorkout.setBounds(6, 7 + (i * 30), 334, 23);
			rdbtnWorkout.setFocusPainted(false);
			
			// RADIO BUTTON LISTENER
			rdbtnWorkout.addActionListener(new ActionListener() {
			    public void actionPerformed(ActionEvent e) {	
			    	int index = Integer.parseInt(WorkoutsRBGroup.getSelection().getActionCommand());
			    	Workout workout = workoutsList.get(index);
			    	lblWorkoutTitle.setText(workout.getIzena());
			    	int workoutMaila = (int) workout.getMaila();
			    	lblWorkoutMaila.setText("Maila: " + workoutMaila);
			    	lblAriketaSize.setText("Ariketa kopurua: " + workout.getAriketaSize());
			    	btnStart.setEnabled(true);
			    }
			});
			WorkoutsRBGroup.add(rdbtnWorkout);
			panelForRadioButtons.add(rdbtnWorkout);
		}

		scrollPane_Workouts.setViewportView(panelForRadioButtons);
		panelForRadioButtons.setLayout(null);
		
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(498, 57, 6, 471);
		panel.add(separator);

		// LISTENERS

		// NIRE PROFILA BUTTON
		btnNireProfila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "COMING SOON..." ,"Nire profila", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// START BUTTON
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "COMING SOON..." ,"Workout hasi", JOptionPane.INFORMATION_MESSAGE);
			}
		});
		
		// LOGOUT BUTTON
		btnLogout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				GlobalVariables.loggedUser = null;
				dispose();
                LoginView login = new LoginView("");
                login.setVisible(true);
			}
		});
	}
}
