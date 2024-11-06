package model.cronometers;

import javax.swing.JLabel;

public class KronometroNagusia extends Thread {
	
	private int ordua = 0;
	private int minutua = 0;
	private int segundoa = 0;
	private boolean stop = false;
	private JLabel lblDenbora;
	
	public KronometroNagusia() {

	}
	
	public void updateLbl(JLabel lblDenbora) {
		this.lblDenbora = lblDenbora;
		if (ordua == 0 && minutua == 0 && segundoa == 0) {
			start();
		}
	}
	
	

	public void run() {
		try {
			while (stop == false) {
				Thread.sleep(1000);
				segundoa++;
				if (segundoa == 60) {
					minutua++;
					segundoa = 0;
				}
				if (minutua == 60) {
					ordua++;
					minutua = 0;
				}
				lblDenbora.setText(ordua + ":" + minutua + ":" + segundoa);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void stopCrono() {
		stop = true;
	}

	public void startCrono() {
		stop = false;
	}

	public void resetCrono() {
		ordua = 0;
		minutua = 0;
		segundoa = 0;
	}

	public String getDenbora() {
		return ordua + ":" + minutua + ":" + segundoa;
	}

	public int getOrdua() {
		return ordua;
	}

	public int getMinutua() {
		return minutua;
	}

	public int getSegundoa() {
		return segundoa;
	}

	public void setOrdua(int ordua) {
		this.ordua = ordua;
	}

	public void setMinutua(int minutua) {
		this.minutua = minutua;
	}

	public void setSegundoa(int segundoa) {
		this.segundoa = segundoa;
	}

	public void setDenbora(int ordua, int minutua, int segundoa) {
		this.ordua = ordua;
		this.minutua = minutua;
		this.segundoa = segundoa;
	}

}
