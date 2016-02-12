package main;

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

	private AudioInputStream mainSound;

	private Clip mainSoundtrack;
	private Pulse pulser;



	public SoundManager() {

		stop = false;

		try {
			//Open Files
//			mainSound = AudioSystem.getAudioInputStream(getClass().getClassLoader().getResourceAsStream("res/sound/Ding.wav"));
		} catch (Exception ex) {
			ex.printStackTrace();
			
		}

	}

	public void stopLoop() {

		stop = true;

	}

	public void playSound(Clip clip) {

		FloatControl volumeControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
		volumeControl.setValue(SettingsManager.getVolume());

		clip.setFramePosition(0);
		clip.start();

	}

	public void run() {

//		stop = false;
//
//		pulser = new Pulse(60);
//		EffectManager.addEffect(pulser);
//
//		try {
//			mainSoundtrack = AudioSystem.getClip();
//			mainSoundtrack.open(mainSound);
//		} catch (LineUnavailableException | IOException e) {
//			e.printStackTrace();
//		}
//
//		FloatControl volumeControl = (FloatControl) mainSoundtrack.getControl(FloatControl.Type.MASTER_GAIN);
//		volumeControl.setValue(SettingsManager.getVolume());
//
//		mainSoundtrack.setFramePosition(0);
//		mainSoundtrack.start();
//
//		while(stop == false) {
//			try {
//				Thread.sleep(200);
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			}
//		}

	}

}
