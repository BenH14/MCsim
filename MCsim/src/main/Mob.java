package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public abstract class Mob {

	public int x;
	public int y;
	protected DIRECTION MOB_DIRECTION;
	protected BufferedImage asset;
	protected BufferedImage possibleAssets[];
	
	public Mob next;
	
	public Mob(int spawnPosX, int spawnPosY, String givenAssetName) {
	
		x = spawnPosX;
		y = spawnPosY;
		
		MOB_DIRECTION = DIRECTION.STILL;
		
		
		
	}
	
	public Graphics2D render(Graphics2D g2d) {
		
		switch(MOB_DIRECTION){
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
		
		return g2d;
		
	}
	
	public void tick() {
		
	}
	
	public abstract void getInputs();	
}
