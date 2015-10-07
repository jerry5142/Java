package t_templates;

import java.awt.Graphics;
import java.awt.Rectangle;

/**
 * @author Jerry Swank Interface for drawable shapes
 */
public interface DrawableShape {
	/**
	 * Draws the shape
	 * 
	 * @param g
	 *            Graphics object
	 */
	public void draw(Graphics g);

	/**
	 * 
	 * @return a Rectangle representing the bounds of the shape
	 */
	public Rectangle getShapeBounds();

	/**
	 * 
	 * @param otherShape
	 *            Shape
	 * @return true if this shape intersects otherShape
	 */
	public boolean intersectsShape(Shape otherShape);
}
