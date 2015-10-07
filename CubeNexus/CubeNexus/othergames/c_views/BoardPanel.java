package c_views;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

import b_models.BoardModel;

/**
 * Panel where the game drawing takes place
 * 
 * @author Jerry Swank
 * 
 */
public class BoardPanel extends JPanel {
	private static final long serialVersionUID = 1L;

	private static final Color boardColor = Color.CYAN;
	private static final Color pieceColor = Color.YELLOW;
	private static final Color circleColor = Color.BLACK;

	/**
	 * The Board model
	 */
	private BoardModel model;

	/**
	 * Holds the x, y coordinates of the game board spaces
	 */
	private Point[][] spaceXY;

	/**
	 * Coordinates of the drag piece
	 */
	private Point dragXY;

	/**
	 * radius of the game board circles
	 */
	private int spaceRadius;

	/**
	 * radius of the game board pieces
	 */
	private int pieceRadius;

	/**
	 * Constructor
	 * 
	 * @param model
	 * @param view
	 */
	public BoardPanel(BoardModel model, BoardView view) {
		if ((this.model = model) == null || view == null)
			throw new IllegalArgumentException("Model and/or view is null");

		int boardSize = view.getSize().width;
		int gridSize = model.getGridSize();
		int spaceIncr = boardSize / gridSize; // increment between game spaces

		this.spaceRadius = spaceIncr / 2;
		this.pieceRadius = 3 * spaceRadius / 4;
		this.spaceXY = new Point[gridSize][gridSize];
		this.dragXY = new Point(0, 0);

		for (int row = 0, y = spaceIncr / 2; row < spaceXY.length; row++, y += spaceIncr) {
			for (int col = 0, x = spaceIncr / 2; col < spaceXY.length; col++, x += spaceIncr) {
				spaceXY[row][col] = new Point(x, y);
			}
		}

		this.setPreferredSize(new Dimension(boardSize, boardSize));
		this.setBackground(boardColor);
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		this.setDoubleBuffered(true);
	}

	/**
	 * Draw the shapes in the model
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // clears drawing area

		// draw the circles
		g.setColor(circleColor);
		for (int row = 0; row < spaceXY.length; row++) {
			for (int col = 0; col < spaceXY.length; col++) {
				if (model.isShown(row, col)) {
					g.fillOval(spaceXY[col][row].x - spaceRadius / 2,
							spaceXY[col][row].y - spaceRadius / 2, spaceRadius,
							spaceRadius);

					if (model.isOccupied(row, col)) {
						showPiece(g, spaceXY[col][row]);
					}
				}
			}
		}

		if (model.isDragPieceVisible()) {
			showPiece(g, dragXY);
		}
		/*
		 * 
		 * // player = maze.getPlayer(); player.movePlayer(); // update the
		 * player's position currentRoom = maze.getCurrentRoom();
		 * g.setColor(Color.WHITE); g.drawString("" +
		 * currentRoom.getRoomNumber(), currentRoom.getRoomWidth() / 2,
		 * currentRoom.getRoomHeight() / 2);
		 * 
		 * // draw the background image if one exists if
		 * (currentRoom.hasBackgroundImage()) {
		 * g.drawImage(currentRoom.getBackgroundImage(), 0, 0, this.getWidth(),
		 * this.getHeight(), this); } else {
		 * setBackground(currentRoom.getBackgroundColor());
		 * view.setTitle(OverWorld.initialTitle + ": Room #" +
		 * currentRoom.getRoomNumber()); }
		 * 
		 * // draw the shapes for (int i = 0; i <
		 * currentRoom.getNumberOfShapes(); i++) { otherShape =
		 * currentRoom.getShape(i); otherShape.draw(g); player.draw(g); // call
		 * interaction if needed if (player.intersectsShape(otherShape)) {
		 * Interactions.interact(maze, view, player, otherShape, g); } }
		 */
	}

	/**
	 * 
	 * @param g
	 *            graphics instance
	 * @param p
	 *            where to draw
	 */
	private void showPiece(Graphics g, Point p) {
		g.setColor(pieceColor);
		g.fillOval(p.x - pieceRadius / 2, p.y - pieceRadius / 2, pieceRadius,
				pieceRadius);
		g.setColor(circleColor);
	}

	/**
	 * Checks if the point lies over a game piece
	 * 
	 * @param p
	 * @return if point p lies over a game piece, the indicies of that piece are
	 *         returned (x = indx[0], y = indx[1]), otherwise returns (-1, -1)
	 */
	public Point getGridIndex(Point p) {
		if (p == null)
			throw new IllegalArgumentException("p is null");

		Point indx = new Point(spaceXY.length - 1, spaceXY.length - 1);

		// find out which grid space to check
		int spaceIncr = this.getSize().width / model.getGridSize();

		System.out.println("width=" + this.getSize().width);
		System.out.println("spaceIncr=" + spaceIncr);

		int incr = this.getSize().width - spaceIncr;
		while (indx.x >= 0) {
			if (p.x > incr) {
				System.out.println("p.x=" + p.x + "\tindx.x=" + indx.x
						+ "\tincr=" + incr);
				break;
			}
			indx.x--;
			incr -= spaceIncr;
		}
		incr = this.getSize().width - spaceIncr;
		while (indx.y >= 0) {
			if (p.y > incr) {
				System.out.println("p.y=" + p.y + "\tindx.y=" + indx.y
						+ "\tincr=" + incr);
				break;
			}
			indx.y--;
			incr -= spaceIncr;
		}

		if (model.validIndex(indx)) {
			// determine if the point is over the game piece in the grid space
			int spaceX = spaceXY[indx.y][indx.x].x;
			int spaceY = spaceXY[indx.y][indx.x].y;

			int grabWidth = pieceRadius / 2;
			if (p.x < spaceX - grabWidth || p.x > spaceX + grabWidth
					|| p.y < spaceY - grabWidth || p.y > spaceY + grabWidth) {
				indx.setLocation(-1, -1);
			}
		}
		System.out.println("indx=" + indx);
		return indx;
	}

	/**
	 * Checks if the point lies on the game board
	 * 
	 * @param x
	 *            , y coordinates to check
	 * @return true if the point is within bounds
	 */
	private boolean validGameXY(int x, int y) {
		return !(x < 0 || y < 0 || x > this.getSize().width || y > this
				.getSize().height);
	}

	/**
	 * Sets the coordinates of the drag piece. Invlaid coordinates are ignored.
	 * 
	 * @param p
	 *            the new coordinates of the drag piece
	 */
	public void setDragXY(Point p) {
		if (p == null)
			throw new IllegalArgumentException("Point is null");

		if (validGameXY(p.x, 0)) {
			this.dragXY.x = p.x;
		}
		if (validGameXY(0, p.y)) {
			this.dragXY.y = p.y;
		}
	}
}
