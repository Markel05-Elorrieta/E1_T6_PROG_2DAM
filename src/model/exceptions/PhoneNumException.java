package model.exceptions;

import javax.swing.JOptionPane;

public class PhoneNumException extends Exception {
	private static final long serialVersionUID = 1L;

	public PhoneNumException() {
		JOptionPane.showMessageDialog(null, "Idatzi ondo telefonoa!", "Errorea", JOptionPane.ERROR_MESSAGE);
	}
}
