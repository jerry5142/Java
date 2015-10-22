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
		this.counter = this.countMax = 50;

		this.setMoveUp(true);
		this.setMoveDn(false);
		this.setMoveLt(false);
		this.setMoveRt(false);

		this.setMoveable(true);
		this.setMoveSpeed(maxSpeed);
	}

	/**
	 * Sets the movement flags randomly
	 */
	public void moveRandomly() {
		if (this.counter-- > 0) {
			this.movePlayer();
			return;
		}
		this.countMax = 0 + rand.nextInt(75);
		this.counter = this.countMax;
		this.setMoveSpeed(1 + rand.nextInt(10));

		this.setMoveUp(false);
		this.setMoveDn(false);
		this.setMoveLt(false);
		this.setMoveRt(false);

		switch (rand.nextInt(7)) {
		case 0: // move up
			this.setMoveUp(true);
			break;
		case 1: // move up right
			this.setMoveUp(true);
			this.setMoveRt(true);
			break;
		case 2: // move right
			this.setMoveRt(true);
			break;
		case 3: // move dn right
			this.setMoveDn(true);
			this.setMoveRt(true);
			break;
		case 4: // move dn
			this.setMoveDn(true);
			break;
		case 5: // move dn left
			this.setMoveDn(true);
			this.setMoveLt(true);
			break;
		case 6: // move left
			this.setMoveLt(true);
			break;
		case 7: // move up left
			this.setMoveUp(true);
			this.setMoveLt(true);
			break;
		}
	}

	@Override
	public void draw(Graphics g) {
		super.draw(g);
		moveRandomly();
	}
}
