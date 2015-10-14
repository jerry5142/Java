package i_images;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

/**
 * Holds the images for the OverWorld.
 * 
 * Syntax: ENUMIMAGENAME(String imageDescription, String iconFileName),
 * 
 * The last entry ends with a semicolon
 * 
 * @author Jerry Swank
 * 
 */
public enum OverWorldImages {

	PLAYERSPIN("spinning player", "spinningplayer.gif"),
	
	PLAYERDEAD("dead player", "playerDead.gif"),

	ZOMBIE("zombie", "zombie.gif"),

	SIMON("Simon", "simon.gif"),

	PLAYERUP("player's back", "playerUp.gif"),

	PLAYERUPRIGHT("player's front", "playerUp.gif"),

	PLAYERUPLEFT("player's front", "playerUp.gif"),

	PLAYERDOWN("player's front", "playerDown.gif"),

	PLAYERDOWNRIGHT("player's front", "playerDown.gif"),

	PLAYERDOWNLEFT("player's front", "playerDown.gif"),

	PLAYERRIGHT("player's right side", "playerRight.gif"),

	PLAYERLEFT("player's left side", "playerLeft.gif"),

	BANANA("dancing banana", "banana.gif"),

	WIZARD("wizard", "wizard.png"),

	ROBOT("robot", "robot.png"),

	KEYGOLD("gold key", "keygold.png"),

	DOORUNLOCKED("unlocked door", "doorunlocked.png"),

	DOOROPEN("open door", "dooropen.gif"),

	DOORLOCKED("locked door", "doorlocked.png"),

	FLOORPLANK("brown plank floor", "floorplankbrown.jpg"),

	FLOORTILEDBROWN("brown tiled floor", "floortiledbrown.png"),

	FLOORTILEDMULTI("multi-colored tiled floor", "floortiledcolors.jpg");

	private final static String imagePath = "OverWorldIcons/";
	private String imageName;
	private Image iconImage;

	/**
	 * Initializes the images listed in the enum definitions
	 * 
	 * @param imageName
	 * @param iconFileName
	 */
	OverWorldImages(String imageName, String iconFileName) {
		this.imageName = imageName;
		iconFileName = OverWorldImages.imagePath + iconFileName;
		File file;
		// make sure the file exists
		if (iconFileName == null || (file = new File(iconFileName)) == null
				|| !file.exists()) {
			System.err.println("The icon file for: " + this.imageName
					+ " cannot be found at path: " + OverWorldImages.imagePath
					+ iconFileName);
			this.iconImage = null;
		} else {
			// set the icon image
			this.iconImage = Toolkit.getDefaultToolkit().getImage(iconFileName);
		}
	}

	/**
	 * @return the imageName
	 */
	public String getImageName() {
		return imageName;
	}

	/**
	 * @return the iconImage
	 */
	public Image getIconImage() {
		return iconImage;
	}
}
