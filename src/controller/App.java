package controller;

import model.BcryptMethods;
import view.LoginView;

public class App {

	public static void main(String[] args) {
		
		LoginView login = new LoginView();
		login.setVisible(true);
		
		// test to hash passwords
		/*
		BcryptMethods bCrypt = new BcryptMethods();
		String password = "jonva";
	    System.out.println(bCrypt.hashPassword(password));
	    */
		

	}

}
