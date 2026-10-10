package fsp;

// import statements
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

// https://stackoverflow.com/questions/11919009/using-javax-sound-sampled-clip-to-play-loop-and-stop-multiple-sounds-in-a-game
// Sound control
public class Sound {

	private Clip clip;
	// https://stackoverflow.com/questions/953598/audio-volume-control-increase-or-decrease-in-java
	FloatControl gainControl; 
	float currentVolume = 0;
	float previousVolume = 0;
	boolean play = false;
	boolean mute = false;
	boolean loop = false;
	boolean pause = false;
	long clipTime;
	
	public Sound(String fileName) {
		// specify the sound to play
		// (assuming the sound can be played by the audio system)
		// from a wave File
		try {
			File file = new File(fileName);
			if (file.exists()) {
				AudioInputStream sound = AudioSystem.getAudioInputStream(file);
				// load the sound into memory (a Clip)
				clip = AudioSystem.getClip();
				clip.open(sound);
				gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
				
			} else {
				throw new RuntimeException("Sound: file not found: " + fileName);
			}
		}
		// catch exceptions
		catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Malformed URL: " + e);
		} catch (UnsupportedAudioFileException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Unsupported Audio File: " + e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Input/Output Error: " + e);
		} catch (LineUnavailableException e) {
			e.printStackTrace();
			throw new RuntimeException("Sound: Line Unavailable Exception Error: " + e);
		}
	}

	// play
	public void play() {
		if (play == false) {
			clip.setFramePosition(0); // Must always rewind!
			clip.start();
			play = true;
		}
	}

	// loop
	public void loop() {
		if(loop == false) {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			loop = true;
		} else if(loop == true) {
			clip.loop(0);
			loop = false;
		}

	}

	// mute
	// https://www.youtube.com/watch?v=sLQOP1DvbXo
	public void mute() {
		if (mute == false) {
			previousVolume = currentVolume;
			currentVolume = -80.0f;
			gainControl.setValue(currentVolume);
			mute = true;
		} else if (mute == true) {
			currentVolume = previousVolume;
			gainControl.setValue(currentVolume);
			mute = false;
		}
	}

	// pauses
	// https://stackoverflow.com/questions/16915241/how-do-i-pause-a-clip-java
	public void pause() {
		if (!pause) {
			clipTime = clip.getMicrosecondPosition();
			clip.stop();
			pause = true;
		} else {
			clip.setMicrosecondPosition(clipTime);
			clip.start();
			pause = false;
		}
	}

	public void stop() {
		clip.stop();
	}
}
