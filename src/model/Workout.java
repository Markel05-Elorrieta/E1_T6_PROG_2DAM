package model;

public class Workout {
	
	private String izena;
	private int maila;
	private String video_url;
	private int ariketaSize;
	
	public Workout(String name, int maila, String video_url, int ariketaSize) {
		this.izena = name;
		this.maila = maila;
		this.video_url = video_url;
		this.ariketaSize = ariketaSize;
	}

	public String getIzena() {
		return izena;
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
