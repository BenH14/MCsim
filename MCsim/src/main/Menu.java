package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import settings.SettingsManager;

public class Menu {

	private int ticks; //Should be incremented up to 600 (10 secs) then reset to 0

	public boolean endGame;
	public boolean goToShop;
	public boolean startGame;

	private int choice;
	private KeyController key;
	private MouseController mouse;

	private BufferedImage backgroundImage;
	private BufferedImage preBake;

	private SoundManager sound;
	private Clip menuSound;

	public Menu(KeyController givenKey, MouseController givenMouse) {

		choice = 0;

		endGame = false;
		startGame = false;

		key = givenKey;
		mouse = givenMouse;

		try {
			//Load the image and resize it to the correct size
			backgroundImage = ImageIO.read(this.getClass().getClassLoader().getResource("res/background.png"));
			backgroundImage.setAccelerationPriority(1);
			Graphics2D tempResizeG2d = (Graphics2D) backgroundImage.getGraphics();
			tempResizeG2d.drawImage(backgroundImage, 0, 0, SettingsManager.getResX(), SettingsManager.getResY(), null);
		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			AudioInputStream in = AudioSystem.getAudioInputStream(this.getClass().getClassLoader().getResource("res/sound/EFFECT_MENU_HIGH.wav"));
			menuSound = AudioSystem.getClip();
			menuSound.open(in);
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	public void preBakeBackground() {

		preBake = new BufferedImage(SettingsManager.getResX(), SettingsManager.getResY(), BufferedImage.TYPE_INT_ARGB);

		Graphics2D g2d = (Graphics2D) preBake.getGraphics();

		int titleDeviation = (int) (Math.sin(ticks / 10.0) * 10.0) - 5;

		g2d.drawImage(backgroundImage, 0,0, null);
		g2d.setColor(Color.BLACK);

		g2d.setFont(new Font("DialogInput", Font.BOLD, 48));
		g2d.drawString("Mr", 100 - titleDeviation, 80);
		g2d.drawString("2016", 600 + titleDeviation, 220);

		g2d.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 72));
		g2d.drawString("Collins Simulator", 60, 160 + titleDeviation);

		g2d.setFont(new Font("DialogInput", Font.BOLD, 60));

		if(choice == 0) {
			g2d.drawString("[COLLECT COURSEWORK]", 60, 450 + titleDeviation);
			g2d.drawString("TECHNICIANS", 60, 550);
			g2d.drawString("HAVE DOOR FITTED", 60, 650);
		} else if(choice == 1) {
			g2d.drawString("COLLECT COURSEWORK", 60, 450);
			g2d.drawString("[TECHNICIANS]", 60, 550 + titleDeviation);
			g2d.drawString("HAVE DOOR FITTED", 60, 650);	
		} else if(choice == 2) {
			g2d.drawString("COLLECT COURSEWORK", 60, 450);
			g2d.drawString("TECHNICIANS", 60, 550);
			g2d.drawString("[HAVE DOOR FITTED]", 60, 650);
		}

		g2d.dispose();

	}

	public void addSound(SoundManager given) {sound = given;}

	public void tick() {

		if(key.up == true || key.down == true) {
			sound.playSound(menuSound);
		}

		if(goToShop) {
			Shop.tick(key);
			goToShop = !key.exit;
		} else {
			if(key.up == true) {
				if(choice != 0) {choice--;key.up = false;}
			} else if(key.down == true) {
				if(choice != 2) {choice++;key.down = false;}
			}

			if(ticks < 600) {ticks++;} else {ticks = 0;}

			if(choice == 0) {startGame = key.enter;}
			else if(choice == 1) {goToShop = key.enter;}
			else {endGame = key.enter;}

			key.enter = false;
		}
		
		if(mouse.check() == true) {
			if(mouse.getX() > 60 && mouse.getX() < 550) {
				System.out.println(mouse.getY());
				if(mouse.getY() > 350 && mouse.getY() < 450) {
					startGame = true;
				} else if (mouse.getY() > 450 && mouse.getY() < 550) {
					goToShop = true;
				} else if (mouse.getY() > 550 && mouse.getY() < 650) {
					endGame = true;
				}
			}
		}

	}

	public Graphics2D render(Graphics2D g2d) {

		if(goToShop) {
			if (preBake == null || ticks % 30 == 0) {
				preBakeBackground();
			}
			g2d.drawImage(preBake, 0, 0, null);
			g2d = Shop.render(g2d);

		} else {

			int titleDeviation = (int) (Math.sin(ticks / 10.0) * 10.0) - 5;

			g2d.drawImage(backgroundImage, 0,0, null);
			g2d.setColor(Color.BLACK);

			g2d.setFont(new Font("DialogInput", Font.BOLD, 48));
			g2d.drawString("Mr", 100 - titleDeviation, 80);
			g2d.drawString("2016", 600 + titleDeviation, 220);

			g2d.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 72));
			g2d.drawString("Collins Simulator", 60, 160 + titleDeviation);

			g2d.setFont(new Font("DialogInput", Font.BOLD, 60));

			if(choice == 0) {
				g2d.drawString("[COLLECT COURSEWORK]", 60, 450 + titleDeviation);
				g2d.drawString("TECHNICIANS", 60, 550);
				g2d.drawString("HAVE DOOR FITTED", 60, 650);
			} else if(choice == 1) {
				g2d.drawString("COLLECT COURSEWORK", 60, 450);
				g2d.drawString("[TECHNICIANS]", 60, 550 + titleDeviation);
				g2d.drawString("HAVE DOOR FITTED", 60, 650);	
			} else if(choice == 2) {
				g2d.drawString("COLLECT COURSEWORK", 60, 450);
				g2d.drawString("TECHNICIANS", 60, 550);
				g2d.drawString("[HAVE DOOR FITTED]", 60, 650 + titleDeviation);
			}

		}

		return g2d;
	}

}
