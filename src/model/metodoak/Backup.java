package model.metodoak;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import com.google.cloud.firestore.Firestore;

import model.User;
import model.Workout;
import model.dao.DbConexion;
import model.dao.UserDAO;
import model.dao.WorkoutsDAO;
import resources.GlobalVariables;

public class Backup extends Thread{
	DbConexion db;
	GlobalButtons globalButtons;
	
	public void createBackup() {
		db = new DbConexion();
		
		globalButtons = new GlobalButtons();
	
		GlobalVariables.isConnexion = db.testConnection();
	
		start();
	}
	
	
	@Override
	public void run() {
		globalButtons.updateLblConnec();
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
}
