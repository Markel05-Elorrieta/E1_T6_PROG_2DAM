package model.cronometers;

import javax.swing.JFrame;
import javax.swing.JLabel;

import view.AriketakView;

public class KronometroAriketak extends Thread {
	
	private int minutua = 0;
	private int segundoa = 0;
	private boolean stop = false;
	private int actualTime = 0;

	
	private AriketakView frame;
	
	public KronometroAriketak(AriketakView frame) {
		this.frame = frame;
		start();
	}

	
	

	public void run() {
		try {
			while (stop == false) {
				Thread.sleep(1000);
				segundoa++;
				actualTime++;
				if (segundoa == 60) {
					minutua++;
					segundoa = 0;
				}
				frame.lblKronometroAriketa.setText(minutua + ":" + segundoa);
				if (actualTime >= frame.ariketaActual.getIraupena()) {
					this.stopCrono();
				}
			}
			this.updateFrame();
		
			
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
		actualTime = 0;
		minutua = 0;
		segundoa = 0;
		
	}

	public String getDenbora() {
		return minutua + ":" + segundoa;
	}


	public int getMinutua() {
		return minutua;
	}

	public int getSegundoa() {
		return segundoa;
	}


	public void setMinutua(int minutua) {
		this.minutua = minutua;
	}

	public void setSegundoa(int segundoa) {
		this.segundoa = segundoa;
	}

	public void setDenbora(int minutua, int segundoa) {
		this.minutua = minutua;
		this.segundoa = segundoa;
	}
	
	private void updateFrame() {
		frame.posAriketa++;
		frame.ariketaActual = frame.ariketaList.get(frame.posAriketa);
		frame.lblAriketaHeader.setText(frame.ariketaActual.getIzena());
		frame.lblDeskrAriketa.setText(frame.ariketaActual.getDeskribapena());
		frame.lblLandutakoMuskulua.setText(frame.ariketaActual.getLandutako_muskulua());
		frame.lblKronometroAriketa.setText("0:0:0");
		this.resetCrono();
	}

}
