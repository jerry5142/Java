package c_views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import b_models.Maze;
import b_models.Room;

/**
 * Provides an overview of the maze and the player's position in it.
 * 
 * @author Jerry Swank
 * 
 */
public class MazeOverview extends JPanel {

	private static final long serialVersionUID = 1L;

	/**
	 * the model of this MVC example
	 */
	private Maze maze;

	/**
	 * Holds the panels used to display the rooms
	 */
	private ArrayList<RoomPanel> roomPanels;

	/**
	 * Number of rooms
	 */
	private int numberOfRooms;

	/**
	 * Constructor
	 * 
	 * @param panelSize
	 * @param maze
	 */
	public MazeOverview(int panelSize, Maze maze) {
		super();
		if (panelSize <= 0 || (this.maze = maze) == null)
			throw new IllegalArgumentException(
					"panelSize is negative and/or maze is null");

		this.setBackground(Color.BLACK);

		int n = maze.getMazeWidth();
		this.numberOfRooms = maze.getNumberOfRooms();
		this.roomPanels = new ArrayList<MazeOverview.RoomPanel>(numberOfRooms);
		this.setPreferredSize(new Dimension(panelSize, panelSize));
		this.setLayout(new GridLayout(n, n, 2, 2));
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

		RoomPanel rp;
		for (int i = 0; i < numberOfRooms; i++) {
			rp = new RoomPanel(maze.getRoom(i), panelSize / n);
			roomPanels.add(rp);
			this.add(rp);
		}
	}

	/**
	 * Refreshes the player's position in the maze overview
	 */
	public void refreshPlayer() {
		for (RoomPanel rp : roomPanels) {
			rp.repaint();
		}
	}

	/**
	 * Used to draw the rooms on the maze overview
	 * 
	 * @author Jerry Swank
	 * 
	 */
	private class RoomPanel extends JPanel {
		private static final long serialVersionUID = 1L;

		/**
		 * Room to draw
		 */
		private Room room;

		/**
		 * Width and Height of the panel
		 */
		private int size;

		/**
		 * Constructor
		 * 
		 * @param maze
		 */
		public RoomPanel(Room room, int size) {
			if ((this.room = room) == null || size <= 0)
				throw new IllegalArgumentException(
						"Maze or room is null and/or scaleFactor is not positive");

			this.size = size;
			this.setPreferredSize(new Dimension(room.getRoomWidth() * size,
					room.getRoomHeight() * size));
			this.setDoubleBuffered(true);
		}

		/**
		 * Draw the shapes in the model
		 */
		public void paintComponent(Graphics g) {
			super.paintComponent(g); // clears drawing area

			// draw the background image if one exists
			if (room.hasBackgroundImage()) {
				g.drawImage(room.getBackgroundImage(), 0, 0, this.getWidth(),
						this.getHeight(), this);
			} else {
				setBackground(room.getBackgroundColor());
			}

			g.setColor(Color.WHITE);
			g.drawString("" + room.getRoomNumber(), size / 2, size / 2);

			if (room == maze.getCurrentRoom()) {
				maze.getPlayer().drawInCenter(g, this.getBounds());
			}
		}
	}
}
