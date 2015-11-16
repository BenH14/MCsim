package main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Mob {

	public Player(int spawnPosX, int spawnPosY, String givenAssetName, int givenSpeedMultipler) {
		super(spawnPosX, spawnPosY, givenAssetName, givenSpeedMultipler);
	}

	public void loadAssets() {
	
		try {
			//Still doesn't need an asset as the last used one will be kept
			Assets[1] = ImageIO.read(new File("res/player/autumn.png")); //NORTH
			Assets[2] = ImageIO.read(new File("res/player/autumn.png")); //NORTH_EAST
			Assets[3] = ImageIO.read(new File("res/player/autumn.png")); //EAST
			Assets[4] = ImageIO.read(new File("res/player/autumn.png")); //SOUTH_EAST
			Assets[5] = ImageIO.read(new File("res/player/autumn.png")); //SOUTH
			Assets[6] = ImageIO.read(new File("res/player/autumn.png")); //SOUTH_WEST
			Assets[7] = ImageIO.read(new File("res/player/autumn.png")); //WEST
			Assets[8] = ImageIO.read(new File("res/player/autumn.png")); //NORTH_WEST
		} catch (IOException ex) {
			ex.printStackTrace();
		}
		
	}
	
	public void getInputs() {
	
		
	}

}
