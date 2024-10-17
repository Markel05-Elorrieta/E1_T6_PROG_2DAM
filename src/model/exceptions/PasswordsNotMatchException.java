package model.exceptions;

import javax.swing.JOptionPane;

public class PasswordsNotMatchException  extends Exception {
	private static final long serialVersionUID = 1L;
	public PasswordsNotMatchException() {
		JOptionPane.showMessageDialog(null, "Pasahitzak ez datoz bat!", "Errorea", JOptionPane.ERROR_MESSAGE);
	}
}
