package main;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import display.Window;
import settings.SettingsManager;

public class SuperController {

	private boolean pause;
	private boolean exit;
	private int renderSleepTime;

	private Window mainWindow; 
	private Menu mainMenu;

	private Mob mobHead;

	private KeyController mainKey;

	public SuperController() {

		SettingsManager.init();

		mainWindow = new Window("MCSim 2016");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(SettingsManager.getResX(), SettingsManager.getResY());
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setUndecorated(true);

		mainWindow.setVisible(true);

		pause = true;
		exit = false;

		mainKey = new KeyController();
		mainWindow.addKeyListener(mainKey);

		mainMenu = new Menu(mainKey);

	}

	public void loop() {

		Thread renderThread = new Thread(renderLoop);
		Thread.currentThread().setPriority(10);
		renderThread.setPriority(9);
		renderThread.start();		

		while(exit == false) {

			//Main Update Loop
			double tickStartTime = System.currentTimeMillis();

			if(pause == false) {

				//Tick all
				Mob tempMob = mobHead;
				while(tempMob != null) {
					tempMob.tick();
					tempMob = tempMob.next;
				}

			} else {

				mainMenu.tick();

				exit = mainMenu.endGame;
				pause = !mainMenu.startGame;
				
				if(pause == false) {
					mobHead = new Player(100,100, mainKey);
				}

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
			while(exit == false) {

				//An image that everything is drawn onto before being drawn onto the actual frame
				BufferedImage stagingImage = new BufferedImage(SettingsManager.getResX(),SettingsManager.getResY(), BufferedImage.TYPE_3BYTE_BGR);
				Graphics2D g2d = (Graphics2D) stagingImage.getGraphics();

				//Set rendering hints
				g2d = SettingsManager.setRenderingHints(g2d);

				if(pause == false) {
					//Render Game
					
					
					
					Mob tempMob = mobHead;
					while(tempMob != null) {
						g2d = tempMob.render(g2d);
						tempMob = tempMob.next;
					}
					
				} else {
					g2d = mainMenu.render(g2d);
				}

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

