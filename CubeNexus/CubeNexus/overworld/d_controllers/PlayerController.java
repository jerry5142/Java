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
		int key = arg0.getKeyCode();

		switch (key) {
		case KeyEvent.VK_EQUALS:
			setSpeed(1);
			break;

		case KeyEvent.VK_MINUS:
			setSpeed(-1);
			break;

		/*******************
		 * DEBUB
		 ******************/
		case KeyEvent.VK_HOME:
			maze.setCurrentRoom(0);
			break;

		default:
			setPlayerMove(key, true);
		}
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		int key = arg0.getKeyCode();

		switch (key) {

		default:
			setPlayerMove(key, false);
		}
	}

	/**
	 * @param speedChange
	 *            increment or decrement speed
	 */
	private void setSpeed(int speedChange) {
		maze.getPlayer().setMoveSpeed(
				maze.getPlayer().getMoveSpeed() + speedChange);
	}

	/**
	 * 
	 * @param key
	 *            keyboard key code
	 * @param moveIt
	 *            true=move the player
	 */
	private void setPlayerMove(int key, boolean moveIt) {
		switch (key) {
		case KeyEvent.VK_UP:
			maze.getPlayer().setMoveUp(moveIt);
			break;

		case KeyEvent.VK_DOWN:
			maze.getPlayer().setMoveDn(moveIt);
			break;

		case KeyEvent.VK_LEFT:
			maze.getPlayer().setMoveLt(moveIt);
			break;

		case KeyEvent.VK_RIGHT:
			maze.getPlayer().setMoveRt(moveIt);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

}
