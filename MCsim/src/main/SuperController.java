package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JFrame;

import display.HUD;
import display.Window;
import effects.EffectManager;
import settings.SettingsManager;

public class SuperController {

	private boolean pause;
	private boolean exit;
	private int renderSleepTime;

	private Window mainWindow;
	private BufferStrategy BuffStrat;
	private Menu mainMenu;

	private Mob mobHead;

	private StatisticsContainer stats;

	private HUD ui;

	private KeyController mainKey;

	private Random ranGen;

	private int FPS;
	private double TPS;

	public SuperController() {

		SettingsManager.init();

		ranGen = new Random();

		mainWindow = new Window("MCSim 2016");
		mainWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		mainWindow.setSize(SettingsManager.getResX(), SettingsManager.getResY());
		mainWindow.setLocationRelativeTo(null);
		mainWindow.setUndecorated(true);

		mainWindow.setVisible(true);

		mainWindow.createBufferStrategy(2);
		BuffStrat = mainWindow.getBufferStrategy();

		pause = true;
		exit = false;

		mainKey = new KeyController();
		mainWindow.addKeyListener(mainKey);

		mainMenu = new Menu(mainKey);

		EffectManager.init();
		Shop.init();

	}

	private void doSpawning(boolean force) {

		int ranInt = ranGen.nextInt(1000);

		if((ranInt % 250) == 0 || force) {

			//Add new enemy
			if(mobHead.next != null) {

				//Create Mob
				Mob temp = mobHead.next;
				mobHead.next = new Enemy(ranInt, ranGen.nextInt(500), (Player) (mobHead));
				mobHead.next.prev = mobHead;
				mobHead.next.next = temp;
				temp.prev = mobHead.next;

				mobHead.next.MOB_DIRECTION = DIRECTION.SPAWNING;

			} else {

				mobHead.next = new Enemy(ranInt, ranGen.nextInt(500), (Player) (mobHead));
				mobHead.next.prev = mobHead;

			}
		}

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


				if(mainKey.exit == true){
					pause = true;
					mainMenu.startGame = false;
				}

				//Tick all
				Mob tempMob = mobHead;
				while(tempMob != null) {
					tempMob.tick();

					if(tempMob.MOB_DIRECTION == DIRECTION.DYING) {
						Shop.addGold(50);
						tempMob.prev.next = tempMob.next;
						if(tempMob.next != null) {
							tempMob.next.prev = tempMob.prev;
						}
					}

					tempMob = tempMob.next;
				}

				stats.tick();
				EffectManager.tick();

				doSpawning(false);
				
				if ((Shop.gameLength - stats.gameTimeSecs) < 0) {
					//End Game if time has exceeded the allotted gamelength
					pause = true;
				}
			} else {

				mainMenu.tick();

				exit = mainMenu.endGame;

				if(mainMenu.startGame) {

					stats = new StatisticsContainer();

					ui = new HUD(stats);

					//Start Game 
					mobHead = new Player(100,100, mainKey);

					doSpawning(true);
					doSpawning(true);
					doSpawning(true);

					pause = false;

				}

			}

			double sleepTime = (1000.0/60) - (System.currentTimeMillis() - tickStartTime);


			TPS = 1/(sleepTime / 1000);

			try {
				if(sleepTime > 0 ) {
					Thread.sleep((int) (sleepTime));
				} else {
					//					renderSleepTime = (int) (sleepTime * -1);
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

				Graphics2D g2d;

				do {
					do {

						double startTime = System.nanoTime();

						BufferedImage img = new BufferedImage(SettingsManager.getResX(), SettingsManager.getResY(), BufferedImage.TYPE_INT_ARGB);
						g2d = (Graphics2D) img.getGraphics();

						//Set rendering hints
						g2d = SettingsManager.setRenderingHints(g2d);

						g2d.setColor(Color.BLACK);
						g2d.fillRect(0, 0, SettingsManager.getResX(), SettingsManager.getResY());

						if(pause == false) {

							//Render Game
							Mob tempMob = mobHead;
							while(tempMob != null) {
								g2d = tempMob.render(g2d);
								tempMob = tempMob.next;
							}

							//Render HUD
							g2d = ui.render(g2d);

						} else {
							//Render Menu
							g2d = mainMenu.render(g2d);
						}

						//Draw TPS and FPS
						g2d.setFont(new Font("DialogInput", Font.PLAIN, 12));
						g2d.setColor(Color.CYAN);
						g2d.drawString("TPS = " + TPS, 10, 10);
						g2d.drawString("FPS = " + FPS, 10, 20);
						g2d.drawString("ST = " + renderSleepTime, 10, 30);

						g2d.dispose();

						if(renderSleepTime > 0) {
							try {Thread.sleep(renderSleepTime);}
							catch (InterruptedException e) {e.printStackTrace();}
						}

						FPS = (int) (1/ ((System.nanoTime() - startTime) / 1000000000.0));

						if(pause == false) {
							img = EffectManager.process(img);
						}

						g2d = (Graphics2D) BuffStrat.getDrawGraphics();
						g2d.drawImage(img,0,0,null);
						g2d.dispose();

					} while (BuffStrat.contentsRestored());

					BuffStrat.show();

				} while (BuffStrat.contentsLost());				
			}
		}
	};
}

