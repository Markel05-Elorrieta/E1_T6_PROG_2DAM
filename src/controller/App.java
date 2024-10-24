package controller;

import model.metodoak.Backup;
import view.LoginView;

public class App {

	public static void main(String[] args) {
		
		
		Backup backup = new Backup();
		backup.createBackup();
		
		
		LoginView login = new LoginView("");
		login.setVisible(true);
		
	
		
		/*test*/
		
		// test to hash passwords
		/*
		BcryptMethods bCrypt = new BcryptMethods();
		String password = "jonva";
	    System.out.println(bCrypt.hashPassword(password));
	    */
		


	
	}

}
