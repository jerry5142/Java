package b_models;

import i_images.OverWorldImages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import t_templates.MoveableShape;

/**
 * Shapes of this class are confined within an enclosing rectangle.
 * 
 * @author Jerry Swank
 * 
 */
public class BoundedShape extends MoveableShape {

	private static final long serialVersionUID = 1L;

	/**
	 * The Rectangle that the shape should be bounded in
	 */
	private Rectangle enclosure;

	/**
	 * The Rectangle that triggers the shapes interaction
	 */
	private Rectangle intersectRect;

	/**
	 * Rectangle for the new bounds of the shape as it is moved
	 */
	private Rectangle newBounds;

	/**
	 * Solid obstacles the shape cannot pass through
	 */
	// private ArrayList<Rectangle> obstacles;

	/**
	 * Constructor
	 * 
	 * @param x
	 *            must be > 0
	 * @param y
	 *            must be > 0
	 * @param width
	 *            must satisfy: (x + width) < enclosure.width
	 * @param height
	 *            must satisfy: (y + height) < enclosure.height
	 * @param imageType
	 * @param popupMessage
	 * @param enclosure
	 *            the rectangle that this shape is confined to
	 */
	public BoundedShape(int x, int y, int width, int height,
			OverWorldImages imageType, String popupMessage, Rectangle enclosure) {
		super(x, y, width, height, Color.WHITE, imageType, true, popupMessage);

		if (x < 1 || y < 1 || (x + width + 1) > enclosure.width
				|| (y + height + 1) > enclosure.height)
			throw new IllegalArgumentException(
					"Initial bounds are invalid. Restrictions are: x > 0, y > 0, (x + width) < enclosure.width, (y + height) < enclosure.height");

		this.enclosure = enclosure;
		this.newBounds = new Rectangle(x, y, width, height);
		// this.obstacles = new ArrayList<Rectangle>(10);
		this.intersectRect = new Rectangle(x - 1, y - 1, width + 1, height + 1);
	}

	/**
	 * Only moves the shape if it's new bounds will still be completely within
	 * the enclosure's bounds
	 * 
	 * @return true if the shape was moved
	 */
	public boolean moveShape(int xIncr, int yIncr) {
		newBounds.setLocation(this.x + xIncr, this.y + yIncr);

		if (!enclosure.contains(newBounds))
			return false;

		// for (Rectangle rect : obstacles) {
		// if (newBounds.intersects(rect))
		// return false;
		// }

		// update the intersection rectangle bounds
		if (super.moveShape(xIncr, yIncr)) {
			intersectRect.setBounds(this.x - 1, this.y - 1, this.width + 1,
					this.height + 1);
			super.setMessagePoint(this.getLocation());
			return true;
		}

		return false;
	}

	/**
	 * Moves the shape to location (x, y) if moveShape restrictions are
	 * satisfied
	 * 
	 * @param x
	 * @param y
	 * @return true if the shape was moved
	 */
	public boolean moveShapeToLocation(int x, int y) {
		return moveShape(x - this.x, y - this.y);
	}

	@Override
	public void draw(Graphics g) {
		if (g == null)
			throw new IllegalArgumentException("Graphics g is null");

		this.drawIconImage(g);
	}

	/**
	 * Draws the icon image if their is one in the center of the new enclosure
	 * specified.
	 * 
	 * @param g
	 *            the graphics instance to draw on
	 * @param newEnclosure
	 *            the enclosing rectangle
	 */
	public void drawInCenter(Graphics g, Rectangle newEnclosure) {
		if (g == null)
			throw new IllegalArgumentException("Graphics g is null");

		if (isVisible()) {
			int newX = (newEnclosure.width - this.width) / 2;
			int newY = (newEnclosure.height - this.height) / 2;

			if (hasImage()) {
				g.drawImage(this.getIconImage(), newX, newY, width, height,
						null);
			} else {
				g.setColor(this.getColor());
				g.fillRect(newX, newY, width, height);
			}
		}
	}

	/**
	 * @return the enclosure
	 */
	public Rectangle getEnclosure() {
		return enclosure;
	}

	/**
	 * @param enclosure
	 *            the enclosure to set
	 */
	public void setEnclosure(Rectangle enclosure) {
		this.enclosure = enclosure;
	}

	/**
	 * @return true if the otherShape intersects this shapes intersection
	 *         rectangle
	 */
	public boolean intersectsShape(BoundedShape otherShape) {
		return this.intersectRect.intersects(otherShape.intersectRect);
		// return this.intersectRect.intersects(otherShape);
		// return this.intersects(otherShape);
	}

	/**
	 * @param otherShape
	 * @return true if the y coordinate of this is less than the y coordinate of
	 *         otherShape
	 */
	public boolean isBehindShape(BoundedShape otherShape) {
		if (this.y < otherShape.y + otherShape.height / 2)
			return true;

		return false;
	}

	/**
	 * @param otherShape
	 * @return true if the y coordinate of this is greater than the y coordinate
	 *         of otherShape
	 */
	public boolean isInFrontOfShape(BoundedShape otherShape) {
		if (this.y > otherShape.y + 3 * otherShape.height / 4)
			return true;

		return false;
	}

	/**
	 * @param otherShape
	 * @return true if the x coordinate of this is less than the x coordinate of
	 *         otherShape
	 */
	public boolean isLeftOfShape(BoundedShape otherShape) {
		if (this.x < otherShape.x + otherShape.width / 2)
			return true;

		return false;
	}

	/**
	 * @param otherShape
	 * @return true if the x coordinate of this is greater than the x coordinate
	 *         of otherShape
	 */
	public boolean isRightOfShape(BoundedShape otherShape) {
		if (this.x > otherShape.x + otherShape.width / 2)
			return true;

		return false;
	}

	/**
	 * @param otherShape
	 * @return true if the center point if the other shape is inside the bounds
	 *         of this shape
	 */
	public boolean isCenteredOnShape(BoundedShape otherShape) {
		if (this.contains(otherShape.getCenterPoint()))
			return true;

		return false;
	}

	/**
	 * @param obstacles
	 *            the obstacles to set
	 */
	// public void addObstacle(Rectangle rect) {
	// this.obstacles.add(rect);
	// }

}
