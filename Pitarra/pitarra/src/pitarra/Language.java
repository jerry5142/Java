package pitarra;

public final class Language {
        protected static boolean langEng = true;
        
        protected final static String menuNewBegEng = "New Beginner Game";
        protected final static String menuNewTradEng = "New Traditional Game";
        protected final static String menuSoundEng = "Sound";
        protected final static String menuExitEng = "Exit";
        protected final static String menuInstructionEng = "Instructions";
        protected final static String menuResetGameEng = "Reset Game";
        protected final static String menuAboutEng = "About";
        protected final static String menuLanguageEng = "Languages";
        protected final static String menuFileEng = "File";
        protected final static String menuGameEng = "Game";
        protected final static String menuHelpEng = "Help";
        protected final static String menuEnglishEng = "English";
        protected final static String menuSpanishEng = "Español";
        protected final static String menuMusicEng = "Music";
        protected final static String playerStringEng = "Player";
        protected final static String playerStringACEng = "PLAYER";
        protected final static String pieceCountTextEng = " Left";
        protected final static String pieceLostTextEng = " lost";
        protected final static String nameFieldPromptEng = "Enter your name";
        protected final static String basicInstructionsTitle = "Basic Game Instructions";
        protected final static String advancedInstructionsTitle = "Advanced Game Instructions";
        protected static final String takePieceMessage = ", take a highlighted square.";
        protected static final String placePieceMessage = "'s turn.";
        protected static final String cantMoveMessage = ", you can't move.";
        protected static final String moveAgainMessage = ", move again.";
        protected static final String movePieceMessage = ", drag your piece on a line.";
        protected static final String tieGameMessage = "Tie Game";
        protected static final String winStringTitle = " wins!";
        protected static final String winString = " is the winner!";
        protected final static String numWinsTextEng = "Wins: ";
        protected final static String pieceCountTextPreficEng = "";
        protected final static String concedeStringEng = "Concede";
        
        protected final static String menuNewBegSpa = "Nuevo Juego Básico";
        protected final static String menuNewTradSpa = "Nuevo Juego Tradicional";
        protected final static String menuSoundSpa = "Sonido";
        protected final static String menuExitSpa = "Salir";
        protected final static String menuInstructionSpa = "Instrucciónes";
        protected final static String menuResetGameSpa = "Reiniciar el juego";
        protected final static String menuAboutSpa = "Información acerca de el juego";
        protected final static String menuLanguageSpa = "Lenguaje";
        protected final static String menuFileSpa = "Archivador";
        protected final static String menuGameSpa = "Juego";
        protected final static String menuHelpSpa = "Ayuda";
        protected final static String menuEnglishSpa = "Ingles";
        protected final static String menuSpanishSpa = "Español";
        protected final static String menuMusicSpa = "Música";
        protected final static String playerStringSpa = "Jugador";
        protected final static String playerStringACSpa = "JUGADOR ";
        protected final static String pieceCountTextSpa = " piezas";
        protected final static String pieceLostTextSpa = " piezas perdidas";
        protected final static String nameFieldPromptSpa = "Escriba su nombre";
        protected final static String basicInstructionsTitleSpa = "Instrucciones básicas para el juego";
        protected final static String AdvancedInstructionsTitleSpa = "Instrucciones avanzadas para el juego";
        protected static final String takePieceMessageSpa = ", Tome un cuadro destacado.";
        protected static final String placePieceMessageSpa = "La vuelta de ";
        protected static final String cantMoveMessageSpa = ", no puede mover";
        protected static final String moveAgainMessageSpa = ", mueva otra vez";
        protected static final String movePieceMessageSpa = ", arrastre su pieza en una línea";
        protected static final String tieGameMessageSpa = "Juego empatado";
        protected static final String winStringTitleSpa = "Gana el ";
        protected static final String winStringSpa = " es el ganador!";
        protected final static String numWinsTextSpa = "Victorias: ";
        protected final static String pieceCountTextPreficSpa = "Sobran ";
        protected final static String concedeStringSpa = "Concedia";
        
        public static String playerString = playerStringEng;
        public static String playerStringAC = playerStringACEng;
        public static String pieceCountText = pieceCountTextEng;
        public static String pieceCountTextPrefix = pieceCountTextPreficEng;
        public static String pieceLostText = pieceLostTextEng;
        public static String nameFieldPrompt = nameFieldPromptEng;
        public static String numWinsText = numWinsTextEng;
        
        public void setPlayerString(String st){
                playerString = st;
        }
        
        public void setPlayerStringAC(String st){
                playerStringAC = st;
        }
        
        private Language(){
        }
}