package t_templates;

/**
 * Provides a method that is called when a subgame terminates. The outcome of
 * the subgame is a parameter to the method.
 * 
 * @author Jerry Swank
 */
public interface LauncherCallback {

	/**
	 * @param gameWon
	 *            true if the subgame was won
	 */
	public void gameOutcome(boolean gameWon);
}
