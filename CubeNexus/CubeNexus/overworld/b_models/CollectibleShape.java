package b_models;

import i_images.OverWorldImages;

import java.awt.Rectangle;

/**
 * Used to distinguish shapes that can be collected by the player.
 * 
 * @author Jerry Swank
 * 
 */
public class CollectibleShape extends BoundedShape {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	 */
	public CollectibleShape(int x, int y, int width, int height,
			OverWorldImages imageType, String popupMessage, Rectangle enclosure) {
		super(x, y, width, height, imageType, popupMessage, enclosure);
	}

}
