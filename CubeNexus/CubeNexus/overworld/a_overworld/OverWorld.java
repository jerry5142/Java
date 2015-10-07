package a_overworld;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import m_menus.MenuHandler;
import b_models.Maze;
import c_views.GamePanel;
import d_controllers.PlayerController;

/**
 * Launcher for OverWorld of CubeNex game
 * 
 * @author Jerry Swank
 */
public class OverWorld {
	/**
	 * set the initial size of the window
	 */
	public static Dimension winSize;

	/**
	 * set the initial size of the rooms
	 */
	public static Dimension roomSize;

	/**
	 * Initial window title
	 */
	public static final String initialTitle = "Cube Nexus";

	/**
	 * get the screen dimensions
	 */
	private static final Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	/**
	 * Number of rows/columns in maze. Total rooms = mazeRows*mazeRows
	 */
	private static final int mazeRows = 5;

	/**
	 * Initial player speed
	 */
	private static final int initialPlayerSpeed = 5;

	/**
	 * Screen refresh delay in milliseconds
	 */
	private static final int refreshDelay = 10;

	/**
	 * main method starts the application
	 */
	public static void main(String[] args) {
		// make the window dimensions equal to the shortest screen dimension
		int size = Math.min((int) screenSize.getWidth(),
				(int) screenSize.getHeight());
		winSize = new Dimension(3 * size / 2, 3 * size / 4);
		roomSize = new Dimension(winSize.height, winSize.height);

		/**
		 * dispatch the GUI via the Event Dispatch Thread
		 */
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				createAndShowGUI();
			}
		});
	}

	/**
	 * Creates and displays the GUI
	 */
	public static void createAndShowGUI() {
		Maze maze = new Maze(roomSize, mazeRows);
		GamePanel view = new GamePanel(winSize, maze, initialPlayerSpeed,
				refreshDelay);

		view.setName(initialTitle);

		PlayerController controller = new PlayerController(maze);
		new MenuHandler(maze, view); // Handles menu commands

		// register controller as the listener
		view.registerListener(controller);
		// start it up
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// set the window in the center of the screen
		view.setLocation(screenSize.width / 2 - 3 * winSize.width / 8,
				screenSize.height / 2 - winSize.height / 2);
//		view.setLocationRelativeTo(null);
		
		view.pack();
		view.setResizable(false);
		view.setVisible(true);
		view.repaint();
	}
}
