package pitarra;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class Vertex extends Point {
        private boolean available;
        private boolean cornerVertex;
        private boolean showSquares, showCornKernals, showGridLines;
        private boolean highlighted;
        private int playerNumber;
        private int squareWidth;
        private Color shapeColor, lineColor, shapeClearColor, shapeHighlightColor;
        private Color player1Color, player2Color;
        private Vertex left, right, above, below; // adjacent vertices
        private ImageIcon player1Ccorn, player2Ccorn;

        public Vertex(boolean isCornerVertex, boolean showSquares,
                        boolean showGridLines, boolean showCornKernals, int squareWidth,
                        Color lineColor, Color shapeClearColor, Color shapeHighlightColor,
                        Color player1Color, Color player2Color, ImageIcon player1Ccorn,
                        ImageIcon player2Ccorn) {
                super();
                this.available = true;
                this.cornerVertex = isCornerVertex;
                this.showSquares = showSquares;
                this.showCornKernals = showCornKernals;
                this.showGridLines = showGridLines;
                this.highlighted = false;
                this.playerNumber = 0;
                this.squareWidth = squareWidth;
                this.lineColor = lineColor;
                this.shapeClearColor = shapeClearColor;
                this.shapeHighlightColor = shapeHighlightColor;
                this.shapeColor = shapeClearColor;
                this.player1Color = player1Color;
                this.player2Color = player2Color;
                this.left = null;
                this.right = null;
                this.above = null;
                this.below = null;
                this.player1Ccorn = player1Ccorn;
                this.player2Ccorn = player2Ccorn;
                setLocation(0, 0);
        }

        public boolean isAvailable() {
                return available;
        }

        public void setAvailable(boolean available) {
                this.available = available;
        }

        public boolean isCornerVertex() {
                return cornerVertex;
        }

        public boolean isHighlighted() {
                return highlighted;
        }

        public void setHighlighted(boolean highlighted) {
                this.highlighted = highlighted;
        }

        public int getPlayerNumber() {
                return playerNumber;
        }

        public void setLineColor(Color lineColor) {
                this.lineColor = lineColor;
        }

        public int getSquareWidth() {
                return squareWidth;
        }

        public void setSquareWidth(int squareWidth) {
                this.squareWidth = squareWidth;
        }

        public Vertex getLeft() {
                return left;
        }

        public void setLeft(Vertex left) {
                this.left = left;
        }

        public Vertex getRight() {
                return right;
        }

        public void setRight(Vertex right) {
                this.right = right;
        }

        public Vertex getAbove() {
                return above;
        }

        public void setAbove(Vertex above) {
                this.above = above;
        }

        public Vertex getBelow() {
                return below;
        }

        public void setBelow(Vertex below) {
                this.below = below;
        }

        public void setPlayer(int player) {
                this.playerNumber = player;
                switch (player) {
                case 1:
                        shapeColor = player1Color;
                        setAvailable(false);
                        break;
                case 2:
                        shapeColor = player2Color;
                        setAvailable(false);
                        break;
                default:
                        shapeColor = shapeClearColor;
                        setAvailable(true);
                }
        }

        public void drawConnectingLines(Graphics page) {
                if (showGridLines) {
                        page.setColor(lineColor);
                        if (left != null)
                                page.drawLine(x, y, left.x, left.y);
                        if (right != null)
                                page.drawLine(x, y, right.x, right.y);
                        if (above != null)
                                page.drawLine(x, y, above.x, above.y);
                }
        }

        public void drawPieces(Graphics page, JPanel panel) {
                if (highlighted) {
                        Color sqColor = shapeColor;
                        int sqWidth = squareWidth;
                        shapeColor = shapeHighlightColor;
                        drawSquare(page);
                        if (showSquares) { // draw a small player color square
                                squareWidth = squareWidth * 7 / 10;
                                shapeColor = sqColor;
                                drawSquare(page);
                                squareWidth = sqWidth;
                        }
                        if (showCornKernals) { // show corn
                                drawCornKernal(page, panel);
                                shapeColor = sqColor;
                        }
                } else {
                        if (showSquares)
                                drawSquare(page);
                        if (showCornKernals && playerNumber != 0)
                                drawCornKernal(page, panel);
                }
        }

        public void clear() {
                this.highlighted = false;
                setPlayer(0);
        }

        private void drawCornKernal(Graphics page, JPanel panel) {
                int cornX = x - squareWidth / 2;
                int cornY = y - squareWidth / 2;
                switch (playerNumber) {
                case 1:
                        page.drawImage(player1Ccorn.getImage(), cornX, cornY, cornX
                                        + squareWidth, cornY + squareWidth, 0, 0,
                                        player1Ccorn.getIconWidth(), player1Ccorn.getIconHeight(),
                                        panel);
                        break;
                case 2:
                        page.drawImage(player2Ccorn.getImage(), cornX, cornY, cornX
                                        + squareWidth, cornY + squareWidth, 0, 0,
                                        player2Ccorn.getIconWidth(), player2Ccorn.getIconHeight(),
                                        panel);
                        break;
                }
        }

        private void drawSquare(Graphics page) {
                page.setColor(shapeColor);
                page.fillRect(x - squareWidth / 2, y - squareWidth / 2, squareWidth,
                                squareWidth);
        }
}