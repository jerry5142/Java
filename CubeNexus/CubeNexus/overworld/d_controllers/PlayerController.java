package d_controllers;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import b_models.Maze;

/**
 * Controls the player's movement
 * 
 * @author Jerry Swank
 */
public class PlayerController implements KeyListener {

	/**
	 * The game model
	 */
	private Maze maze;

	/**
	 * Constructor
	 * 
	 * @param maze
	 */
	public PlayerController(Maze maze) {
		if (maze == null)
			throw new IllegalArgumentException("Maze is null");

		if (maze.getPlayer() == null)
			throw new IllegalArgumentException(
					"Maze player is null. Please initialize the player before setting the controller");

		this.maze = maze;
	}

	@Override
	public void keyPressed(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
			maze.getPlayer().setMoveUp(true);
			break;

		case KeyEvent.VK_DOWN:
			maze.getPlayer().setMoveDn(true);
			break;

		case KeyEvent.VK_LEFT:
			maze.getPlayer().setMoveLt(true);
			break;

		case KeyEvent.VK_RIGHT:
			maze.getPlayer().setMoveRt(true);
			break;

		case KeyEvent.VK_EQUALS:
			maze.getPlayer().setMoveSpeed(maze.getPlayer().getMoveSpeed() + 1);
			break;

		case KeyEvent.VK_MINUS:
			maze.getPlayer().setMoveSpeed(maze.getPlayer().getMoveSpeed() - 1);
			break;

		/*******************
		 * DEBUB
		 ******************/
		case KeyEvent.VK_HOME:
			maze.setCurrentRoom(0);
			break;

		default:

		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		switch (arg0.getKeyCode()) {
		case KeyEvent.VK_UP:
			maze.getPlayer().setMoveUp(false);
			break;

		case KeyEvent.VK_DOWN:
			maze.getPlayer().setMoveDn(false);
			break;

		case KeyEvent.VK_LEFT:
			maze.getPlayer().setMoveLt(false);
			break;

		case KeyEvent.VK_RIGHT:
			maze.getPlayer().setMoveRt(false);
			break;

		default:
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
