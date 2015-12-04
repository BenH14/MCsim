package effects;

import java.awt.image.BufferedImage;

public abstract class Effect {

	//Linked List
	public Effect next;
	
	//how long the effect should appear for
	public int lifeTime;
	
	public Effect(int givenLifeTime) {
		lifeTime = givenLifeTime;
	}
	
	public abstract void tick();
	public abstract BufferedImage render(BufferedImage img);
	
}
