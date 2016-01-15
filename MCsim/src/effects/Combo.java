package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import display.Animator;
import settings.SettingsManager;

public class Combo extends Effect {

	private Animator mainAnim;
	private Animator numAnim;

	private int tickCount;

	private static int killCount;
	private static int timeout;

	public Combo() {
		super(1);
		mainAnim = new Animator("ANIM_COMBO.png", 200, 60);
		tickCount = 0;
		mainAnim.nextSprite();
	}


	public void tick() {

		if(tickCount == 100) {
			tickCount = 0;
		} else {
			tickCount++;
		}

		if(tickCount % 10 == 0) {
			mainAnim.nextSprite();
		}

		if(timeout == 0){
			killCount = 0;
		} else {
			timeout--;
		}

	}


	public BufferedImage render(BufferedImage img) {

		if(timeout != 0 && killCount > 2) {
			Graphics2D g2d = (Graphics2D) img.getGraphics();

			g2d.drawImage(mainAnim.getSprite(),(int) ((SettingsManager.getResX() / 2) - 100), (int) (timeout/2.0) - 60,null);

			g2d.dispose();
		}
		return img;

	}

	public static void addKill() {
		killCount++;
		timeout = 240;
	}
	
	public static int getCombo() {
		return killCount;
	}

}
