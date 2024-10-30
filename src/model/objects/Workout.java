package model.objects;

import java.util.ArrayList;

public class Workout {
	private String workoutID;
	private String izena;
	private int maila;
	private String video_url;
	private int ariketaSize;
	private ArrayList<String> ariketasID = null;
	
	public Workout(String workoutID, String name, int maila, String video_url, int ariketaSize) {
		this.workoutID = workoutID;
		this.izena = name;
		this.maila = maila;
		this.video_url = video_url;
		this.ariketaSize = ariketaSize;
	}
	
	public Workout() {
		
	}
	
	public String getWorkoutID() {
		return workoutID;
	}

	public void setWorkoutID(String workoutID) {
		this.workoutID = workoutID;
	}

	public String getIzena() {
		return izena;
	}
	
	public ArrayList<String> getAriketasID() {
		return ariketasID;
	}
	
	public void setAriketasID(ArrayList<String> ariketasID) {
		this.ariketasID = ariketasID;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public int getMaila() {
		return maila;
	}

	public void setMaila(int maila) {
		this.maila = maila;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}
	
	public int getAriketaSize() {
		return ariketaSize;
	}
	
	public void setAriketaSize(int ariketaSize) {
		this.ariketaSize = ariketaSize;
	}

    @Override
	public String toString() {
		return "Workout{" + "izena='" + izena + '\'' + ", maila=" + maila + ", video_url='" + video_url + '\''
				+ ", ariketaSize=" + ariketaSize + '}';
	}
}
