package main;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Player extends Mob {

	private KeyController key;

	public Player(int spawnPosX, int spawnPosY, String givenAssetName, int givenSpeedMultipler) {
		super(spawnPosX, spawnPosY, givenAssetName, givenSpeedMultipler);
	}

	public void getInputs() {

		DIRECTION GIVEN_DIRECTION = key.getCurrentDirection();
		
		//Check to see if direction has changed
		if(GIVEN_DIRECTION != MOB_DIRECTION) {
		
			MOB_DIRECTION = GIVEN_DIRECTION;
			
			switch(MOB_DIRECTION) {
			case EAST:
				currentAsset = Assets[0];
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

}
