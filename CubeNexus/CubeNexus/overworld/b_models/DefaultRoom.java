package b_models;

import i_images.OverWorldImages;

import java.awt.Color;
import java.awt.Rectangle;
import java.util.Random;

import a_overworld.OverWorld;
import b_models.Door.POSITION;

public class DefaultRoom extends Room {

	/**
	 * Random number generator for key placement
	 */
	private static Random rand = new Random();

	public DefaultRoom() {
		this(0);
	}

	/**
	 * Creates a default room with a door on each wall and a key somewhere in
	 * the room
	 * 
	 * @param roomNumber
	 *            valid array index
	 */
	public DefaultRoom(int roomNumber) {
		super(OverWorld.roomSize.width, OverWorld.roomSize.height);

		if (roomNumber < 0)
			throw new IllegalArgumentException("roomNumber must be >= 0 ");

		this.setRoomNumber(roomNumber);
		this.setBackgroundColor(Color.GRAY);

		Rectangle roomSize = new Rectangle(this.getRoomWidth(),
				this.getRoomHeight());

		// add 4 doors, 1 on each wall
		// left
		this.addShape(new Door(POSITION.LEFT, 60, 60,
				OverWorldImages.DOORLOCKED, OverWorldImages.DOOROPEN, "",
				roomSize));

		// right
		this.addShape(new Door(POSITION.RIGHT, 60, 60,
				OverWorldImages.DOORLOCKED, OverWorldImages.DOOROPEN, "",
				roomSize));

		// top
		this.addShape(new Door(POSITION.TOP, 60, 60,
				OverWorldImages.DOORLOCKED, OverWorldImages.DOOROPEN, "",
				roomSize));

		// bottom
		this.addShape(new Door(POSITION.BOTTOM, 60, 60,
				OverWorldImages.DOORLOCKED, OverWorldImages.DOOROPEN, "",
				roomSize));

		// door key
		this.addShape(new CollectibleShape(60 + rand
				.nextInt(roomSize.width - 30 - 60 - 60), 60 + rand
				.nextInt(roomSize.height - 30 - 60 - 60), 30, 30,
				OverWorldImages.KEYGOLD, "key", roomSize));
	}
}
