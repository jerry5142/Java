package pitarra;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class DisplayText extends JTextArea {
        private File file;
        private Scanner scan;

        public DisplayText(String pathAndFilename) {
                this.setSize(20, 30);
                this.file = new File(pathAndFilename);

                // DONT NEED THE FILE CHOOSER STUFF.
                // THE PATH IS PASSED INTERNALLY, NOT FROM THE USER.
                // JFileChooser chooser = new JFileChooser(
                // "Text Files/Instructions.txt");

                // int status = chooser.showOpenDialog(null);

                // if(status != JFileChooser.APPROVE_OPTION)
                // ta.setText("No File Chosen");
                // else{
                // File file = chooser.getSelectedFile();

                // NEED TO PUT INTO A TRY CATCH IN CASE THE FILE CANT BE FOUND
                try {
                        scan = new Scanner(file);
                } catch (FileNotFoundException e) {
                        JOptionPane.showMessageDialog(this, "Instructions file not found.");
                        return;
                }

                String info = "";
                while (scan.hasNext())
                        info += scan.nextLine() + "\n";

                setText(info);
                setEditable(false);
        }
}
