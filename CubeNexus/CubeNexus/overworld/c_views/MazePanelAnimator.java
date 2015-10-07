package c_views;

import javax.swing.SwingWorker;

/**
 * Refreshes the MazePanel at a constant rate
 * 
 * @author Jerry Swank
 * 
 */
public class MazePanelAnimator extends SwingWorker<Long, GamePanel> {

	/**
	 * Delay between repaints in milliseconds
	 */
	private int delay;

	/**
	 * The JPanel to refresh
	 */
	private GamePanel view;

	/**
	 * Pause refresh
	 */
	private boolean paused;

	/**
	 * Constructor
	 * 
	 * @param delay
	 *            delay between MazePanel refreshes
	 * @param view
	 */
	public MazePanelAnimator(int delay, GamePanel view) {
		super();
		if (delay < 1)
			throw new IllegalArgumentException("Delay must be > 0");

		if (view == null)
			throw new IllegalArgumentException("view is null");

		this.delay = delay;
		this.view = view;
		this.paused = false;
	}

	/**
	 * Repaints the panel every delay milliseconds
	 */
	@Override
	protected Long doInBackground() throws Exception {
		long endOfLoopTime, timeDiff, sleepTime;

		endOfLoopTime = System.currentTimeMillis();

		while (true) {
			if (!paused)
				view.getMazePanel().repaint();

			// subtract loop execution time
			timeDiff = System.currentTimeMillis() - endOfLoopTime;
			sleepTime = delay - timeDiff;

			if (sleepTime < 0) {
				sleepTime = 2;
			}

			try {
				Thread.sleep(sleepTime);
			} catch (InterruptedException e) {
				System.err.println("Interrupted: " + e.getMessage());
			}

			endOfLoopTime = System.currentTimeMillis();

		}
	}

	/**
	 * @return the paused
	 */
	public boolean isPaused() {
		return paused;
	}

	/**
	 * @param paused
	 *            the paused to set
	 */
	public void setPaused(boolean paused) {
		this.paused = paused;
	}

}
