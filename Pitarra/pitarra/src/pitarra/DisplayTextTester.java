package pitarra;

import javax.swing.JFrame;

public class DisplayTextTester {

        public static void main(String[] args) {
                JFrame frame = new JFrame("I");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                DisplayText ta = new DisplayText("Text Files/Instructions.txt");

                frame.getContentPane().add(ta);
                frame.pack();
                frame.setVisible(true);

        }
}