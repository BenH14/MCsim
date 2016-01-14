package main;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;

import effects.EffectManager;
import effects.Pulse;
import settings.SettingsManager;

public class SoundManager extends Thread{

	private boolean stop;

	private AudioInputStream drums;

	private Clip mainSoundtrack;
	private Pulse pulser;



	public SoundManager() {

		stop = false;

		try {
			//Open Files
			drums = AudioSystem.getAudioInputStream(new File("res/sound/Ding.wav"));
		} catch (Exception ex) {
			ex.printStackTrace();
		}

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

	public void playSound(AudioInputStream in) {

		Clip clip = null;

		try {
			clip = AudioSystem.getClip();
			clip.open(in);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}

		FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volumeControl.setValue(SettingsManager.getVolume());
		
		clip.setFramePosition(0);
		clip.start();

	}

	public void run() {

		stop = false;

		pulser = new Pulse(60);
		EffectManager.addEffect(pulser);

		try {
			mainSoundtrack = AudioSystem.getClip();
			mainSoundtrack.open(drums);
		} catch (LineUnavailableException | IOException e) {
			e.printStackTrace();
		}

		FloatControl volumeControl = (FloatControl) mainSoundtrack.getControl(FloatControl.Type.MASTER_GAIN);
		volumeControl.setValue(SettingsManager.getVolume());
		
		mainSoundtrack.setFramePosition(0);
		mainSoundtrack.start();
		mainSoundtrack.loop(Clip.LOOP_CONTINUOUSLY);





	}

}
