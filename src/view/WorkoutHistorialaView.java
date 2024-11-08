package view;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import model.dao.WHistorialaDAO;
import model.metodoak.GlobalButtons;
import model.objects.WHistoriala;
import resources.GlobalVariables;
import java.awt.Component;

public class WorkoutHistorialaView extends JFrame {
	private static final long serialVersionUID = 1L;
	private GlobalButtons globalVariables = new GlobalButtons();
	private JButton btnAtzera;
	private JTable table;
	private WHistorialaDAO workoutsHiDAO = new WHistorialaDAO();
	private ArrayList<WHistoriala> historialList = new ArrayList<WHistoriala>();
	
	/**
	 * Create the frame.
	 */
	public WorkoutHistorialaView() {
		setTitle("Workout historiala - JEM Fit - Erabiltzailea: " + GlobalVariables.loggedUser.getUsername());
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(GlobalVariables.WINDOW_X, GlobalVariables.WINDOW_Y, GlobalVariables.WINDOW_WIDTH,
				GlobalVariables.WINDOW_HEIGHT);
		setResizable(false);
		Image resizedIconImage = new ImageIcon(getClass().getResource("/resources/images/logo.png")).getImage().getScaledInstance(50, 50, java.awt.Image.SCALE_SMOOTH);
		setIconImage(resizedIconImage);

		BackgroundImageView panel = new BackgroundImageView();
		panel.setLayout(null);
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panel);
		
		JLabel lblWorkoutHistorialHeader = new JLabel("WORKOUT HISTORIALA");
		lblWorkoutHistorialHeader.setHorizontalAlignment(SwingConstants.CENTER);
		lblWorkoutHistorialHeader.setFont(new Font("Tahoma", Font.BOLD, 26));
		lblWorkoutHistorialHeader.setBounds(0, 11, 984, 39);
		panel.add(lblWorkoutHistorialHeader);
		
		Object[] columnNames = {"Workout izena", "Maila", "Denbora totala", "Aurreikusitako denbora", "Data", "% Betetako ariketak"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
	
		try {
			historialList = workoutsHiDAO.getWHistoriala();
		} catch (Exception e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Errorea historiala kargatzean. Barkatu eragozpenak.");
		}
		
		for (int i = 0; i < historialList.size(); i++) {
			Date data = historialList.get(i).getData();
			int dataYear = data.getYear() + 1900;
			int dataMonth = data.getMonth() + 1;
			String dataString = data.getDate() + "/" + dataMonth + "/" + dataYear;
			String minutuakSt1 = "";
			String minutuakSt2 = "";
			if (historialList.get(i).getDenboraTotala() >= 60) {
				int minutuak = historialList.get(i).getDenboraTotala() / 60;
				int segunduak = historialList.get(i).getDenboraTotala() % 60;
				minutuakSt1 = minutuak + "m " + segunduak + "s";
			} else {
				minutuakSt1 = historialList.get(i).getDenboraTotala() + "s";
			}
			
			if (historialList.get(i).getAurreikusitakoDenbora() >= 60) {
				int minutuak = historialList.get(i).getAurreikusitakoDenbora() / 60;
				int segunduak = historialList.get(i).getAurreikusitakoDenbora() % 60;
				minutuakSt2 = minutuak + "m " + segunduak + "s";
			} else {
				minutuakSt2 = historialList.get(i).getAurreikusitakoDenbora() + "s";
			}
			
            model.addRow(new Object[]{historialList.get(i).getIzena(), historialList.get(i).getMaila(), minutuakSt1, minutuakSt2, dataString, historialList.get(i).getBetetakoAriketak()});
		};
		
		
		table = new JTable(model);
		table.setEnabled(false);
		
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(55, 74, 883, 421);
		panel.add(scrollPane);
		
		btnAtzera = globalVariables.btnAtzera("WorkoutHistorialaView");
		panel.add(btnAtzera);
		
		// LISTENERS
		
		// ATZERA BUTTON
		btnAtzera.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				ProfileView nireProfila = new ProfileView();
				nireProfila.setVisible(true);
			}
		});
	}
}
