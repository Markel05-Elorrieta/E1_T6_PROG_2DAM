package model.exceptions;

import javax.swing.JOptionPane;

public class noBackupException extends Exception {
	private static final long serialVersionUID = 1L;

	public noBackupException() {
		JOptionPane.showMessageDialog(null, "Ez dago backupsik", "Error", JOptionPane.ERROR_MESSAGE);
	}
}
