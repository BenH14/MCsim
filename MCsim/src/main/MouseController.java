package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MouseController implements MouseListener{

	private int lastClickX;
	private int lastClickY;
	private long lastClickTime;

	public MouseController() {
		System.out.println("init");
		lastClickX = -1;
		lastClickY = -1;
	}

	public boolean check() {
		
		if(lastClickX != -1) {
			if(System.currentTimeMillis() - lastClickTime > 1000) {
				System.out.println("reset");
				lastClickX = -1;
				lastClickY = -1;
			} else {
				return true;
			}
		}

		return false;

	}

	public int getX(){return lastClickX;}
	public int getY(){return lastClickY;}

	@Override
	public void mouseClicked(MouseEvent arg0) {

		System.out.println("click");

		lastClickX = arg0.getX();
		System.out.println(lastClickX);
		lastClickY = arg0.getY();
		System.out.println(lastClickY);
		lastClickTime = System.currentTimeMillis();

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		//Ignored

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		//Ignored

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		//Ignored

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		//Ignored

	}

}
