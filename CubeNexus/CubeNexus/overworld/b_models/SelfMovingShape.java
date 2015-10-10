package b_models;

import i_images.OverWorldImages;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import s_sounds.OverWorldSounds;

/**
 * Class for self animated objects
 * 
 * @author Jerry Swank
 * 
 */
public class SelfMovingShape extends Player {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Random number generator for shape movement
	 */
	private Random rand;

	/**
	 * Everytime move is called, counter is decremented. When it reaches 0, a
	 * random direction change is made.
	 */
	private int counter, countMax;

	public SelfMovingShape(int x, int y, int width, int height, int maxSpeed,
			OverWorldImages imageType, String popupMessage,
			Rectangle enclosure, OverWorldSounds defaultSound) {
		super(x, y, width, height, maxSpeed, imageType, popupMessage,
				enclosure, defaultSound);

		this.rand = new Random();
		this.counter = this.countMax = 1;

		this.setMoveUp(true);
		this.setMoveDn(false);
		this.setMoveLt(false);
		this.setMoveRt(false);
	}

	/**
	 * Sets the movement flags randomly
	 */
	public void moveRandomly() {
		if (this.counter-- > 0) {
			return;
		}
		this.counter = this.countMax;

		do {
//			boolean onOrOff = rand.nextBoolean();

			// switch (rand.nextInt(3)) {
			// case 0:
			// this.setMoveUp(onOrOff);
			// break;
			// case 1:
			// this.setMoveDn(onOrOff);
			// break;
			// case 2:
			// this.setMoveLt(onOrOff);
			// break;
			// case 3:
			// this.setMoveRt(onOrOff);
			// break;
			// }

			// boolean onOrOff = rand.nextBoolean();
			//
			// switch (rand.nextInt(1)) {
			// case 0:
			// this.setMoveUp(onOrOff);
			// this.setMoveDn(!onOrOff);
			// break;
			// case 1:
			// this.setMoveLt(onOrOff);
			// this.setMoveRt(!onOrOff);
			// break;
			// }
			//
			this.setMoveUp(rand.nextBoolean());
			this.setMoveDn(rand.nextBoolean());
			this.setMoveLt(rand.nextBoolean());
			this.setMoveRt(rand.nextBoolean());
		} while (!this.movePlayer());
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		moveRandomly();
	}
}
