package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EffectManager {

	public EffectManager() {



	}
	
	public void tick() {
		
	}

	public BufferedImage process(BufferedImage img) {
		
		Graphics2D g2d = (Graphics2D) img.getGraphics();		
				
		return img;
	}

}
