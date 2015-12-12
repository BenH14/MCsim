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

	private static int gameLength;
	public static int gameLengthVal;
	public static int captureSpeed;
	public static int spawnRate;
	public static boolean staticSpawn;
	public static boolean deadlines;


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
			resetValues();

		}

		scaleFactor = new double[2];
		scaleFactor[0] = SettingsManager.getResX() / 1000;
		scaleFactor[1] = SettingsManager.getResY() / 500;

		choice = 0;
		ticks = 0;

	}

	private static void resetValues() {

		gameLength = 1;
		captureSpeed = 1;
		spawnRate = 1;
		staticSpawn = false;
		deadlines = false;

		updateGameLength();

	}

	private static void updateGameLength() {

		switch(gameLength) {
		case 1: gameLengthVal = 40;
		break;
		case 2: gameLengthVal = 50;
		break;
		case 3: gameLengthVal = 60;
		break;
		case 4: gameLengthVal = 75;
		break;
		case 5: gameLengthVal = 90;
		break;
		}

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
		g2d.setColor(new Color(100,100,255,200));
		g2d.fillRoundRect(refLoc[0], refLoc[1], SettingsManager.getResX() - (2*refLoc[0]), SettingsManager.getResY() - (2*refLoc[1]), 20, 20);
		g2d.setColor(new Color(255, 255, 255, 150));
		g2d.fillRoundRect(refLoc[0] + 10, refLoc[1] + 10, SettingsManager.getResX() - (2*refLoc[0]) - 20, SettingsManager.getResY() - (2*refLoc[1]) - 20, 20,20);

		//Title
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 72));
		g2d.drawString("SHOP",(int) (SettingsManager.getResX() / 2) - (g2d.getFontMetrics().stringWidth("SHOP") / 2), refLoc[1] - 10);

		//Titles
		g2d.setColor(Color.BLACK);
		g2d.setFont(new Font("DialogInput", Font.BOLD, 42));
		g2d.drawString("Period Length : " + gameLengthVal + " seconds", refLoc[0] + 80,(int) scaleFactor[1] * 160);
		g2d.drawString("Enforced Deadlines :" + captureSpeed, refLoc[0] + 80,(int) scaleFactor[1] * 260);
		g2d.drawString("Class Size : " + spawnRate, refLoc[0] + 80,(int) scaleFactor[1] * 360);
		g2d.drawString("Chairs at the front : " + staticSpawn, refLoc[0] + 80,(int) scaleFactor[1] * 460);
		g2d.drawString(" : " + deadlines, refLoc[0] + 80,(int) scaleFactor[1] * 560);

		if(ticks % 60 < 45) {
			switch(choice) {
			case 0: g2d.drawString(">", refLoc[0] + 40,(int) scaleFactor[1] * 160);
			break;
			case 1: g2d.drawString(">", refLoc[0] + 40,(int) scaleFactor[1] * 260);
			break;
			case 2: g2d.drawString(">", refLoc[0] + 40,(int) scaleFactor[1] * 360);
			break;
			case 3: g2d.drawString(">", refLoc[0] + 40,(int) scaleFactor[1] * 460);
			break;
			case 4: g2d.drawString(">", refLoc[0] + 40,(int) scaleFactor[1] * 560);
			break;
			}
		}

		//Subtext
		g2d.setColor(new Color(0,0,0,100));
		g2d.setFont(new Font("DialogInput", Font.ITALIC, 28));
		g2d.drawString("Dedicate more time to coursework collection",refLoc[0] + 80,(int) scaleFactor[1] * 200);
		g2d.drawString("End of the week, monday at the latest",refLoc[0] + 80,(int) scaleFactor[1] * 300);
		g2d.drawString("Increase the rate of Students spawning",refLoc[0] + 80,(int) scaleFactor[1] * 400);
		g2d.drawString("Students spawn in a smaller space",refLoc[0] + 80,(int) scaleFactor[1] * 500);
		g2d.drawString("",refLoc[0] + 80,(int) scaleFactor[1] * 600);

		return g2d;
	}

	public static void tick(KeyController key) {



		if(key.up == true) {
			if(choice != 0) {choice--;key.up = false;}
		} else if(key.down == true) {
			if(choice != 4) {choice++;key.down = false;}
		}

		if(ticks < 600) {ticks++;} else {ticks = 0;}

	}

}
