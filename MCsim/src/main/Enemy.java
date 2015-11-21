package main;

public class Enemy extends Mob{

	private Player mainPlayer;

	private int instructionLifeTime;

	public Enemy(int spawnPosX, int spawnPosY, Player givenPlayer) {

		super(spawnPosX, spawnPosY, "enemy");

		speedMultiplier = 2;

		mainPlayer = givenPlayer;

		instructionLifeTime = 0;

	}

	public void getInputs() {

		int deltaX = x - mainPlayer.x;
		int deltaY = y - mainPlayer.y;


		if(deltaX > -10 && deltaX < 10 && deltaY > -10 && deltaX < 10) {//If the enemy is within 10 units of the mob, it should destroy itself;
			if(next != null) {next.prev = prev;}
			prev.next = next;
		} else if (instructionLifeTime == 0){//Work out direction if an instruction has not been issued recently;

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

		}
		
		setTextBox();

	}

}
