package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import settings.SettingsManager;

public class KeyController implements KeyListener{

	private boolean up;
	private final int upKey;

	private boolean down;
	private final int downKey;

	private boolean left;
	private final int leftKey;

	private boolean right;
	private final int rightKey;

	public KeyController() {

		leftKey = SettingsManager.getKeyCode("left");
		rightKey = SettingsManager.getKeyCode("right");
		downKey = SettingsManager.getKeyCode("pattack");
		upKey = SettingsManager.getKeyCode("jump");


	}

	public void keyPressed(KeyEvent arg0) {

		int keyCode = arg0.getKeyCode();

		if(keyCode == leftKey) { left = true;
		} else if(keyCode == rightKey) {right = true;
		} else if(keyCode == upKey) {up = true;
		} else if(keyCode == downKey) {down = true;
		}

	}


	public void keyReleased(KeyEvent arg0) {

		int keyCode = arg0.getKeyCode();

		if(keyCode == leftKey) { left = false;
		} else if(keyCode == rightKey) {right = false;
		} else if(keyCode == upKey) {up = false;
		} else if(keyCode == downKey) {down = false;
		}

	}


	public void keyTyped(KeyEvent arg0) {

		//not used

	}

	public DIRECTION getCurrentDirection() {

		DIRECTION RESULT = DIRECTION.STILL;

		if(up == true) {
			if(right == true) {
				RESULT = DIRECTION.NORTH_WEST;
			} else if(left == true) {
				RESULT = DIRECTION.NORTH_EAST;
			} else {
				RESULT = DIRECTION.NORTH;
			}
		} else if(down == true) {
			if(right == true) {
				RESULT = DIRECTION.SOUTH_WEST;
			} else if(left == true) {
				RESULT = DIRECTION.SOUTH_EAST;
			} else {
				RESULT = DIRECTION.SOUTH;
			}
		}


		return RESULT;
	}

}
