package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import logging.DebugFactory;
import logging.Logger;
import settings.FileLoader;
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
	private static String filePath;

	private static double scaleFactor[];

	public static void init() {

		filePath = "save.dat";
		
		try {
			readFile();
		} catch (Exception ex) {
			DebugFactory.getDebug(Logger.URGENCY.ERROR).write("No data for progression! Error - " + ex.toString());

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

		gold = 10000; DebugFactory.getDebug(Logger.URGENCY.DEBUG).write("10000 gold is still being allocated as debug, fix this in shop.resetValues()"); //TODO	

		updateGameLength();
		
		saveFile();

	}

	private static void updateGameLength() {

		gameLengthVal = 30 + (gameLength * 15);

	}

	public static void readFile() throws IOException {
		
		Properties prop = new Properties();
		InputStream in = new FileLoader().getInStream(filePath);

		prop.load(in);
		
		gold = Integer.parseInt(prop.getProperty("gold"));
		gameLength = Integer.parseInt(prop.getProperty("gameLength"));
		captureSpeed = Integer.parseInt(prop.getProperty("captureSpeed"));
		spawnRate = Integer.parseInt(prop.getProperty("spawnRate"));
		staticSpawn = Boolean.parseBoolean(prop.getProperty("staticSpawn"));
		deadlines = Boolean.parseBoolean(prop.getProperty("deadlines"));

		in.close();
		
		updateGameLength();


	}

	public static void saveFile() {

		Properties prop = new Properties();
		OutputStream out = null;
		try {
			out = new FileLoader().getOutStream(filePath);
		} catch (FileNotFoundException | URISyntaxException e1) {
			DebugFactory.getDebug(Logger.URGENCY.ERROR).write("Could not load output stream to save file for progression");
		}

		prop.setProperty("gold", Integer.toString(gold));
		prop.setProperty("gameLength", Integer.toString(gameLength));
		prop.setProperty("captureSpeed", Integer.toString(captureSpeed));
		prop.setProperty("spawnRate", Integer.toString(spawnRate));
		prop.setProperty("staticSpawn", Boolean.toString(staticSpawn));
		prop.setProperty("deadlines", Boolean.toString(deadlines));

		try {
			prop.store(out, null);
			out.close();
		} catch (IOException e) {
			DebugFactory.getDebug(Logger.URGENCY.ERROR).write("Could not store properties to save file for progression");
		}
		
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
		g2d.drawString("[500]Period Length : " + gameLengthVal + " seconds", refLoc[0] + 80,(int) scaleFactor[1] * 160);
		g2d.drawString("[500]Enforced Deadlines :" + deadlines, refLoc[0] + 80,(int) scaleFactor[1] * 260);
		g2d.drawString("[500]Class Size : " + spawnRate, refLoc[0] + 80,(int) scaleFactor[1] * 360);
		g2d.drawString("[500]Chairs at the front : " + staticSpawn, refLoc[0] + 80,(int) scaleFactor[1] * 460);
		g2d.drawString("[500]Printer Maintenance : " + captureSpeed, refLoc[0] + 80,(int) scaleFactor[1] * 560);

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
		g2d.drawString("Less time messing around, more time getting this done",refLoc[0] + 80,(int) scaleFactor[1] * 600);

		//Show gold
		g2d.setColor(Color.YELLOW);
		g2d.fillRoundRect(SettingsManager.getResX() - (refLoc[0]+300), (int) scaleFactor[1] * 140, 280, 40, 10, 10);
		g2d.setColor(Color.WHITE);
		g2d.drawString("Money: " + gold,SettingsManager.getResX() - (refLoc[0]+280) ,(int) (scaleFactor[1] * 140) + 30);

		return g2d;
	}

	public static void tick(KeyController key, MouseController mouse) {



		if(key.up == true) {
			if(choice != 0) {choice--;key.up = false;}
		} else if(key.down == true) {
			if(choice != 4) {choice++;key.down = false;}
		}

		if(key.enter && gold >= 500) {
			gold = gold - 500;
			key.enter = false;
			switch(choice) {
			case 0: gameLength++; updateGameLength();
			break;
			case 1: deadlines = true;
			break;
			case 2: spawnRate++;
			break;
			case 3: staticSpawn = true;
			break;
			case 4: if(captureSpeed < 50) {captureSpeed++;}
			break;
			}
			
			saveFile();
			
		}
		
		if(mouse.check() == true) {
			gold--;
		}

		if(ticks < 600) {ticks++;} else {ticks = 0;}

	}

}
