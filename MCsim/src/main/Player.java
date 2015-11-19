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
			
			textBoxLifetime = 120;
			
			//TODO NEED TO DECIDE ON POSSIBLE PHRASES FOR THE PLAYER
			
			switch (ranInt) {
			case 0:
				textBoxString = "Wheres your coursework?";
				break;
			case 1:
				textBoxString = "Jamie that breaks the chairs, yeah?";
				break;
			case 2:
				textBoxString = "Ben, I don't want to hear any more from you";
				break;
			case 3:
				textBoxString = "Need it by the end of the week, monday at the latest";
				break;
			case 4:
				textBoxString = "We don't need a running commentairy";
				break;
			case 5:
				textBoxString = "Lets not waste any more time, yeah?";
				break;
			case 6:
				textBoxString = "Ahad, wheres your extended essay?";
				break;
			case 7:
				textBoxString = "Correct format is 120% of the marks";
				break;
			case 8:
				textBoxString = "Hindolo, this isn't a maths lesson";
				break;
			case 9:
				textBoxString = "Just do it";
				break;
			case 10:
				textBoxString = "Google it, yeah?";
				break;
			}
			
		}
		
		
		
	}

}
