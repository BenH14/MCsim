package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import settings.SettingsManager;

public class Menu {

	private int ticks; //Should be incremented up to 600 (10 secs) then reset to 0

	public boolean endGame;
	public boolean goToShop;
	public boolean startGame;

	private int choice;
	private KeyController key;

	private BufferedImage backgroundImage;
	private BufferedImage preBake;

	public Menu(KeyController givenKey) {

		choice = 0;

		endGame = false;
		startGame = false;

		key = givenKey;

		try {
			//Load the image and resize it to the correct size
			backgroundImage = ImageIO.read(new File("res/background.png"));
			backgroundImage.setAccelerationPriority(1);
			Graphics2D tempResizeG2d = (Graphics2D) backgroundImage.getGraphics();
			tempResizeG2d.drawImage(backgroundImage, 0, 0, SettingsManager.getResX(), SettingsManager.getResY(), null);
		} catch (IOException e) {
			e.printStackTrace();
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

	public void tick() {

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
				g2d.drawString("[HAVE DOOR FITTED]", 60, 650);
			}

		}

		return g2d;
	}

}
