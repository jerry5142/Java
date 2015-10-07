package c_views;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import a_overworld.OverWorld;
import b_models.BoundedShape;
import b_models.Maze;
import b_models.SpaceShip;
import b_models.Room;
import d_controllers.Interactions;

/**
 * Panel where the game drawing takes place
 * 
 * @author Jerry Swank
 * 
 */
public class MazePanel extends JPanel {
	private static final long serialVersionUID = 1L;

	/**
	 * The maze object containing the player and the room to draw
	 */
	private Maze maze;

	/**
	 * Holds the view
	 */
	private GamePanel view;

	/**
	 * Holds the current room object
	 */
	private Room currentRoom;

	/**
	 * Holds the player object
	 */
	private SpaceShip player;

	/**
	 * Holds the shape the player may be interacting with at a given time
	 */
	private BoundedShape otherShape;

	/**
	 * Constructor
	 * 
	 * @param maze
	 * @param view
	 */
	public MazePanel(Maze maze, GamePanel view) {
		if ((this.maze = maze) == null || (this.view = view) == null)
			throw new IllegalArgumentException("Maze and/or view is null");

		if (maze.getPlayer() == null)
			throw new IllegalArgumentException(
					"Maze player is null. Please initialize the player before creating the maze panel");

		this.player = maze.getPlayer();
		this.otherShape = null;
		this.currentRoom = null;
		this.setPreferredSize(maze.getRoomSize().getSize());
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.setDoubleBuffered(true);
	}

	/**
	 * Draw the shapes in the model
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears drawing area
		// player = maze.getPlayer();
		player.movePlayer(); // update the player's position
		currentRoom = maze.getCurrentRoom();
		g.setColor(Color.WHITE);
		g.drawString("" + currentRoom.getRoomNumber(),
				currentRoom.getRoomWidth() / 2, currentRoom.getRoomHeight() / 2);

		// draw the background image if one exists
		if (currentRoom.hasBackgroundImage()) {
			g.drawImage(currentRoom.getBackgroundImage(), 0, 0,
					this.getWidth(), this.getHeight(), this);
		} else {
			setBackground(currentRoom.getBackgroundColor());
			view.setTitle(OverWorld.initialTitle + ": Room #"
					+ currentRoom.getRoomNumber());
		}

		// draw the shapes
		for (int i = 0; i < currentRoom.getNumberOfShapes(); i++) {
			otherShape = currentRoom.getShape(i);
			otherShape.draw(g);
			player.draw(g);
			// call interaction if needed
			if (player.intersectsShape(otherShape)) {
				Interactions.interact(maze, view, player, otherShape, g);
			}
		}
	}
}