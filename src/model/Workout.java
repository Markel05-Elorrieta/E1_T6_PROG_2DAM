package model;

public class Workout {
	
	private String izena;
	private double maila;
	private String video_url;
	
	public Workout(String name, double maila, String video_url) {
		this.izena = name;
		this.maila = maila;
		this.video_url = video_url;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public double getMaila() {
		return maila;
	}

	public void setMaila(double maila) {
		this.maila = maila;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	@Override
	public String toString() {
		return "Workouts [name=" + izena + ", maila=" + maila + ", video_url=" + video_url + "]";
	}
}
