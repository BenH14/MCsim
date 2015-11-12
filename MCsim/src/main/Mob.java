package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Mob {

	public int x;
	public int y;
	protected DIRECTION MOB_DIRECTION;
	protected BufferedImage asset;
	protected BufferedImage possibleAssets[];
	protected int speedMultiplier;

	public Mob next;

	public Mob(int spawnPosX, int spawnPosY, String givenAssetName, int givenSpeedMultipler) {

		x = spawnPosX;
		y = spawnPosY;

		MOB_DIRECTION = DIRECTION.STILL;

		speedMultiplier = givenSpeedMultipler;

	}

	public Graphics2D render(Graphics2D g2d) {

		getInputs();
		
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

		return g2d;

	}

	public void tick() {

		if(x > 0 && x < 1000 && y > 0 && y < 500) {
			switch(MOB_DIRECTION) {
			case EAST:
				break;
			case NORTH:
				break;
			case NORTH_EAST:
				break;
			case NORTH_WEST:
				break;
			case SOUTH:
				break;
			case SOUTH_EAST:
				break;
			case SOUTH_WEST:
				break;
			case STILL:
				break;
			case WEST:
				break;
			default:
				break;

			}
		}

	}

	public abstract void getInputs();	
}
