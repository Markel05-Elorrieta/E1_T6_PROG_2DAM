package view;

import javax.swing.*;
import java.awt.*;

public class BackgroundImageView extends JPanel {
	private static final long serialVersionUID = 1L;
	private Image imagen;

    public BackgroundImageView() {
        // Carga la imagen desde la ruta del recurso
        imagen = new ImageIcon(getClass().getResource("/resources/duytu2.jpg")).getImage();
    }

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
    }
}