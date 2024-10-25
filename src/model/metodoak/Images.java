package model.metodoak;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Base64;

import javax.swing.ImageIcon;

public class Images {
	
	public String encode(File file) {
		
		try {
			FileInputStream fp = new FileInputStream(file);
			byte[] data = fp.readAllBytes();
			String ret = Base64.getEncoder().encodeToString(data);
			fp.close();
			return new String(ret);
		} catch (Exception e) {
			System.out.println("File error");
			e.printStackTrace();
		}
		
	
		return null;
		
	}
	
	public ImageIcon decode(String image) {

		byte[] imageBytes = Base64.getDecoder().decode(image);

		return new ImageIcon(imageBytes);

	}
}
