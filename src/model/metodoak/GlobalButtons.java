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
	
	public GlobalButtons() {
		

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
}
