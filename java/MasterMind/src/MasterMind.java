/**
 *    Plays the game of MasterMind.
 *    MasterMind is a game where a code maker(computer) makes a secret 4 letter code
 *     which the code breaker(user) will try to find using strategy and luck
 *    @author Aarav Noronha
 *    @since 9/30/19
 */

public class MasterMind {

    private boolean reveal;            // true = reveal the master combination
    private PegArray[] guesses;        // the array of guessed peg arrays
    private PegArray master;        // the master (key) peg array
    private int[][] guessScores;

    // Constants
    private final int PEGS_IN_CODE = 4;        // Number of pegs in code
    private final int MAX_GUESSES = 10;        // Max number of guesses
    private final int PEG_LETTERS = 6;        // Number of different letters on pegs
    // 6 = A through F

    public static void main(String[] args){
        MasterMind mm = new MasterMind();
        mm.printIntroduction();
        mm.printBoard();
        mm.createMaster();
        mm.runGame();

    }

    /**
     *method creates the master code randomly using dice class
     */

    public void createMaster() {
        Dice d = new Dice(PEG_LETTERS);
        for(int i = 0; i < PEGS_IN_CODE; i++) {
            int rollValue = d.roll();
            master.getPeg(i).setLetter((char)(rollValue - 1 + 'A'));
        }

        for(int i = 0; i < PEGS_IN_CODE; i++) {
            System.out.printf("%s ", master.getPeg(i).getLetter());
        }
        System.out.println();

    }

    /**
     * constructor - sets variables to correct values and creates arrays for exact & partial values
     */
    public MasterMind(){
        reveal = false;
        master = new PegArray(PEGS_IN_CODE);
        guesses = new PegArray[MAX_GUESSES];
        for(int i = 0; i < MAX_GUESSES; i++) {
            guesses[i] = new PegArray(PEGS_IN_CODE);
        }
        guessScores = new int[2][MAX_GUESSES];
        for(int i = 0; i < 2;i++){
            for(int j = 0; j < MAX_GUESSES; j++ ){
                guessScores[i][j] = 0;
            }
        }

    }

    /**
     *    Print the introduction screen
     */
    public void printIntroduction() {
        System.out.println("\n");
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("| ___  ___             _              ___  ___ _             _                       |");
        System.out.println("| |  \\/  |            | |             |  \\/  |(_)           | |                      |");
        System.out.println("| | .  . |  __ _  ___ | |_  ___  _ __ | .  . | _  _ __    __| |                      |");
        System.out.println("| | |\\/| | / _` |/ __|| __|/ _ \\| '__|| |\\/| || || '_ \\  / _` |                      |");
        System.out.println("| | |  | || (_| |\\__ \\| |_|  __/| |   | |  | || || | | || (_| |                      |");
        System.out.println("| \\_|  |_/ \\__,_||___/ \\__|\\___||_|   \\_|  |_/|_||_| |_| \\__,_|                      |");
        System.out.println("|                                                                                    |");
        System.out.println("| WELCOME TO MONTA VISTA MASTERMIND!                                                 |");
        System.out.println("|                                                                                    |");
        System.out.println("| The game of MasterMind is played on a four-peg gameboard, and six peg letters can  |");
        System.out.println("| be used.  First, the computer will choose a random combination of four pegs, using |");
        System.out.println("| some of the six letters (A, B, C, D, E, and F).  Repeats are allowed, so there are |");
        System.out.println("| 6 * 6 * 6 * 6 = 1296 possible combinations.  This \"master code\" is then hidden     |");
        System.out.println("| from the player, and the player starts making guesses at the master code.  The     |");
        System.out.println("| player has 10 turns to guess the code.  Each time the player makes a guess for     |");
        System.out.println("| the 4-peg code, the number of exact matches and partial matches are then reported  |");
        System.out.println("| back to the user. If the player finds the exact code, the game ends with a win.    |");
        System.out.println("| If the player does not find the master code after 10 guesses, the game ends with   |");
        System.out.println("| a loss.                                                                            |");
        System.out.println("|                                                                                    |");
        System.out.println("| LET'S PLAY SOME MASTERMIND!                                                        |");
        System.out.println("+------------------------------------------------------------------------------------+");
        System.out.println("\n");
    }

    /**
     * method gets input from user and simulates a turn of the game inside a for loop
     * and deals with ending conditions of the game
     */

    public void runGame() {
        int numGuess = 0;
        boolean guessCorrect = false;
        while(numGuess < MAX_GUESSES && !guessCorrect) {
            PegArray currGuess = guesses[numGuess];
            String guess = Prompt.getString("Enter the code using (A,B,C,D,E,F). For example, ABCD or abcd from left-to-right");
            if (guess.length() == PEGS_IN_CODE) {
                guess = guess.toUpperCase();
                for(int i = 0; i < PEGS_IN_CODE; i++) {
                    if(guess.charAt(i) >= 'A' && guess.charAt(i) <= 'F'){
                        // handle valid input
                        currGuess.getPeg(i).setLetter(guess.charAt(i));
                    } else {
                        System.out.println("ERROR: Bad input, try again.");
                        return;
                    }
                }

            } else {
                System.out.println("ERROR: Bad input, try again.");
                break;
            }
            // check if guess is correct

            currGuess.getExactMatches(master); // update exact matches
            currGuess.getPartialMatches(master); // update partial matches
            numGuess++;
            if(master.getExactMatches(currGuess) == PEGS_IN_CODE) {
                guessCorrect = true;
                reveal = true; // reveal master code if guessed
                printBoard();
                System.out.println("Nice work! You found the master code in " + numGuess + " guesses. ");
                return;
            }
            printBoard();

        }
        reveal = true;
        printBoard();
        System.out.println("Oops. You were unable to find the solution in 10 guesses.");


    }
    /**
     *    Print the peg board to the screen
     */
    public void printBoard() {
        // Print header
        System.out.print("+--------+");
        for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
        System.out.println("---------------+");
        System.out.print("| MASTER |");
        for (int a = 0; a < PEGS_IN_CODE; a++)
            if (reveal)
                System.out.printf("   %c   |", master.getPeg(a).getLetter());
            else
                System.out.print("  ***  |");
        System.out.println(" Exact Partial |");
        System.out.print("|        +");
        for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
        System.out.println("               |");
        // Print Guesses
        System.out.print("| GUESS  +");
        for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
        System.out.println("---------------|");
        for (int g = 0; g < MAX_GUESSES - 1; g++) {
            printGuess(g);
            System.out.println("|        +-------+-------+-------+-------+---------------|");
        }
        printGuess(MAX_GUESSES - 1);
        // print bottom
        System.out.print("+--------+");
        for (int a = 0; a < PEGS_IN_CODE; a++) System.out.print("-------+");
        System.out.println("---------------+");
    }

    /**
     *    Print one guess line to screen
     *    @param t    the guess turn
     */
    public void printGuess(int t) {
        System.out.printf("|   %2d   |", (t + 1));
        // If peg letter in the A to F range
        char c = guesses[t].getPeg(0).getLetter();
        if (c >= 'A' && c <= 'F')
            for (int p = 0; p < PEGS_IN_CODE; p++)
                System.out.print("   " + guesses[t].getPeg(p).getLetter() + "   |");
            // If peg letters are not A to F range
        else
            for (int p = 0; p < PEGS_IN_CODE; p++)
                System.out.print("       |");
        System.out.printf("   %d      %d    |\n",
                guesses[t].getExact(), guesses[t].getPartial());
    }

}