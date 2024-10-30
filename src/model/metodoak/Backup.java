package model.metodoak;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.cloud.firestore.Firestore;

import model.dao.DbConexion;
import model.dao.UserDAO;
import model.dao.WorkoutsDAO;
import model.objects.User;
import model.objects.Workout;
import resources.GlobalVariables;

public class Backup extends Thread{
	DbConexion db;
	GlobalButtons globalButtons;
	
	public void createBackup() {
		db = new DbConexion();
	
		GlobalVariables.isConnexion = db.testConnection();
	
		start();
	}
	
	
	@Override
	public void run() {
		while (!GlobalVariables.isConnexion) {
			try {
				Thread.sleep(10000);
				GlobalVariables.isConnexion = db.testConnection();
				System.out.println("Trying to connect to the database");
			} catch (InterruptedException e) {
				
			}
		}
		
		try {
			System.out.println("Connected");
			this.updateUsers();
			this.userBackup();
			this.workoutBackup();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	private void userBackup() throws Exception {
		
		File file = new File("src/resources/backup/UsersBackup.dat");
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		UserDAO userDAO = new UserDAO();
		
		ArrayList<User> userList = userDAO.getUsers();
		try {
		    for (User user : userList) {
		        dos.writeUTF(user.getUsername());
		        dos.writeUTF(user.getName());
		        dos.writeUTF(user.getSubname());
		        dos.writeUTF(user.getPassword());
		        dos.writeUTF(user.getEmail());
		        dos.writeInt(user.getPhone());
		        dos.writeInt(user.getMaila());
		        dos.writeUTF(user.getBirthdate().toString());
		    }
		} catch (Exception e) {
		    e.printStackTrace(); 
		}
		fos.close();
		dos.close();
	}
	
	private void workoutBackup() throws Exception{
		File file = new File("src/resources/backup/WorkoutsBackup.dat");
		FileOutputStream fos = new FileOutputStream(file);
		DataOutputStream dos = new DataOutputStream(fos);
		WorkoutsDAO workoutsDAO = new WorkoutsDAO();
		
		ArrayList<Workout> workoutList = workoutsDAO.getWorkoutsBackup();

		try {
			for (Workout workout : workoutList) {
				dos.writeUTF(workout.getIzena());
				dos.writeInt(workout.getMaila());
				dos.writeUTF(workout.getVideo_url());
				dos.writeInt(workout.getAriketaSize());
			}
		} catch (Exception e) {

		}
		fos.close();
		dos.close();
	}
	
	private void updateUsers() {
		try {
			File file = new File("src/resources/backup/UpdateUsers.dat");
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			UserDAO userDAO = new UserDAO();
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
					user.setpPhoto(dis.readUTF());
					
					userDAO.updateUser(user);

				} catch (Exception e) {
                    break;
				}
				
			}
			
			dis.close();
			fis.close();
			
	       if (file.delete()) {
	            System.out.println("File deleted successfully: " + file.getPath());
	        } else {
	            System.out.println("Failed to delete the file: " + file.getPath());
	        }
			
			

		} catch (Exception e) {
	        return;
		}
	}
}
