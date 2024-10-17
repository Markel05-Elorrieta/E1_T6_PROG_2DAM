package model.exceptions;

import javax.swing.JOptionPane;

public class UsernameException extends Exception {
	private static final long serialVersionUID = 1L;
	public UsernameException() {
            JOptionPane.showMessageDialog(null, "Erabiltzailea existitzen da jada!", "Errorea", JOptionPane.ERROR_MESSAGE);
	}
}
