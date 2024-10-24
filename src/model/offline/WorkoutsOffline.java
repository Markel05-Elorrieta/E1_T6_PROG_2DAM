package model.offline;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;

import model.objects.Workout;
import resources.GlobalVariables;

public class WorkoutsOffline {
	private ArrayList<Workout> workoutList = new ArrayList<Workout>();
	
	public WorkoutsOffline(){
		
		ArrayList<Workout> aux = new ArrayList<Workout>();
	
		try {
			
			File file = new File("src/resources/backup/WorkoutsBackup.dat");
			if (file.length() == 0) {
				System.out.println("vacio");
			}
			FileInputStream fis = new FileInputStream(file);
			DataInputStream dis = new DataInputStream(fis);
			while (fis.getChannel().position() < fis.getChannel().size()) {
				Workout workout = new Workout();
				workout.setIzena(dis.readUTF());
				workout.setMaila(dis.readInt());
				workout.setVideo_url(dis.readUTF());
				workout.setAriketaSize(dis.readInt());
				aux.add(workout);
	
			}
			fis.close();
			dis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		this.workoutList = aux;
	
	}
	
	 public ArrayList<Workout> getWorkouts() {
		 ArrayList<Workout> aux = new ArrayList<Workout>();
			for (int i = 0; i < this.workoutList.size(); i++) {
				System.out.println(this.workoutList.get(i).getMaila());
				System.out.println(GlobalVariables.loggedUser.getMaila());
				if (this.workoutList.get(i).getMaila() <= GlobalVariables.loggedUser.getMaila()) {
					aux.add(this.workoutList.get(i));
				}
			}
		 return aux;
	 }
}				

