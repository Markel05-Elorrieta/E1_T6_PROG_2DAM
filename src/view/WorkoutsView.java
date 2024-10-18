package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import model.Workout;
import model.dao.WorkoutsDAO;
import resources.GlobalVariables;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.DefaultComboBoxModel;

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
	public WorkoutsView() throws Exception {
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
		btnNireProfila.setBounds(890, 11, 53, 35);
		contentPane.add(btnNireProfila);

		JPanel panelWorkoutsInfo = new JPanel();
		panelWorkoutsInfo.setBounds(364, 57, 620, 484);
		contentPane.add(panelWorkoutsInfo);
		panelWorkoutsInfo.setLayout(null);

		JScrollPane scrollPane_WInfo = new JScrollPane();
		scrollPane_WInfo.setBounds(0, 0, 610, 473);
		panelWorkoutsInfo.add(scrollPane_WInfo);

		JLabel lblLogin_Header = new JLabel("WORKOUTS");
		lblLogin_Header.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin_Header.setFont(new Font("Segoe UI Black", Font.PLAIN, 30));
		lblLogin_Header.setBounds(0, 11, 984, 35);
		contentPane.add(lblLogin_Header);

		JPanel panelWorkouts = new JPanel();
		panelWorkouts.setBounds(0, 57, 354, 484);
		contentPane.add(panelWorkouts);
		panelWorkouts.setLayout(null);

		JLabel lblZureWorkoutak = new JLabel("Zure workout-ak");
		lblZureWorkoutak.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		lblZureWorkoutak.setBounds(20, 11, 145, 27);
		panelWorkouts.add(lblZureWorkoutak);

		JScrollPane scrollPane_Workouts = new JScrollPane();
		scrollPane_Workouts.setBounds(10, 49, 334, 424);
		panelWorkouts.add(scrollPane_Workouts);

		ButtonGroup WorkoutsRBGroup = new ButtonGroup();

		workoutsList = workoutsDAO.getWorkouts();

		JPanel panelForRadioButtons = new JPanel();
		panelForRadioButtons.setLayout(new BoxLayout(panelForRadioButtons, BoxLayout.Y_AXIS));

		for (int i = 0; i < workoutsList.size(); i++) {
			rdbtnWorkout = new JRadioButton(workoutsList.get(i).getIzena());
			rdbtnWorkout.setFont(new Font("Segoe UI", Font.PLAIN, 15));
			rdbtnWorkout.setActionCommand(workoutsList.get(i).getIzena());
			rdbtnWorkout.setBounds(6, 7 + (i * 30), 334, 23);
			rdbtnWorkout.setFocusPainted(false);
			WorkoutsRBGroup.add(rdbtnWorkout);
			panelForRadioButtons.add(rdbtnWorkout);
		}

		scrollPane_Workouts.setViewportView(panelForRadioButtons);

		// LISTENERS

		// RADIO BUTTONS
		rdbtnWorkout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < workoutsList.size(); i++) {
                    if (WorkoutsRBGroup.getSelection().getActionCommand().equals(workoutsList.get(i).getIzena())) {
                    	System.out.println("Selected workout: " + WorkoutsRBGroup.getSelection().getActionCommand());
                    }
			}
		}
		});
		
		
		// NIRE PROFILA BUTTON
		btnNireProfila.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

			}
		});
	}
}
