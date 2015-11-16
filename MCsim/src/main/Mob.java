package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import settings.SettingsManager;

public abstract class Mob {

	//LOCATION
	public int x;
	public int y;
	protected DIRECTION MOB_DIRECTION;
	protected int speedMultiplier;

	//ASSETS
	protected BufferedImage Assets[];
	protected BufferedImage currentAsset;
	protected int scaleFactor[];

	//TEXT BOX
	protected BufferedImage textBoxImage;
	protected String textBoxString;
	protected int textBoxLifetime;

	//LINKED LIST
	public Mob next;

	public Mob(int spawnPosX, int spawnPosY, String givenAssetName, int givenSpeedMultipler) {

		x = spawnPosX;
		y = spawnPosY;

		MOB_DIRECTION = DIRECTION.STILL;

		speedMultiplier = givenSpeedMultipler;

		loadAssets();

		scaleFactor = new int[2];

		scaleFactor[0] = 1000 / SettingsManager.getResX();
		scaleFactor[1] = 500 / SettingsManager.getResY();

	}

	public abstract void loadAssets();

	public Graphics2D render(Graphics2D g2d) {

		//Renders the currently selected image to the screen
		g2d.drawImage(currentAsset,(int) x * scaleFactor[0] ,(int) y * scaleFactor[1] ,(int) 50 * scaleFactor[0] ,(int) 50 * scaleFactor[0], null);

		//Draws text box if it still has a lifetime
		if(textBoxLifetime != 0){g2d.drawImage(textBoxImage,((int) x * scaleFactor[0]) +  20,((int) y * scaleFactor[1]) + 20 ,(int) 2000 * scaleFactor[0] ,(int) 50 * scaleFactor[0], null);}

		return g2d;

	}

	public void tick() {

		getInputs();

		if(textBoxLifetime  != 0) {
			textBoxLifetime--;
		}

		//Works out where to move the mob
		if(x > 0 && x < 1000 && y > 0 && y < 500) {
			switch(MOB_DIRECTION){
			case EAST:
				x = x + speedMultiplier;
				break;
			case NORTH:
				y = y - speedMultiplier;
				break;
			case NORTH_EAST:
				y = y - speedMultiplier;
				x = x + speedMultiplier;
				break;
			case NORTH_WEST:
				x = x - speedMultiplier;
				y = y - speedMultiplier;
				break;
			case SOUTH:
				y = y - speedMultiplier;
				break;
			case SOUTH_EAST:
				x = x + speedMultiplier;
				y = y - speedMultiplier;
				break;
			case SOUTH_WEST:
				y = y - speedMultiplier;
				x = x - speedMultiplier;
				break;
			case STILL:
				break;
			case WEST:
				x = x - speedMultiplier;
				break;
			default:
				break;

			}
		}
	}

	public abstract void getInputs();

}
