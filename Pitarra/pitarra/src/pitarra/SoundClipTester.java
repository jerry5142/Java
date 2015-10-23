package pitarra;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class SoundClipTester {
        private static String sounds[] = { "Sound Files/BD.wav",
                        "Sound Files/GuitarBackSound.wav",
                        "Sound Files/GuitarBackSoundSlow.wav",
                        "Sound Files/GuitarBackSoundSlowReversed.wav",
                        "Sound Files/GuitarBackSoundFast.wav",
                        "Sound Files/GuitarBackSoundFastReversed.wav" };
        private static String frameTitle = "SOUND CLIP TESTER";
        private static JButton play, playForever, stop, nextSound;
        private static SoundClipPlayer player;
        private static int soundIndex = 0;
        private static JFrame frame = new JFrame(frameTitle + " Sound #"
                        + soundIndex);

        public static void main(String[] args) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                player = new SoundClipPlayer(sounds[soundIndex]);
                JPanel panel = new JPanel();

                play = new JButton("Play");
                playForever = new JButton("Play Forever");
                stop = new JButton("Stop");
                nextSound = new JButton("Next");

                ButtonListener listener = new ButtonListener();
                play.addMouseListener(listener);
                playForever.addMouseListener(listener);
                stop.addMouseListener(listener);
                nextSound.addMouseListener(listener);

                panel.add(play, BorderLayout.CENTER);
                panel.add(playForever, BorderLayout.CENTER);
                panel.add(stop, BorderLayout.CENTER);
                panel.add(nextSound, BorderLayout.CENTER);

                frame.getContentPane().add(panel);
                frame.pack();
                frame.setVisible(true);
        }

        private static class ButtonListener implements MouseListener {

                @Override
                public void mouseClicked(MouseEvent e) {
                        if (e.getSource() == play) {
                                player.play();
                        }
                        if (e.getSource() == playForever) {
                                player.playItForever();
                        }
                        if (e.getSource() == stop) {
                                player.stop();
                        }
                        if (e.getSource() == nextSound) {
                                player.stop();
                                soundIndex++;
                                soundIndex %= sounds.length;
                                player = new SoundClipPlayer(sounds[soundIndex]);
                                frame.setTitle(frameTitle + " Sound #" + soundIndex);
                                player.play();
                        }
                }

                @Override
                public void mouseEntered(MouseEvent e) {
                        // TODO Auto-generated method stub

                }

                @Override
                public void mouseExited(MouseEvent e) {
                        // TODO Auto-generated method stub

                }

                @Override
                public void mousePressed(MouseEvent e) {
                        // TODO Auto-generated method stub

                }

                @Override
                public void mouseReleased(MouseEvent e) {
                        // TODO Auto-generated method stub

                }

        }
}