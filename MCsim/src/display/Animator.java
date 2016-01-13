package display;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Animator {

	private BufferedImage SpriteSheet;
	private BufferedImage Output;
	private int spriteSizeX;
	private int spriteSizeY;

	private int currentStage;
	private int totalStages;
	private int stagesPerRow;

	public Animator(String filePath) {
		this(filePath, 100,100); //If no sprite sizes are given, use 100x100
	}
	
	public Animator(String filePath, int givenSpriteSizeX, int givenSpriteSizeY) {

		try {SpriteSheet = ImageIO.read(new File("res/" + filePath));
		spriteSizeX = givenSpriteSizeX;
		spriteSizeY = givenSpriteSizeY;

		currentStage = 0;

		stagesPerRow = (int) ((SpriteSheet.getWidth() * 1.0) / (spriteSizeX * 1.0));
		
		totalStages = (int) (stagesPerRow * (SpriteSheet.getHeight() / (spriteSizeY * 1.0)));
		
		
		} catch (Exception e) {e.printStackTrace();}


	}

	public void nextSprite() {

		if(currentStage == totalStages) {
			currentStage = 1;
		} else {
			currentStage++;
		}
		
		
		
		int y = 1; 
		int x = 0;

		System.out.println(currentStage);
		System.out.println(stagesPerRow);
		while(currentStage > stagesPerRow * y) {
			y++;
		}
		
		y--;
		currentStage--;
		x = (currentStage * spriteSizeX) - (y * stagesPerRow);
		currentStage++;
		y = y * spriteSizeY;
		
		System.out.println("x " + x);
		System.out.println("y " + y);
		
		Output = SpriteSheet.getSubimage(x, y, spriteSizeX, spriteSizeY);

	}

	public BufferedImage getSprite() {
		return Output;
	}

}
