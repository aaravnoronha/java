/**
 *    Provides utilities for word games:
 *    1. finds all words in the dictionary that match a list of letters
 *    2. prints an array of words to the screen in tabular format
 *    3. finds the word from an array of words with the highest score
 *    4. calculates the score of a word according to a table
 *
 *    Uses the FileUtils and Prompt classes.
 *
 *    @author Aarav Noronha
 *    @since 9/15
 */

public class WordUtils
{
    private String[] words;        // the dictionary of words

    // File containing dictionary of almost 100,000 words.
    private final String WORD_FILE = "src/WordList.txt";
    /* Constructor */
    public WordUtils() {
        loadWords();
    }

    /**    Load all of the dictionary from a file into words array. */
    private void loadWords () {
        int count = 0;
        java.util.Scanner input = FileUtils.openToRead(WORD_FILE);
        while (input.hasNext()) {
            input.nextLine();
            count++;
        }
        input.close();  //close and reopen to reset pointer to start of file
        words = new String[count];
        count = 0;
        input = FileUtils.openToRead(WORD_FILE);
        while (input.hasNext()) {
            words[count] = input.nextLine().toUpperCase();
            count++;
        }
        System.out.println("count = " + count);
        input.close();
    }

    /**    Find all words that can be formed by a list of letters.
     *  @param letters    string containing list of letters
     *  @return            array of strings with all words found.
     */
    public String[] findAllWords (String letters)
    {
        int count = 0;
        String[] temp = new String[words.length];
        for(int i = 0; i < words.length; i++){
            if(isWordMatch(words[i], letters)){
                temp[count] = words[i];
                count++;
            }
        }
        String[] allWords = new String[count];
        for(int i = 0; i < count; i++){
            allWords[i] = temp[i];
        }
        return allWords;
    }

    /**
     *  Decides if a word matches a group of letters.
     *
     *  @param word  The word to test.
     *  @param letters  A string of letters to compare
     *  @return  true if the word matches the letters, false otherwise
     */
    public boolean isWordMatch (String word, String letters) {
        for(int a = 0; a < word.length(); a++) {
            char c = word.charAt(a);
            if (letters.indexOf(c) > -1){
                letters = letters.substring(0, letters.indexOf(c)) +
                        letters.substring(letters.indexOf(c) + 1);
            }

            else
                return false;
        }
        return true;
    }

    /**    Print the words found to the screen.
     *  @param wordList    array containing the words to be printed
     */
    public void printWords (String [] wordList) {
        int cols = 5;
        for(int i = 0; i < wordList.length; i++){
            if(i % cols == 0) {
                System.out.println();
            }
            System.out.printf("%-15s", wordList[i]);
        }
    }

    /**    Finds the highest scoring word according to a score table.
     *
     *  @param wordList          An array of words to check
     *  @param scoreTable    An array of 26 integer scores in letter order
     *  @return               The word with the highest score
     */

    public String bestWord (String[] wordList, int[] scoreTable)
    {
        int maxVal = 0;
        String best = "";
        for(int i = 0; i < wordList.length; i++){
            int currScore = getScore(wordList[i], scoreTable);
            if(currScore > maxVal) {
                maxVal = currScore;
                best = wordList[i];
            }
        }
        return best;
    }

    /**    Calculates the score of one word according to a score table.
     *
     *  @param word            The word to score
     *  @param scoreTable    An array of 26 integer scores in letter order
     *  @return                The integer score of the word
     */
    public int getScore (String word, int[] scoreTable)
    {
        int score = 0;
        for(int i = 0; i < word.length(); i++){
            int charIndex = word.charAt(i) - 'A';
            score += scoreTable[charIndex];
        }
        return score;
    }

    /***************************************************************/
    /************************** Testing ****************************/
    /***************************************************************/
    public static void main (String [] args)
    {
        WordUtils wu = new WordUtils();
        wu.run();
    }

    public boolean inWordList(String word){
        for(int i = 0; i < words.length; i++){
            if(words[i].equals(word)){
                return true;
            }
        }
        return false;
    }

    public void run() {
        System.out.println(isWordMatch("abcd", "abc"));
        loadWords();
        String letters = Prompt.getString("Please enter a list of letters, from 3 to 12 letters long, without spaces");
        System.out.println("letters = " + letters);
        String [] word = findAllWords(letters);
        printWords(word);

        // Score table in alphabetic order according to Scrabble
        int [] scoreTable = {1,3,3,2,1,4,2,4,1,8,5,1,3,1,1,3,10,1,1,1,1,4,4,8,4,10};
        String best = bestWord(word,scoreTable);
        System.out.println("\nHighest scoring word: " + best + "\nScore = "
                + getScore(best, scoreTable) + "\n");
    }


}
