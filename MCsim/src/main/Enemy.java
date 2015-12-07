package main;

import effects.EffectManager;
import effects.ProgressBar;

public class Enemy extends Mob{

	private Player mainPlayer;

	private int instructionLifeTime;
	
	private boolean pbcreated;

	public Enemy(int spawnPosX, int spawnPosY, Player givenPlayer) {

		super(spawnPosX, spawnPosY, "enemy");

		speedMultiplier = 2;

		mainPlayer = givenPlayer;

		instructionLifeTime = 0;
		
		pbcreated = false;

	}

	public void getInputs() {

		int deltaX = x - mainPlayer.x;
		int deltaY = y - mainPlayer.y;

		double deviation = Math.sqrt(Math.pow(deltaX, 2)) + Math.sqrt(Math.pow(deltaY, 2)) + 0.0;
		
		if (deviation > 300) {
			speedMultiplier = 2;
		} else if (deviation > 200) {
			speedMultiplier = 3;
		} else if (deviation > 100) {
			speedMultiplier = 4;
		} 

		if (instructionLifeTime == 0 || deviation < 50){//Work out direction if an instruction has not been issued recently or the player is very near;

			if(deltaY < 0) {
				if(deltaX > 100) {
					MOB_DIRECTION = DIRECTION.NORTH_EAST;
				}else if(deltaX < -100) {
					MOB_DIRECTION = DIRECTION.NORTH_WEST;
				} else {
					MOB_DIRECTION = DIRECTION.NORTH;
				}
			} else if(deltaY > 0) {
				if(deltaX > 100) {
					MOB_DIRECTION = DIRECTION.SOUTH_EAST;
				} else if(deltaX < -100) {
					MOB_DIRECTION = DIRECTION.SOUTH_WEST;
				} else {
					MOB_DIRECTION = DIRECTION.SOUTH;
				}
			}

			instructionLifeTime = 60;

		} else {

			instructionLifeTime--;

			if (x < 10) {
				MOB_DIRECTION = DIRECTION.EAST;
				instructionLifeTime = 60;
			} else if(x > 970) {
				MOB_DIRECTION = DIRECTION.WEST;
				instructionLifeTime = 60;
			} else if(y < 10) {
				MOB_DIRECTION = DIRECTION.SOUTH;
				instructionLifeTime = 60;
			} else if(y > 460) {
				MOB_DIRECTION = DIRECTION.NORTH;
				instructionLifeTime = 60;
			}


		}
		
		if(deviation < 50 && pbcreated == false) {
			EffectManager.addEffect(new ProgressBar(this, mainPlayer));
		} else if(pbcreated = true) {
			pbcreated = false;
		}

		setTextBox();

	}

}
