package effects;

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

			if(tempEffect.lifeTime == 0) { //TODO Properly Implement
				//Remove effect
				if(tempEffect.next != null && tempEffect.prev != null) {
					tempEffect.next.prev = tempEffect.prev;
					tempEffect.prev.next = tempEffect.next;
				} else if (tempEffect == effectHead) {
					effectHead = null;
				}

			}

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

		e.next = effectHead.next;
		if(e.next != null) {e.next.prev = e;}
		effectHead.next = e;
		e.prev = effectHead;

	}

}
