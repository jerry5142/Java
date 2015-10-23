package pitarra;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class GamePanel extends JPanel {
        private ImageIcon backdrop;
        private PyramidPanel pyramid;
        private boolean isPlayer1Turn;
        private boolean basicGame;
        private boolean soundOn;
        private boolean musicOn;
        private boolean takeNextPiece; // if true, next piece clicked is removed
        private int playerToRemove;
        private int p1PiecesLeft, p2PiecesLeft, p1PiecesLost, p2PiecesLost, p1Wins,
                        p2Wins;
        private JPanel eastPanel, westPanel, centerPanel, northPanel;
        private PlayerPanel player1, player2;
        private JLabel notifyText;
        private Dimension windowSize;
        private SoundClipPlayer[] sounds;
        private boolean lang;
        private Font notifyFont;

        public GamePanel(ImageIcon backdrop) {
                super();
                this.backdrop = backdrop;
                this.pyramid = new PyramidPanel(PitCons.pyramidBackdrop, this);

                this.basicGame = true;
                this.soundOn = true;
                this.musicOn = true;
                this.takeNextPiece = false;
                this.playerToRemove = 0;
                this.eastPanel = new JPanel();
                this.westPanel = new JPanel();
                this.centerPanel = new JPanel();
                this.northPanel = new JPanel();
                this.player1 = new PlayerPanel(PitCons.player1Backdrop, 1,
                                PitCons.player1Color, this);
                this.player2 = new PlayerPanel(PitCons.player2Backdrop, 2,
                                PitCons.player2Color, this);
                this.player1.reset(basicGame);
                this.player1.showColorText(true);
                this.player2.reset(basicGame);
                this.player2.showColorText(true);
                this.p1PiecesLeft = PitCons.initialNumberOfPieces;
                this.p2PiecesLeft = PitCons.initialNumberOfPieces;
                this.p1PiecesLost = 0;
                this.p2PiecesLost = 0;
                this.p1Wins = PitCons.initialNumberOfWins1;
                this.p2Wins = PitCons.initialNumberOfWins2;
                this.isPlayer1Turn = true;
                this.notifyText = new JLabel();
                this.windowSize = new Dimension(
                                (int) PitCons.initialWindowSize.getWidth(),
                                (int) PitCons.initialWindowSize.getHeight());
                this.sounds = new SoundClipPlayer[6];
                this.sounds[0] = new SoundClipPlayer(PitCons.dropSound);
                this.sounds[1] = new SoundClipPlayer(PitCons.moveSound);
                this.sounds[2] = new SoundClipPlayer(PitCons.takeSound);
                this.sounds[3] = new SoundClipPlayer(PitCons.winSound);
                this.sounds[4] = new SoundClipPlayer(PitCons.highlightSound);
                this.sounds[5] = new SoundClipPlayer(PitCons.backgroundMusic);
                this.lang = true;
                this.notifyFont = PitCons.bigBoldFont;

                clearTakeNextPiece();

                setLayout(new BorderLayout());
                setPreferredSize(windowSize);
                setDoubleBuffered(true);

                eastPanel.setOpaque(false);
                eastPanel.add(player2);

                centerPanel.setOpaque(false);
                centerPanel.setLayout(new GridLayout());
                centerPanel.add(pyramid);

                westPanel.setOpaque(false);
                westPanel.add(player1);

                notifyText.setOpaque(false);
                notifyText.setFont(notifyFont);
                notifyText.setForeground(PitCons.notifyTextColor);
                notifyText.setText(PitCons.title);

                northPanel.setOpaque(false);
                northPanel.add(notifyText);

                add(eastPanel, BorderLayout.EAST);
                add(centerPanel, BorderLayout.CENTER);
                add(westPanel, BorderLayout.WEST);
                add(northPanel, BorderLayout.NORTH);

                if (lang) {
                        notifyPlayer(getCurrentPlayerNumber(), Language.placePieceMessage,
                                        0);
                } else {
                        notifyPlayer(getCurrentPlayerNumber(),
                                        Language.placePieceMessageSpa, 1);
                }

                playBackgroundSound(true);
        }

        public PlayerPanel getPlayer1() {
                return player1;
        }

        public PlayerPanel getPlayer2() {
                return player2;
        }

        public void setLang(boolean val) {
                lang = val;
        }

        public void resetWin(int player) {
                if (player == 1) {
                        p1Wins = 0;
                } else if (player == 2) {
                        p2Wins = 0;
                } else if (player == 3) {
                        p1Wins = 0;
                        p2Wins = 0;
                }
        }

        public void concedeGame() {
                if (isPlayer1Turn) {
                        p2Wins++;
                        player2.updateLabels();
                } else {
                        p1Wins++;
                        player1.updateLabels();
                }
                resetGame();
        }

        public void resetGame() {
                this.player1.reset(basicGame);
                this.player2.reset(basicGame);
                this.p1PiecesLeft = PitCons.initialNumberOfPieces;
                this.p2PiecesLeft = PitCons.initialNumberOfPieces;
                this.p1PiecesLost = 0;
                this.p2PiecesLost = 0;
                this.isPlayer1Turn = true;
                this.pyramid.resetPyramid();
                clearTakeNextPiece();
                if (lang) {
                        notifyPlayer(getCurrentPlayerNumber(), Language.placePieceMessage,
                                        0);
                } else {
                        notifyPlayer(getCurrentPlayerNumber(),
                                        Language.placePieceMessageSpa, 1);
                }
                playBackgroundSound(true);
        }

        public void paintComponent(Graphics page) {
                Utilities.drawBackdrop(page, backdrop, getWidth(), getHeight(), this);
                updateSizes();
        }

        private void updateSizes() {
                if (windowSize.width != getWidth() || windowSize.height != getHeight()) {
                        // update the window dimensions
                        windowSize.setSize(getWidth(), getHeight());
                        // update the size of the other panels
                        int newPyramidPanelWidth = (getWidth() / 2);
                        int newPlayerPanelWidth = newPyramidPanelWidth / 2 - 20;

                        player2.updateSize(newPlayerPanelWidth);
                        pyramid.setPanelWidth(newPyramidPanelWidth);
                        pyramid.setLocation(0, 0);
                        player1.updateSize(newPlayerPanelWidth);

                        float sizeChange = (float) windowSize.width
                                        / PitCons.initialWindowSize.width;
                        this.notifyText.setFont(notifyFont.deriveFont(notifyFont.getSize()
                                        * sizeChange));
                }
        }

        // PlayGame is called from PyramidPanel when a valid mouse event occurs
        public void playGame(Vertex startVertex, Vertex endVertex) {
                if (startVertex == null || endVertex == null)
                        throw new IllegalArgumentException("Vertices cannot be null.");

                int currentPlayer = getCurrentPlayerNumber();
                int otherPlayer = getOtherPlayerNumber();

                boolean dragAndDrop = (startVertex != endVertex);
                boolean piecesLeft = getPiecesLeft(currentPlayer) > 0;
                boolean endVertexIsAdjacent = endVertex == startVertex.getLeft()
                                || endVertex == startVertex.getRight()
                                || endVertex == startVertex.getAbove()
                                || endVertex == startVertex.getBelow();

                boolean takePiece = takeNextPiece && !dragAndDrop
                                && (startVertex.getPlayerNumber() == playerToRemove);
                boolean putPiece = !takeNextPiece && !dragAndDrop && piecesLeft
                                && startVertex.isAvailable();
                boolean movePiece = !takeNextPiece && dragAndDrop && !piecesLeft
                                && endVertex.isAvailable() && endVertexIsAdjacent
                                && startVertex.getPlayerNumber() == currentPlayer;

                if (basicGame) {// basic game
                        if (!dragAndDrop)
                                playBasicGame(startVertex);
                } else { // traditional game
                        if (takePiece) {
                                takePieceFromBoard(startVertex);
                                if (pyramid.playerCantMove(otherPlayer)) {
                                        String message;
                                        if (lang) {
                                                notifyPlayer(otherPlayer, Language.cantMoveMessage, 0);
                                                message = "Player " + otherPlayer
                                                                + Language.cantMoveMessage;
                                        } else {
                                                notifyPlayer(otherPlayer, Language.cantMoveMessageSpa,
                                                                0);
                                                message = "Jugador " + otherPlayer
                                                                + Language.cantMoveMessageSpa;
                                        }
                                        showMessage(message, message);
                                        if (lang) {
                                                notifyPlayer(currentPlayer, Language.moveAgainMessage,
                                                                0);
                                        } else {
                                                notifyPlayer(currentPlayer,
                                                                Language.moveAgainMessageSpa, 0);
                                        }
                                        switchPlayer();
                                }

                        } else if (putPiece) {
                                putPieceOnBoard(startVertex, currentPlayer, otherPlayer);
                                switchPlayer();
                        } else if (movePiece) {
                                movePieceOnBoard(startVertex, endVertex, currentPlayer,
                                                otherPlayer);
                                if (pyramid.playerCantMove(otherPlayer)) {
                                        String message;
                                        if (lang) {
                                                notifyPlayer(otherPlayer, Language.cantMoveMessage, 0);
                                                message = "Player " + otherPlayer
                                                                + Language.cantMoveMessage;
                                        } else {
                                                notifyPlayer(otherPlayer, Language.cantMoveMessageSpa,
                                                                0);
                                                message = "Jugador " + otherPlayer
                                                                + Language.cantMoveMessageSpa;
                                        }
                                        showMessage(message, message);
                                        if (lang) {
                                                notifyPlayer(currentPlayer, Language.moveAgainMessage,
                                                                0);
                                        } else {
                                                notifyPlayer(currentPlayer,
                                                                Language.moveAgainMessageSpa, 0);
                                        }
                                } else {
                                        switchPlayer();
                                }

                        }
                        checkForEndOfGame();
                }
        }

        // win conditions for traditional game
        private void checkForEndOfGame() {
                // tie traditional game: no pieces left and no one lost pieces
                if (p1PiecesLeft == 0 && p2PiecesLeft == 0 && p1PiecesLost == 0
                                && p2PiecesLost == 0) {
                        if (lang) {
                                setNotifyText(Language.tieGameMessage, Color.blue);
                                playBackgroundSound(false);
                                showMessage(Language.tieGameMessage, Language.tieGameMessage);
                        } else {
                                setNotifyText(Language.tieGameMessageSpa, Color.blue);
                                playBackgroundSound(false);
                                showMessage(Language.tieGameMessageSpa,
                                                Language.tieGameMessageSpa);
                        }
                        resetGame();
                        return;
                }
                // player1 wins: player2 loses maxPiecesYouCanLose
                if (p1PiecesLeft == 0 && p2PiecesLeft == 0
                                && p2PiecesLost > PitCons.maxPiecesYouCanLose
                                && p1PiecesLost <= PitCons.maxPiecesYouCanLose) {

                        weGotWinner(1);
                        resetGame();
                        return;
                }
                // player2 wins: player1 loses maxPiecesYouCanLose
                if (p1PiecesLeft == 0 && p2PiecesLeft == 0
                                && p1PiecesLost > PitCons.maxPiecesYouCanLose
                                && p2PiecesLost <= PitCons.maxPiecesYouCanLose) {
                        weGotWinner(2);
                        resetGame();
                        return;
                }
        }

        // traditional game play
        private void takePieceFromBoard(Vertex v) {
                takePiece(v);
                incrPiecesLost(playerToRemove);
                if (getPiecesLeft(playerToRemove) > 0) {
                        if (lang) {
                                notifyPlayer(getCurrentPlayerNumber(),
                                                Language.placePieceMessage, 0);
                        } else {
                                notifyPlayer(getCurrentPlayerNumber(),
                                                Language.placePieceMessageSpa, 1);
                        }
                } else {
                        if (lang) {
                                notifyPlayer(playerToRemove, Language.movePieceMessage, 0);
                        } else {
                                notifyPlayer(playerToRemove, Language.movePieceMessageSpa, 0);
                        }

                }
                clearTakeNextPiece();
        }

        private void putPieceOnBoard(Vertex v, int currentPlayer, int otherPlayer) {
                v.setPlayer(currentPlayer);
                playDropSound();
                v.drawPieces(pyramid.getGraphics(), this);
                decrPiecesLeft(currentPlayer); // set the game piece counts
                if (playerGot3inArow(currentPlayer, v)) { // is v in 3-in-a-row?
                        highlightPieces(otherPlayer);
                        if (lang) {
                                notifyPlayer(currentPlayer, Language.takePieceMessage, 0);
                        } else {
                                notifyPlayer(currentPlayer, Language.takePieceMessageSpa, 0);
                        }
                } else {
                        if (getPiecesLeft(otherPlayer) > 0) {
                                if (lang) {
                                        notifyPlayer(otherPlayer, Language.placePieceMessage, 0);
                                } else {
                                        notifyPlayer(otherPlayer, Language.placePieceMessageSpa, 1);
                                }
                        } else {
                                if (lang) {
                                        notifyPlayer(otherPlayer, Language.movePieceMessage, 0);
                                } else {
                                        notifyPlayer(otherPlayer, Language.movePieceMessageSpa, 0);
                                }
                        }
                }
        }

        private void movePieceOnBoard(Vertex v, Vertex newLocation,
                        int currentPlayer, int otherPlayer) {
                playMoveSound();
                v.clear();
                newLocation.setPlayer(currentPlayer);
                pyramid.drawPyramid(pyramid.getGraphics(), false);
                if (playerGot3inArow(currentPlayer, newLocation)) {
                        highlightPieces(otherPlayer);
                        if (lang) {
                                notifyPlayer(currentPlayer, Language.takePieceMessage, 0);
                        } else {
                                notifyPlayer(currentPlayer, Language.takePieceMessageSpa, 0);
                        }
                } else {
                        if (lang) {
                                notifyPlayer(otherPlayer, Language.movePieceMessage, 0);
                        } else {
                                notifyPlayer(otherPlayer, Language.movePieceMessageSpa, 0);
                        }
                }
        }

        // basic game play
        private void playBasicGame(Vertex v) {
                if (!v.isAvailable()) // can't change a square once it's been set
                        return;
                int playerNumber = getCurrentPlayerNumber();
                v.setPlayer(playerNumber);
                playDropSound();
                v.drawPieces(pyramid.getGraphics(), this);
                decrPiecesLeft(playerNumber);
                switchPlayer(); // next player's turn
                if (lang) {
                        notifyPlayer(getCurrentPlayerNumber(), Language.placePieceMessage,
                                        0);
                } else {
                        notifyPlayer(getCurrentPlayerNumber(),
                                        Language.placePieceMessageSpa, 1);
                }
                int winner = checkWin();
                if (winner != 0) {
                        weGotWinner(winner);
                        resetGame();
                        return;
                }
                // check for tie game: no pieces left
                if (basicGame && p1PiecesLeft == 0 && p2PiecesLeft == 0) {
                        if (lang) {
                                setNotifyText(Language.tieGameMessage, Color.blue);
                                playBackgroundSound(false);
                                showMessage(Language.tieGameMessage, Language.tieGameMessage);
                        } else {
                                setNotifyText(Language.tieGameMessageSpa, Color.blue);
                                playBackgroundSound(false);
                                showMessage(Language.tieGameMessageSpa,
                                                Language.tieGameMessageSpa);
                        }
                        resetGame();
                        return;
                }
        }

        private int checkLeft(Vertex mid) {
                int win = 0;
                if (mid.getPlayerNumber() != 0) {
                        if (mid.getPlayerNumber() == mid.getLeft().getPlayerNumber()
                                        && mid.getPlayerNumber() == mid.getLeft().getLeft()
                                                        .getPlayerNumber()) {
                                return mid.getPlayerNumber();
                        }
                }
                return win;
        }

        private int checkWin() {
                int win = 0;
                Vertex[][] grid = pyramid.getGrid();
                int gridCols = pyramid.getGridCols();

                for (int x = 0; x < gridCols; x++) {
                        if (grid[0][x].getPlayerNumber() == 1) {
                                if (grid[0][x].getPlayerNumber() == grid[0][x].getAbove()
                                                .getPlayerNumber()
                                                && grid[0][x].getPlayerNumber() == grid[0][x]
                                                                .getAbove().getAbove().getPlayerNumber()) {
                                        return grid[0][x].getPlayerNumber();
                                }
                        } else if (grid[0][x].getPlayerNumber() == 2) {
                                if (grid[0][x].getPlayerNumber() == grid[0][x].getAbove()
                                                .getPlayerNumber()
                                                && grid[0][x].getPlayerNumber() == grid[0][x]
                                                                .getAbove().getAbove().getPlayerNumber()) {
                                        return grid[0][x].getPlayerNumber();
                                }
                        }
                }
                for (int x = 0; x < gridCols; x++) {
                        if (x % 2 == 0) {
                                int win1 = checkLeft(grid[0][x]);
                                int win2 = checkLeft(grid[1][x]);
                                int win3 = checkLeft(grid[2][x]);
                                if (win1 != 0) {
                                        return win1;
                                } else if (win2 != 0) {
                                        return win2;
                                } else if (win3 != 0) {
                                        return win3;
                                }
                        }
                }

                return win;
        }

        private void highlightPieces(int player) {
                playHighlightSound();
                // highlight pieces that can be removed
                pyramid.highlightPlayerSquares(player);
                // set remove piece flag
                setTakeNextPiece(true, player);
        }

        private void notifyPlayer(int player, String message, int code) {
                if (code == 0) {
                        if (player == 1) {
                                setNotifyText(player1.getPlayerName() + message,
                                                PitCons.player1Color);
                        } else {
                                setNotifyText(player2.getPlayerName() + message,
                                                PitCons.player2Color);
                        }
                } else if (code == 1) {
                        if (player == 1) {
                                setNotifyText(message + player1.getPlayerName(),
                                                PitCons.player1Color);
                        } else {
                                setNotifyText(message + player2.getPlayerName(),
                                                PitCons.player2Color);
                        }
                }
        }

        // checks if Vertex v is part of a 3-in-a-row for the current player
        private boolean playerGot3inArow(int player, Vertex v) {
                if (v.getPlayerNumber() != player)
                        return false;
                // check for vertical 3-in-a-row
                Vertex bottom = v;
                // go to the bottom vertex in the current column
                while (bottom.getBelow() != null) {
                        bottom = bottom.getBelow();
                }
                if (v.getPlayerNumber() == bottom.getPlayerNumber()
                                && bottom.getPlayerNumber() == bottom.getAbove()
                                                .getPlayerNumber()
                                && bottom.getPlayerNumber() == bottom.getAbove().getAbove()
                                                .getPlayerNumber()) {
                        return true;
                }
                // check for horizontal 3-in-a-row
                // have to check 2 sides of the pyramid if v is a corner
                if (v.isCornerVertex()) {
                        // check left face of pyramid
                        if (v.getPlayerNumber() == v.getLeft().getPlayerNumber()
                                        && v.getPlayerNumber() == v.getLeft().getLeft()
                                                        .getPlayerNumber()) {
                                return true;
                        }
                        // check right face of pyramid
                        if (v.getPlayerNumber() == v.getRight().getPlayerNumber()
                                        && v.getPlayerNumber() == v.getRight().getRight()
                                                        .getPlayerNumber()) {
                                return true;
                        }
                } else { // v is a middle vertex
                                        // just check vertices to the left and right of v
                        if (v.getPlayerNumber() == v.getLeft().getPlayerNumber()
                                        && v.getPlayerNumber() == v.getRight().getPlayerNumber()) {
                                return true;
                        }
                }
                return false;
        }

        private void switchPlayer() {
                this.isPlayer1Turn = !isPlayer1Turn;
        }

        public int getPiecesLeft(int playerNumber) {
                switch (playerNumber) {
                case 1:
                        return p1PiecesLeft;
                case 2:
                        return p2PiecesLeft;
                default:
                        throw new IllegalArgumentException("Player number must be 1 or 2.");
                }
        }

        // decrements the number of pieces left by 1
        public void decrPiecesLeft(int playerNumber) {
                switch (playerNumber) {
                case 1:
                        p1PiecesLeft--;
                        player1.setPiecesLeft(p1PiecesLeft);
                        break;
                case 2:
                        p2PiecesLeft--;
                        player2.setPiecesLeft(p2PiecesLeft);
                        break;
                default:
                        throw new IllegalArgumentException("Player number must be 1 or 2.");
                }
        }

        public int getNumWins(int playerNumber) {
                switch (playerNumber) {
                case 1:
                        return p1Wins;

                case 2:
                        return p2Wins;
                default:
                        throw new IllegalArgumentException("Player number must be 1 or 2.");
                }
        }

        public int getPiecesLost(int playerNumber) {
                switch (playerNumber) {
                case 1:
                        return p1PiecesLost;
                case 2:
                        return p2PiecesLost;
                default:
                        throw new IllegalArgumentException("Player number must be 1 or 2.");
                }
        }

        // increments the number of pieces lost by 1
        public void incrPiecesLost(int playerNumber) {
                switch (playerNumber) {
                case 1:
                        p1PiecesLost++;
                        player1.setPiecesLost(p1PiecesLost);
                        break;
                case 2:
                        p2PiecesLost++;
                        player2.setPiecesLost(p2PiecesLost);
                        break;
                default:
                        throw new IllegalArgumentException("Player number must be 1 or 2.");
                }
        }

        public void setBasicGame(boolean basicGame) {
                this.basicGame = basicGame;
        }

        public boolean isSoundOn() {
                return soundOn;
        }

        public void setSoundOn(boolean soundOn) {
                this.soundOn = soundOn;
        }

        public boolean isMusicOn() {
                return musicOn;
        }

        public void setMusicOn(boolean musicOn) {
                this.musicOn = musicOn;
        }

        public void setNotifyText(String message, Color color) {
                this.notifyText.setForeground(color);
                this.notifyText.setText(message);
        }

        public void clearTakeNextPiece() {
                setTakeNextPiece(false, 0);
        }

        public void setTakeNextPiece(boolean takeNextPiece, int player) {
                this.takeNextPiece = takeNextPiece;
                this.playerToRemove = player;
        }

        public void takePiece(Vertex v) {
                playTakeSound();
                v.clear();
                pyramid.unHighlightSquares();
                pyramid.paintComponent(pyramid.getGraphics());
        }

        public int getCurrentPlayerNumber() {
                return isPlayer1Turn ? 1 : 2;
        }

        public int getOtherPlayerNumber() {
                return getCurrentPlayerNumber() % 2 + 1;
        }

        public void showMessage(String message, String title) {
                JOptionPane.showMessageDialog(this, message, title,
                                JOptionPane.PLAIN_MESSAGE);
        }

        public void showInstructions(boolean english) {
                if (basicGame) {
                        if (english) {
                                showMessage(PitCons.basicInstructions,
                                                Language.basicInstructionsTitle);
                        } else {
                                showMessage(PitCons.basicInstructionsSpa,
                                                Language.basicInstructionsTitleSpa);
                        }
                } else {
                        if (english) {
                                showMessage(PitCons.advancedInstructions,
                                                Language.advancedInstructionsTitle);
                        } else {
                                showMessage(PitCons.advancedInstructionsSpa,
                                                Language.AdvancedInstructionsTitleSpa);
                        }
                }
        }

        public void weGotWinner(int playerNumber) {
                String message;
                Color color;
                switch (playerNumber) { // get the player's name
                case 1:
                        message = player1.getPlayerName();
                        color = PitCons.player1Color;
                        p1Wins++;
                        player1.setNumWins(p1Wins);
                        break;
                case 2:
                        message = player2.getPlayerName();
                        color = PitCons.player2Color;
                        p2Wins++;
                        player2.setNumWins(p1Wins);
                        break;
                default:
                        throw new IllegalArgumentException("Player number must be 1 or 2.");
                }

                if (lang) {
                        message += Language.winString;
                } else {
                        message += Language.winStringSpa;
                }
                setNotifyText(message, color);
                playBackgroundSound(false);
                playWinSound();
                if (lang) {
                        showMessage(message, "Player " + playerNumber
                                        + Language.winStringTitle);
                } else {
                        showMessage(message, Language.winStringTitleSpa + "jugado "
                                        + playerNumber);
                }
        }

        public void playDropSound() {
                if (soundOn)
                        sounds[0].play();
        }

        public void playMoveSound() {
                if (soundOn)
                        sounds[1].play();
        }

        public void playTakeSound() {
                if (soundOn)
                        sounds[2].play();
        }

        public void playWinSound() {
                if (soundOn)
                        sounds[3].play();
        }

        public void playHighlightSound() {
                if (soundOn)
                        sounds[4].play();
        }

        public void playBackgroundSound(boolean play) {
                if (musicOn && play)
                        sounds[5].playItForever();
                else
                        sounds[5].stop();
        }
}