package t_templates;

import i_images.OverWorldImages;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import b_models.Door;
import b_models.Maze;
import c_views.GamePanel;

/**
 * Disables the Maze panel and launches whatever application is initialized in
 * createAndShowGUI(). When the application is closed, the Maze panel is
 * reactivated.
 * 
 * @author Jerry Swank
 * 
 */
public abstract class Launcher extends WindowAdapter {
	/**
	 * Overworld view
	 */
	private GamePanel view;

	/**
	 * Overworld maze
	 */
	private Maze maze;

	/**
	 * Holds the win/lost status of the game
	 */
	private boolean wonGame;

	/**
	 * True when the game is over
	 */
	private boolean gameOver;

	/**
	 * Door that called this launcher
	 */
	private Door door;

	/**
	 * Launches the game defined in createAndShowGUI()
	 * 
	 * @param view
	 * @param maze
	 * @param door
	 *            the door that holds the game launcher
	 */
	public Launcher(GamePanel view, Maze maze, Door door) {
		if (view == null)
			throw new IllegalArgumentException("view is null");
		if (maze == null)
			throw new IllegalArgumentException("maze is null");
		if (door == null)
			throw new IllegalArgumentException("door is null");

		this.view = view;
		this.maze = maze;
		this.gameOver = false;
		this.wonGame = false;
		this.door = door;
	}

	/**
	 * Activates the game
	 */
	public void playGame() {
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
	 * Used to initialize a JFrame
	 * 
	 * Add game initialization code here
	 */
	public abstract void createAndShowGUI();

	/**
	 * Frees all resources associated with the view and closes the associated
	 * JFrame. Must use JFrame.DISPOSE_ON_CLOSE when initializing the view
	 * within createAndShowGUI() in order to return to the calling window.
	 * 
	 * view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	 * 
	 * @param window
	 */
	public static void closeWindow(JFrame window) {
		window.dispose();
	}

	/**
	 * Adds the object to the players inventory
	 * 
	 * @param thing
	 */
	public void addPlayerStuff(OverWorldImages thing) {
		if (thing == null)
			return;
		maze.getPlayer().addShape(thing);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		this.gameOver = false;
		view.getSoundPlayer().stop();
		maze.getPlayer().stopPlayer();
		view.setEnabled(false);
	}

	@Override
	public void windowClosed(WindowEvent e) {
		door.gameOutcome(wonGame);
		view.setEnabled(true);
		view.getMazePanel().requestFocus();
		view.getSoundPlayer().loop();
		door.moveShapeOutside(maze.getPlayer());
	}

	/**
	 * @param wonGame
	 *            true = player won the game
	 */
	public void setWonGame(boolean wonGame) {
		this.wonGame = wonGame;
	}

	/**
	 * @return the gameOver
	 */
	public boolean isGameOver() {
		return gameOver;
	}

	/**
	 * @param gameOver
	 *            the gameOver to set
	 */
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}

}
