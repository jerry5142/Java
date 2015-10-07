package b_models;

import i_images.OverWorldImages;

import java.awt.Rectangle;


import javax.sound.sampled.Clip;

/**
 * Class for self animated objects
 * 
 * @author Jerry Swank
 * 
 */
public class SelfMovingShape extends SpaceShip {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Random number generator for shape movement
	 */
//	private Random rand;

	public SelfMovingShape(int x, int y, int width, int height, int maxSpeed,
			OverWorldImages imageType, String popupMessage,
			Rectangle enclosure, Clip defaultSound) {
		super(x, y, width, height, maxSpeed, imageType, popupMessage,
				enclosure, defaultSound);
	}

	public void moveRandomly() {

	}

}
