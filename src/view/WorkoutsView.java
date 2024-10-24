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
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JButton btnNireProfila = new JButton("");
		btnNireProfila.setToolTipText("Kaixo, " + GlobalVariables.loggedUser.getName() + "!");
		btnNireProfila.setFont(new Font("Tahoma", Font.PLAIN, 6));
		btnNireProfila.setBounds(897, 21, 53, 35);
		btnNireProfila.setFocusPainted(false);
		ImageIcon originalIcon = new ImageIcon(getClass().getResource("/resources/user_icon.png"));
		Image originalImage = originalIcon.getImage();
		Image resizedImage = originalImage.getScaledInstance(20, 20, java.awt.Image.SCALE_SMOOTH);
		
		btnNireProfila.setIcon(new ImageIcon(resizedImage));

		contentPane.add(btnNireProfila);

		JPanel panelWorkoutsInfo = new JPanel();
		panelWorkoutsInfo.setBorder(null);
		panelWorkoutsInfo.setBounds(506, 57, 478, 484);
		contentPane.add(panelWorkoutsInfo);
		panelWorkoutsInfo.setLayout(null);
		
		JLabel lblWorkoutTitle = new JLabel("");
		lblWorkoutTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutTitle.setFont(new Font("Tahoma", Font.PLAIN, 35));
		lblWorkoutTitle.setBounds(1, 98, 476, 46);
		panelWorkoutsInfo.add(lblWorkoutTitle);
		
		JLabel lblWorkoutMaila = new JLabel("Aukeratu workout bat...");
		lblWorkoutMaila.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutMaila.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblWorkoutMaila.setBounds(1, 173, 476, 36);
		panelWorkoutsInfo.add(lblWorkoutMaila);
		
		JButton btnStart = new JButton("▶️ Hasi");
		btnStart.setVerticalAlignment(SwingConstants.BOTTOM);
		btnStart.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 20));
		btnStart.setFocusPainted(false);
		btnStart.setBounds(159, 423, 176, 35);
		btnStart.setEnabled(false);
		panelWorkoutsInfo.add(btnStart);
		
		JLabel lblWorkoutInformazioa = new JLabel("Workout informazioa");
		lblWorkoutInformazioa.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutInformazioa.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblWorkoutInformazioa.setBounds(-1, 17, 479, 27);
		panelWorkoutsInfo.add(lblWorkoutInformazioa);
		
		JLabel lblAriketaSize = new JLabel("");
		lblAriketaSize.setHorizontalAlignment(SwingConstants.CENTER);
		lblAriketaSize.setFont(new Font("Tahoma", Font.PLAIN, 22));
		lblAriketaSize.setBounds(1, 264, 476, 36);
		panelWorkoutsInfo.add(lblAriketaSize);

		JLabel lblLogin_Header = new JLabel("WORKOUTS");
		lblLogin_Header.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_Header.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblLogin_Header.setBounds(0, 11, 984, 35);
		contentPane.add(lblLogin_Header);

		JPanel panelWorkouts = new JPanel();
		panelWorkouts.setBounds(0, 57, 498, 484);
		contentPane.add(panelWorkouts);
		panelWorkouts.setLayout(null);

		JLabel lblZureWorkoutak = new JLabel("Zure workout-ak");
		lblZureWorkoutak.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 20));
		lblZureWorkoutak.setBounds(38, 11, 173, 27);
		panelWorkouts.add(lblZureWorkoutak);

		JScrollPane scrollPane_Workouts = new JScrollPane();
		scrollPane_Workouts.setBorder(null);
		scrollPane_Workouts.setBounds(48, 49, 432, 424);
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
		panelForRadioButtons.setLayout(new BoxLayout(panelForRadioButtons, BoxLayout.Y_AXIS));

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
		
		JSeparator separator = new JSeparator();
		separator.setBackground(new Color(0, 0, 0));
		separator.setOrientation(SwingConstants.VERTICAL);
		separator.setBounds(498, 57, 6, 471);
		contentPane.add(separator);

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
				System.out.println("START WORKOUT");
			}
		});
	}
}
