package jomung.sound;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SoundPlayer extends Thread {

	private static SoundPlayer instance;
	private String path = "audio/default.wav";
	private volatile boolean play = true;

	public static SoundPlayer getInstance() {
		if (instance == null) {
			instance = new SoundPlayer();
		}
		return instance;
	}

	public SoundPlayer() {
		super("Audio Thread");
	}

	@Override
	public void run() {
		try {
			AudioInputStream audioInputStream = AudioSystem
					.getAudioInputStream(new File(path));
			AudioFormat format = audioInputStream.getFormat();
			DataLine.Info info = new DataLine.Info(Clip.class, format);
			Clip clip = (Clip) AudioSystem.getLine(info);
			clip.open(audioInputStream);
			while (play) {
				clip.start();
			}
		} catch (IOException | UnsupportedAudioFileException
				| LineUnavailableException exception) {
			exception.printStackTrace();
		}
	}

	public void setPath(String path) {
		this.path = path;
	}

	public void refresh() {
		play = false;
		play = true;
		this.start();
	}

}
