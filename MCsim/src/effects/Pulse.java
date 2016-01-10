package effects;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import settings.SettingsManager;

public class Pulse extends Effect{

	private int period;
	private long nextBeat;
	private int offset;
	private int offsetTrend;

	public Pulse(int givenBPM) {

		super(1);
		setBPM(givenBPM);
		nextBeat = System.currentTimeMillis() + period;
		offset = 0;

	}


	public void tick() {

		if (System.currentTimeMillis() > nextBeat) {

			offsetTrend = 1;

			nextBeat = System.currentTimeMillis() + period - 20;

		} else if (offset == 12) {
			offsetTrend = -1;
		} else if (offset == 0) {
			offsetTrend = 0;
		}

		if(offsetTrend == 1) {
			offset+=4;
		} else if(offsetTrend == -1){
			offset-=4;
		}

	}


	public BufferedImage render(BufferedImage img) {

		if (offset > 0) {
			Graphics2D g2d = (Graphics2D) img.getGraphics();
			g2d.setColor(Color.MAGENTA);
			g2d.drawString(Integer.toString(period), 100, 100);
			

			g2d.drawImage(img, -offset, -offset, null);
		}
		return img;

	}

	public void setBPM(int BPM){

		period = (int) (1000.0 / (BPM / 60.0));
		

	}



}
