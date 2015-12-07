package main;

public class Player extends Mob {

	private KeyController key;

	public Player(int spawnPosX, int spawnPosY, KeyController givenKey) {

		super(spawnPosX, spawnPosY, "player");

		speedMultiplier = 4;

		key = givenKey;
		
	}

	public void getInputs() {

		setAsset(key.getCurrentDirection());

		//TextBox stuff
		setTextBox();

		
		
	}



}

