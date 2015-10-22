package c_views;

import i_images.OverWorldImages;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.KeyListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

import s_sounds.OverWorldSounds;
import s_sounds.SoundClipPlayer;
import a_marblesolitare.MarbleSolitare;
import b_models.BoundedShape;
import b_models.Maze;
import b_models.Player;
import b_models.Room;
import b_models.SelfMovingShape;
import d_controllers.PlayerController;

/**
 * View for the game
 */
public class GamePanel extends JFrame {
	/**
	 * Added to shut the compiler up
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Random for drawing objects in random locations
	 */
	private static final Random rand = new Random();

	/**
	 * the model of this MVC example
	 */
	private Maze maze;

	/**
	 * the JPanel where the user can paint
	 */
	private MazePanel mazePanel;

	/**
	 * Refreshes the panel periodically
	 */
	private MazePanelAnimator animator;

	/**
	 * Sound player
	 */
	private SoundClipPlayer soundPlayer;

	/**
	 * keyboard panel listener
	 */
	private KeyListener listener;

	/**
	 * Panel used to display a grid view of the maze
	 */
	private MazeOverview overview;

	/**
	 * Create and organize the components of the window.
	 * 
	 * @param windowSize
	 * @param maze
	 * @param playerSpeed
	 * @param refreshDelay
	 *            delay between MazePanel refreshes
	 */
	public GamePanel(Dimension windowSize, Maze maze, int playerSpeed,
			int refreshDelay) {
		super();
		if ((this.maze = maze) == null || refreshDelay < 0)
			throw new IllegalArgumentException(
					"maze is null and/or refreshDelay is < 0");

		this.setLayout(new BorderLayout());
		this.setSize(windowSize);

		// initialize the player and maze rooms
		Rectangle roomSize = maze.getRoomSize();

		// add player
		maze.setPlayer(new Player(20, 20, 30, 50, playerSpeed,
				OverWorldImages.PLAYERDOWN, "Hi", roomSize,
				OverWorldSounds.HIGHLIGHT));

		// main directions
		maze.getPlayer().setImageDown(OverWorldImages.PLAYERDOWN);
		maze.getPlayer().setImageUp(OverWorldImages.PLAYERUP);
		maze.getPlayer().setImageLeft(OverWorldImages.PLAYERLEFT);
		maze.getPlayer().setImageRight(OverWorldImages.PLAYERRIGHT);
		// diagonals (same as main, need to get more images)
		maze.getPlayer().setImageLowerLeft(OverWorldImages.PLAYERDOWN);
		maze.getPlayer().setImageLowerRight(OverWorldImages.PLAYERDOWN);
		maze.getPlayer().setImageUpperLeft(OverWorldImages.PLAYERUP);
		maze.getPlayer().setImageUpperRight(OverWorldImages.PLAYERUP);

		customizeRooms();

		// Add a panel to draw on.
		this.mazePanel = new MazePanel(maze, this);
		this.mazePanel.setFocusable(true); // set the focus on the room

		try {
			this.animator = new MazePanelAnimator(refreshDelay, this);
			this.animator.execute();
		} catch (Exception e) {
			System.err.println("Animator exception");
		}
		this.soundPlayer = new SoundClipPlayer(
				OverWorldSounds.BACKGROUND.getSoundClip());
		this.soundPlayer.loop(); // play background music

		add(mazePanel, BorderLayout.CENTER);

		// add overview panel
		JPanel westPanel = new JPanel(new GridLayout(2, 1));
		westPanel.setBackground(Color.LIGHT_GRAY);
		overview = new MazeOverview(this.getWidth() / 4, maze);
		westPanel.add(overview);
		add(westPanel, BorderLayout.WEST);
	} // end constructor

	/**
	 * Register the controller as the listener to the JList and the MousePanel.
	 * 
	 * @param listener
	 */
	public void registerListener(PlayerController listener) {
		this.listener = listener;
		mazePanel.addKeyListener(listener);
	}

	/**
	 * @return the soundPlayer
	 */
	public SoundClipPlayer getSoundPlayer() {
		return soundPlayer;
	}

	/**
	 * Do any room customization here
	 */
	private void customizeRooms() {
		// add other stuff

		// Create game launchers
		Room room = null;

		room = maze.getRoom(0);
		room.getLeftDoor().setGame(
				new MarbleSolitare(this, maze, room.getLeftDoor()));
		// for (int i = 0; i < maze.getNumberOfRooms(); i++) {
		// room = maze.getRoom(i);
		// room.getLeftDoor().setGame(
		// new Launcher2048(this, maze, room.getLeftDoor()));
		// room.getRightDoor().setGame(
		// new Launcher2048(this, maze, room.getRightDoor()));
		// room.getTopDoor().setGame(
		// new LauncherNS(this, maze, room.getTopDoor()));
		// room.getBottomDoor().setGame(
		// new LauncherNS(this, maze, room.getBottomDoor()));
		// }

		room = maze.getRoom(rand.nextInt(maze.getNumberOfRooms()));
		room.addShape(new BoundedShape(60, 300, 60, 60, OverWorldImages.WIZARD,
				"Beware my powers!", maze.getRoomSize()));
		room.setBackgroundImage(OverWorldImages.FLOORPLANK);

		room = maze.getRoom(rand.nextInt(maze.getNumberOfRooms()));
		room.addShape(new BoundedShape(250, 85, 60, 60, OverWorldImages.ROBOT,
				"I just broke the 3 laws.", maze.getRoomSize()));
		room.setBackgroundImage(OverWorldImages.FLOORTILEDMULTI);

		room = maze.getRoom(rand.nextInt(maze.getNumberOfRooms()));
		room.addShape(new BoundedShape(60, 400, 60, 60,
				OverWorldImages.PLAYERSPIN, "what am I", maze.getRoomSize()));

		room = maze.getRoom(0);
		room.addShape(new SelfMovingShape(300, 300, 60, 60, 1,
				OverWorldImages.ZOMBIE, "Brains!", maze.getRoomSize(),
				OverWorldSounds.ZOMBIE));
	}

	/**
	 * @return the mazePanel
	 */
	public MazePanel getMazePanel() {
		return mazePanel;
	}

	/**
	 * @return the animator
	 */
	public MazePanelAnimator getAnimator() {
		return animator;
	}

	/**
	 * @return the listener
	 */
	public KeyListener getListener() {
		return listener;
	}

	/**
	 * @return the overview
	 */
	public MazeOverview getOverview() {
		return overview;
	}

}
