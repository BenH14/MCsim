package display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

import main.Shop;
import main.StatisticsContainer;
import settings.SettingsManager;

public class HUD {
	
	private StatisticsContainer stats;
	
	private int timePos[];
	
	public HUD(StatisticsContainer givenStats) {
		
		stats = givenStats;
		
		timePos = new int[2];
		timePos[0] = (SettingsManager.getResX() - (SettingsManager.getResX() / 100)) - 380;
		timePos[1] = SettingsManager.getResY() / 100;
	}
	
	public Graphics2D render(Graphics2D g2d) {
		
		//Draw Time remaining
		g2d.setFont(new Font("DialogInput", Font.BOLD, 28));
		g2d.setColor(new Color(255,255,255,150));
		g2d.fillRoundRect(timePos[0],timePos[1], g2d.getFontMetrics().stringWidth("Time Remaining - 60.00") + 10, g2d.getFontMetrics().getHeight() + 10, 10, 10);
		g2d.setColor(Color.BLACK);
		g2d.drawString("Time Remaining - " + (60 - stats.gameTimeSecs), timePos[0] + 5, timePos[1] + g2d.getFontMetrics().getHeight());
		
		//Draw gold amount
		g2d.setColor(new Color(255,255,255,150));
		g2d.setFont(new Font("DialogInput", Font.BOLD, 22));
		g2d.fillRoundRect(10, timePos[1],g2d.getFontMetrics().stringWidth("Bonus - £999 ") + 10 , g2d.getFontMetrics().getHeight() + 10, 10, 10);
		g2d.setColor(Color.YELLOW);
		g2d.drawString("Bonus - £" + Shop.gold, 20, timePos[1] + g2d.getFontMetrics().getHeight());
		
		
		return g2d;
	}
	
	
}
