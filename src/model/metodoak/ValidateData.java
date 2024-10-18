package model.metodoak;
import model.User;
import model.exceptions.*;

public class ValidateData {
	public String checkPasswords(String password, String repeatPassword) throws PasswordsNotMatchException {
		if (repeatPassword.equals(password) && !password.equals("") && !repeatPassword.equals("")) {
			return password;
		} else {
			throw new PasswordsNotMatchException();
		}
	}
	
	public int checkEmail(String email) throws EmailException {
		if (email.contains("@") && email.contains(".")) {
			return 1;
		} else {
			throw new EmailException();
		}
	}
	
	public int checkPhoneNumber(String phone) throws PhoneNumException {
		try {
			int phoneNum = Integer.parseInt(phone);
			if (phoneNum < 0 || phoneNum > 999999999 || phone.length() != 9) {
				throw new PhoneNumException();
			}
			return phoneNum;
		} catch (NumberFormatException e) {
			throw new PhoneNumException();
		}
	}
	
	public void checkDate(java.util.Date birthdate) throws DateException {
		java.util.Date currentDate = new java.util.Date();
		if (birthdate.after(currentDate)) {
			throw new DateException();
		}
	}
	
	public void checkEmptyFields(User user) throws EmptyFieldException {
		if (user.getUsername().equals("") || user.getName().equals("") || user.getSubname().equals("") || user.getPassword().equals("") || user.getBirthdate().equals("") || user.getEmail().equals("") || user.getPhone() == 0) {
            throw new EmptyFieldException();
		}
	}
}
