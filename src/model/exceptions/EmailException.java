package model.exceptions;

import javax.swing.JOptionPane;

public class EmailException extends Exception {
	public EmailException() {
		JOptionPane.showMessageDialog(null, "Email-a ondo idatzi!", "Errorea", JOptionPane.ERROR_MESSAGE);
	}
}
