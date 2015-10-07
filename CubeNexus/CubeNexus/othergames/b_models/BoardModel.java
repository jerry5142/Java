package b_models;

import java.awt.Point;

public class BoardModel {
	private static final int NOTSHOWN = -1;
	private static final int AVAILABLE = 0;
	private static final int OCCUPIED = 1;

	private int[][] boardArray;
	private int boardRows;
	private boolean dragPieceVisible;

	public BoardModel(int boardRows) {
		if (boardRows < 1)
			throw new IllegalArgumentException("boardRows is less than 1");

		this.boardRows = boardRows;
		this.boardArray = new int[3 * boardRows - 2][3 * boardRows - 2];
		this.dragPieceVisible = false;
		initBoardArray();
	}

	/*
	 * Initializes the game board -1 means don't show, 0 means available, 1
	 * means occupied
	 */
	private void initBoardArray() {
		// initialize to not shown
		int length = boardArray.length;
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				if ((i < boardRows - 1 && j < boardRows - 1)
						|| (i < boardRows - 1 && j >= 2 * boardRows - 1)
						|| (i >= 2 * boardRows - 1 && j < boardRows - 1)
						|| (i >= 2 * boardRows - 1 && j >= 2 * boardRows - 1)) {
					boardArray[i][j] = NOTSHOWN;
				} else {
					boardArray[i][j] = OCCUPIED;
				}
			}
		}
		setAvailable(length/2, length/2);
	}

	/**
	 * @param p
	 * @return true if the point p represents valid row and col indices
	 */
	public boolean validIndex(Point p) {
		boolean valid = !(p == null || p.x < 0 || p.x > boardArray.length - 1
				|| p.y < 0 || p.y > boardArray.length - 1);

		return valid;
	}

	/**
	 * @param Point
	 *            containing valid indices
	 * @return true if the referenced space is available
	 */
	public boolean isAvailable(Point p) {
		if (!validIndex(p))
			throw new IllegalArgumentException("Invalid indices: (" + p.x + ","
					+ p.y + ")");

		return boardArray[p.y][p.x] == AVAILABLE;
	}

	/**
	 * @param row
	 * @param col
	 * @return true if the referenced space is available
	 */
	public boolean isAvailable(int row, int col) {
		return isAvailable(new Point(row, col));
	}

	/**
	 * @param Point
	 *            containing valid indices
	 * @return true if the referenced space is shown
	 */
	public boolean isShown(Point p) {
		if (!validIndex(p))
			throw new IllegalArgumentException("Invalid indices: (" + p.x + ","
					+ p.y + ")");

		return boardArray[p.y][p.x] != NOTSHOWN;
	}

	/**
	 * @param row
	 * @param col
	 * @return true if the referenced space is shown
	 */
	public boolean isShown(int row, int col) {
		return isShown(new Point(row, col));
	}

	/**
	 * @param Point
	 *            containing valid indices
	 * @return true if the referenced space is occupied
	 */
	public boolean isOccupied(Point p) {
		if (!validIndex(p))
			throw new IllegalArgumentException("Invalid indices: (" + p.x + ","
					+ p.y + ")");

		return boardArray[p.y][p.x] == OCCUPIED;
	}

	/**
	 * @param row
	 * @param col
	 * @return true if the referenced space is occupied
	 */
	public boolean isOccupied(int row, int col) {
		return isOccupied(new Point(row, col));
	}

	/**
	 * Sets the referenced space to occupied
	 * 
	 * @param Point
	 *            containing valid indices
	 */
	public void setOccupied(Point p) {
		if (!validIndex(p))
			throw new IllegalArgumentException("Invalid indices: (" + p.x + ","
					+ p.y + ")");

		this.boardArray[p.y][p.x] = OCCUPIED;
	}

	/**
	 * @param row
	 * @param col
	 */
	public void setOccupied(int row, int col) {
		setOccupied(new Point(row, col));
	}

	/**
	 * Sets the referenced space to available
	 * 
	 * @param Point
	 *            containing valid indices
	 */
	public void setAvailable(Point p) {
		if (!validIndex(p))
			throw new IllegalArgumentException("Invalid indices: (" + p.x + ","
					+ p.y + ")");

		this.boardArray[p.y][p.x] = AVAILABLE;
	}

	/**
	 * @param row
	 * @param col
	 */
	public void setAvailable(int row, int col) {
		setAvailable(new Point(row, col));
	}

	/**
	 * @return the game grid width in spaces (height == width)
	 */
	public int getGridSize() {
		return boardArray.length;
	}

	/**
	 * @param dragPieceVisible
	 *            the dragPieceVisible to set
	 */
	public void setDragPieceVisible(boolean dragPieceVisible) {
		this.dragPieceVisible = dragPieceVisible;
	}

	/**
	 * @return the dragPieceVisible
	 */
	public boolean isDragPieceVisible() {
		return dragPieceVisible;
	}

}
