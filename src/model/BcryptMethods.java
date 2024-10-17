package model;

import org.mindrot.jbcrypt.BCrypt;

public class BcryptMethods {
	public String hashPassword(String password) {
		String hashedPwd = BCrypt.hashpw(password, BCrypt.gensalt());
		return hashedPwd;
	}
	
	public boolean checkPassword(String password, String hashedPwd) {
		boolean passwordMatch = BCrypt.checkpw(password, hashedPwd);
		return passwordMatch;
	}
}
