package model.offline;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import model.BcryptMethods;
import model.dao.UserDAO;
import model.objects.User;
import resources.GlobalVariables;

public class UserOffline {
	
	private ArrayList<User> userList = new ArrayList<User>();
	private BcryptMethods bCrypt = new BcryptMethods();
	
	public UserOffline(){
		
		ArrayList<User> aux = new ArrayList<User>();
	
		try {
			
			File file = new File("src/resources/backup/UsersBackup.dat");
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			while (fis.getChannel().position() < fis.getChannel().size()) {
				try {
					User user = new User();
					user.setUsername(dis.readUTF());
					user.setName(dis.readUTF());
					user.setSubname(dis.readUTF());
					user.setPassword(dis.readUTF());
					user.setEmail(dis.readUTF());
					user.setPhone(dis.readInt());
					user.setMaila(dis.readInt());
					Date d = new Date(0);
					user.setBirthdate(d);
					dis.readUTF();
					aux.add(user);
				} catch (Exception e) {
					System.out.println("first");
					e.printStackTrace();
                    break;
				}
				
			}
			fis.close();
			dis.close();
		} catch (Exception e) {
			System.out.println("second");
			e.printStackTrace();
		}
		this.userList = aux;
		
	}
	
	public boolean checkLogin(String username, String password) {
		for (int i = 0; i < this.userList.size(); i++) {
			if (this.userList.get(i).getUsername().equals(username)){
				if (bCrypt.checkPassword(password, userList.get(i).getPassword())) {
					GlobalVariables.loggedUser = this.userList.get(i);
			        return true;
				}
			}
		}
		return false;
	}
}


