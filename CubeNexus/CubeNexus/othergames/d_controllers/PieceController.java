package d_controllers;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import b_models.BoardModel;
import c_views.BoardView;

/**
 * Control piece movement
 * 
 * @author Jerry Swank
 */
public class PieceController implements MouseListener, MouseMotionListener {

	/**
	 * The game model
	 */
	private BoardModel model;
	/**
	 * The game view
	 */
	private BoardView view;

	/**
	 * Temporary point variables
	 */
	private Point startPoint, endPoint;

	/**
	 * Constructor
	 * 
	 * @param model
	 */
	public PieceController(BoardModel model, BoardView view) {
		if (model == null)
			throw new IllegalArgumentException("Model is null");

		this.model = model;
		this.view = view;
		this.startPoint = new Point(0, 0);
		this.endPoint = new Point(0, 0);
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		if (model.isDragPieceVisible()) {
			System.out.println("mouseDragged" + ": (" + e.getX() + ","
					+ e.getY() + ")");

			this.endPoint.setLocation(e.getPoint());

			view.setDragXY(endPoint);
			view.repaint();
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// System.out.println("mouseMoved" + ": (" + e.getX() + "," + e.getY()
		// + ")");
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// System.out.println("mouseClicked" + ": (" + e.getX() + "," + e.getY()
		// + ")");
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed: " + e.getPoint());

		startPoint.setLocation(this.view.getGridIndex(e.getPoint()));

		if (model.validIndex(startPoint) && model.isOccupied(startPoint)) {
			model.setDragPieceVisible(true);
			model.setAvailable(startPoint);
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if (!model.isDragPieceVisible())
			return;

		endPoint.setLocation(this.view.getGridIndex(e.getPoint()));

		if (model.validIndex(endPoint) && model.isAvailable(endPoint)) {
			model.setOccupied(endPoint);
		} else if (model.validIndex(startPoint)) {
			model.setOccupied(startPoint);
		}

		model.setDragPieceVisible(false);
		view.repaint();
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered" + ": (" + e.getX() + "," + e.getY()
				+ ")");
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited" + ": (" + e.getX() + "," + e.getY()
				+ ")");
	}

}
