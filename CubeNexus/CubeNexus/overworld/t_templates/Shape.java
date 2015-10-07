package t_templates;

import i_images.OverWorldImages;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Basic shape class that displays an image. All other shape classes are derived
 * from this one.
 * 
 * @author Jerry Swank
 * 
 */
public abstract class Shape extends Rectangle implements DrawableShape {

	private static final long serialVersionUID = 1L;

	/**
	 * Color to fill the bounding rectangle with if there is no icon
	 */
	private Color defaultColor;

	/**
	 * The image to use for the shape
	 */
	private Image iconImage;

	/**
	 * List of Image names
	 */
	private ArrayList<OverWorldImages> imageTypes;

	/**
	 * Index for the current image
	 */
	private int currentImage;

	/**
	 * Number of the room the shape belongs to
	 */
	private int roomNumber;

	/**
	 * Determines if the shape is shown
	 */
	private boolean visible;

	/**
	 * Message to display when this shope intersects another shape
	 */
	private String message;

	/**
	 * Determines where the message is shown
	 */
	private Point messagePoint;

	/**
	 * Create a new shape
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param color
	 *            color to use if no image is available
	 * @param imageType
	 *            the image registered in OverWorldImages to use
	 * @param popupMessage
	 *            interaction message to display
	 */
	public Shape(int x, int y, int width, int height, Color color,
			OverWorldImages imageType, String popupMessage) {
		super(x, y, width, height);
		this.defaultColor = color;
		this.imageTypes = new ArrayList<OverWorldImages>(1);
		if (imageType != null) {
			this.imageTypes.add(imageType);
			this.iconImage = imageType.getIconImage();
		}
		this.currentImage = 0;
		this.roomNumber = 0;
		this.visible = true;
		this.messagePoint = new Point(x, y);
		setMessage(popupMessage);
	}

	/**
	 * @return the color
	 */
	public Color getColor() {
		return defaultColor;
	}

	/**
	 * @param color
	 *            the color to set
	 */
	public void setColor(Color color) {
		this.defaultColor = color;
	}

	/**
	 * @return the visible
	 */
	public boolean isVisible() {
		return visible;
	}

	/**
	 * @param visible
	 *            the visible to set
	 */
	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	/**
	 * Checks if an image has been set for this shape
	 * 
	 * @return true if an image has been set
	 */
	public boolean hasImage() {
		return this.iconImage != null;
	}

	/**
	 * @return the iconImage
	 */
	public Image getIconImage() {
		return iconImage;
	}

	/**
	 * @param iconImage
	 *            the iconImage to set
	 */
	public void setIconImage(Image iconImage) {
		this.iconImage = iconImage;
	}

	/**
	 * @return the current imageType
	 */
	public OverWorldImages getImageType() {
		return imageTypes.get(currentImage);
	}

	/**
	 * @param imageType
	 *            the imageType to set
	 */
	public void setImageType(OverWorldImages imageType) {
		if (imageType == null)
			return;

		int index = imageTypes.indexOf(imageType);

		if (index == -1) { // add if not in list
			imageTypes.add(imageType);
			currentImage = imageTypes.size() - 1;
		} else {
			currentImage = index;
		}

		setIconImage(imageType.getIconImage());
	}

	/**
	 * Draws the icon image if their is one.
	 * 
	 * @param g
	 *            the graphics instance to draw on
	 */
	public void drawIconImage(Graphics g) {
		if (g == null)
			throw new IllegalArgumentException("Graphics g is null");

		if (isVisible()) {
			if (hasImage()) {
				g.drawImage(this.iconImage, this.x, this.y, this.width,
						this.height, null);
			} else {
				g.setColor(defaultColor);
				g.fillRect(x, y, width, height);
			}
		}
	}

	/**
	 * 
	 * @return a Rectangle representing the bounds of the shape
	 */
	@Override
	public Rectangle getShapeBounds() {
		return this.getBounds();
	}

	/**
	 * 
	 * @param otherShape
	 *            Shape object
	 * @return true if this shape intersects otherShape
	 */
	@Override
	public boolean intersectsShape(Shape otherShape) {
		return this.intersects(otherShape);
	}

	/**
	 * @return the point at the center of the shape
	 */
	public Point getCenterPoint() {
		return new Point((int) this.getCenterX(), (int) this.getCenterY());
	}

	/**
	 * @param popupMsg
	 *            the popupMsg to set
	 */
	public void setMessage(String popupMsg) {
		if (popupMsg == null)
			this.message = "";
		else
			this.message = popupMsg;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * shows the popup message
	 * 
	 * @param g
	 *            Graphics instance
	 */
	public void showMessage(Graphics g) {
		if (g == null)
			throw new IllegalArgumentException("Graphics g is null");

		g.setColor(defaultColor);
		g.drawString(this.toString(), messagePoint.x, messagePoint.y);
	}

	public void showMessage(Graphics g, String message) {
		if (g == null)
			throw new IllegalArgumentException("Graphics g is null");

		this.setMessage(message);
		showMessage(g);
	}

	/**
	 * @return the message to display
	 */
	@Override
	public String toString() {
		return message;
	}

	/**
	 * @return the roomNumber
	 */
	public int getRoomNumber() {
		return roomNumber;
	}

	/**
	 * @param roomNumber
	 *            the roomNumber to set
	 */
	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	/**
	 * Sets the message point
	 * 
	 * @param x
	 *            x-coordinate
	 * @param y
	 *            y-coordinate
	 */
	public void setMessagePoint(int x, int y) {
		this.messagePoint.x = x;
		this.messagePoint.y = y;
	}

	/**
	 * @param messagePoint
	 *            the messagePoint to set
	 */
	public void setMessagePoint(Point messagePoint) {
		setMessagePoint(messagePoint.x, messagePoint.y);
	}

}
