package model.cronometers;

import javax.swing.JLabel;

public class KronometroKontaketa extends Thread{
	
	private JLabel lblKrono;
	private JLabel seriesName;
	private JLabel seriesTime;
	private KronometroAriketak kronometroAriketa;
	private KronometroSerie kronometroSerie;

	public KronometroKontaketa(JLabel lblKrono, JLabel seriesName, JLabel seriesTime, KronometroAriketak kronometroAriketa, KronometroSerie kronometroSerie) {
		this.lblKrono = lblKrono;
		this.seriesName = seriesName;
		this.seriesTime = seriesTime;
		this.kronometroAriketa = kronometroAriketa;
		this.kronometroSerie = kronometroSerie;
		
		lblKrono.setVisible(true);
		seriesName.setVisible(false);
		seriesTime.setVisible(false);
		kronometroAriketa.stopRunning();
		kronometroSerie.stopRunning();
        start();
    }
		

	public void run() {
		try {
			for (int i = 5; i >= 0; i--) {
				
				lblKrono.setText(i + "");
				Thread.sleep(1000);
			}
			lblKrono.setVisible(false);
			seriesName.setVisible(true);
			seriesTime.setVisible(true);
			kronometroAriketa.startRunning();
			kronometroSerie.startRunning();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
}
