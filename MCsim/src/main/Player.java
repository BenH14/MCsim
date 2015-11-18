package main;

public class Player extends Mob {

	private KeyController key;

	public Player(int spawnPosX, int spawnPosY) {

		super(spawnPosX, spawnPosY, "player");

		speedMultiplier = 2;

	}

	public void getInputs() {

		setAsset(key.getCurrentDirection());


	}

}
