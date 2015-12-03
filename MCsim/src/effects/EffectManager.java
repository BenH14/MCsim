package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EffectManager {
	
	Effect effectHead;
	
	public static void init() {
		
	}
	
	public static void tick() {
		
	}

	public static BufferedImage process(BufferedImage img) {
		
		Graphics2D g2d = (Graphics2D) img.getGraphics();		
				
		return img;
	}
	
	public static void addEffect(Effect e) {
		
	}

}
