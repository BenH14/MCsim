package effects;

import java.awt.Graphics2D;

public abstract class Effect {

	//Linked List
	public Effect next;
	
	//how long the effect should appear for
	public int lifeTime;
	
	public abstract void tick();
	public abstract Graphics2D render(Graphics2D g2d);
	
}
