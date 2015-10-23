package pitarra;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class PlayerPanel extends JPanel {
        private GamePanel gPanel;
        private JLabel titleLabel, nameLabel, piecesLeft, piecesLost, numWins;
        private JPanel namePanel;
        private String player;
        private String playerName;
        private ImageIcon backdrop;
        private Color playerColor;
        private boolean showColorText;
        private boolean nameLock = false;
        private int pNum;
        private Dimension panelDimension;
        private float fontSize;

        public PlayerPanel(ImageIcon backdrop, int playerNumber, Color playerColor,
                        GamePanel panel) {
                this.gPanel = panel;
                this.titleLabel = new JLabel(Language.playerStringAC + playerNumber,
                                JLabel.CENTER);
                this.nameLabel = new JLabel(Language.nameFieldPrompt, JLabel.CENTER);
                this.numWins = new JLabel(Language.numWinsText
                                + gPanel.getNumWins(playerNumber), JLabel.CENTER);
                this.piecesLeft = new JLabel(Language.pieceCountTextPrefix
                                + gPanel.getPiecesLeft(playerNumber) + Language.pieceCountText,
                                JLabel.CENTER);
                this.piecesLost = new JLabel(gPanel.getPiecesLost(playerNumber)
                                + Language.pieceLostText, JLabel.CENTER);
                this.namePanel = new JPanel();
                this.player = Language.playerString + playerNumber;
                this.playerName = Language.nameFieldPrompt;
                this.setOpaque(false);
                this.backdrop = backdrop;
                this.playerColor = playerColor;
                this.showColorText = true;
                this.pNum = playerNumber;
                this.panelDimension = new Dimension(PitCons.initialPlayerPanelWidth,
                                PitCons.initialPlayerPanelWidth);
                this.fontSize = PitCons.fontSize;

                // titleLabel appearance
                titleLabel.setOpaque(false);
                titleLabel.setFont(PitCons.italicBoldFont);

                // nameLabel appearance
                nameLabel.setOpaque(false);
                nameLabel.setFont(PitCons.boldFont);
                nameLabel.addMouseListener(new PlayerListener());

                // pieceCount appearance
                piecesLeft.setOpaque(false);
                piecesLeft.setFont(PitCons.italicBoldFont);

                // pieceCount appearance
                piecesLost.setOpaque(false);
                piecesLost.setFont(PitCons.italicBoldFont);

                // numWins appearance
                numWins.setOpaque(false);
                numWins.setFont(PitCons.italicBoldFont);

                // namePanel appearance
                namePanel.setOpaque(false);
                namePanel.setPreferredSize(panelDimension);

                setPiecesLeft(PitCons.initialNumberOfPieces);

                namePanel.setLayout(new GridLayout(5, 1));

                // add labels to namePanel
                namePanel.add(titleLabel);
                namePanel.add(nameLabel);
                namePanel.add(piecesLeft);
                namePanel.add(piecesLost);
                namePanel.add(numWins);

                add(namePanel);
        }

        public void updateSize(int newPlayerPanelWidth) {
                this.fontSize = (float) (PitCons.fontSize * newPlayerPanelWidth / PitCons.initialPlayerPanelWidth);
                this.panelDimension.setSize(newPlayerPanelWidth, newPlayerPanelWidth);

                titleLabel.setFont(titleLabel.getFont().deriveFont(fontSize));
                nameLabel.setFont(nameLabel.getFont().deriveFont(fontSize));
                piecesLeft.setFont(piecesLeft.getFont().deriveFont(fontSize));
                piecesLost.setFont(piecesLost.getFont().deriveFont(fontSize));
                numWins.setFont(numWins.getFont().deriveFont(fontSize));

                namePanel.setSize(panelDimension);
                this.setSize(panelDimension);
        }

        public float getFontSize() {
                return fontSize;
        }

        public void updateLabels() {
                titleLabel.setText(Language.playerStringAC + " " + pNum);
                piecesLeft.setText(Language.pieceCountTextPrefix
                                + gPanel.getPiecesLeft(pNum) + Language.pieceCountText);
                piecesLost.setText(gPanel.getPiecesLost(pNum) + Language.pieceLostText);
                if (!nameLock) {
                        nameLabel.setText(Language.nameFieldPrompt);
                }
                player = Language.playerString + " " + pNum;
                numWins.setText(Language.numWinsText + gPanel.getNumWins(pNum));
        }

        public void paintComponent(Graphics page) {
                Utilities.drawBackdrop(page, backdrop, getWidth(), getHeight(), this);
        }

        public void reset(boolean isBasicGame) {
                setPiecesLeft(PitCons.initialNumberOfPieces);
                setPiecesLost(0);
                piecesLost.setVisible(!isBasicGame);
                showColorText(showColorText);
        }

        public String getPlayerName() {
                if (nameLock)
                        return playerName;
                else
                        return player;
        }

        public String getPlayer() {
                return player;
        }

        public void setNumWins(int numWins) {
                this.numWins.setText(Language.numWinsText + numWins);
        }

        public void showColorText(boolean highlightOn) {
                Color textColor = highlightOn ? playerColor : Color.black;
                titleLabel.setForeground(textColor);
                nameLabel.setForeground(textColor);
                piecesLeft.setForeground(textColor);
                piecesLost.setForeground(textColor);
                numWins.setForeground(textColor);
                repaint();
        }

        public void setPiecesLeft(int pieceCount) {
                this.piecesLeft.setText(Language.pieceCountTextPrefix + pieceCount
                                + Language.pieceCountText);
        }

        public void setPiecesLost(int piecesLost) {
                this.piecesLost.setText(piecesLost + Language.pieceLostText);
        }

        private class PlayerListener implements MouseListener {
                @Override
                public void mouseClicked(MouseEvent e) {
                        if (e.getSource() == nameLabel) {
                                String userName = JOptionPane.showInputDialog(nameLabel,
                                                Language.nameFieldPrompt, player,
                                                JOptionPane.QUESTION_MESSAGE);

                                if (userName != null && !userName.isEmpty()) {
                                        playerName = userName;
                                        nameLabel.setForeground(playerColor);
                                        nameLabel.setText(playerName);
                                        nameLock = true;
                                        gPanel.resetWin(pNum);
                                        numWins.setText(Language.numWinsText + "0");
                                }
                        }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                }

                @Override
                public void mouseExited(MouseEvent e) {
                }

                @Override
                public void mousePressed(MouseEvent e) {
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                }
        }
}