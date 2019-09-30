// imports go here
import java.io.PrintWriter;
import java.io.File;
import java.util.Scanner;
import java.io.FileNotFoundException;

/**
 *	MVCipher - Add your description here
 *	Requires Prompt and FileUtils classes.
 *
 *	@author Aarav Noronha
 *	@since 9/23/19
 */
public class MVCipher {

    // fields go here

    /** Constructor */
    public MVCipher() { }

    public static void main(String[] args) {
        MVCipher mvc = new MVCipher();
        mvc.run();
    }

    /**
     *	Method header goes here
     */
    public void run() {
        System.out.println("\n Welcome to the MV Cipher machine!\n");

		/* Prompt for a key and change to uppercase
		   Do not let the key contain anything but alpha
		   Use the Prompt class to get user input */
        String key = Prompt.getString("Please input a word to use as key (letters only)");

        /* Prompt for encrypt or decrypt */
        int encOrDec = Prompt.getInt("Encrypt or decrypt? (1 - 2) ");

        /* Prompt for an input file name */
        String encOrDecFile = "";
        if (encOrDec == 1) {
            encOrDecFile = Prompt.getString("Name of file to encrypt ");
        } else {
            encOrDecFile = Prompt.getString("Name of file to decrypt ");
        }
        Scanner fileToRead = FileUtils.openToRead(encOrDecFile);

        /* Prompt for an output file name */
        String outputFile = Prompt.getString("Name of output file ");
        PrintWriter fileToWrite = FileUtils.openToWrite(outputFile);
        System.out.println("The encrypted file " + outputFile + " has been created using the keyword -> " + key);

        /* Read input file, encrypt or decrypt */
        String message = "";
        while (fileToRead.hasNextLine()) {
            message += fileToRead.nextLine() + "\n";
        }
        fileToRead.close();

        // encrypt/decrypt message
        String encOrDecMsg = "";
        if (encOrDec == 1) {
            encOrDecMsg = encryptStr(message, key);
        } else {
            encOrDecMsg = decryptStr(message, key);
        }
        // System.out.println("encOrDecMsg = " + encOrDecMsg );

        // write string to output file
        fileToWrite.print(encOrDecMsg);

        /* Don't forget to close your output file */
        fileToWrite.close();

    }

    /**
     *
     * @param valueIn -> character passed in corresponding to ascii value
     * @param valueKey -> int denoting ascii value of encrypted character
     * @return -> output value casted as a character
     * method encrypts one character and tests for different ascii values
     * depending on that, it encrypts accordingly
     */
    public char encryptChar(char valueIn, int valueKey){
        int subAscii = 'A' - 1;
        final int SIZE_ALPH = 26;
        valueKey -= subAscii;
        int valueOut = 0;
        if (valueIn >= 'A' && valueIn <= 'Z') {
            if(valueIn + valueKey > 'Z'){
                valueOut = valueIn + valueKey - SIZE_ALPH;
            }
            else {
                valueOut = valueIn + valueKey;
            }
        }
        else if (valueIn >= 'a' && valueIn <= 'z') {
            if (valueIn + valueKey > 'z'){
                valueOut = valueIn + valueKey - SIZE_ALPH;
            }
            else {
                valueOut = valueIn + valueKey;
            }
        }
        else {
            valueOut = valueIn;
        }
        return (char)valueOut;
    }

    /**
     *
     * @param messageEnc -> Plaintext string
     * @param keywordEnc -> String inputted by user
     * @return -> encrypted message as a string
     * method puts encrypted characters together and
     * makes it into a string - uses 2 string arrays
     */
    public String encryptStr(String messageEnc, String keywordEnc) {
        String[] messageChars = messageEnc.split("");
        String[] keywordChars = keywordEnc.split("");
        String encryptedMsg = "";
        for(int i = 0; i < messageChars.length; i++) {
            int keywordCount = i % keywordEnc.length();
            char letterMessage = messageChars[i].charAt(0);
            char letterKeyword = keywordChars[i % keywordEnc.length()].charAt(0);
            char encryptedChar = encryptChar(letterMessage,(int)letterKeyword);
            encryptedMsg += encryptedChar;

        }
        return encryptedMsg;

    }
    /**
     *
     * @param msgChar -> character passed in corresponding to ascii value
     * @param keyVal -> int denoting ascii value of encrypted character
     * @return -> output value casted as a character
     * method decrypts one character and tests for different ascii values
     * depending on that, it decrypts accordingly
     */
    public char decryptChar(char msgChar, int keyVal){
        int subAscii = 'A' - 1;
        final int SIZE_ALPH = 26;
        keyVal -= subAscii;
        int valueOut = 0;
        if (msgChar >= 'A' && msgChar <= 'Z') {
            if(msgChar - keyVal < 'A'){
                valueOut = msgChar - keyVal + SIZE_ALPH;
            }
            else {
                valueOut = msgChar - keyVal;
            }
        }
        else if(msgChar >= 'a' && msgChar <= 'z') {
            if(msgChar - keyVal < 'a'){
                valueOut = msgChar - keyVal + SIZE_ALPH;
            }
            else {
                valueOut = msgChar - keyVal;
            }
        }
        else {
            valueOut = msgChar;
        }
        return (char)valueOut;
    }

    /**
     *
     * @param messageDec -> String equivalent to output of certain encryption
     * @param keywordDec -> String inputted by user
     * @return -> decrypted message as a string
     * method puts decrypted characters together and
     * makes it into a string - uses 2 string arrays
     */
    public String decryptStr(String messageDec, String keywordDec) {
        String[] messageChars = messageDec.split("");
        String[] keywordChars = keywordDec.split("");
        String decryptedMsg = "";
        for(int i = 0; i < messageChars.length; i++) {
            int keywordCount = i % keywordDec.length();
            char letterMessage = messageChars[i].charAt(0);
            char letterKeyword = keywordChars[i % keywordDec.length()].charAt(0);
            char decryptedChar = decryptChar(letterMessage,(int)letterKeyword);
            decryptedMsg += decryptedChar;

        }
        return decryptedMsg;

    }
}