package main;

public class Player extends Mob {

	private KeyController key;

	public Player(int spawnPosX, int spawnPosY) {

		super(spawnPosX, spawnPosY, "player");

		speedMultiplier = 3;

	}

	public void getInputs() {

		setAsset(key.getCurrentDirection());

		//TextBox stuff
		
		int ranInt = RanGen.nextInt(100);
		
		if(textBoxLifetime < 0 && ranInt == 69) { //LE DANK MEMES XDDDDDDD
			
			ranInt = (int) ranInt / 10;
			
			//TODO NEED TO DECIDE ON POSSIBLE PHRASES FOR THE PLAYER
			
			switch (ranInt) {
			case 0:
				textBoxString = "";
				break;
			case 1:
				textBoxString = "";
				break;
			case 2:
				textBoxString = "";
				break;
			case 3:
				textBoxString = "";
				break;
			case 4:
				textBoxString = "";
				break;
			case 5:
				textBoxString = "";
				break;
			case 6:
				textBoxString = "";
				break;
			case 7:
				textBoxString = "";
				break;
			case 8:
				textBoxString = "";
				break;
			case 9:
				textBoxString = "";
				break;
			case 10:
				textBoxString = "";
				break;
			}
			
		}
		
		
		
	}

}
