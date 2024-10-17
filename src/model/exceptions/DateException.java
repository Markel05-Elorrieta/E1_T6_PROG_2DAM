package model.exceptions;

import javax.swing.JOptionPane;

public class DateException extends Exception {
	private static final long serialVersionUID = 1L;

	public DateException() {
		JOptionPane.showMessageDialog(null, "Jaiotze-data ez da baliozkoa!", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
