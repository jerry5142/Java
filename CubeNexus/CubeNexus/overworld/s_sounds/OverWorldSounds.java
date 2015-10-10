package s_sounds;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 * Holds the sounds for the OverWorld
 * 
 * @author Jerry Swank
 * 
 */
public enum OverWorldSounds {
	ZOMBIE("zombie", "pitarra-take-sound.wav"),

	BACKGROUND("background", "music-pitarra-1-short.wav"),

	HIGHLIGHT("highlight", "pitarra-highlight-sound.wav"),

	MOVE("highlight", "pitarra-move-sound.wav"),

	PIECEDROP("piecedrop", "pitarra-piecedrop-sound.wav"),

	TAKE("take", "pitarra-take-sound.wav"),

	WIN("win", "pitarra-win-sound.wav");

	private static final String soundsPath = "OverWorldSounds/";
	private String soundName;
	private Clip soundClip;

	/**
	 * Constructor
	 * 
	 * @param soundName
	 * @param soundFileName
	 */
	OverWorldSounds(String soundName, String soundFileName) {
		this.soundName = soundName;
		soundFileName = OverWorldSounds.soundsPath + soundFileName;
		try {
			// Get a sound clip resource.
			soundClip = AudioSystem.getClip();
			// Open an audio input stream. Then open audio clip
			// and load samples from the audio input stream.
			soundClip.open(AudioSystem.getAudioInputStream(new File(
					soundFileName)));
		} catch (NullPointerException e) {
			System.err.println("Cannot find " + soundFileName);
		} catch (UnsupportedAudioFileException e) {
			System.err.println(OverWorldSounds.soundsPath + soundFileName
					+ " is not a valid audio file.\nUse .wav files.");
		} catch (IOException e) {
			System.err.println("Error reading " + soundFileName + " for sound "
					+ this.soundName);
		} catch (LineUnavailableException e) {
			System.err.println("Line unavailable error for: " + this.soundName
					+ "at path: " + soundFileName);
		}
	}

	/**
	 * @return the soundName
	 */
	public String getSoundName() {
		return soundName;
	}

	/**
	 * @return the soundClip
	 */
	public Clip getSoundClip() {
		return soundClip;
	}
}
