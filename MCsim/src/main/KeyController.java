package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import settings.SettingsManager;

public class KeyController implements KeyListener{

	private boolean up;
	private int upKey;

	private boolean down;
	private int downKey;

	private boolean left;
	private int leftKey;

	private boolean right;
	private int rightKey;

	public KeyController() {

		leftKey = SettingsManager.getKeyCode("left");
		rightKey = SettingsManager.getKeyCode("right");
		downKey = SettingsManager.getKeyCode("pattack");
		upKey = SettingsManager.getKeyCode("jump");
		
		
	}

	public void keyPressed(KeyEvent arg0) {


	}


	public void keyReleased(KeyEvent arg0) {



	}


	public void keyTyped(KeyEvent arg0) {

		//not used

	}

	public DIRECTION getCurrentDirection() {

		DIRECTION RESULT = DIRECTION.STILL;



		return RESULT;
	}

}
