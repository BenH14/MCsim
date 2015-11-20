package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class Menu {

	private int ticks; //Should be incremented up to 600 (10 secs) then reset to 0

	public boolean endGame;
	public boolean startGame;

	public int choice;

	public Menu() {

		choice = 0;

		endGame = false;
		startGame = false;

	}

	public void tick() {




		if(ticks < 600) {ticks++;} else {ticks = 0;}

	}

	public Graphics2D render(Graphics2D g2d) {

		int titleDeviation = (int) (Math.sin(ticks / 10) * 10) - 5;

		g2d.setColor(Color.WHITE);

		g2d.setFont(new Font("DialogInput", Font.BOLD, 48));
		g2d.drawString("Mr", 100 - titleDeviation, 80);
		g2d.drawString("2016", 600 + titleDeviation, 220);

		g2d.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 72));
		g2d.drawString("Collins Simulator", 60, 160 + titleDeviation);

		g2d.setFont(new Font("DialogInput", Font.BOLD, 60));
		g2d.drawString("COLLECT COURSEWORK", 60, 500 + titleDeviation);
		g2d.drawString("HAVE DOOR FITTED", 60, 600 + titleDeviation);
		
		

		return g2d;
	}

}
