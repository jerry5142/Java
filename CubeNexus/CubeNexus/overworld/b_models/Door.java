package b_models;

import i_images.OverWorldImages;

import java.awt.Rectangle;

import t_templates.Launcher;
import t_templates.LauncherCallback;

public class Door extends BoundedShape implements LauncherCallback {

	private static final long serialVersionUID = 1L;

	/**
	 * Coordinate definitions for
	 */
	public static enum POSITION {
		LEFT, RIGHT, TOP, BOTTOM
	};

	/**
	 * Position of the door
	 */
	private POSITION position;

	/**
	 * Door that is the other side of this door
	 */
	private Door otherSideOfDoor;

	/**
	 * Determines if the door is locked
	 */
	private boolean locked;

	/**
	 * Image for the open door
	 */
	private OverWorldImages openDoor;

	/**
	 * Image for the closed door
	 */
	private OverWorldImages closedDoor;

	/**
	 * Game Launcher for this door
	 */
	private Launcher game;

	/**
	 * True when a game is in progress
	 */
	private boolean gameInProgress;

	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param closedDoor
	 * @param openDoor
	 * @param popupMessage
	 * @param enclosure
	 */
	public Door(int x, int y, int width, int height,
			OverWorldImages closedDoor, OverWorldImages openDoor,
			String popupMessage, Rectangle enclosure) {
		this(POSITION.LEFT, width, height, closedDoor, openDoor, popupMessage,
				enclosure);
		this.setLocation(x, y);

	}

	/**
	 * Convenience constructor using Door position enum definitions
	 * 
	 * @param position
	 * @param width
	 * @param height
	 * @param closedDoor
	 * @param openDoor
	 * @param popupMessage
	 * @param enclosure
	 */
	public Door(POSITION position, int width, int height,
			OverWorldImages closedDoor, OverWorldImages openDoor,
			String popupMessage, Rectangle enclosure) {
		super(1, 1, width, height, null, popupMessage, enclosure);

		int x, y;
		switch (position) {
		case LEFT:
			x = 1;
			y = enclosure.height / 2 - height / 2;
			break;
		case RIGHT:
			x = enclosure.width - width - 1;
			y = enclosure.height / 2 - height / 2;
			break;
		case TOP:
			x = enclosure.width / 2 - width / 2;
			y = 1;
			break;
		case BOTTOM:
			x = enclosure.width / 2 - width / 2;
			y = enclosure.height - height - 1;
			break;
		default:
			x = 1;
			y = 1;
		}

		this.position = position;
		if (this.moveShapeToLocation(x, y)) {
			setMessagePoint(this.x - getMessage().length(),
					this.getCenterPoint().y);
		}

		if (closedDoor == OverWorldImages.DOORLOCKED)
			this.locked = true;
		else
			this.locked = false;

		// linked to itself by default
		this.otherSideOfDoor = this;
		this.openDoor = openDoor;
		this.closedDoor = closedDoor;
		this.game = null;
		this.gameInProgress = false;

		this.setImageType(openDoor);
		this.setImageType(closedDoor);
	}

	/**
	 * @return the locked
	 */
	public boolean isLocked() {
		return locked;
	}

	/**
	 * @param locked
	 *            the locked to set
	 */
	public void setLocked(boolean locked) {
		this.locked = locked;
	}

	/**
	 * If the door is unlocked, then the current image is set to the open door
	 * image
	 * 
	 * @return true if the door was opened
	 */
	public boolean openDoor() {
		if (isLocked())
			return false;

		setImageType(openDoor);
		return true;
	}

	/**
	 * @return true if the door is open
	 */
	public boolean isOpen() {
		return this.getImageType() == openDoor;
	}

	/**
	 * Sets the current image to the closed door image
	 */
	public void closeDoor() {
		setImageType(closedDoor);
	}

	/**
	 * @return the position
	 */
	public POSITION getPosition() {
		return position;
	}

	/**
	 * @param position
	 *            the position to set
	 */
	public void setPosition(POSITION position) {
		this.position = position;
	}

	/**
	 * @return the otherSideOfDoor
	 */
	public Door getOtherSideOfDoor() {
		return otherSideOfDoor;
	}

	/**
	 * @param otherSideOfDoor
	 *            the otherSideOfDoor to set
	 */
	public void setOtherSideOfDoor(Door otherSideOfDoor) {
		this.otherSideOfDoor = otherSideOfDoor;
	}

	/**
	 * From LauncherCallback Interface
	 * 
	 * Unlock the door if the player won the subgame
	 */
	@Override
	public void gameOutcome(boolean gameWon) {
		if (gameWon) {
			setLocked(false);
			openDoor();
		}
		gameInProgress = false;
	}

	/**
	 * @return the game
	 */
	public Launcher getGame() {
		return game;
	}

	/**
	 * @param game
	 *            the game to set
	 */
	public void setGame(Launcher game) {
		if (game == null)
			throw new IllegalArgumentException("game is null");

		this.game = game;
	}

	/**
	 * Starts the game associated with this door
	 */
	public void playGame() {
		if (game == null || gameInProgress)
			return;

		gameInProgress = true;
		game.playGame();
	}

	/**
	 * Moves the shape so that the door does not contain the shape's center
	 * point.
	 * 
	 * @param shape
	 */
	public void moveShapeOutside(BoundedShape shape) {
		switch (this.position) {
		case TOP:
			shape.moveShapeToLocation(
					(int) this.getCenterX() - shape.width / 2, this.y
							+ this.height);
			break;
		case BOTTOM:
			shape.moveShapeToLocation(
					(int) this.getCenterX() - shape.width / 2, this.y
							- shape.height);
			break;
		case LEFT:
			shape.moveShapeToLocation(this.x + this.width,
					(int) this.getCenterY() - shape.height / 2);
			break;
		case RIGHT:
			shape.moveShapeToLocation(this.x - shape.width,
					(int) this.getCenterY() - shape.height / 2);
			break;

		default:
			break;
		}
	}
}
