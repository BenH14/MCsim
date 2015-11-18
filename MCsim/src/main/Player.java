package main;

public class Player extends Mob {

	private KeyController key;

	public Player(int spawnPosX, int spawnPosY) {

		super(spawnPosX, spawnPosY, "player");

		speedMultiplier = 3;

	}

	public void getInputs() {

		setAsset(key.getCurrentDirection());

		int ranInt = RanGen.nextInt(100);
		
		if(textBoxLifetime < 0 && ranInt == 69) { //LE DANK MEMES XDDDDDDD
			
			
			
		}

	}

}
