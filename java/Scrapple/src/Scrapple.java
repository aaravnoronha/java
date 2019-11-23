import java.lang.Math;
import java.util.Queue;

/**
 *  A simple version of the Scrabble game where the user plays against the computer.
 *
 *  @author Aarav Noronha
 *  @since 9/18
 */

public class Scrapple {

    public int [] scores = {1, 3, 3, 2, 1, 4, 2, 4, 1, 8, 5, 1, 3, 1, 1, 3, 10,
            1, 1, 1, 1, 4, 4, 8, 4, 10};
    private String tilesRemaining = "AAAAAAAAAABBCCDDDDEEEEEEEEEEEEEFFGGGHHIIIIIIIII" +
            "JKLLLLMMNNNNNNOOOOOOOOPPQRRRRRRSSSSTTTTTTUUUUVVWWXYYZ";
    private final int NUMTILES = 8;			// the number of tiles per player
    private final int MIN_WORD_LENGTH = 4;	// minimum of 4 characters

    /**
     * Constructor - does not perform any task
     */
    public Scrapple() {
    }

    public static void main(String [] args) {
        Scrapple sjr = new Scrapple();
        sjr.printIntroduction();
        sjr.playGame();
    }


    /**
     *  Print the introduction screen for Scrapple.
     */
    public void printIntroduction() {
        System.out.print(" _______     _______     ______     ______    ");
        System.out.println(" ______    ______   __          _______");
        System.out.print("/\\   ___\\   /\\  ____\\   /\\  == \\   /\\  __ \\   ");
        System.out.println("/\\  == \\  /\\  == \\ /\\ \\        /\\  ____\\");
        System.out.print("\\ \\___   \\  \\ \\ \\____   \\ \\  __<   \\ \\  __ \\  ");
        System.out.println("\\ \\  _-/  \\ \\  _-/ \\ \\ \\_____  \\ \\  __\\");
        System.out.print(" \\/\\______\\  \\ \\______\\  \\ \\_\\ \\_\\  \\ \\_\\ \\_\\ ");
        System.out.println(" \\ \\_\\     \\ \\_\\    \\ \\______\\  \\ \\______\\");
        System.out.print("  \\/______/   \\/______/   \\/_/ /_/   \\/_/\\/_/ ");
        System.out.println("  \\/_/      \\/_/     \\/______/   \\/______/ TM");
        System.out.println();
        System.out.print("This game is a modified version of Scrabble. ");
        System.out.println("The game starts with a pool of letter tiles, with");
        System.out.println("the following group of 100 tiles:\n");

        for (int i = 0; i < tilesRemaining.length(); i ++) {
            System.out.printf("%c ", tilesRemaining.charAt(i));
            if (i == 49) System.out.println();
        }
        System.out.println("\n");
        System.out.printf("The game starts with %d tiles being chosen at ran", NUMTILES);
        System.out.println("dom to fill the player's hand. The player must");
        System.out.printf("then create a valid word, with a length from 4 to %d ", NUMTILES);
        System.out.println("letters, from the tiles in his/her hand. The");
        System.out.print("\"word\" entered by the player is then checked. It is first ");
        System.out.println("checked for length, then checked to make ");
        System.out.print("sure it is made up of letters from the letters in the ");
        System.out.println("current hand, and then it is checked against");
        System.out.print("the word text file. If any of these tests fail, the game");
        System.out.println(" terminates. If the word is valid, points");
        System.out.print("are added to the player's score according to the following table ");
        System.out.println("(These scores are taken from the");
        System.out.println("game of Scrabble):");

        // Print line of letter scores
        char c = 'A';
        for (int i = 0; i < 26; i++) {
            System.out.printf("%3c", c);
            c = (char)(c + 1);
        }
        System.out.println();
        for (int i = 0; i < scores.length; i++) System.out.printf("%3d", scores[i]);
        System.out.println("\n");

        System.out.print("The score is doubled (BONUS) if the word has consecutive double ");
        System.out.println("letters (e.g. ball).\n");

        System.out.print("Once the player's score has been updated, more tiles are ");
        System.out.println("chosen at random from the remaining pool");
        System.out.printf("of letters, to fill the player's hand to %d letters. ", NUMTILES);
        System.out.println("The player again creates a word, and the");
        System.out.print("process continues. The game ends when the player enters an ");
        System.out.println("invalid word, or the letters in the");
        System.out.println("pool and player's hand run out. Ready? Let's play!\n");

        Prompt.getString("HIT ENTER on the keyboard to continue:");

    }

    /**
     *
     * @return
     */

    public String getRandomHand() {
        String hand = "";
        for(int i = 0; i < NUMTILES; i++) {
            int range = tilesRemaining.length() - 1;
            int randIndex = (int)(Math.random() * range);
            char randChar = tilesRemaining.charAt(randIndex);
            hand += randChar;
            // System.out.printf("char[%d] = %s\n", i, randChar);
            // System.out.printf("BEFORE: tilesRem = %s\n", tilesRemaining);
            tilesRemaining = tilesRemaining.substring(0, randIndex)
                    + tilesRemaining.substring(randIndex + 1);
            // System.out.printf("AFTER:  tilesRem = %s\n", tilesRemaining);
        }
        return hand;
    }

    /**
     *
     */
    public void printTiles(){
        System.out.println("Here are the tiles remaining in the pool of letters: ");
        for(int i = 0; i < tilesRemaining.length();i++) {
            if(i % 20 == 0) {
                System.out.println();
            }
            System.out.print(tilesRemaining.charAt(i) + " ");
        }
        System.out.println();
    }

    /**
     *
     * @param userHand
     * @param compHand
     */
    public void printHands(String userHand, String compHand) {
        String userStr = "";
        String compStr = "";
        for (int i = 0; i < userHand.length(); i++) {
            userStr += userHand.charAt(i) + " ";
        }
        for (int i = 0; i < compHand.length(); i++) {
            compStr += compHand.charAt(i) + " ";
        }

        System.out.println("THE TILES IN YOUR HAND ARE:            " + userStr);
        System.out.println("THE TILES IN THE COMPUTER HAND ARE:    " + compStr);
    }

    /**
     *
     */
    public void playGame() {
        int[] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        WordUtils wu = new WordUtils();
        int userScore = 0;
        int compScore = 0;
        boolean gameOver = false;
        boolean userTurn = true;
        String userHand = getRandomHand();
        String compHand = getRandomHand();
        while (!gameOver) { //check game over
            if (userTurn) {
                printHands(userHand, compHand);
                String word = Prompt.getString("Please enter a word from your current set of tiles");
                // check if letters in word exist in tiles
                if(wu.isWordMatch(word, userHand) &&
                        wu.inWordList(word) &&
                        word.length() >= MIN_WORD_LENGTH) {
                    // add word to score, deal with double points
                    userScore += wu.getScore(word, scoreTable);
                    // remove tiles in word inputted from tilesRemaining
                    tilesRemaining = removeTiles(word, tilesRemaining);
                    userHand = removeTiles(word, userHand);
                    if (NUMTILES - userHand.length() > tilesRemaining.length()) {
                        System.out.println("User turn over");
                        gameOver = true;
                    } else {
                        userHand = addLetters(userHand, tilesRemaining);
                    }
                    printScores(userScore, compScore);
                    printTiles();
                } else {
                    // if word not in word list, what to do?
                }
            } else {
                // computer turn
                String[] words = wu.findAllWords(compHand);
                if(words.length == 0){
                    gameOver = true;
                }

                // Score table in alphabetic order according to Scrabble
                String word = wu.bestWord(words, scoreTable);
                if(word.length() >= MIN_WORD_LENGTH){
                    compScore += wu.getScore(word, scoreTable);
                    // remove tiles in word inputted from tilesRemaining
                    tilesRemaining = removeTiles(word, tilesRemaining);
                    compHand = removeTiles(word, compHand);
                    if (NUMTILES - compHand.length() > tilesRemaining.length()) {
                        System.out.println("Computer turn over");
                        gameOver = true;
                    } else {
                        compHand = addLetters(compHand, tilesRemaining);
                    }
                    printScores(userScore, compScore);
                    printTiles();
                    // add word to score/deal with double points
                }

            }
            userTurn = !userTurn; // switch turns
        }
    }

    /**
     *
     * @param word
     * @param target
     * @return
     */
    public String removeTiles(String word, String target) {
        for(int i = 0; i < word.length(); i++) {
            char wordChar = word.charAt(i);
            int indexChar = target.indexOf(wordChar);
            // System.out.printf("word = %s, wordChar = %s, indexChar = %d\n", word, wordChar, indexChar);
            if(indexChar >= 0) {
                target = target.substring(0, indexChar) + target.substring(indexChar + 1);
            }
        }
        return target;
    }

    /**
     *
     * @param userScore
     * @param compScore
     */
    public void printScores(int userScore, int compScore) {
        System.out.println();
        System.out.printf("Player Score:   %-8d\n", userScore );
        System.out.printf("Computer Score: %-8d\n", compScore );
    }

    /**
     *
     * @param word
     * @param target
     * @return
     */
    public String addLetters(String word, String target){
        int remTiles = NUMTILES - word.length();
        for(int i = 0; i < remTiles; i++) {
            int range = target.length() - 1;
            int randIndex = (int)(Math.random() * range);
            char randChar = target.charAt(randIndex);
            word += randChar;
            // System.out.printf("char[%d] = %s\n", i, randChar);
            // System.out.printf("BEFORE: tilesRem = %s\n", tilesRemaining);
            target = target.substring(0, randIndex) + target.substring(randIndex + 1);
            // System.out.printf("AFTER:  tilesRem = %s\n", tilesRemaining);
        }
        return word;
    }

}
