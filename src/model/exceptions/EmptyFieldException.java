package model.exceptions;

import javax.swing.JOptionPane;

public class EmptyFieldException extends Exception {
	private static final long serialVersionUID = 1L;

	public EmptyFieldException() {
		JOptionPane.showMessageDialog(null, "Eremu guztiak bete behar dira!", "Kontuz", JOptionPane.INFORMATION_MESSAGE);
	}
}
