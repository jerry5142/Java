package c_views;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JFrame;

import b_models.BoardModel;
import d_controllers.PieceController;

/**
 * View for the game
 */
public class BoardView extends JFrame {
	/**
	 * Added to shut the compiler up
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * the JPanel where the user can paint
	 */
	private BoardPanel boardPanel;

	/**
	 * Create and organize the components of the window.
	 * 
	 * @param windowSize
	 * @param model
	 */
	public BoardView(Dimension windowSize, BoardModel model) {
		super();
		if (model == null)
			throw new IllegalArgumentException("model is null");
		if (windowSize == null)
			throw new IllegalArgumentException("windowSize is null");
		if (windowSize.height < 1 || windowSize.width < 1)
			throw new IllegalArgumentException("windowSize is invalid");

		this.setLayout(new BorderLayout());
		this.setSize(windowSize);

		// Add a panel to draw on.
		this.boardPanel = new BoardPanel(model, this);
		this.boardPanel.setFocusable(true); // set the focus on the board

		add(boardPanel, BorderLayout.CENTER);

	} // end constructor

	/**
	 * Register the controller
	 * 
	 * @param listener
	 */
	public void registerListener(PieceController listener) {
		boardPanel.addMouseListener(listener);
		boardPanel.addMouseMotionListener(listener);
	}

	/**
	 * @return the boardPanel
	 */
	public BoardPanel getBoardPanel() {
		return boardPanel;
	}

	/**
	 * Checks if the point lies over a game piece
	 * 
	 * @param p
	 * @return if point p lies over a game piece, the indicies of that piece are
	 *         returned (x = Point.x, y = Point.y), otherwise returns null
	 */
	public Point getGridIndex(Point p) {
		return boardPanel.getGridIndex(p);
	}

	/**
	 * @param p
	 *            the new coordinates of the drag piece
	 */
	public void setDragXY(Point p) {
		boardPanel.setDragXY(p);
	}
}
