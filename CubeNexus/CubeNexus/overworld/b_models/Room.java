package b_models;

import i_images.OverWorldImages;

import java.awt.Color;
import java.awt.Image;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Constructor for a Room in the Maze
 * 
 * @author Jerry Swank
 * 
 */
public class Room {
	/**
	 * width of the room
	 */
	private int roomWidth;

	/**
	 * height of the room
	 */
	private int roomHeight;

	/**
	 * The index of the room in the maze
	 */
	private int roomNumber;

	/**
	 * Default doors
	 */
	private Door leftDoor, rightDoor, topDoor, bottomDoor;

	/**
	 * The background color of the painting
	 */
	private Color backgroundColor;

	/**
	 * background image to draw on the panel
	 */
	private Image backgroundImage;

	/**
	 * Image type
	 */
	private OverWorldImages imageType;

	/**
	 * An arrayList for shape references.
	 */
	private ArrayList<BoundedShape> shapes;

	/**
	 * Constructor for a room model. A room consists of objects and doors
	 * 
	 * @param roomWidth
	 * @param roomHeight
	 * @param backgroundColor
	 * @param backgroundImage
	 */
	public Room(int roomWidth, int roomHeight, Color backgroundColor,
			OverWorldImages backgroundImage) {
		super();

		this.shapes = new ArrayList<BoundedShape>(10);
		this.roomNumber = 0;
		this.leftDoor = null;
		this.rightDoor = null;
		this.topDoor = null;
		this.bottomDoor = null;

		setRoomWidth(roomWidth);
		setRoomHeight(roomHeight);
		setBackgroundColor(backgroundColor);
		setBackgroundImage(backgroundImage);
	}

	/**
	 * Convenience constructor
	 * 
	 * @param roomWidth
	 * @param roomHeight
	 */
	public Room(int roomWidth, int roomHeight) {
		this(roomWidth, roomHeight, null, null);
	}

	/**
	 * Checks if an index is valid for the given list
	 * 
	 * @param index
	 * @param list
	 * @return true if the index is valid
	 */
	private boolean isValidIndex(int index, ArrayList<BoundedShape> list) {
		if (index < 0 || index > list.size() - 1)
			return false;
		return true;
	}

	/**
	 * Add a BoundedShape to the shapes list.
	 * 
	 * @param shape
	 *            the BoundedShape to add.
	 */
	public void addShape(BoundedShape shape) {
		if (shape == null || !(shape instanceof BoundedShape))
			throw new IllegalArgumentException(
					"Shape must be non-null and an instance of BoundedShape");

		// check if the shape is a door
		if (shape instanceof Door) {
			Door door = (Door) shape;

			switch (door.getPosition()) {
			case LEFT:
				this.leftDoor = door;
				break;
			case RIGHT:
				this.rightDoor = door;
				break;
			case TOP:
				this.topDoor = door;
				break;
			case BOTTOM:
				this.bottomDoor = door;
				break;
			}
		}

		shape.setRoomNumber(this.roomNumber);
		shape.setEnclosure(new Rectangle(roomWidth, roomHeight));
		shapes.add(shape);
	}

	/**
	 * Removes a BoundedShape from the shapes list.
	 * 
	 * @param shape
	 *            the BoundedShape to remove.
	 */
	public void removeShape(BoundedShape shape) {
		if (shape == null)
			return;
		shapes.remove(shape);
	}

	/**
	 * Returns the shape at index i. Returns null if no such shape exists.
	 * 
	 * @param i
	 */
	public BoundedShape getShape(int i) {
		if (isValidIndex(i, shapes))
			return shapes.get(i);
		return null;
	}

	/**
	 * @return the backgroundColor
	 */
	public Color getBackgroundColor() {
		return backgroundColor;
	}

	/**
	 * Sets the background color. null is ignored
	 * 
	 * @param backgroundColor
	 *            the backgroundColor to set
	 */
	public void setBackgroundColor(Color backgroundColor) {
		if (backgroundColor == null)
			return;
		this.backgroundColor = backgroundColor;
	}

	/**
	 * @return the backgroundImage
	 */
	public Image getBackgroundImage() {
		return backgroundImage;
	}

	/**
	 * @param backgroundImage
	 *            the backgroundImage to set
	 */
	public void setBackgroundImage(OverWorldImages backgroundImage) {
		if (backgroundImage == null)
			return;
		this.imageType = backgroundImage;
		this.backgroundImage = backgroundImage.getIconImage();
	}

	/**
	 * @return the imageType
	 */
	public OverWorldImages getImageType() {
		return imageType;
	}

	/**
	 * @return the backgroundImage
	 */
	public boolean hasBackgroundImage() {
		return backgroundImage != null;
	}

	/**
	 * clear the shapes list
	 */
	public void clearShapes() {
		shapes.clear();
	}

	/**
	 * @return the number of shapes in the model
	 */
	public int getNumberOfShapes() {
		return shapes.size();
	}

	/**
	 * @return the roomWidth
	 */
	public int getRoomWidth() {
		return roomWidth;
	}

	/**
	 * @param roomWidth
	 *            the roomWidth to set
	 */
	public void setRoomWidth(int roomWidth) {
		if (roomWidth < 1)
			return;
		this.roomWidth = roomWidth;
	}

	/**
	 * @return the roomHeight
	 */
	public int getRoomHeight() {
		return roomHeight;
	}

	/**
	 * @param roomHeight
	 *            the roomHeight to set
	 */
	public void setRoomHeight(int roomHeight) {
		if (roomHeight < 1)
			return;
		this.roomHeight = roomHeight;
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
		for (BoundedShape shape : shapes) {
			shape.setRoomNumber(roomNumber);
		}
	}

	/**
	 * @return the leftDoor
	 */
	public Door getLeftDoor() {
		return leftDoor;
	}

	/**
	 * @param leftDoor
	 *            the leftDoor to set
	 */
	public void setLeftDoor(Door leftDoor) {
		this.leftDoor = leftDoor;
	}

	/**
	 * @return the rightDoor
	 */
	public Door getRightDoor() {
		return rightDoor;
	}

	/**
	 * @param rightDoor
	 *            the rightDoor to set
	 */
	public void setRightDoor(Door rightDoor) {
		this.rightDoor = rightDoor;
	}

	/**
	 * @return the topDoor
	 */
	public Door getTopDoor() {
		return topDoor;
	}

	/**
	 * @param topDoor
	 *            the topDoor to set
	 */
	public void setTopDoor(Door topDoor) {
		this.topDoor = topDoor;
	}

	/**
	 * @return the bottomDoor
	 */
	public Door getBottomDoor() {
		return bottomDoor;
	}

	/**
	 * @param bottomDoor
	 *            the bottomDoor to set
	 */
	public void setBottomDoor(Door bottomDoor) {
		this.bottomDoor = bottomDoor;
	}

}
