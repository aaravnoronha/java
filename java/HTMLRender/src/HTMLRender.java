import java.util.Scanner;

/**
 *	HTMLRender
 *	This program renders HTML code into a JFrame window.
 *	It requires your HTMLUtilities class and
 *	the SimpleHtmlRenderer and HtmlPrinter classes.
 *
 *	The tags supported:
 *		<html>, </html> - start/end of the HTML file
 *		<body>, </body> - start/end of the HTML code
 *		<p>, </p> - Start/end of a paragraph.
 *					Causes a newline before and a blank line after. Lines are restricted
 *					to 80 characters maximum.
 *		<hr>	- Creates a horizontal rule on the following line.
 *		<br>	- newline (break)
 *		<b>, </b> - Start/end of bold font print
 *		<i>, </i> - Start/end of italic font print
 *		<q>, </q> - Start/end of quotations
 *		<hX>, </hX> - Start/end of heading with size X = 1, 2, 3, 4, 5, 6
 *		<pre>, </pre> - Preformatted text
 *
 *	@author Aarav Noronha
 *	@version
 */
public class HTMLRender {

    // the array holding all the tokens of the HTML file
    private String [] tokens;
    private final int TOKENS_SIZE = 100000;	// size of array
    private HTMLUtilities util;


    // SimpleHtmlRenderer fields
    private SimpleHtmlRenderer render;
    private HtmlPrinter browser;


    /**
     * HTMLRender constructor
     */
    public HTMLRender() {
        // Initialize token array
        tokens = new String[TOKENS_SIZE];
        util = new HTMLUtilities();

        // Initialize Simple Browser
        render = new SimpleHtmlRenderer();
        browser = render.getHtmlPrinter();
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        HTMLRender hf = new HTMLRender();

        hf.run(args);
    }

    /**
     *
     * @param args
     */
    public void run(String[] args) {
        // read html file into string
        Scanner input = null;
        String[] allTokens = new String[10000];
        String fileName = "";
        // if the command line contains the file name, then store it
        if (args.length > 0) {
            fileName = args[0];
            // otherwise print out usage message
        } else {
            System.out.println("Usage: java HTMLTester <htmlFileName>");
            System.exit(0);
        }

        // Open the HTML file
        input = FileUtils.openToRead(fileName);

        // Read each line of the HTML file, tokenize, then print tokens
        int tokenCount = 0;
        while (input.hasNext()) {
            String line = input.nextLine();
            String[] tkns = util.tokenizeHTMLString(line);
            for(int i = 0; i < tkns.length; i++) {
                allTokens[tokenCount] = tkns[i];
                tokenCount++;
            }
        }
        // fill up only to tokenCount
        tokens = new String[tokenCount];
        for(int i = 0; i < tokenCount;i++){
            tokens[i] = allTokens[i];
        }

        // util.printTokens(tokens);
        input.close();

        // Print plain text without line feed at end
        boolean tagsComplete = false;
        int tagCount = 0;

        while(!tagsComplete) {
            String currTag = tokens[tagCount];
            String currTagLC = currTag.toLowerCase(); // currTag lowercased
            boolean tagFound = false;
            String tagStr = "";
            String nextTag = "";

            switch(currTagLC) {

                case "<p>":                  //p tags tested
                    int maxPLen = 80;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</p>") && !nextTag.equals("</P>")) {
                        if (isB(nextTag)) { // handle <b>
                            browser.print(tagStr); // flush previous tagStr
                            tagStr = "";
                            tagCount++;
                            nextTag = tokens[tagCount];
                            while (!nextTag.equals("</b>") && !nextTag.equals("</B>")) {  //test if b inside p
                                if (isBR(nextTag)) { // handle <br>
                                    browser.printBold(tagStr);
                                    tagStr = "";
                                    browser.printBreak();
                                } else if (isHR(nextTag)) { // handle <hr>
                                    browser.printBold(tagStr);
                                    tagStr = "";
                                    browser.printHorizontalRule();
                                } else if (isPunctuation(nextTag)) { // handle punctuation
                                    if (tagStr.length() + nextTag.length() > maxPLen) {
                                        browser.printBold(tagStr);
                                        tagStr = "";
                                        browser.printBreak();
                                    }
                                    tagStr += nextTag;
                                } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word/number
                                    if (tagStr.length() + nextTag.length() + 1 > maxPLen) {
                                        browser.printBold(tagStr);
                                        tagStr = "";
                                        browser.printBreak();
                                    }
                                    tagStr += " " + nextTag;
                                }
                                tagCount++;
                                nextTag = tokens[tagCount];
                            }
                            browser.printBold(tagStr);
                            tagStr = "";
                            tagCount++;
                            nextTag = tokens[tagCount];
                        } else if (isI(nextTag)) { // handle <i>
                            browser.print(tagStr); // flush previous tagStr
                            tagStr = "";
                            tagCount++;
                            nextTag = tokens[tagCount];
                            while (!nextTag.equals("</i>") && !nextTag.equals("</I>")) { // test if i inside p
                                if (isBR(nextTag)) { // handle <br>
                                    browser.printItalic(tagStr);
                                    tagStr = "";
                                    browser.printBreak();
                                } else if (isHR(nextTag)) { // handle <hr>
                                    browser.printItalic(tagStr);
                                    tagStr = "";
                                    browser.printHorizontalRule();
                                } else if (isPunctuation(nextTag)) { // handle punctuation
                                    if (tagStr.length() + nextTag.length() > maxPLen) {
                                        browser.printItalic(tagStr);
                                        tagStr = "";
                                        browser.printBreak();
                                    }
                                    tagStr += nextTag;
                                } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word/number
                                    if (tagStr.length() + nextTag.length() + 1 > maxPLen) {
                                        browser.printItalic(tagStr);
                                        tagStr = "";
                                        browser.printBreak();
                                    }
                                    tagStr += " " + nextTag;
                                }
                                tagCount++;
                                nextTag = tokens[tagCount];
                            }
                            browser.printItalic(tagStr);
                            tagStr = "";
                            tagCount++;
                            nextTag = tokens[tagCount];
                        } else if (isBR(nextTag)) { // handle <br>
                            browser.print(tagStr);
                            tagStr = "";
                            browser.printBreak();
                        } else if (isHR(nextTag)) { // handle <hr>
                            browser.print(tagStr);
                            tagStr = "";
                            browser.printHorizontalRule();
                        } else if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxPLen) {
                                browser.print(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag)) { // handle word
                            if (tagStr.length() + nextTag.length() + 1 > maxPLen) {
                                browser.print(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.print(tagStr);
                    browser.printBreak();
                    break;

                case "<i>":                       // i tags tested
                    int maxILen = 80;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</i>") && !nextTag.equals("</I>")) {
                        if (isBR(nextTag)) { // handle <br>
                            browser.print(tagStr);
                            tagStr = "";
                            browser.printBreak();
                        } else if (isHR(nextTag)) { // handle <hr>
                            browser.print(tagStr);
                            tagStr = "";
                            browser.printHorizontalRule();
                        } else if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxILen) {
                                browser.printItalic(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag)) { // handle word
                            if (tagStr.length() + nextTag.length() + 1 > maxILen) {
                                browser.printItalic(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printItalic(tagStr);
                    break;

                case "<b>":         // b tags tested
                    int maxBLen = 80;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</b>") && !nextTag.equals("</B>")) {
                        if (isBR(nextTag)) { // handle <br>
                            browser.print(tagStr);
                            tagStr = "";
                            browser.printBreak();
                        } else if (isHR(nextTag)) { // handle <hr>
                            browser.print(tagStr);
                            tagStr = "";
                            browser.printHorizontalRule();
                        } else if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxBLen) {
                                browser.printBold(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag)) { // handle word
                            if (tagStr.length() + nextTag.length() + 1 > maxBLen) {
                                browser.printBold(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printBold(tagStr);
                    break;

                case "<q>":          // q tags tested
                    int maxQLen = 80;
                    browser.print("\"");
                    tagFound = true;
                    tagCount++;
                    nextTag = tokens[tagCount];
                    tagStr = "";
                    while(!nextTag.equals("</q>") && !nextTag.equals("</Q>")) {
                        if (isBR(nextTag)) { // handle <br>
                            browser.print(tagStr);
                            tagStr = "";
                            browser.printBreak();
                        } else if (isHR(nextTag)) { // handle <hr>
                            browser.print(tagStr);
                            tagStr = "";
                            browser.printHorizontalRule();
                        } else if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxQLen) {
                                browser.print(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word
                            if (tagStr.length() + nextTag.length() + 1 > maxQLen) {
                                browser.print(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.print(tagStr);
                    browser.print("\"");
                    break;

                case "<h1>":                   // h1 through h6 tags tested
                    int maxH1Len = 40;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</h1>") && !nextTag.equals("</H1>")) {
                        if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxH1Len) {
                                browser.printHeading1(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word/number
                            if (tagStr.length() + nextTag.length() + 1 > maxH1Len) {
                                browser.printHeading1(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printHeading1(tagStr);
                    break;

                case "<h2>":
                    int maxH2Len = 50;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</h2>") && !nextTag.equals("</H2>")) {
                        if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxH2Len) {
                                browser.printHeading2(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word/number
                            if (tagStr.length() + nextTag.length() + 1 > maxH2Len) {
                                browser.printHeading2(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printHeading2(tagStr);
                    break;

                case "<h3>":
                    int maxH3Len = 60;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</h3>") && !nextTag.equals("</H3>")) {
                        if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxH3Len) {
                                browser.printHeading3(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word/number
                            if (tagStr.length() + nextTag.length() + 1 > maxH3Len) {
                                browser.printHeading3(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printHeading3(tagStr);
                    break;

                case "<h4>":
                    int maxH4Len = 80;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</h4>") && !nextTag.equals("</H4>")) {
                        if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxH4Len) {
                                browser.printHeading4(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word/number
                            if (tagStr.length() + nextTag.length() + 1 > maxH4Len) {
                                browser.printHeading4(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printHeading4(tagStr);
                    break;

                case "<h5>":
                    int maxH5Len = 100;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</h5>") && !nextTag.equals("</H5>")) {
                        if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxH5Len) {
                                browser.printHeading5(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word/number
                            if (tagStr.length() + nextTag.length() + 1 > maxH5Len) {
                                browser.printHeading5(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printHeading5(tagStr);
                    break;

                case "<h6>":
                    int maxH6Len = 120;
                    tagFound = true;
                    tagCount++;
                    tagStr = "";
                    nextTag = tokens[tagCount];
                    while(!nextTag.equals("</h6>") && !nextTag.equals("</H6>")) {
                        if (isPunctuation(nextTag)) { // handle punctuation
                            if (tagStr.length() + nextTag.length() > maxH6Len) {
                                browser.printHeading6(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += nextTag;
                        } else if (isWord(nextTag) || isNumber(nextTag)) { // handle word/number
                            if (tagStr.length() + nextTag.length() + 1 > maxH6Len) {
                                browser.printHeading6(tagStr);
                                tagStr = "";
                                browser.printBreak();
                            }
                            tagStr += " " + nextTag;
                        }
                        tagCount++;
                        nextTag = tokens[tagCount];
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printHeading6(tagStr);
                    break;

                case "<hr>":            // hr tags tested
                    tagFound = true;
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printHorizontalRule();
                    break;

                case "<br>":            // br tags tested
                    tagFound = true;
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printBreak();
                    break;

                case "<pre>" :         // preformatted tags tested
                    tagFound = true;
                    tagCount++;
                    nextTag = tokens[tagCount];
                    String[] preTextLines = nextTag.split("\n"); // split preText into lines
                    // print each line as is - with spaces
                    for (int i = 0; i < preTextLines.length; i++) {
                        browser.print(preTextLines[i]);
                        browser.printBreak();
                    }
                    tagCount++;
                    nextTag = tokens[tagCount];
                    browser.printBreak();
                    break;

                default:           // default case if none satisfied
                    // outside any other tags i.e. within <html><body>  </body></html>
                    if (isPunctuation(currTag)) { // handle punctuation
                        tagStr += currTag;
                    } else if (isWord(currTag) || isNumber(currTag)) { // handle word
                        tagStr += " " + currTag;
                    }
                    if (tagStr.length() >= 0) {
                        browser.print(tagStr);
                    }
            }

            if (! tagFound) {
                tagCount++;
            }
            // check for end of tokens
            if(tagCount >= tokens.length){
                tagsComplete = true;
            }
        }

    }

    /**
     *
     * @param token tag p at that spot
     * @return true if tag found, false otherwise
     */
    public boolean isP(String token) {
        if (token.equals("<P>") || token.equals("<p>")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param token tag b at that spot
     * @return true if tag found, false otherwise
     */
    public boolean isB(String token) {
        if (token.equals("<B>") || token.equals("<b>")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param token tag i at that spot
     * @return true if tag found, false otherwise
     */
    public boolean isI(String token) {
        if (token.equals("<I>") || token.equals("<i>")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param token tag br at that spot
     * @return true if tag found, false otherwise
     */
    public boolean isBR(String token) {
        if (token.equals("<BR>") || token.equals("<br>")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param token tag hr at that spot
     * @return true if tag found, false otherwise
     */
    public boolean isHR(String token) {
        if (token.equals("<HR>") || token.equals("<hr>")) {
            return true;
        } else {
            return false;
        }
    }

    /**
     *
     * @param token tag (any punctuation) at that spot
     * @return true if tag found, false otherwise
     */
    public boolean isPunctuation(String token) {
        // handle characters in [. , ; : ( ) ? ! = & ~ + -]
        if (token.length() > 1 || token.length() == 0) {
            // if length == 0 or length >= 2, return false
            return false;
        } else {
            char c = token.charAt(0);
            String puncChars = ".,;:()?!=&~+-";
            if (puncChars.indexOf(c) >= 0) {
                return true;
            } else {
                return false;
            }
        }
    }

    /**
     *
     * @param token tag (any contiguous letters) at that spot
     * @return true if tag found, false otherwise
     */
    public boolean isWord(String token) {
        // first character must be 'A'-'Z' or 'a'-'z'
        if (token.length() >= 1) {
            char c = token.charAt(0);
            if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     *
     * @param token tag (any number positive or negative) at that spot
     * @return true if tag found, false otherwise
     */
    public boolean isNumber(String token) {
        // first character must be '0'-'9' or '+'/'-'
        if (token.length() >= 1) {
            char c = token.charAt(0);
            if ((c >= '0' && c <= '9') || c == '+' || c == '-') {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    /**
     *
     * @param token
     * @return
     */
    public boolean isPreText(String token) {
        // first character must be '0'-'9' or '+'/'-'
        if (token.length() >= 10 && token.toLowerCase().substring(0,5).equals("<pre>")) {
            return true;
        } else {
            return false;
        }
    }

}
