package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EffectManager {
	
	static Effect effectHead;
	
	
	public static void init() {
		
	}
	
	public static void tick() {
		
	}

	public static BufferedImage process(BufferedImage img) {
		
		Effect tempEffect = effectHead;
		
			while(tempEffect != null) {
				tempEffect.render(img);
				tempEffect = tempEffect.next;
			}
				
		return img;
	}
	
	public static void addEffect(Effect e) {
		
	}

}
