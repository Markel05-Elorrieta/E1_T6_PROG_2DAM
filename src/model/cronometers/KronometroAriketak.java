package model.cronometers;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import view.AriketakView;
import view.WorkoutsView;

public class KronometroAriketak extends Thread {
	
	private int minutua = 0;
	private int segundoa = 0;
	private boolean stop = false;
	private int actualTime = 0;
	private boolean isRunning = true;
	
	

	
	private AriketakView frame;
	
	public KronometroAriketak(AriketakView frame) {
		this.frame = frame;
	}

	public void run() {
		try {
			while (stop == false) {
				Thread.sleep(1000);
				if (isRunning) {
					segundoa++;
					actualTime++;
					if (segundoa == 60) {
						minutua++;
						segundoa = 00;
					}
					frame.lblKronometroAriketa.setText(minutua + ":" + segundoa);

				}

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
	
	public void stopRunning() {
		isRunning = false;
	}
	
	public void startRunning() {
		isRunning = true;
	}
	

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

	public void resetCrono() {
		actualTime = 00;
		minutua = 00;
		segundoa = 00;
		
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
	

}
