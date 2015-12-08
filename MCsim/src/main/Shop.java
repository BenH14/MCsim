package main;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Shop {

	public static int gameLength;
	public static int captureSpeed;
	public static int gold;
	
	public static void init() {
		
		try {
		readFile();
		} catch (Exception ex) {
			System.out.println("No data for progression!");
			
			//Set default values:
			gameLength = 60;
			captureSpeed = 1;
			
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
	
}
