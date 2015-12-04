package effects;

import java.awt.image.BufferedImage;

public class EffectManager {

	static Effect effectHead;


	public static void init() {

	}

	public static void tick() {

		Effect tempEffect = effectHead;

		while(tempEffect != null) {
			tempEffect.tick();

			if(tempEffect.lifeTime == 0) {
				//Remove effect
				if(tempEffect.next == null && tempEffect.prev == null) {
					//If node is first in linked list
					effectHead = null;
				} else {
					if(tempEffect.prev != null) {
						tempEffect.prev.next = tempEffect.next;
					}
					if(tempEffect.next != null) {
						tempEffect.next.prev = tempEffect.prev;
					}
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
