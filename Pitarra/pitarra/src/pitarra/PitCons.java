//constants class for Pitarra
package pitarra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.Toolkit;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.border.Border;

public final class PitCons {
        // text strings
        protected final static String title = "PITARRA";
        protected final static String newGame = "New Game";

        // window constants
        protected final static double windowSizeScale = 0.8; // % of screen width
        protected final static double pyramidSizeScale = .5; // % of window width
        protected final static double squareSizeScale = .06; // % of pyramid width

        // Fonts
        protected final static int fontSize = (int) (35 * windowSizeScale);
        protected final static String fontName = "Ariel";
        protected final static Font normalFont = new Font(fontName, Font.PLAIN,
                        fontSize);
        protected final static Font boldFont = new Font(fontName, Font.BOLD,
                        fontSize);
        protected final static Font italicFont = new Font(fontName, Font.ITALIC,
                        fontSize);
        protected final static Font italicBoldFont = new Font(fontName, Font.ITALIC
                        + Font.BOLD, fontSize);
        protected final static Font bigBoldFont = new Font(fontName, Font.ITALIC
                        + Font.BOLD, fontSize * 3 / 2);

        // number of pieces
        protected final static int initialNumberOfPieces = 12;
        // lose more than maxPiecesYouCanLose and you lose the game
        // (i.e. have to have at least 3 pieces to keep playing)
        protected final static int maxPiecesYouCanLose = initialNumberOfPieces - 3;
        protected final static int initialNumberOfWins1 = 0;
        protected final static int initialNumberOfWins2 = 0;

        // color constants
        protected final static Color pyramidLineColor = Color.black;
        protected final static Color customFontColor = new Color(200, 25, 255);
        protected final static Color notifyTextColor = Color.cyan;
        // protected final static Color player1Color = custonFontColor;
        protected final static Color player1Color = Color.yellow;
        protected final static Color player2Color = customFontColor;
        protected final static Color genericBackColor = Color.orange;
        protected final static Color squareClearColor = Color.gray;
        protected final static Color squareHighlightColor = Color.cyan;

        // border thickness constants
        protected final static Color genericBorderColor = Color.black;
        protected final static int borderThickness = 3;
        protected final static Border genericBorder = BorderFactory
                        .createLineBorder(PitCons.genericBorderColor,
                                        PitCons.borderThickness);

        // sound constants
        protected final static String dropSound = "Sound Files/pitarra-piecedrop-sound.wav";
        protected final static String moveSound = "Sound Files/Windows XP Minimize.wav";
        protected final static String takeSound = "Sound Files/recycle.wav";
        protected final static String winSound = "Sound Files/tada.wav";
        protected final static String highlightSound = "Sound Files/windows xp pop-up blocked.wav";
        protected final static String backgroundMusic = "Sound Files/music-pitarra-1-short.wav";

        // image icons
        // Change the .jpg image files in the icons folder to change the backdrops.
        // Make sure the file names are the same.
        protected final static ImageIcon gameBackdrop = new ImageIcon(
                        "icons/GameBackdrop.jpg");
        // protected final static ImageIcon pyramidBackdrop = new ImageIcon(
        // "icons/PyramidBackdrop.jpg");
        protected final static ImageIcon pyramidBackdrop = new ImageIcon(
                        "icons/PyramidBackdrop.jpg");
        protected final static ImageIcon player1Backdrop = new ImageIcon(
                        "icons/Player1Backdrop.jpg");
        protected final static ImageIcon player2Backdrop = new ImageIcon(
                        "icons/Player2Backdrop.jpg");
        protected final static ImageIcon calendarBackdrop = new ImageIcon(
                        "icons/AztecCalendarBackdrop.jpg");
        protected final static ImageIcon sacrificeBackdrop = new ImageIcon(
                        "icons/AztecSacrifice.jpg");
        protected final static ImageIcon player1CornKernal = new ImageIcon(
                        "icons/player1corn.png");
        protected final static ImageIcon player2CornKernal = new ImageIcon(
                        "icons/player2corn.png");

        // File text constants
        protected final static String advancedInstructions = Utilities
                        .readFile("Text Files/Advanced Instructions.txt");
        protected final static String advancedInstructionsSpa = Utilities
                        .readFile("Text Files/Advanced Instructions-spa.txt");
        protected final static String basicInstructions = Utilities
                        .readFile("Text Files/Basic Instructions.txt");
        protected final static String basicInstructionsSpa = Utilities
                        .readFile("Text Files/Basic Instructions-spa.txt");

        // facts from file constant
        protected final static String[] facts = Utilities
                        .readFilePerLine("Text Files/Facts.txt");

        // Calculated size and position constants
        // Don't change these. Change the "Scale" values above to change the sizes.
        protected final static Dimension screenSize = Toolkit.getDefaultToolkit()
                        .getScreenSize();
        protected final static Dimension initialWindowSize = new Dimension(
                        (int) (screenSize.getHeight() * windowSizeScale * 4 / 3),
                        (int) (screenSize.getHeight() * windowSizeScale));
        protected final static Point initialWindowPosition = new Point(
                        (int) (screenSize.getWidth() - initialWindowSize.getWidth()) / 2,
                        (int) (screenSize.getHeight() - initialWindowSize.getHeight()) / 2);

        // pyramid constants
        protected final static int initialPyramidSize = (int) (initialWindowSize
                        .getWidth() * pyramidSizeScale);
        protected final static Point initialPyramidPosition = new Point(
                        (int) initialWindowSize.getWidth() / 2,
                        (int) initialWindowSize.getHeight() / 2);
        protected final static int squareSize = (int) (initialPyramidSize * squareSizeScale);
        protected final static boolean showCornKernals = true;
        protected final static boolean showSquares = false;
        protected final static boolean showGridLines = false;

        // Panel Size constants
        protected final static int initialPlayerPanelWidth = (initialWindowSize.width - initialPyramidSize) / 2;

        // constructor: nothing to do for static constants class
        private PitCons() {
        };
}