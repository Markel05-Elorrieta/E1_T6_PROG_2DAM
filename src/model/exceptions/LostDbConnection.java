package model.exceptions;

import model.metodoak.Backup;
import resources.GlobalVariables;

public class LostDbConnection extends Exception {
	private static final long serialVersionUID = 1L;

	public LostDbConnection() {
		GlobalVariables.isConnexion = false;
		Backup backup = new Backup();
		backup.createBackup();
	}
}
