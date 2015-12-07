package effects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.Mob;
import settings.SettingsManager;

public class ProgressBar extends Effect {

	public int progress;

	private Mob Parent;
	private Mob Chaser;

	public ProgressBar(Mob givenParent, Mob givenChaser) {
		super(1);

		Parent = givenParent;
		Chaser = givenChaser;

		progress = 0;
	}

	public void tick() {

		int deltaX = Parent.x - Chaser.x;
		int deltaY = Parent.y - Chaser.y;

		double deviation = Math.sqrt(Math.pow(deltaX, 2)) + Math.sqrt(Math.pow(deltaY, 2)) + 0.0;
		
		if(deviation < 50) {
			if(progress != 100) {
				progress++;				
			} else {
				//TODO delete mob, increment score
			}
		} else {
			if(progress != 0) {
				progress--;
			} else {
				//Destroy
				lifeTime = 0;
			}
		}
	}


	public BufferedImage render(BufferedImage img) {

		Graphics2D g2d = (Graphics2D) img.getGraphics();

		g2d = SettingsManager.setRenderingHints(g2d);

		g2d.setColor(new Color(255,255,255,150)); //Translucent White
		g2d.fillRoundRect((int) ((Parent.x + 20) * scaleFactor[0]), (int) ((Parent.y - 20) * scaleFactor[1]), 120, 60, 10, 10);
		g2d.setColor(new Color(255,0,0,255)); //Red
		g2d.fillRect((int) ((Parent.x + 30) * scaleFactor[0]), (int) ((Parent.y - 35) * scaleFactor[1]), progress, 30);

		return img;
	}



}
