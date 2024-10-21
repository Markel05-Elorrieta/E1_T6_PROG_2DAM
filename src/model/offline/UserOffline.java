package model.offline;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import model.BcryptMethods;
import model.User;
import resources.GlobalVariables;

public class UserOffline {
	
	private ArrayList<User> userList = new ArrayList<User>();
	private BcryptMethods bCrypt = new BcryptMethods();
	
	public UserOffline() throws IOException {
		
		ArrayList<User> aux = new ArrayList<User>();
		File file = new File("src/resources/backup/UsersBackup.dat");
		System.out.println(file.getAbsolutePath());
		FileInputStream fis = new FileInputStream(file);
		DataInputStream dis = new DataInputStream(fis);
		
		try {
			while (fis.getChannel().position() < fis.getChannel().size()) {
				User user = new User();
				user.setUsername(dis.readUTF());
				user.setName(dis.readUTF());
				user.setSubname(dis.readUTF());
				user.setPassword(dis.readUTF());
				user.setEmail(dis.readUTF());
				user.setPhone(dis.readInt());
				user.setMaila(dis.readInt());
				dis.readUTF();
				Date d = new Date(0);
				user.setBirthdate(d);
				System.out.println(user.toString());
				aux.add(user);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		this.userList = aux;
		fis.close();
		dis.close();
	}
	
	public boolean checkLogin(String username, String password) {
		System.out.println(this.userList.size());
		for (int i = 0; i < this.userList.size(); i++) {
			if (this.userList.get(i).getUsername().equals(username)){
				if (bCrypt.checkPassword(password, userList.get(i).getPassword())) {
					GlobalVariables.loggedUser = this.userList.get(i);
					System.out.println(userList.get(i).getPassword());
			        return true;
				}
			}
		}
		return false;
	}
}


