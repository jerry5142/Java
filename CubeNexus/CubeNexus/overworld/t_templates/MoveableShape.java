package t_templates;

import i_images.OverWorldImages;

import java.awt.Color;
import java.awt.Point;

/**
 * A movable versions of the shape class.
 * 
 * @author Jerry Swank
 * 
 */
public abstract class MoveableShape extends Shape {
	private static final long serialVersionUID = 1L;

	/**
	 * Determines if the shape is allowed to move
	 */
	private boolean moveable;

	/**
	 * The last position of the shape
	 */
	private Point lastPosition;

	/**
	 * Creates a moveable shape
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 * @param iconType
	 * @param canMove
	 * @param popupMessage
	 */
	public MoveableShape(int x, int y, int width, int height, Color color,
			OverWorldImages iconType, boolean canMove, String popupMessage) {
		super(x, y, width, height, color, iconType, popupMessage);
		this.moveable = canMove;
		this.lastPosition = new Point(x, y);
	}

	/**
	 * Moves the shape by the specified increment
	 * 
	 * @param xIncr
	 * @param yIncr
	 * @return true if the shape was moved
	 */
	public boolean moveShape(int xIncr, int yIncr) {
		if (moveable) {
			// save the last location
			lastPosition.setLocation(this.getLocation());

			this.setBounds(this.x += xIncr, this.y += yIncr, this.width,
					this.height);
			return true;
		}
		return false;
	}

	/**
	 * @return true if the shape is moveable
	 */
	public boolean isMoveable() {
		return moveable;
	}

	/**
	 * Set to true if the shape is allowed to move
	 * 
	 * @param canMove
	 */
	public void setMoveable(boolean canMove) {
		this.moveable = canMove;
	}

	/**
	 * @return the lastPosition
	 */
	public Point getLastPosition() {
		return lastPosition;
	}

	/**
	 * @param lastPosition
	 *            the lastPosition to set
	 */
	public void setLastPosition(Point lastPosition) {
		this.lastPosition = lastPosition;
	}

}