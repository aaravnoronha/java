import java.util.regex.Pattern;
import java.util.regex.Matcher;
/**
 * Utilities for handling HTML
 *
 * @author Aarav Noronha
 * @since  10/25
 */
public class HTMLUtilities {
    // NONE = not nested in a block, COMMENT = inside a comment block
    // PREFORMAT = inside a pre-format block
    private enum TokenState { NONE, COMMENT, PREFORMAT };
    // the current tokenizer state
    private TokenState tokenState = TokenState.NONE;
    private String htmlComment = "";
    private String preText = "";
    public static void main(String[] args) {
        HTMLUtilities hu = new HTMLUtilities();
        hu.tokenizeHTMLString("<html><body><q>Quote this line.</q> Statement: a <b;  <!-- 5.5 :; Another comment --><pre>This preformatted text</pre> Horizontal rule <hr>");
    }
    /**
     * Break the HTML string into tokens. The array returned is
     * exactly the size of the number of tokens in the HTML string.
     * Example:   HTML string = "Goodnight moon goodnight stars"
     *          returns { "Goodnight", "moon", "goodnight", "stars" }
     * @param str       the HTML string
     * @return             the String array of tokens
     */
    public String[] tokenizeHTMLString(String str) {
        // make the size of the array large to start
        String[] result = new String[10000];

        boolean done = false; // when str is processed
        int count = 0; // number of all tokens
        int currIndex = 0; // index of current char being processed

        while (! done) {
            boolean tokenFound = false; // set to true if any type of token is found
            if (currIndex >= str.length()) {
                done = true;
                break;
            }

            // get current char
            char currChar = str.charAt(currIndex);

            // Handle HTML comments start
            String openHTML = "<!--";
            String closeHTML = "-->";
            int openHTMLLen = openHTML.length();
            int closeHTMLLen = closeHTML.length();
            if (tokenState != TokenState.COMMENT && tokenState != TokenState.PREFORMAT &&
                    currIndex < str.length() - openHTMLLen &&
                    str.substring(currIndex, currIndex + openHTMLLen).equals(openHTML)) {
                // detect open html comment
                tokenState = TokenState.COMMENT;
                boolean found = false; // html comment found
                boolean abort = false; // abort while loop if needed
                int closeIndex = currIndex + openHTMLLen + 1;
                while (!found && !abort) { // search for close html tag
                    if (closeIndex + closeHTMLLen > str.length()) {
                        abort = true; // abort if reached end of str
                    } else if (str.substring(closeIndex, closeIndex + closeHTMLLen).equals(closeHTML)) {
                        found = true;
                    } else {
                        closeIndex++;
                    }
                }
                if (found) { // found complete HTML comment
                    tokenState = TokenState.NONE;
                    tokenFound = true;
                    result[count] = str.substring(currIndex, closeIndex + closeHTMLLen);
                    currIndex = closeIndex + closeHTMLLen;
                    htmlComment = "";
                    count++;
                } else {
                    htmlComment += str.substring(currIndex) + "\n";
                    currIndex = closeIndex + closeHTMLLen;
                }
            }

            // Handle multi-line HTML comment (not the first line)
            if (currIndex < str.length() && tokenState == TokenState.COMMENT) {
                // start of html comment seen earlier, inside HTML comment
                boolean found = false; // html comment found
                boolean abort = false; // abort while loop if needed
                int closeIndex = currIndex;
                while (!found && !abort) { // search for close html tag
                    if (closeIndex + closeHTMLLen > str.length()) {
                        abort = true; // abort if reached end of str
                    } else if (str.substring(closeIndex, closeIndex + closeHTMLLen).equals(closeHTML)) {
                        found = true;
                        htmlComment += str.substring(0, closeIndex + closeHTMLLen);
                    } else {
                        closeIndex++;
                    }
                }
                if (found) { // found end of HTML comment
                    tokenFound = true;
                    result[count] = htmlComment;
                    currIndex = closeIndex + closeHTMLLen;
                    htmlComment = "";
                    count++;
                    tokenState = TokenState.NONE;
                } else {
                    currIndex = closeIndex + closeHTMLLen;
                    htmlComment += str + "\n";   // append whole str, part of comment
                }
            }

            // Handle start of pre-formatted text
            String openPretext = "<pre>";
            String closePretext = "</pre>";
            int openPretextLen = openPretext.length();
            int closePretextLen = closePretext.length();
            if (tokenState != TokenState.PREFORMAT &&
                    currIndex <= str.length() - openPretextLen &&
                    str.substring(currIndex, currIndex + openPretextLen).equals("<pre>")) {
                // add <pre> to tokens
                result[count] = str.substring(currIndex, currIndex + openPretextLen);
                count++;
                tokenState = TokenState.PREFORMAT;
                boolean found = false; // start of preformat text found
                boolean abort = false; // abort while loop if needed
                int closeIndex = currIndex + openPretextLen;
                while (!found && !abort) { // search for close preformatText tag
                    if (closeIndex + closePretextLen > str.length()) {
                        abort = true; // abort if reached end of str
                    } else if (str.substring(closeIndex, closeIndex + closePretextLen).equals(closePretext)) {
                        found = true;
                        preText += str.substring(0, closeIndex);
                    } else {
                        closeIndex++;
                    }
                }
                if (found) {
                    tokenState = TokenState.NONE;
                    tokenFound = true;
                    result[count] = str.substring(currIndex + openPretextLen, closeIndex);
                    count++;
                    currIndex = closeIndex + closePretextLen;
                    preText = "";
                    result[count] = "</pre>"; // add </pre> tag after preText
                    count++;
                } else {
                    preText += str.substring(currIndex + openPretextLen) + "\n";
                    currIndex = closeIndex;
                }
            }

            // Handle multi-line preformated text (not the first line)
            // tokenState must be PREFORMAT
            if (currIndex < str.length() && tokenState == TokenState.PREFORMAT) {
                // start of html comment seen earlier, inside HTML comment
                boolean found = false; // preformatText found
                boolean abort = false; // abort while loop if needed
                int closeIndex = currIndex;
                while (!found && !abort) { // search for close html tag
                    if (closeIndex + closePretextLen > str.length()) {
                        abort = true; // abort if reached end of str
                    } else if (str.substring(closeIndex, closeIndex + closePretextLen).equals(closePretext)) {
                        found = true;
                        preText += str.substring(0, closeIndex);
                    } else {
                        closeIndex++;
                    }
                }
                if (found) { // found end of HTML comment
                    tokenState = TokenState.NONE;
                    tokenFound = true;
                    result[count] = preText;
                    count++;
                    currIndex = closeIndex + closePretextLen;
                    preText = "";
                    result[count] = "</pre>"; // add </pre> tag after preText
                    count++;
                } else {
                    currIndex = closeIndex + closePretextLen;
                    preText += str + "\n";   // append str to preformatText
                }
            }

            // Handle HTML tag: must begin with a '<' and end with '>'
            else if (currIndex < str.length() - 2 && str.charAt(currIndex) == '<') {
                int closeIndex = currIndex + 1;
                if (str.charAt(currIndex+1) == '/') { // handle close tag
                    closeIndex++;
                }
                boolean found = false; // set if HTML tag found
                boolean abort = false; // abort while loop if not HTML tag character detected

                while (!found && !abort) {
                    char c = str.charAt(closeIndex);
                    if (c != '>') {
                        closeIndex++;
                    } else if (c == '>') {
                        found = true; // close tag found
                    } else {
                        abort = true; // non-tag character detected, abort
                    }
                }
                if (found) {
                    tokenState = TokenState.NONE;
                    tokenFound = true;
                    result[count] = str.substring(currIndex, closeIndex + 1);
                    currIndex = closeIndex;
                    count++;
                }
            }

            // Handle word
            else if (currIndex < str.length() &&
                    ((currChar >= 'A' && currChar <= 'Z') || (currChar >= 'a' && currChar <= 'z'))) {
                int closeIndex = currIndex + 1;
                boolean found = false; // set if word found

                while (!found) {
                    if (closeIndex < str.length()) {
                        char c = str.charAt(closeIndex);
                        if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                            closeIndex++;
                        } else if (c == '-' && closeIndex < str.length() - 1) {
                            char nextc = str.charAt(closeIndex+1);
                            if ((nextc >= 'A' && nextc <= 'Z') || (nextc >= 'a' && nextc <= 'z')) {
                                // continue word if alpha character occurs after '-'
                                closeIndex++;
                            }
                        } else {
                            found = true; // non-word character detected
                        }
                    } else {
                        found = true;
                    }
                }
                tokenState = TokenState.NONE;
                tokenFound = true;
                result[count] = str.substring(currIndex, closeIndex);
                currIndex = closeIndex;
                count++;
            }

            // Handle number: begins with '+' or '-' or <digit>
            else if ((currChar == '+' || currChar == '-') || (currChar >= '0' && currChar <= '9')) {
                int closeIndex = currIndex + 1;
                // handle +<digit>, -<digit>
                if ((currChar == '+' || currChar == '-') &&
                        str.charAt(currIndex+1) >= '0' && str.charAt(currIndex+1) <= '9') {
                    closeIndex++;
                }
                boolean found = false; // set if word found
                boolean decimalFound = false; // set if decimal point found
                boolean expFound = false; // set if exp found

                while (!found) {
                    if (closeIndex < str.length()) {
                        char c = str.charAt(closeIndex);
                        if (c >= '0' && c <= '9') {
                            closeIndex++;
                        } else if (c == '.') {
                            // handle decimal point
                            if (!decimalFound) {
                                decimalFound = true;
                                closeIndex++;
                            } else {
                                // 2nd decimal point not allowed, exit with current number
                                found = true;
                            }
                        } else if (c == 'e') {
                            // handle exp
                            if (!expFound) {
                                // handle e+<number> and e-<number>
                                if (closeIndex < str.length() + 1 &&
                                        (str.charAt(closeIndex+1) == '-' || str.charAt(closeIndex+1) == '+')) {
                                    closeIndex++;
                                }
                                decimalFound = false; // allow decimal in exp
                                expFound = true;
                                closeIndex++;
                            } else {
                                // 2nd exp not allowed, exit with current number
                                found = true;
                            }
                        } else {
                            found = true; // non-number character detected
                        }
                    } else {
                        found = true;
                    }
                }
                if (found) {
                    tokenState = TokenState.NONE;
                    tokenFound = true;
                    result[count] = str.substring(currIndex, closeIndex);
                    currIndex = closeIndex;
                    count++;
                }
            }

            // Handle punctuation
            else if (isPunctuation(currChar)) {
                tokenState = TokenState.NONE;
                tokenFound = true;
                result[count] = str.substring(currIndex, currIndex + 1);
                currIndex++;
                count++;
            }

            // if no token found, move index forward
            if (!tokenFound) {
                currIndex++;
            }

            // if reached end of str, we are done
            if (currIndex >= str.length()) {
                done = true;
            }

        }

        // return the correctly sized array
        String[] actual = new String[count];
        for(int i = 0; i < count;i++){
            actual[i] = result[i];
        }
        return actual;
    }

    /**
     *
     * @param c character in string
     * @return if character in string
     */
    public boolean isPunctuation(char c) {
        // handle characters in [. , ; : ( ) ? ! = & ~ + -]
        String puncChars = ".,;:()?!=&~+-";
        if (puncChars.indexOf(c) >= 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Print the tokens in the array to the screen
     * Precondition: All elements in the array are valid String objects.
     *          (no nulls)
     * @param tokens     an array of String tokens
     */
    public void printTokens(String[] tokens) {
        if (tokens == null) return;
        for (int a = 0; a < tokens.length; a++) {
            if (a % 5 == 0) System.out.print("\n  ");
            System.out.print("[token " + a + "]: " + tokens[a] + " ");
        }
        System.out.println();
    }

}
