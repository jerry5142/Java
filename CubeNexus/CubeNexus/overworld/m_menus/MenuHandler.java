package m_menus;

import javax.swing.JFrame;

import b_models.Maze;
import c_views.GamePanel;

/**
 * Handles Menu events declared in the Menus class.
 * 
 * @author Jerry Swank
 * 
 */
public class MenuHandler extends Menus<JFrame> {
	/**
	 * demo model reference
	 */
	private Maze maze;

	/**
	 * demo view reference
	 */
	private GamePanel view;

	/**
	 * Constructor
	 * 
	 * @param maze
	 * @param view
	 */
	public MenuHandler(Maze maze, GamePanel view) {
		super(view);

		if (maze == null || view == null)
			throw new IllegalArgumentException("maze and or view are null");

		this.maze = maze;
		this.view = view;
	}

	@Override
	public void handleMenuEvent(MenuDefs menuItem) {

		switch (menuItem) {

		case NEWGAME:

			break;

		case NEWLIFE:
			maze.getPlayer().revivePlayer();
			view.repaint();
			break;

		case PAUSEGAME:
			boolean isPaused = view.getAnimator().isPaused();
			if (isPaused) {// restart if currently paused
				view.getSoundPlayer().loop();
				view.getAnimator().setPaused(false);
			} else {// pause if currently running
				view.getSoundPlayer().stop();
				view.getAnimator().setPaused(true);
			}
			break;

		case EXIT:
			System.exit(1);
			break;

		case PAUSEALL:
			if (maze.getPlayer().isMute()) {
				view.getSoundPlayer().play();
				maze.getPlayer().setMute(false);
			} else {
				view.getSoundPlayer().stop();
				maze.getPlayer().setMute(true);
			}
			break;

		case PAUSEBKGRND:
			view.getSoundPlayer().toggle();
			break;

		case PAUSEPLAYER:
			maze.getPlayer().setMute(!maze.getPlayer().isMute());
			break;

		default:
			System.err
					.println("ERROR: menuItem should only be a member of MenuDefs\n"
							+ "Make sure all menus defined in 'Menus' have been handled");
		}
	}
}
