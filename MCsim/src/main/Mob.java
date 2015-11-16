package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Mob {

	public int x;
	public int y;
	protected DIRECTION MOB_DIRECTION;
	protected BufferedImage Assets[];
	protected BufferedImage currentAsset;
	protected BufferedImage textBoxImage;
	protected String textBoxString;
	protected int speedMultiplier;

	public Mob next;

	public Mob(int spawnPosX, int spawnPosY, String givenAssetName, int givenSpeedMultipler) {

		x = spawnPosX;
		y = spawnPosY;

		MOB_DIRECTION = DIRECTION.STILL;

		speedMultiplier = givenSpeedMultipler;

		loadAssets();

	}

	public abstract void loadAssets();

	public Graphics2D render(Graphics2D g2d) {

		g2d.drawImage(currentAsset,x ,y ,50 ,50 ,null);
		g2d.drawImage(textBoxImage, x + 10, y - 10, 200, 50, null);

		return g2d;

	}

	public void tick() {

		getInputs();


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
