package d_controllers;

import i_images.OverWorldImages;

import java.awt.Graphics;

import b_models.BoundedShape;
import b_models.CollectibleShape;
import b_models.Door;
import b_models.Maze;
import b_models.SpaceShip;
import c_views.GamePanel;

/**
 * Defines the interaction of objects when they intersect
 * 
 * @author Jerry Swank
 * 
 */
public class Interactions {

	/**
	 * Call whenever shapes intersect
	 * 
	 * @param maze
	 * @param view
	 * @param player
	 * @param shape
	 * @param g
	 */
	public static void interact(Maze maze, GamePanel view, SpaceShip player,
			BoundedShape shape, Graphics g) {
		// pause screen refresh
		view.getAnimator().setPaused(true);

		Door door = null;
		if (shape instanceof Door) {
			door = (Door) shape;

			switch (shape.getImageType()) {
			// if unlocked, then open it
			case DOORUNLOCKED:
				door.openDoor();
				door.getOtherSideOfDoor().setLocked(false);
				door.getOtherSideOfDoor().openDoor();
				break;

			case DOORLOCKED:
				if (door.isLocked()) {
					door.showMessage(g);
					if (player.hasObject(OverWorldImages.KEYGOLD)) {
						door.setLocked(false);
						door.getOtherSideOfDoor().setLocked(false);
						player.removeShape(OverWorldImages.KEYGOLD);
					} else {
						if (door.getGame() != null
								&& player.isCenteredOnShape(door)) {
							door.playGame();
						}
					}
				} else {
					door.openDoor();
					door.getOtherSideOfDoor().setLocked(false);
					door.getOtherSideOfDoor().openDoor();
				}
				break;

			case DOOROPEN:
				if (player.isCenteredOnShape(door)) {
					delay(300);

					maze.nextRoom(door);
					player.movePlayerThroughDoor(door);
					view.getOverview().refreshPlayer();
				}
				break;

			default:
				break;
			}
		} else if (shape instanceof CollectibleShape) {
			// collect the shape and remove it from the room
			if (!shape.isVisible()) {
				shape.setVisible(true);
			} else {
				player.addShape(shape.getImageType());
				maze.getCurrentRoom().removeShape(shape);
			}
		} else {
			switch (shape.getImageType()) {
			case ROBOT:
			case WIZARD:

			default:
				shape.showMessage(g);
				break;
			}
		}

		// restart screen refresh
		view.getAnimator().setPaused(false);
	}

	private static void delay(int milliseconds) {
		if (milliseconds < 1)
			return;

		try {
			Thread.sleep(milliseconds);
		} catch (InterruptedException e) {
			System.err.println("Error when trying to sleep for " + milliseconds
					+ " milliseconds.");
		}
	}

}
