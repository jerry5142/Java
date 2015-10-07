package s_sounds;

import javax.sound.sampled.Clip;

/**
 * Plays sound clips defined in OverWorldSounds enum class. The player is on the
 * EDT, so it runs independently.
 */
public class SoundClipPlayer implements Runnable {

	/**
	 * The sound clip to play
	 */
	private Clip soundClip;

	/**
	 * Constructor
	 * 
	 * @param soundClip
	 */
	public SoundClipPlayer(Clip soundClip) {
		setSoundClip(soundClip);
	}

	/**
	 * Stop the sound from playing
	 */
	public void stop() {
		soundClip.stop();
	}

	/**
	 * Start playing the sound
	 */
	public void play() {
		if (soundClip.isRunning())
			return;
		soundClip.setFramePosition(0);
		soundClip.start();
	}

	/**
	 * Toggle playing the sound
	 */
	public void toggle() {
		if (soundClip.isRunning()) {
			soundClip.stop();
		} else {
			soundClip.setFramePosition(0);
			soundClip.start();
		}
	}

	/**
	 * Plays the sound in a continuous loop
	 */
	public void loop() {
		try {
			if (soundClip.isRunning())
				soundClip.stop();
			soundClip.setFramePosition(0);
			soundClip.loop(Clip.LOOP_CONTINUOUSLY);
		} catch (NullPointerException e) {
			System.err.println("sound error 4");
		}

	}

	/**
	 * @param soundClip
	 *            the soundClip to set
	 */
	public void setSoundClip(Clip soundClip) {
		if (soundClip == null) {
			System.err.println("Sound clip is null");
			return;
		}
		this.soundClip = soundClip;
	}

	@Override
	public void run() {
		play();
	}
}