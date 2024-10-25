package controller;

import model.metodoak.Backup;
import view.LoginView;
import view.ProfileView;

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
	    
		
		
		// test profile view
		ProfileView profile = new ProfileView();
		profile.setVisible(true);
		*/


	
	}

}
