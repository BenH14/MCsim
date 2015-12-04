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
	public boolean startGame;

	private int choice;
	private KeyController key;
	
	private BufferedImage backgroundImage;

	public Menu(KeyController givenKey) {

		choice = 0;

		endGame = false;
		startGame = false;

		key = givenKey;
		
		try {
			backgroundImage = ImageIO.read(new File("res/background.png"));
			backgroundImage.setAccelerationPriority(1);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void tick() {

		if(key.up == true) {
			choice = 0;
		} else if(key.down == true) {
			choice = 1;
		}

		if(ticks < 600) {ticks++;} else {ticks = 0;}
		
		if(choice == 0) {startGame = key.enter;}
		else {endGame = key.enter;}

		
	}

	public Graphics2D render(Graphics2D g2d) {

		int titleDeviation = (int) (Math.sin(ticks / 10.0) * 10.0) - 5;
		
		g2d.drawImage(backgroundImage, 0,0,SettingsManager.getResX(),SettingsManager.getResY(), null);
		g2d.setColor(Color.BLACK);

		g2d.setFont(new Font("DialogInput", Font.BOLD, 48));
		g2d.drawString("Mr", 100 - titleDeviation, 80);
		g2d.drawString("2016", 600 + titleDeviation, 220);

		g2d.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 72));
		g2d.drawString("Collins Simulator", 60, 160 + titleDeviation);

		g2d.setFont(new Font("DialogInput", Font.BOLD, 60));

		if(choice == 0) {
			g2d.drawString("[COLLECT COURSEWORK]", 60, 500 + titleDeviation);
			g2d.drawString("HAVE DOOR FITTED", 60, 600);
		} else {
			g2d.drawString("COLLECT COURSEWORK", 60, 500);
			g2d.drawString("[HAVE DOOR FITTED]", 60, 600 + titleDeviation);	
		}
		
		return g2d;
	}

}
