package model.cronometers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;

import view.AriketakView;

public class KronometroSerie extends Thread {
	
	private int minutua = 0;
	private int segundoa = 0;
	private boolean stop = false;
	private int posSerie = 0;
	
	private boolean isRunning = true;
	private AriketakView frame;
	
	public KronometroSerie(AriketakView frame ) {
		this.frame = frame;
        frame.lblSerieName.setText(frame.ariketaActual.getSeries().get(posSerie).getIzena());
        frame.lblSerieRepes.setText(String.valueOf(frame.ariketaActual.getSeries().get(posSerie).getRepetizioak()));
		frame.btnHurrengoSerie.addActionListener(new ActionListener() {
	        public void actionPerformed(ActionEvent e) {
	            if (posSerie < frame.ariketaActual.getSeries().size()) {
	            	System.out.println("hola");
	                posSerie++;
	                frame.lblSerieName.setText(frame.ariketaActual.getSeries().get(posSerie).getIzena());
	                frame.lblSerieRepes.setText(String.valueOf(frame.ariketaActual.getSeries().get(posSerie).getRepetizioak()));
	                resetCrono();
	            } else {
	                frame.lblSerieName.setText("AMAITUTA");
	                frame.lblSerieRepes.setText("AMAITUTA");
	                stopCrono();
	            }
	        }
	    });

	}
	

	public void run() {
		try {
			while (stop == false) {
				Thread.sleep(1000);
				if (isRunning) {
					segundoa++;
					if (segundoa == 60) {
						minutua++;
						segundoa = 0;
					}

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

		minutua = 0;
		segundoa = 0;
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
