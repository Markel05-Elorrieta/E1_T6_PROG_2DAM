package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import resources.GlobalVariables;

public class WorkoutsView extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public WorkoutsView() {
		setTitle("Workouts - JEM Fit · Erabiltzailea: " + GlobalVariables.loggedUser.getUsername());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH, GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
	}

}
