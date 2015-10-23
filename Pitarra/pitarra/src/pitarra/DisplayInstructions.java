package pitarra;

import java.util.Scanner;
import java.io.*;
import javax.swing.*;

public class DisplayInstructions {

        public static void main (String[] args) throws IOException{
            JFrame frame = new JFrame ("Instructions");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            JTextArea ta = new JTextArea (20,30);
            //i tried setting it to open the text file under my machine
            JFileChooser choose = new JFileChooser("C:/Users/Gamble/Desktop/eclipse/CS2123/menu/example.txt");
            
        int state = choose.showOpenDialog(choose);
            
        if(state != JFileChooser.APPROVE_OPTION)
                ta.setText("No File Chosen");
            else{
                File file = choose.getSelectedFile();
                Scanner scan = new Scanner(file);
                
                String info = "";
                while(scan.hasNext())
                    info += scan.nextLine() + "\n";
                
                ta.setText(info);
                scan.close();
            }
            frame.getContentPane().add(ta);
            frame.pack();
            frame.setVisible(true);
            
        }
}

