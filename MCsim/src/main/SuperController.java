package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import display.Window;
import settings.SettingsManager;

public class SuperController {

	private boolean endGame;
	private int renderSleepTime;

	private Window mainWindow; 

	private Mob mobHead;

	public SuperController() {

		SettingsManager.init();

		mainWindow = new Window("PolyScript Game");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(SettingsManager.getResX(), SettingsManager.getResY());
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setUndecorated(true);

		mainWindow.setVisible(true);

		endGame = false;

	}

	public void loop() {

		Thread renderThread = new Thread(renderLoop);
		Thread.currentThread().setPriority(10);
		renderThread.setPriority(9);
		renderThread.start();		

		while(endGame == false) {
			//Main Update Loop
			double tickStartTime = System.currentTimeMillis();

			//Tick all
			Mob tempMob = mobHead;
			while(tempMob != null) {
				tempMob.tick();
				tempMob = tempMob.next;
			}


			double sleepTime = (1000/60) - (System.currentTimeMillis() - tickStartTime);


			//			System.out.println("TPS = " + (1/(sleepTime / 1000)));

			try {
				if(sleepTime > 0 ) {
					Thread.sleep((int) (sleepTime));
				} else {
					renderSleepTime = (int) (sleepTime * -1);
				}
			} catch (InterruptedException e) {e.printStackTrace();}

		}

		try {
			renderThread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		//EXIT GAME
		System.out.println("Goodbye!");
		System.exit(0);

	}

	Runnable renderLoop = new Runnable() {
		public void run() {
			while(endGame == false) {				

				//An image that everything is drawn onto before being drawn onto the actual frame
				BufferedImage stagingImage = new BufferedImage(SettingsManager.getResX(),SettingsManager.getResY(), BufferedImage.TYPE_3BYTE_BGR);
				Graphics2D g2d = (Graphics2D) stagingImage.getGraphics();

				//Set rendering hints
				g2d = SettingsManager.setRenderingHints(g2d);

				//Render Everything


				//Draw the actual image onto the frame
				g2d = (Graphics2D) mainWindow.getGraphics();
				g2d.drawImage(stagingImage, 0 , 0 , null );	


				g2d.dispose();

				if(renderSleepTime > 0) {
					try {Thread.sleep(renderSleepTime);}
					catch (InterruptedException e) {e.printStackTrace();}
				}


			}
		}
	};
}

