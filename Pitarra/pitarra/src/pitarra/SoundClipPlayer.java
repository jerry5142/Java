package pitarra;

import java.io.*;
import javax.sound.sampled.*;
import javax.swing.*;

// To play sound using Clip, the process need to be alive.
// Hence, we use a Swing application.
@SuppressWarnings("serial")
public class SoundClipPlayer extends JFrame {

        public Clip sClip;

        // Constructor
        public SoundClipPlayer(String soundFileName) {
                try {
                        // Open an audio input stream.
                        File soundFile = new File(soundFileName);
                        AudioInputStream audioIn = AudioSystem
                                        .getAudioInputStream(soundFile);
                        // Get a sound clip resource.
                        sClip = AudioSystem.getClip();
                        // Open audio clip and load samples from the audio input stream.
                        sClip.open(audioIn);

                } catch (UnsupportedAudioFileException e) {
                        System.out.println("sound error 1");
                } catch (IOException e) {
                        System.out.println("sound error 2");
                } catch (LineUnavailableException e) {
                        System.out.println("sound error 3");
                }
        }

        public void stop() {// to stop it
                try {
                        sClip.stop();
                } catch (NullPointerException e) {
                        System.out.println("sound error 4");
                }
        }

        public void play() {// play it once
                try {
                        if (sClip.isRunning())
                                sClip.stop();
                        sClip.setFramePosition(0);
                        sClip.start();
                } catch (NullPointerException e) {
                        System.out.println("sound error 4");
                }
        }

        public void playItForever() {// loop it
                try {
                        if (sClip.isRunning())
                                sClip.stop();
                        sClip.setFramePosition(0);
                        sClip.loop(Clip.LOOP_CONTINUOUSLY);
                } catch (NullPointerException e) {
                        System.out.println("sound error 4");
                }

        }
}