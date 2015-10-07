package a_marblesolitare;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import t_templates.Launcher;
import b_models.BoardModel;
import b_models.Door;
import b_models.Maze;
import c_views.BoardView;
import c_views.GamePanel;
import d_controllers.PieceController;

/**
 * Launcher for MarbleSolitare
 * 
 * @author Jerry Swank
 */
public class MarbleSolitare extends Launcher {

	/**
	 * set the initial size of the window
	 */
	public static Dimension winSize;

	/**
	 * set the initial size of the board
	 */
	public static Dimension boardSize;

	/**
	 * Initial window title
	 */
	public static final String initialTitle = "Marble Solitare";

	/**
	 * get the screen dimensions
	 */
	private static final Dimension screenSize = Toolkit.getDefaultToolkit()
			.getScreenSize();

	/**
	 * Number of rows/columns on board
	 */
	private static final int boardRows = 3;

	/**
	 * 
	 * @param view
	 * @param maze
	 * @param door
	 */
	public MarbleSolitare(GamePanel view, Maze maze, Door door) {
		super(view, maze, door);
		// make the window dimensions equal to the shortest screen dimension
		int size = Math.min((int) screenSize.getWidth(),
				(int) screenSize.getHeight());
		winSize = new Dimension(3 * size / 2, 3 * size / 4);
		boardSize = new Dimension(winSize.height, winSize.height);
	}

	/**
	 * Creates and displays the GUI
	 */
	public void createAndShowGUI() {
		BoardModel model = new BoardModel(boardRows);
		BoardView view = new BoardView(boardSize, model);

		view.setTitle(initialTitle);

		PieceController controller = new PieceController(model, view);

		// register controller as the listener
		view.registerListener(controller);
		// start it up
		view.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		// set the window in the center of the screen
		view.setLocation(screenSize.width / 2 - boardSize.width / 2,
				screenSize.height / 2 - boardSize.height / 2);
		view.pack();
		view.setResizable(false);
		view.setVisible(true);
		view.repaint();
	}
}
