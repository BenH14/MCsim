package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import settings.SettingsManager;

public class Shop {

	public static int gameLength;
	public static int captureSpeed;
	public static int gold;

	private static int choice;
	private static int ticks;

	private static double scaleFactor[];

	public static void init() {

		try {
			readFile();
		} catch (Exception ex) {
			System.out.println("No data for progression!");

			//Set default values:
			gameLength = 60;
			captureSpeed = 1;

		}

		scaleFactor = new double[2];
		scaleFactor[0] = SettingsManager.getResX() / 1000;
		scaleFactor[1] = SettingsManager.getResY() / 500;

		choice = 0;
		ticks = 0;

	}

	public static void readFile() throws IOException {

		BufferedImage abc = ImageIO.read(new File("res/packground.png"));

	}

	public static void saveFile() {

	}

	public static void addGold(int amount) {
		gold = gold + amount;
	}

	public static Graphics2D render(Graphics2D g2d) {
		
		int refLoc[] = new int[2];
		refLoc[0] = (int) (100 * scaleFactor[0]);
		refLoc[1] = (int) ((100 * scaleFactor[1]) + ((Math.sin(ticks / 10.0) * 10.0) - 5));

		//Box
		g2d.setColor(Color.BLUE);
		g2d.fillRoundRect(refLoc[0], refLoc[1], SettingsManager.getResX() - (2*refLoc[0]), SettingsManager.getResY() - (2*refLoc[1]), 20, 20);
		g2d.setColor(Color.WHITE);
		g2d.fillRoundRect(refLoc[0] + 10, refLoc[1] + 10, SettingsManager.getResX() - (2*refLoc[0]) - 20, SettingsManager.getResY() - (2*refLoc[1]) - 20, 20,20);

		//Title
		g2d.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 72));
		g2d.drawString("SHOP",(int) (SettingsManager.getResX() / 2) - (g2d.getFontMetrics().stringWidth("SHOP") / 2), refLoc[1] - 10);
		return g2d;
	}

	public static void tick(KeyController key) {



		if(key.up == true) {
			if(choice != 0) {choice--;key.up = false;}
		} else if(key.down == true) {
			if(choice != 2) {choice++;key.down = false;}
		}

		if(ticks < 600) {ticks++;} else {ticks = 0;}

	}

}
