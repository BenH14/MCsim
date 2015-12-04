package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class EffectManager {
	
	static Effect effectHead;
	
	
	public static void init() {
		
		effectHead = new ScreenShake(1200);
		
	}
	
	public static void tick() {
		
		Effect tempEffect = effectHead;
		
		while(tempEffect != null) {
			tempEffect.tick();
			tempEffect = tempEffect.next;
		}
		
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
