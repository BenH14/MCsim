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

		g2d.setColor(Color.black);
		g2d.drawRect(0, 0, 1000, 1500);
		
		g2d.setColor(Color.WHITE);
		g2d.setFont(new Font("DialogInput", Font.BOLD, 12));
		g2d.drawString("Mr", 60, 60);
		g2d.drawString("2016", 160, 250);

		g2d.setFont(new Font("DialogInput", Font.BOLD + Font.ITALIC, 72));
		g2d.drawString("Collins Simulator", 60,(int) 180 + ((ticks/10) - 30));



		return g2d;
	}

}
