package model.objects;

public class Ariketa {
	private String izena;
	private String deskribapena;
	private String landutako_muskulua;
	private int iraupena;
	private String video_url;

	public Ariketa(String izena, String deskribapena, String landutako_muskulua, int iraupena, String video_url) {
		this.izena = izena;
		this.deskribapena = deskribapena;
		this.landutako_muskulua = landutako_muskulua;
		this.iraupena = iraupena;
		this.video_url = video_url;
	}

	public Ariketa() {
		
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getDeskribapena() {
		return deskribapena;
	}

	public void setDeskribapena(String deskribapena) {
		this.deskribapena = deskribapena;
	}

	public String getLandutako_muskulua() {
		return landutako_muskulua;
	}

	public void setLandutako_muskulua(String landutako_muskulua) {
		this.landutako_muskulua = landutako_muskulua;
	}

	public int getIraupena() {
		return iraupena;
	}

	public void setIraupena(int iraupena) {
		this.iraupena = iraupena;
	}

	public String getVideo_url() {
		return video_url;
	}

	public void setVideo_url(String video_url) {
		this.video_url = video_url;
	}

	@Override
	public String toString() {
		return "Ariketa [izena=" + izena + ", deskribapena=" + deskribapena + ", landutako_muskulua="
				+ landutako_muskulua + ", iraupena=" + iraupena + ", video_url=" + video_url + "]";
	}
}
