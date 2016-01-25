package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import display.Animator;
import main.SoundManager;
import settings.SettingsManager;

public class Combo extends Effect {

	private Animator mainAnim;
	private Animator numAnim;

	private int tickCount;

	private static int killCount;
	private static int timeout;

	private static Clip comboSound;
	private static SoundManager sm;

	public Combo(SoundManager givenSM) {
		super(1);
		
		sm = givenSM;
		
		mainAnim = new Animator("ANIM_COMBO.png", 200, 60);
		numAnim = new Animator("ANIM_COMBO_NUM.png", 70, 60);
		tickCount = 0;
		mainAnim.nextSprite();

		try {
			AudioInputStream in = AudioSystem.getAudioInputStream(new File("res/sound/EFFECT_COMBO.wav"));
			comboSound = AudioSystem.getClip();
			comboSound.open(in);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
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
		
		if(killCount > 2) {
			if(killCount < 7) {
			numAnim.setStage(killCount - 2);
			} else {
				numAnim.setStage(5);
			}
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
			g2d.drawImage(numAnim.getSprite(),(int) ((SettingsManager.getResX() / 2) + 100), (int) (timeout/2.0) - 60,null);

			g2d.dispose();
		}
		return img;

	}

	public static void addKill() {
		killCount++;
		timeout = 240;
		
		if(killCount > 3) {
			System.out.println("another one");
			sm.playSound(comboSound);
		}
	}

	public static int getCombo() {
		return killCount;
	}

}
