package pitarra;

import java.awt.*;
import java.awt.event.*;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.JFrame;


public class HowToPlayMenu implements ActionListener, ItemListener {
    JTextArea results;
    JScrollPane scrollPane;
    String newline = "\n";

    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();
        menu = new JMenu("Menu");
        menu.setMnemonic(KeyEvent.VK_A);
        menu.getAccessibleContext().setAccessibleDescription("Menu");
        menuBar.add(menu);
        menuItem = new JMenuItem("How To Play",KeyEvent.VK_T);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
                return menuBar;       
    }

    public Container createContentPane() {
        JPanel contentPane = new JPanel(new BorderLayout());
        contentPane.setOpaque(true);
        results = new JTextArea(20, 30);
        results.setEditable(false);
        scrollPane = new JScrollPane(results);
        contentPane.add(scrollPane, BorderLayout.CENTER);
        return contentPane;
    }

    public void actionPerformed(ActionEvent e) {
              String string = "How To Play \n\n " +
             "1. At the start of the game, each player is given 12 tokens.\n\n"+

                 "2. Every turn a player selects where they want to place their\n" +
                 "token on the board\n\n"+

                 "3. When one player lines up 3 tokens in a row (vertically horizontally\n" +
                 "or diagonally),that player will then take one token from the oppsing player,\n" +
                 " this is called a tertiary\n\n"+
                 
                 "4. Once players have layed down all of their tokens on the board,\n"+
                 "players will then start moving their tokens to open spots\n"+
                 "to form a tertiary.\n\n"+

                 "5. Tokens can only be moved to adjacent open spots, no jumping allowed.\n\n"+

                 "6. If a player gets multiple tertiary when moving a token,\n" +
                 "only one token can be taken away\n\n"+

                 "7. If a player can not move any of their toekns, they forfeit their turn and\n"+ 
                 "it goes to the opposing player.\n\n"+

                 "8. The game will come to a draw if all players tokens are on the\n"+ 
                 "board and no moves can be made\n\n"+

                 "9. The game will carry on until on eplayer has less then 3 tokesn on the board.\n\n"
                                    ;
        results.append(string);
        results.setCaretPosition(results.getDocument().getLength());
    }

    public void itemStateChanged(ItemEvent e) {
        JMenuItem source = (JMenuItem)(e.getSource());
        String s = "Instructions."
                   + newline
                   + "    Event source: " + source.getText()
                   + " (an instance of " + getClassName(source) + ")"
                   + newline
                   + "    New state: "
                   + ((e.getStateChange() == ItemEvent.SELECTED) ?
                     "selected":"unselected");
        results.append(s + newline);
        results.setCaretPosition(results.getDocument().getLength());
    }

    
    protected String getClassName(Object object) {
        String classString = object.getClass().getName();
        int dotIndex = classString.lastIndexOf(".");
        return classString.substring(dotIndex+1);
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Pitarra");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        HowToPlayMenu demo = new HowToPlayMenu();
        frame.setJMenuBar(demo.createMenuBar());
        frame.setContentPane(demo.createContentPane());
        frame.setSize(450, 600);
        frame.setVisible(true);
    }

    public static void main(String[] args) {
              javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        }
        );
    }
}