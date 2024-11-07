package model.objects;

public class Serie {
	private String izena;
	private int iraupena;
	private int repetizioak;
	
	public Serie(String izena, int iraupena, int repetizioak) {
		this.izena = izena;
		this.iraupena = iraupena;
		this.repetizioak = repetizioak;
	}
	
	public Serie() {

	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public int getIraupena() {
		return iraupena;
	}

	public void setIraupena(int iraupena) {
		this.iraupena = iraupena;
	}

	public int getRepetizioak() {
		return repetizioak;
	}

	public void setRepetizioak(int repetizioak) {
		this.repetizioak = repetizioak;
	}
	
	
}
