package model.objects;

import java.util.Date;

public class WHistoriala {
	private String izena;
	private int maila;
	private int denboraTotala;
	private int aurreikusitakoDenbora;
	private Date data;
	private String betetakoAriketak;

	public WHistoriala(String izena, int maila, int denboraTotala, int aurreikusitakoDenbora, Date data,
			String betetakoAriketak) {
		this.izena = izena;
		this.maila = maila;
		this.denboraTotala = denboraTotala;
		this.aurreikusitakoDenbora = aurreikusitakoDenbora;
		this.data = data;
		this.betetakoAriketak = betetakoAriketak;
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

	public int getDenboraTotala() {
		return denboraTotala;
	}

	public void setDenboraTotala(int denboraTotala) {
		this.denboraTotala = denboraTotala;
	}

	public int getAurreikusitakoDenbora() {
		return aurreikusitakoDenbora;
	}

	public void setAurreikusitakoDenbora(int aurreikusitakoDenbora) {
		this.aurreikusitakoDenbora = aurreikusitakoDenbora;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public String getBetetakoAriketak() {
		return betetakoAriketak;
	}

	public void setBetetakoAriketak(String betetakoAriketak) {
		this.betetakoAriketak = betetakoAriketak;
	}

}
