package pitarra;

import javax.swing.JFrame;

public class Pitarra {
        private static JFrame frame = new JFrame(PitCons.title);
        private static GamePanel gPanel = new GamePanel(PitCons.gameBackdrop);
        private static Menu menu = new Menu(gPanel);

        public static void main(String[] args) {
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setPreferredSize(PitCons.initialWindowSize);

                frame.getContentPane().add(gPanel);
                frame.pack();
                frame.setLocation(PitCons.initialWindowPosition);
                frame.setJMenuBar(menu.getMenuBar());

                frame.setResizable(false);
                frame.setVisible(true);
        }
}