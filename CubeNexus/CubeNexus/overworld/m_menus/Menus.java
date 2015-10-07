package m_menus;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * Defines menus and adds them to the JFrame view specified.
 * 
 * @author Jerry Swank
 * 
 * @param view
 *            any JFrame object
 */
public abstract class Menus<T extends JFrame> implements ActionListener {
	/**
	 * The enum specifies the actual menus: ENUMNAME(ActualMenuName,
	 * ShortcutKeyCharacter, isaMainMenu)
	 * 
	 * If isaMainMenu is true, then that menu is a main menu. If isaMainMenu is
	 * false, then it is a submenu item on the preceding main menu.
	 */
	public enum MenuDefs {
		GAME("Game", 'G', true), // main menu

		SIMON("Simon Game", 'S', false),

		PAUSEGAME("Toggle pause", 'P', false),

		EXIT("Exit", 'E', false),

		SOUND("Sound", 'S', true),

		PAUSEALL("Toggle all sounds", 'a', false),

		PAUSEBKGRND("Toggle background music", 'm', false),

		PAUSEPLAYER("Toggle player sounds", 'p', false),

		HELP("Help", 'H', true);

		/**
		 * Name that appears on the drop down menu
		 */
		private final String menuName;
		/**
		 * Keyboard shortcut letter
		 */
		private final char menuShortcut;
		/**
		 * Used to determine if the menu entry is a main menu
		 */
		private final boolean isMainMenu;

		/**
		 * Enum Constructor
		 * 
		 * @param menuName
		 * @param menuShortcut
		 *            character used to access the menu via the keyboard
		 * @param isMainMenu
		 *            set to true if the entry is a main menu entry
		 */
		MenuDefs(String menuName, char menuShortcut, boolean isMainMenu) {
			this.isMainMenu = isMainMenu;
			this.menuName = menuName;
			this.menuShortcut = menuShortcut;
		}
	}

	/**
	 * JFrame object
	 */
	private T view;

	/**
	 * @param view
	 *            any JFrame object
	 */
	public Menus(T view) {
		this.view = view;
		// create menu objects
		this.view.setJMenuBar(new JMenuBar());
		JMenu menu = null;
		JMenuItem mitem = null;

		// add the menus defined in MenuDefs
		boolean start = true;
		for (MenuDefs mdefs : MenuDefs.values()) {
			if (mdefs.isMainMenu || start) {
				// initial menu def is always a main menu
				start = false;
				menu = new JMenu(mdefs.menuName);
				menu.setMnemonic(KeyEvent
						.getExtendedKeyCodeForChar(mdefs.menuShortcut));
				// add the menu to the menu bar
				this.view.getJMenuBar().add(menu);
			} else {
				// create a new menu item
				mitem = new JMenuItem(mdefs.menuName);
				mitem.addActionListener(this); // add listener
				// set the keyboard shortcut key for this menu item
				mitem.setMnemonic(KeyEvent
						.getExtendedKeyCodeForChar(mdefs.menuShortcut));
				menu.add(mitem); // add the item to the current menu
			}
		}
	}

	/**
	 * Catches Action Events from the specified menus and send them to the
	 * handler
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// send the enum that corresponds to the event
		for (MenuDefs md : MenuDefs.values()) {
			if (md.menuName.equals(e.getActionCommand())) {
				handleMenuEvent(md);
				break;
			}
		}
	}

	/**
	 * Handles a menu generated event
	 * 
	 * @param menuItem
	 */
	public abstract void handleMenuEvent(MenuDefs menuItem);

}