package effects;

import java.awt.image.BufferedImage;

import settings.SettingsManager;

public abstract class Effect {

	//Linked List
	public Effect next;
	public Effect prev;
	
	//how long the effect should appear for
	public int lifeTime;
	
	//SF
	protected double scaleFactor[];
	
	public Effect(int givenLifeTime) {
		lifeTime = givenLifeTime;

		scaleFactor = new double[2];
		
		scaleFactor[0] = SettingsManager.getResX() / 1000.0;
		scaleFactor[1] = SettingsManager.getResY() / 500.0;
	}
	
	public abstract void tick();
	public abstract BufferedImage render(BufferedImage img);
	
}
