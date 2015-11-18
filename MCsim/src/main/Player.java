package main;

public class Player extends Mob {

	private KeyController key;

	public Player(int spawnPosX, int spawnPosY, String givenAssetName, int givenSpeedMultipler) {
		super(spawnPosX, spawnPosY, givenAssetName, givenSpeedMultipler);
	}

	public void getInputs() {

		setAsset(key.getCurrentDirection());



	}

}
