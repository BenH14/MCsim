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

	public Animator(String filePath, int givenSpriteSizeX, int givenSpriteSizeY) {

		try {SpriteSheet = ImageIO.read(new File("res/" + filePath));} catch (IOException e) {e.printStackTrace();}

		spriteSizeX = givenSpriteSizeX;
		spriteSizeY = givenSpriteSizeY;

		currentStage = 0;

		stagesPerRow = SpriteSheet.getHeight() / spriteSizeX;

		totalStages = stagesPerRow * (SpriteSheet.getWidth() / spriteSizeY);

	}

	public void nextSprite() {

		if(currentStage == totalStages) {
			currentStage = 0;
		}
		
		int y = 1; 
		int x = 0;

		while(currentStage > stagesPerRow * y) {
			y++;
		}

		y--;
		x = currentStage - (y * stagesPerRow);
		y = y * spriteSizeY;

		Output = SpriteSheet.getSubimage(x, y, spriteSizeX, spriteSizeY);

	}

	public BufferedImage getSprite() {
		return Output;
	}

}
