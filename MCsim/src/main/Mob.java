package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import settings.SettingsManager;

public abstract class Mob {

	//LOCATION
	public int x;
	public int y;
	protected DIRECTION MOB_DIRECTION;
	protected int speedMultiplier;

	//ASSETS
	protected BufferedImage Assets[];
	protected BufferedImage currentAsset;
	protected int scaleFactor[];

	//TEXT BOX
	protected BufferedImage textBoxImage;
	protected String textBoxString;
	protected int textBoxLifetime;

	//LINKED LIST
	public Mob next;
	public Mob prev;

	//IDENTIFICATION
	protected String TypeName;

	//UTIL
	protected Random RanGen;

	public Mob(int spawnPosX, int spawnPosY, String GivenTypeName) {

		RanGen = new Random();

		x = spawnPosX;
		y = spawnPosY;

		MOB_DIRECTION = DIRECTION.STILL;

		TypeName = GivenTypeName;

		loadAssets();

		scaleFactor = new int[2];

		scaleFactor[0] = 1000 / SettingsManager.getResX();
		scaleFactor[1] = 500 / SettingsManager.getResY();

	}

	public void loadAssets() {

		Assets = new BufferedImage[8];

		try {

			//DIRECTIONS
			//Still doesn't need an asset as the last used one will be kept
			Assets[0] = ImageIO.read(new File("res/" + TypeName + "/NORTH.png")); //NORTH
			Assets[1] = ImageIO.read(new File("res/" + TypeName + "/NORTH_EAST.png")); //NORTH_EAST
			Assets[2] = ImageIO.read(new File("res/" + TypeName + "/EAST.png")); //EAST
			Assets[3] = ImageIO.read(new File("res/" + TypeName + "/SOUTH_EAST.png")); //SOUTH_EAST
			Assets[4] = ImageIO.read(new File("res/" + TypeName + "/SOUTH.png")); //SOUTH
			Assets[5] = ImageIO.read(new File("res/" + TypeName + "/SOUTH_WEST.png")); //SOUTH_WEST
			Assets[6] = ImageIO.read(new File("res/" + TypeName + "/WEST.png")); //WEST
			Assets[7] = ImageIO.read(new File("res/" + TypeName + "/NORTH_WEST.png")); //NORTH_WEST

			//TEXT BOX
			textBoxImage = ImageIO.read(new File("res/textbox.png"));

		} catch (IOException ex) {
			ex.printStackTrace();
		}


	}

	public void setAsset(DIRECTION GIVEN_DIRECTION) {

		//Check to see if direction has changed
		if(GIVEN_DIRECTION != MOB_DIRECTION) {

			MOB_DIRECTION = GIVEN_DIRECTION;

			switch(MOB_DIRECTION) {
			case EAST:
				currentAsset = Assets[0];
				break;
			case NORTH:
				currentAsset = Assets[1];
				break;
			case NORTH_EAST:
				currentAsset = Assets[2];
				break;
			case NORTH_WEST:
				currentAsset = Assets[3];
				break;
			case SOUTH:
				currentAsset = Assets[4];
				break;
			case SOUTH_EAST:
				currentAsset = Assets[5];
				break;
			case SOUTH_WEST:
				currentAsset = Assets[6];
				break;
			case WEST:
				currentAsset = Assets[7];
				break;
			case STILL:
				break;
			default:
				break;

			}

		}
	}

	public Graphics2D render(Graphics2D g2d) {

		//Renders the currently selected image to the screen
		g2d.drawImage(currentAsset,(int) x * scaleFactor[0] ,(int) y * scaleFactor[1] ,(int) 50 * scaleFactor[0] ,(int) 50 * scaleFactor[0], null);

		//Draws text box if it still has a lifetime
		if(textBoxLifetime != 0){

			g2d.drawImage(textBoxImage,((int) x * scaleFactor[0]) +  20,((int) y * scaleFactor[1]) + 20 ,(int) 2000 * scaleFactor[0] ,(int) 50 * scaleFactor[0], null);

			g2d.drawString(textBoxString,((int) x * scaleFactor[0]) +  20,((int) y * scaleFactor[1]) + 20);

		}

		return g2d;

	}

	public void tick() {

		getInputs();

		if(textBoxLifetime  != 0) {
			textBoxLifetime--;
		}

		//Works out where to move the mob
		if(x > 0 && x < 1000 && y > 0 && y < 500) {
			switch(MOB_DIRECTION){
			case EAST:
				x = x + speedMultiplier;
				break;
			case NORTH:
				y = y - speedMultiplier;
				break;
			case NORTH_EAST:
				y = y - speedMultiplier;
				x = x + speedMultiplier;
				break;
			case NORTH_WEST:
				x = x - speedMultiplier;
				y = y - speedMultiplier;
				break;
			case SOUTH:
				y = y - speedMultiplier;
				break;
			case SOUTH_EAST:
				x = x + speedMultiplier;
				y = y - speedMultiplier;
				break;
			case SOUTH_WEST:
				y = y - speedMultiplier;
				x = x - speedMultiplier;
				break;
			case STILL:
				break;
			case WEST:
				x = x - speedMultiplier;
				break;
			default:
				break;

			}
		}
	}

	public void setTextBox() {

		int ranInt = RanGen.nextInt(100);

		if(textBoxLifetime < 0 && ranInt == 69) { //LE DANK MEMES XDDDDDDD

			ranInt = (int) ranInt / 10;

			textBoxLifetime = 120;


			if(TypeName == "player") {
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
					textBoxString = "We don't need a running commentary";
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
			} else {
				switch (ranInt) {
				case 0:
					textBoxString = "I just need to print it off";
					break;
				case 1:
					textBoxString = "Its on my computer at home";
					break;
				case 2:
					textBoxString = "My printer is broken";
					break;
				case 3:
					textBoxString = "The program is done, I just need to do the documentation";
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

	//This should get the direction of the mob, set the assset and set up textboxes
	public abstract void getInputs();

}
