package jomung.sound;

import java.io.FileInputStream;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer extends Thread {

	private String path;

	@Override
	public void run() {
		try {
			Clip clip = AudioSystem.getClip();
			AudioInputStream inputStream = AudioSystem
					.getAudioInputStream(new FileInputStream(path));
			clip.open(inputStream);
			clip.start();
		} catch (IOException | UnsupportedAudioFileException
				| LineUnavailableException exception) {
		}
	}
}
