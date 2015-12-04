package effects;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.Random;

public class ScreenShake extends Effect{

	private Random ranGen;
	private int offset;

	public ScreenShake(int givenLifeTime) {
		super(givenLifeTime);
		
		ranGen = new Random();
	}
	
	public void tick() {
		
		if(lifeTime != 0) {
			lifeTime--;
		}
		
		offset = ranGen.nextInt(10);
		offset = offset - 5;

	}

	public BufferedImage render(BufferedImage img) {
		
		Graphics2D g2d = (Graphics2D) img.getGraphics();
		
		g2d.translate(offset,(offset - 5));
		
		g2d.drawImage(img, 0, 0, null);
		
		g2d.dispose();
		
		return img;
	}

}
