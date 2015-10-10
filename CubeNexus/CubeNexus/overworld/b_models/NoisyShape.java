package b_models;

import i_images.OverWorldImages;

import java.awt.Rectangle;

import s_sounds.OverWorldSounds;
import s_sounds.SoundClipPlayer;

/**
 * Shapes with the ability to play sounds
 * 
 * @author me
 *
 */
public class NoisyShape extends BoundedShape {

	private static final long serialVersionUID = 1L;
	/**
	 * Sound player to play shape sounds
	 */
	private SoundClipPlayer soundPlayer;

	/**
	 * Determines if the shape is allowed to make noise
	 */
	private boolean mute;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param imageType
	 * @param popupMessage
	 * @param enclosure
	 * @param defaultSound
	 */
	public NoisyShape(int x, int y, int width, int height,
			OverWorldImages imageType, String popupMessage,
			Rectangle enclosure, OverWorldSounds defaultSound) {
		super(x, y, width, height, imageType, popupMessage, enclosure);

		this.mute = true;

		if (defaultSound != null) {
			this.soundPlayer = new SoundClipPlayer(defaultSound.getSoundClip());
			Thread soundThread = new Thread(soundPlayer);
			soundThread.run();
			this.mute = false;
		}
	}

	/**
	 * @return the mute
	 */
	public boolean isMute() {
		return mute;
	}

	/**
	 * @param mute
	 *            the mute to set
	 */
	public void setMute(boolean mute) {
		this.mute = mute;
	}

	/**
	 * play the sound
	 */
	public void playSound() {
		if (!mute)
			try {
				this.soundPlayer.play();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}

	/**
	 * @return the soundPlayer
	 */
	public SoundClipPlayer getSoundPlayer() {
		return soundPlayer;
	}

}
