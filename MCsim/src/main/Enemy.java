package main;

import effects.EffectManager;
import effects.ProgressBar;
import effects.ScreenShake;

public class Enemy extends Mob{

	private Player mainPlayer;

	private int instructionLifeTime;

	private boolean pbcreated;
	private ProgressBar pb;
	private ScreenShake shake;

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
			speedMultiplier = 1;
		} else if (deviation > 200) {
			speedMultiplier = 2;
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

		if(shake != null) {
			if(shake.lifeTime == 0) {
				shake = null; 
			}
		}

		if(deviation < 50) {

			if(shake == null) {EffectManager.addEffect(shake = new ScreenShake(60, 5));}

			if(pbcreated == false) {
				pb = new ProgressBar(this, mainPlayer);
				EffectManager.addEffect(pb);
				pbcreated = true;
			} else if(pbcreated = true && pb != null) {
				if(pb.lifeTime == 0) {
					pb = null;
					pbcreated = false;
				}
			}
		}

		setTextBox();

	}

}
