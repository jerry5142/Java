package b_models;

import i_images.OverWorldImages;

import java.awt.Rectangle;

/**
 * Changes the size of the shape based on it y-coordinate with respect to the
 * encloseure
 * 
 * @author Jerry Swank
 * 
 */
public class PerspectiveShape extends BoundedShape {

	private static final long serialVersionUID = 1L;

	/**
	 * determines the minimum size allowed for the image
	 */
	private double minPercentSize;

	/**
	 * the original width of the image
	 */
	private int maxWidth;

	/**
	 * the original height of the image
	 */
	private int maxHeight;

	/**
	 * the original move speed of the image
	 */
	private int maxSpeed;

	/**
	 * Constructor
	 * 
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param imageType
	 * @param popupMessage
	 * @param enclosure
	 * @param minPercentSize
	 * @param maxWidth
	 * @param maxHeight
	 * @param maxSpeed
	 */
	public PerspectiveShape(int x, int y, int width, int height,
			OverWorldImages imageType, String popupMessage,
			Rectangle enclosure, double minPercentSize, int maxWidth,
			int maxHeight, int maxSpeed) {
		super(x, y, width, height, imageType, popupMessage, enclosure);
		this.minPercentSize = minPercentSize;
		this.maxWidth = maxWidth;
		this.maxHeight = maxHeight;
		this.maxSpeed = maxSpeed;
		scaleValues(x, y);
	}

	/**
	 * Scales the shape based on the x and y values
	 * 
	 * @param x
	 * @param y
	 */
	private void scaleValues(int x, int y) {
		// scale the icon dimensions
		double scale = minPercentSize + (1 - minPercentSize)
				* ((double) y / this.getEnclosure().height);
		this.width = (int) (this.maxWidth * scale);
		this.height = (int) (this.maxHeight * scale);

		// scale the speed
		// this.setMoveSpeed((int) (this.maxSpeed * scale));
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
		this.maxSpeed = maxSpeed;
	}

}
