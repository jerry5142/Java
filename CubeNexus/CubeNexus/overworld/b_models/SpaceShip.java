package b_models;

import i_images.OverWorldImages;

import java.awt.Rectangle;
import java.util.ArrayList;

import javax.sound.sampled.Clip;

import t_templates.Shape;

public class SpaceShip extends NoisyShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Holds the objects the player has acquired
	 */
	private ArrayList<OverWorldImages> playersStuff;

	/**
	 * Determines how fast the shape moves with each key press
	 */
	private int moveSpeed;

	/**
	 * Determines the maximum allowed speed
	 */
	private int maxSpeed;

	/**
	 * Allows the y coordinate to be decremented.
	 */
	private boolean moveUp;

	/**
	 * Allows the y coordinate to be incremented.
	 */
	private boolean moveDn;

	/**
	 * Allows the x coordinate to be decremented.
	 */
	private boolean moveLt;

	/**
	 * Allows the x coordinate to be incremented.
	 */
	private boolean moveRt;

	/**
	 * Holds the animated .gif images used to display the player in the left,
	 * left upper diagonal, up, right upper diagonal, right, right lower
	 * diagonal, down, and left lower diagonal positions.
	 */
	private OverWorldImages imageLeft, imageRight, imageUp, imageDown,
			imageUpperLeft, imageUpperRight, imageLowerLeft, imageLowerRight;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param maxSpeed
	 * @param imageType
	 * @param popupMessage
	 * @param enclosure
	 * @param defaultSound
	 */
	public SpaceShip(int x, int y, int width, int height, int maxSpeed,
			OverWorldImages imageType, String popupMessage,
			Rectangle enclosure, Clip defaultSound) {
		super(x, y, width, height, imageType, popupMessage, enclosure,
				defaultSound);

		if (moveSpeed < 0)
			throw new IllegalArgumentException("moveSpeed must be >= 0");

		setMaxSpeed(maxSpeed);
		this.moveSpeed = maxSpeed / 2;
		this.playersStuff = new ArrayList<OverWorldImages>(10);
		this.moveUp = false;
		this.moveDn = false;
		this.moveLt = false;
		this.moveRt = false;
		this.imageLeft = null;
		this.imageRight = null;
		this.imageUp = null;
		this.imageDown = null;
		this.imageUpperLeft = null;
		this.imageUpperRight = null;
		this.imageLowerLeft = null;
		this.imageLowerRight = null;
	}

	/**
	 * Allows the player to move even if it is intersecting another shape
	 */
	public void unfreeze() {
		setMoveable(true);
	}

	/**
	 * @return
	 */

	/**
	 * 
	 * @param object
	 * @return the playersStuff
	 */
	public boolean hasObject(OverWorldImages object) {
		return playersStuff.contains(object);
	}

	/**
	 * @param shape
	 *            the shape to add to the player's stuff
	 */
	public void addShape(OverWorldImages shape) {
		if (shape == null)
			return;

		this.playersStuff.add(shape);
	}

	/**
	 * Removes a DrawableShape from the playersStuff
	 * 
	 * @param shape
	 *            the Shape to remove.
	 */
	public void removeShape(OverWorldImages shape) {
		if (shape == null)
			return;
		playersStuff.remove(shape);
	}

	/**
	 * @return the maxSpeed
	 */
	public int getMaxSpeed() {
		return maxSpeed;
	}

	/**
	 * @param maxSpeed
	 *            the maxSpeed to set
	 */
	public void setMaxSpeed(int maxSpeed) {
		if (maxSpeed >= 0)
			this.maxSpeed = maxSpeed;
	}

	/**
	 * @return the moveSpeed
	 */
	public int getMoveSpeed() {
		return moveSpeed;
	}

	/**
	 * @param moveSpeed
	 *            the moveSpeed to set
	 */
	public void setMoveSpeed(int moveSpeed) {
		if (moveSpeed >= 1 && moveSpeed <= maxSpeed)
			this.moveSpeed = moveSpeed;
	}

	/**
	 * @return the moveUp
	 */
	public boolean isMoveUp() {
		return moveUp;
	}

	/**
	 * @param moveUp
	 *            the moveUp to set
	 */
	public void setMoveUp(boolean moveUp) {
		this.moveUp = moveUp;
	}

	/**
	 * @return the moveDn
	 */
	public boolean isMoveDn() {
		return moveDn;
	}

	/**
	 * @param moveDn
	 *            the moveDn to set
	 */
	public void setMoveDn(boolean moveDn) {
		this.moveDn = moveDn;
	}

	/**
	 * @return the moveLt
	 */
	public boolean isMoveLt() {
		return moveLt;
	}

	/**
	 * @param moveLt
	 *            the moveLt to set
	 */
	public void setMoveLt(boolean moveLt) {
		this.moveLt = moveLt;
	}

	/**
	 * @return the moveRt
	 */
	public boolean isMoveRt() {
		return moveRt;
	}

	/**
	 * @param moveRt
	 *            the moveRt to set
	 */
	public void setMoveRt(boolean moveRt) {
		this.moveRt = moveRt;
	}

	/**
	 * Moves the player in the direction(s) indicated by the move variables at
	 * the speed specified in moveSpeed
	 * 
	 * @return true if the player was moved
	 */
	public boolean movePlayer() {
		if (!(moveUp || moveDn || moveLt || moveRt))
			return false;

		int xIncr = 0;
		int yIncr = 0;

		if (moveUp) {
			yIncr += -moveSpeed;
		}
		if (moveDn) {
			yIncr += moveSpeed;
		}
		if (moveLt) {
			xIncr += -moveSpeed;
		}
		if (moveRt) {
			xIncr += moveSpeed;
		}

		if (super.moveShape(xIncr, yIncr)) {
			super.playSound();

			// set the correct image for the move direction

			if (moveLt) {
				if (moveUp)
					setImageType(imageUpperLeft);
				else if (moveDn)
					setImageType(imageLowerLeft);
				else
					setImageType(imageLeft);
			} else if (moveRt) {
				if (moveUp)
					setImageType(imageUpperRight);
				else if (moveDn)
					setImageType(imageLowerRight);
				else
					setImageType(imageRight);
			} else if (moveUp) {
				if (moveLt)
					setImageType(imageUpperLeft);
				else if (moveRt)
					setImageType(imageUpperRight);
				else
					setImageType(imageUp);
			} else if (moveDn) {
				if (moveLt)
					setImageType(imageLowerLeft);
				else if (moveRt)
					setImageType(imageLowerRight);
				else
					setImageType(imageDown);
			}

			return true;
		}

		return false;
	}

	/**
	 * Stops the player's movement
	 */
	public void stopPlayer() {
		this.moveDn = false;
		this.moveUp = false;
		this.moveLt = false;
		this.moveRt = false;
	}

	/**
	 * Moves the player just outside of the door that corresponds to the other
	 * side of this door
	 * 
	 * @param door
	 *            the door that the player is passing through
	 */
	public void movePlayerThroughDoor(Door door) {
		Door otherDoor = door.getOtherSideOfDoor();

		switch (otherDoor.getPosition()) {
		// move the player just outside the corresponding door
		case LEFT:
			this.setLocation(
					(int) (otherDoor.getX() + otherDoor.getWidth() + 1),
					(int) this.getY());
			break;
		case RIGHT:
			this.setLocation((int) (otherDoor.getX() - this.getWidth() - 1),
					(int) this.getY());
			break;
		case TOP:
			this.setLocation((int) this.getX(), (int) (otherDoor.getY()
					+ otherDoor.getHeight() + 1));
			break;
		case BOTTOM:
			this.setLocation((int) this.getX(),
					(int) (otherDoor.getY() - this.getHeight() - 1));
			break;
		default:
			break;

		}
		// update the solid objects in the new room
	}

	/**
	 * @param imageLeft
	 *            the imageLeft to set
	 */
	public void setImageLeft(OverWorldImages imageLeft) {
		this.imageLeft = imageLeft;
	}

	/**
	 * @param imageRight
	 *            the imageRight to set
	 */
	public void setImageRight(OverWorldImages imageRight) {
		this.imageRight = imageRight;
	}

	/**
	 * @param imageUp
	 *            the imageUp to set
	 */
	public void setImageUp(OverWorldImages imageUp) {
		this.imageUp = imageUp;
	}

	/**
	 * @param imageDown
	 *            the imageDown to set
	 */
	public void setImageDown(OverWorldImages imageDown) {
		this.imageDown = imageDown;
	}

	/**
	 * @param imageUpperLeft
	 *            the imageUpperLeft to set
	 */
	public void setImageUpperLeft(OverWorldImages imageUpperLeft) {
		this.imageUpperLeft = imageUpperLeft;
	}

	/**
	 * @param imageUpperRight
	 *            the imageUpperRight to set
	 */
	public void setImageUpperRight(OverWorldImages imageUpperRight) {
		this.imageUpperRight = imageUpperRight;
	}

	/**
	 * @param imageLowerLeft
	 *            the imageLowerLeft to set
	 */
	public void setImageLowerLeft(OverWorldImages imageLowerLeft) {
		this.imageLowerLeft = imageLowerLeft;
	}

	/**
	 * @param imageLowerRight
	 *            the imageLowerRight to set
	 */
	public void setImageLowerRight(OverWorldImages imageLowerRight) {
		this.imageLowerRight = imageLowerRight;
	}

	@Override
	public boolean intersectsShape(Shape otherShape) {
		if (super.intersectsShape(otherShape)) {

			return true;
		}
		return false;
	}
}