package b_models;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Rectangle;
import java.util.ArrayList;

/**
 * Holds all the rooms in the game
 * 
 * @author Jerry Swank
 */
public class Maze {
	/**
	 * Holds the game rooms
	 */
	private ArrayList<Room> maze;

	/**
	 * Holds the index of the current room. Defaults to the last room added to
	 * the maze.
	 */
	private int currentRoom;

	/**
	 * Player object
	 */
	private SpaceShip player;

	/**
	 * Determines the size of the rooms
	 */
	private Rectangle roomSize;

	/**
	 * Number of rows and columns in the maze
	 */
	private int mazeWidth;

	/**
	 * Background colors
	 */
	private static final Color[] colors = { Color.BLUE, Color.CYAN,
			Color.GREEN, Color.GRAY, Color.MAGENTA, Color.ORANGE, Color.PINK,
			Color.RED, Color.YELLOW };

	/**
	 * Convenience Constructor for empty maze
	 * 
	 * @param roomSize
	 */
	public Maze(Dimension roomSize) {
		this(roomSize, 1);
	}

	/**
	 * Constructor: Creates a maze with mazeWidth columns of rooms by mazeWidth
	 * rows of rooms. Total number of rooms is mazeWidth squared.
	 * 
	 * @param roomSize
	 * @param mazeWidth
	 */
	public Maze(Dimension roomSize, int mazeWidth) {
		super();
		if (mazeWidth < 1)
			throw new IllegalArgumentException("mazeWidth is < 1");

		this.mazeWidth = mazeWidth;
		this.maze = new ArrayList<Room>();
		this.setCurrentRoom(0);
		this.player = null;
		this.roomSize = new Rectangle(roomSize);

		Room room;
		for (int i = 0; i < mazeWidth * mazeWidth; i++) {
			room = new DefaultRoom();
			room.setBackgroundColor(colors[i % colors.length]);
			this.addRoom(room);
		}

		// link the doors in each room to the
		// corresponding door in adjacent rooms
		linkRooms(mazeWidth);
	}

	/**
	 * @return true if there are no rooms in the maze
	 */
	public boolean isEmpty() {
		return maze.size() == 0;
	}

	/**
	 * @param index
	 * @return true if the index is valid
	 */
	private boolean validIndex(int index) {
		if (index < 0 || index > maze.size() - 1)
			return false;
		return true;
	}

	/**
	 * Adds the Room to the maze
	 * 
	 * @param room
	 */
	public void addRoom(Room room) {
		if (room == null)
			return;
		room.setRoomNumber(maze.size());
		maze.add(room);
	}

	/**
	 * @return the currentRoom if one exists, null otherwise
	 */
	public Room getCurrentRoom() {
		if (isEmpty())
			return null;
		return maze.get(currentRoom);
	}

	/**
	 * Sets the current room index
	 * 
	 * @param roomIndex
	 * @return true if the index was valid
	 */
	public boolean setCurrentRoom(int roomIndex) {
		if (validIndex(roomIndex)) {
			this.currentRoom = roomIndex;
			return true;
		}
		return false;
	}

	/**
	 * @return the player
	 */
	public SpaceShip getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(SpaceShip player) {
		if (player == null)
			return;
		this.player = player;
	}

	/**
	 * Set the current room to the room on the other side of the currently
	 * engaged door
	 * 
	 * @param door
	 * @return Room the current room after passing through the door
	 */
	public Room nextRoom(Door door) {
		setCurrentRoom(door.getOtherSideOfDoor().getRoomNumber());
		return getRoom(currentRoom);
	}

	/**
	 * @param roomIndex
	 * @return the Room at roomIndex
	 */
	public Room getRoom(int roomIndex) {
		if (roomIndex < 0 || roomIndex >= maze.size())
			throw new IndexOutOfBoundsException();

		return maze.get(roomIndex);
	}

	/**
	 * @return the roomSize
	 */
	public Rectangle getRoomSize() {
		return roomSize;
	}

	/**
	 * @param roomSize
	 *            the roomSize to set
	 */
	public void setRoomSize(Rectangle roomSize) {
		this.roomSize = roomSize;
	}

	/**
	 * @return the mazeWidth
	 */
	public int getMazeWidth() {
		return mazeWidth;
	}

	/**
	 * @return the number of rooms in the maze
	 */
	public int getNumberOfRooms() {
		return maze.size();
	}

	/**
	 * Links the default doors in each room to the appropriate door in adjacent
	 * rooms
	 * 
	 * @param n
	 *            number of rooms wide or high the maze is
	 */
	private void linkRooms(int n) {
		if (maze.size() < 2)
			return;

		for (Room room : maze) {
			int rmNum = room.getRoomNumber();
			int otherRoom = 0;
			// determines if a room is on the perimeter of the maze
			boolean onTopRow = rmNum < n;
			boolean onBottomRow = rmNum >= n * (n - 1);
			boolean onLeftSide = rmNum % n == 0;
			boolean onRightSide = (rmNum + 1) % n == 0;

			// top door
			if (onTopRow) {
				// link to the door on the opposite side of the maze
				otherRoom = n * (n - 1) + rmNum;
			} else {
				otherRoom = rmNum - n;
			}
			// link top door to bottom door of room above
			room.getTopDoor().setOtherSideOfDoor(
					maze.get(otherRoom).getBottomDoor());

			// bottom door
			if (onBottomRow) {
				// link to the door on the opposite side of the maze
				otherRoom = rmNum % n;
			} else {
				otherRoom = rmNum + n;
			}
			// link bottom door to top door of room below
			room.getBottomDoor().setOtherSideOfDoor(
					maze.get(otherRoom).getTopDoor());

			// left door
			if (onLeftSide) {
				// link to the door on the opposite side of the maze
				otherRoom = rmNum + (n - 1);
			} else {
				otherRoom = rmNum - 1;
			}
			// link left door to right door of previous room
			room.getLeftDoor().setOtherSideOfDoor(
					maze.get(otherRoom).getRightDoor());

			// right door
			if (onRightSide) {
				// link to the door on the opposite side of the maze
				otherRoom = rmNum - (n - 1);
			} else {
				otherRoom = rmNum + 1;
			}
			// link left door to right door of previous room
			room.getRightDoor().setOtherSideOfDoor(
					maze.get(otherRoom).getLeftDoor());

		}

	}
}
