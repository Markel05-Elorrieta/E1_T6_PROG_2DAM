package model.metodoak;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import resources.GlobalVariables;

public class GlobalButtons {
	private JLabel lblConnecStatus;
	
	public GlobalButtons() {
		lblConnecStatus = new JLabel("");
		lblConnecStatus.setFont(new Font("Tahoma", Font.PLAIN, 11));
		lblConnecStatus.setBounds(900, 0, 66, 14);
		if (!GlobalVariables.isConnexion) {
			lblConnecStatus.setText("Offline");
			lblConnecStatus.setForeground(Color.RED);
		} else {
			lblConnecStatus.setText("Online");
			lblConnecStatus.setForeground(Color.GREEN);
		}

	}
	public JButton btnAtzera(String where) {
		JButton btnAtzera = new JButton("⬅️ Atzera");
		btnAtzera.setHorizontalAlignment(SwingConstants.RIGHT);
		btnAtzera.setVerticalAlignment(SwingConstants.BOTTOM);
		btnAtzera.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 19));
		btnAtzera.setFocusPainted(false);
		btnAtzera.setBounds(59, 11, 167, 35);
        return btnAtzera;
	}
	
	public void updateLblConnec() {
		if (!GlobalVariables.isConnexion) {
			lblConnecStatus.setText("Offline");
			lblConnecStatus.setForeground(Color.RED);
		} else {
			lblConnecStatus.setText("Online");
			lblConnecStatus.setForeground(Color.GREEN);
		}
	}
	public JLabel getLblConnecStatus() {
		return lblConnecStatus;
	}
	
	
	
	
}
