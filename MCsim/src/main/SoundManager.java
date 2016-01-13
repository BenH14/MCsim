package main;

import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;

import effects.EffectManager;
import effects.Pulse;

public class SoundManager extends Thread{

	private boolean stop;

	private AudioInputStream drums;

	private Clip players[];
	private Pulse pulser;

	public SoundManager() {
		//
		//		stop = false;
		//
		//		players = new Clip[10];
		//		
		//		try {
		//			//Open Files
		////			drums = AudioSystem.getAudioInputStream(new File("/res/music/drums.mp3"));
		//		} catch (Exception ex) {
		//			ex.printStackTrace();
		//		}
	}

	public void stopLoop() {
//		for (int x = 0;x < 10; x++) {
//			
//				if(players[x].isRunning()) {
//					players[x].stop();	
//				}
//			
//		}
	}

	public void playerSound(AudioInputStream in) {

		Clip clip = null;

		try {
			clip = AudioSystem.getClip();
			clip.open(in);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}

		clip.setFramePosition(0);
		clip.start();

	}

	public void run() {

		stop = false;

		pulser = new Pulse(60);
		EffectManager.addEffect(pulser);
		//		try {
		//			players[0] = AudioSystem.getClip();
		//			players[0].open(drums);
		//		} catch (LineUnavailableException | IOException e) {
		//			e.printStackTrace();
		//		}
		//
		//		players[0].setFramePosition(0);
		//		players[0].start();
		//		players[0].loop(Clip.LOOP_CONTINUOUSLY);





	}

}
