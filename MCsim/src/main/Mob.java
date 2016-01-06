package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import display.Animator;
import settings.SettingsManager;

public abstract class Mob {

	//LOCATION
	public int x;
	public int y;
	public DIRECTION MOB_DIRECTION;
	protected int speedMultiplier;

	//ASSETS
	protected Animator Animators[];
	protected Animator CurrentAnimator;
	protected double scaleFactor[];

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
	protected int tickCount;

	public Mob(int spawnPosX, int spawnPosY, String GivenTypeName) {

		RanGen = new Random();
		tickCount = 0;

		x = spawnPosX;

		y = spawnPosY;

		MOB_DIRECTION = DIRECTION.STILL;	

		TypeName = GivenTypeName;

		loadAssets();

		scaleFactor = new double[2];

		scaleFactor[0] = SettingsManager.getResX() / 1000.0;
		scaleFactor[1] = SettingsManager.getResY() / 500.0;

	}

	public void loadAssets() {

		Animators = new Animator[8];

		//DIRECTIONS
		//Still doesn't need an asset as the last used one will be kept
		Animators[0] = new Animator(TypeName + "/NORTH.png"); //NORTH
		Animators[1] = new Animator(TypeName + "/NORTH_EAST.png"); //NORTH_EAST
		Animators[2] = new Animator(TypeName + "/EAST.png"); //EAST
		Animators[3] = new Animator(TypeName + "/SOUTH_EAST.png"); //SOUTH_EAST
		Animators[4] = new Animator(TypeName + "/SOUTH.png"); //SOUTH
		Animators[5] = new Animator(TypeName + "/SOUTH_WEST.png"); //SOUTH_WEST
		Animators[6] = new Animator(TypeName + "/WEST.png"); //WEST
		Animators[7] = new Animator(TypeName + "/NORTH_WEST.png"); //NORTH_WEST

		//TEXT BOX
		try {
			textBoxImage = new BufferedImage(300,50, BufferedImage.TYPE_3BYTE_BGR);
			Graphics2D g2d = (Graphics2D) textBoxImage.getGraphics();
			g2d.drawImage(ImageIO.read(new File("res/textbox.png")), 0, 0, 300, 50, null);
		} catch (IOException e) {e.printStackTrace();}


	}

	public void setAsset(DIRECTION GIVEN_DIRECTION) {

		//Check to see if direction has changed
		if(GIVEN_DIRECTION != MOB_DIRECTION) {

			MOB_DIRECTION = GIVEN_DIRECTION;

			switch(MOB_DIRECTION) {
			case EAST:
				CurrentAnimator = Animators[0];
				break;
			case NORTH:
				CurrentAnimator = Animators[1];
				break;
			case NORTH_EAST:
				CurrentAnimator = Animators[2];
				break;
			case NORTH_WEST:
				CurrentAnimator = Animators[3];
				break;
			case SOUTH:
				CurrentAnimator = Animators[4];
				break;
			case SOUTH_EAST:
				CurrentAnimator = Animators[5];
				break;
			case SOUTH_WEST:
				CurrentAnimator = Animators[6];
				break;
			case WEST:
				CurrentAnimator = Animators[7];
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
		//		g2d.drawImage(CurrentAnimator.getSprite(),(int) x * scaleFactor[0] ,(int) y * scaleFactor[1] ,(int) 50 * scaleFactor[0] ,(int) 50 * scaleFactor[0], null);
		g2d.setColor(Color.WHITE);
		g2d.drawRect((int) (x * scaleFactor[0]),(int) (y * scaleFactor[1]), 20, 20);
		g2d.drawString(MOB_DIRECTION.toString(),(int) (x * scaleFactor[0]),(int) (y * scaleFactor[1]));
		g2d.setColor(Color.BLACK);
		//Draws text box if it still has a lifetime
		if(textBoxLifetime != 0) {
			g2d.setColor(Color.WHITE);
			//			g2d.fillRect((int) (x * scaleFactor[0]) +  20,(int) (y * scaleFactor[1]) + 20 ,(int) (300 * scaleFactor[0]) ,(int) (20 * scaleFactor[0]));
			g2d.drawImage(textBoxImage,(int) (x * scaleFactor[0]) +  20,(int) (y * scaleFactor[1]) - 60, null);
			g2d.setColor(Color.BLACK);
			int size = 12;
			g2d.setFont(new Font("DialogInput", Font.BOLD, size));
			while(g2d.getFontMetrics().stringWidth(textBoxString) > 300){
				g2d.setFont(new Font("DialogInput", Font.BOLD, size));
				size--;
			}
			g2d.drawString(textBoxString,(int) ((x * scaleFactor[0]) +  170) - (g2d.getFontMetrics().stringWidth(textBoxString) / 2),(int) ((y * scaleFactor[1]) - 55) + g2d.getFontMetrics().getHeight());

		}

		return g2d;

	}

	public void tick() {

		if(MOB_DIRECTION != DIRECTION.DYING) {

			getInputs();

			if(tickCount == 120) {
				tickCount = 0;
			} else if(tickCount % 20 == 0) {
				//			CurrentAnimator.nextSprite();
			}

			if(textBoxLifetime  != 0) {
				textBoxLifetime--;
			}

			//Works out where to move the mob
			if(x < 1) {
				x = 1;
			} else if(x > 980) {
				x = 979;
			} else if(y < 1) {
				y = 1;
			} else if(y > 460) {
				y = 459;
			} else {
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
					y = y + speedMultiplier;
					break;
				case SOUTH_EAST:
					x = x + speedMultiplier;
					y = y + speedMultiplier;
					break;
				case SOUTH_WEST:
					y = y + speedMultiplier;
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

			tickCount++;

		}
	}

	public void setTextBox() {

		int ranInt = RanGen.nextInt(500);

		if(textBoxLifetime < 1 && ranInt == 69) { //LE DANK MEMES XDDDDDDD

			ranInt = RanGen.nextInt(10);

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
			} else { //TODO decide on enemy text box contents
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
					textBoxString = "I am doing it right now";
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

			System.out.println(textBoxString);

		}
	}

	//This should get the direction of the mob, set the asset and set up textboxes
	public abstract void getInputs();

}
