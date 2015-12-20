package effects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import main.DIRECTION;
import main.Mob;
import main.Shop;
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
				progress = progress + Shop.captureSpeed;				
			} else {
				lifeTime = 0;
				Parent.MOB_DIRECTION = DIRECTION.DYING;
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

		g2d.setColor(new Color(255,255,255,55 + (progress * 2))); //Translucent White

		int xPos = (int) ((Parent.x + 20) * scaleFactor[0]);
		int yPos = (int) ((Parent.y - 20) * scaleFactor[1]);

		if((xPos + 230) > SettingsManager.getResX()) {
			xPos = SettingsManager.getResX() - 230;
		}
		
		if(yPos - 30 < 0) {
			yPos = 30;
		}
		g2d.fillRoundRect(xPos,yPos, 220, 20, 10, 10);
		g2d.setColor(new Color(255,0,0)); //Red
		g2d.fillRect(xPos + 10,yPos + 5, progress * 2, 10);

		g2d.dispose();

		return img;
	}



}
