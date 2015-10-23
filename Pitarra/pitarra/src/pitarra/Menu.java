
package pitarra;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JCheckBoxMenuItem;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.ButtonGroup;

public class Menu extends JMenuBar {
        /**
         * 
         */
        private static final long serialVersionUID = -9120600991573125349L;
        private JMenuBar menuBar;
        private GamePanel gPanel;
        private JMenu menuMain, menuGame, menuHelp, menuLang, menuSound;
        private JMenuItem Exit, Instruction, ResetGame, About, Concede;
        // action listener for traditional game option, beginner game option, exit,
        // sound, and instructions
        private ActionListener alTrad, alBeg, alEx, alSound, alInstruction,
                        alAbout, alReset, alEng, alSpa, alMusic, alConcede;
        private JCheckBoxMenuItem sound, NewTrad, NewBeg, langEng, langSpa, music;
        private ButtonGroup menuGroupGame, menuGroupLang;
        private boolean lang;
        private String aboutString, aboutStringSpa;
        
        public Menu(GamePanel game) {
                this.gPanel = game;

                // menu bar
                this.menuBar = new JMenuBar();

                // menu bar options
                this.menuMain = new JMenu(Language.menuFileEng);
                this.menuGame = new JMenu(Language.menuGameEng);
                this.menuHelp = new JMenu(Language.menuHelpEng);
                this.menuLang = new JMenu(Language.menuLanguageEng);
                this.menuSound = new JMenu(Language.menuSoundEng);

                // menu items
                this.NewBeg = new JCheckBoxMenuItem(Language.menuNewBegEng);
                this.NewTrad = new JCheckBoxMenuItem(Language.menuNewTradEng);
                this.sound = new JCheckBoxMenuItem(Language.menuSoundEng);
                this.Exit = new JMenuItem(Language.menuExitEng);
                this.Instruction = new JMenuItem(Language.menuInstructionEng);
                this.ResetGame = new JMenuItem(Language.menuResetGameEng);
                this.About = new JMenuItem(Language.menuAboutEng);
                this.langEng = new JCheckBoxMenuItem(Language.menuEnglishEng);
                this.langSpa = new JCheckBoxMenuItem(Language.menuSpanishEng);
                this.music = new JCheckBoxMenuItem(Language.menuMusicEng);
                this.Concede = new JMenuItem(Language.concedeStringEng);

                // menu grouping to make game mode mutually exclusive
                this.menuGroupGame = new ButtonGroup();
                this.menuGroupLang = new ButtonGroup();

                this.alTrad = new ButtonActionNewGameT();
                this.alBeg = new ButtonActionNewGameB();
                this.alEx = new ButtonActionExit();
                this.alSound = new ButtonActionSound();
                this.alInstruction = new ButtonActionInstruction();
                this.alAbout = new ButtonActionAbout();
                this.alReset = new ButtonActionResetGame();
                this.alEng = new ButtonActionEnglish();
                this.alSpa = new ButtonActionSpanish();
                this.alMusic = new ButtonActionMusic();
                this.alConcede = new ButtonActionConcede();

                this.NewTrad.addActionListener(alTrad);
                this.NewBeg.addActionListener(alBeg);
                this.Exit.addActionListener(alEx);
                this.sound.addActionListener(alSound);
                this.Instruction.addActionListener(alInstruction);
                this.ResetGame.addActionListener(alReset);
                this.About.addActionListener(alAbout);
                this.langEng.addActionListener(alEng);
                this.langSpa.addActionListener(alSpa);
                this.music.addActionListener(alMusic);
                this.Concede.addActionListener(alConcede);
                
                this.lang = true;
                
                this.aboutString = "Pitarra Ver 0.2" + 
                                                "\n\n" + 
                                                "By:" + 
                                                "\n\n" + 
                                                "Lin D. \"The Master\" Wyeth\n" + 
                                                "Matthew \"The Artist\" Gamble\n" + 
                                                "Jesse \"The Sound Creator\" Leija\n" + 
                                                "Jerry \"The Guardian of Code\" Swank\n" + 
                                                "Priya \"The King of Kings\" Pramesi";
                this.aboutStringSpa = "Pitarra Ver 0.2" + 
                                                                "\n\n" + 
                                                                "Por:" + 
                                                                "\n\n" + 
                                                                "Lin D. \"El Maestro\" Wyeth\n" + 
                                                                "Matthew \"El Artista\" Gamble\n" + 
                                                                "Jesse \"El Creador de Sonido\" Leija\n" + 
                                                                "Jerry \"El Guardián de Código\" Swank\n" + 
                                                                "Priya \"El Rey de Reyes\" Pramesi";

                setMenu();
        }

        public JMenuBar getMenuBar() {
                return menuBar;
        }

        private void setMenu() {
                menuBar.add(menuMain);
                menuBar.add(menuGame);
                menuBar.add(menuSound);
                menuBar.add(menuLang);
                menuBar.add(menuHelp);

                menuGroupGame.add(NewTrad);
                menuGroupGame.add(NewBeg);
                menuGroupGame.setSelected(NewBeg.getModel(), true);
                menuGroupLang.add(langEng);
                menuGroupLang.add(langSpa);
                menuGroupLang.setSelected(langEng.getModel(), true);

                menuGame.add(NewBeg);
                menuGame.add(NewTrad);
                menuGame.addSeparator();
                menuGame.add(Concede);
                menuGame.addSeparator();
                menuGame.add(Instruction);

                menuSound.add(sound);
                menuSound.add(music);
                sound.setSelected(true);
                music.setSelected(true);

                menuLang.add(langEng);
                menuLang.add(langSpa);

                menuHelp.add(About);

                menuMain.add(ResetGame);
                menuMain.addSeparator();
                menuMain.add(Exit);
        }

        private class ButtonActionAbout implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        // PitCons.aboutText
                        if(lang){
                        gPanel.showMessage(aboutString, "About Pitarra");
                        }else{
                                gPanel.showMessage(aboutStringSpa, "Sobre Pitarra");
                        }
                }

        }

        private class ButtonActionResetGame implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        gPanel.resetGame();
                }

        }

        private class ButtonActionNewGameT implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        gPanel.setBasicGame(false);
                        gPanel.resetGame();
                }
        }

        private class ButtonActionNewGameB implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        gPanel.setBasicGame(true);
                        gPanel.resetGame();
                }
        }

        private class ButtonActionExit implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        System.exit(0);
                }
        }

        private class ButtonActionSound implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        if (gPanel.isSoundOn()) {
                                gPanel.setSoundOn(false);
//                                gPanel.playBackgroundSound(false);
                        } else {
                                gPanel.setSoundOn(true);
//                                gPanel.playBackgroundSound(true);
                        }
                }
        }

        private class ButtonActionMusic implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent arg0) {
                        if (gPanel.isMusicOn()) {
                                gPanel.setMusicOn(false);
                                gPanel.playBackgroundSound(false);
                        } else {
                                gPanel.setMusicOn(true);
                                gPanel.playBackgroundSound(true);
                        }
                }

        }

        private class ButtonActionInstruction implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        gPanel.showInstructions(lang);
                }
        }
        
        private class ButtonActionConcede implements ActionListener {

            @Override
            public void actionPerformed(ActionEvent e) {
                    gPanel.concedeGame();
            }
                }

        private class ButtonActionEnglish implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent arg0) {

                        menuMain.setText(Language.menuFileEng);
                        menuGame.setText(Language.menuGameEng);
                        menuHelp.setText(Language.menuHelpEng);
                        menuLang.setText(Language.menuLanguageEng);
                        menuSound.setText(Language.menuSoundEng);

                        NewBeg.setText(Language.menuNewBegEng);
                        NewTrad.setText(Language.menuNewTradEng);
                        sound.setText(Language.menuSoundEng);
                        Exit.setText(Language.menuExitEng);
                        Instruction.setText(Language.menuInstructionEng);
                        ResetGame.setText(Language.menuResetGameEng);
                        About.setText(Language.menuAboutEng);
                        langEng.setText(Language.menuEnglishEng);
                        langSpa.setText(Language.menuSpanishEng);
                        music.setText(Language.menuMusicEng);
                        Concede.setText(Language.concedeStringEng);
                        
                        lang = true;
                        
                        Language.playerString = Language.playerStringEng;
                        Language.playerStringAC = Language.playerStringACEng;
                        Language.pieceCountText = Language.pieceCountTextEng;
                        Language.pieceLostText = Language.pieceLostTextEng;
                        Language.nameFieldPrompt = Language.nameFieldPromptEng;
                        Language.numWinsText = Language.numWinsTextEng;
                        Language.pieceCountTextPrefix = Language.pieceCountTextPreficEng;
                        gPanel.getPlayer1().updateLabels();
                        gPanel.getPlayer2().updateLabels();
                        gPanel.setLang(true);
                        System.out.print(Language.playerString);
                }

        }

        private class ButtonActionSpanish implements ActionListener {

                @Override
                public void actionPerformed(ActionEvent e) {
                        menuMain.setText(Language.menuFileSpa);
                        menuGame.setText(Language.menuGameSpa);
                        menuHelp.setText(Language.menuHelpSpa);
                        menuLang.setText(Language.menuLanguageSpa);
                        menuSound.setText(Language.menuSoundSpa);

                        NewBeg.setText(Language.menuNewBegSpa);
                        NewTrad.setText(Language.menuNewTradSpa);
                        sound.setText(Language.menuSoundSpa);
                        Exit.setText(Language.menuExitSpa);
                        Instruction.setText(Language.menuInstructionSpa);
                        ResetGame.setText(Language.menuResetGameSpa);
                        About.setText(Language.menuAboutSpa);
                        langEng.setText(Language.menuEnglishSpa);
                        langSpa.setText(Language.menuSpanishSpa);
                        music.setText(Language.menuMusicSpa);
                        Concede.setText(Language.concedeStringSpa);
                        
                        lang = false;
                        
                        Language.playerString = Language.playerStringSpa;
                        Language.playerStringAC = Language.playerStringACSpa;
                        Language.pieceCountText = Language.pieceCountTextSpa;
                        Language.pieceLostText = Language.pieceLostTextSpa;
                        Language.nameFieldPrompt = Language.nameFieldPromptSpa;
                        Language.numWinsText = Language.numWinsTextSpa;
                        Language.pieceCountTextPrefix = Language.pieceCountTextPreficSpa;
                        gPanel.getPlayer1().updateLabels();
                        gPanel.getPlayer2().updateLabels();
                        gPanel.setLang(false);
                        System.out.print(Language.playerString);     
                }

        }

}