import java.io.PrintWriter;
import java.io.File;
import java.io.FileNotFoundException;

/*File Utilities for Reading and Writing
Author Aarav Noronha
since August 21, 2019
*/
public class FileUtils {
    /*Opens a file to read using the Scanner class
         * @param fileName      name of the file to open
         * @return              the scannner object to the file
         */
    public static java.util.Scanner openToRead(String fileName) {
        java.util.Scanner input = null;
        try{
            input = new java.util.Scanner(new java.io.File(fileName));
        }
        catch(java.io.FileNotFoundException e){
            System.err.println("ERROR: CANNOT OPEN " + fileName + "FOR READING.");
            System.exit(1);
        }
        return input;
    }
    /** Opens a file to write using the PrintWriter class
        * @param fileName        name of the file to open
        * @return                the PrintWriter object to the file
        */
    public static PrintWriter openToWrite(String fileName){
        PrintWriter output = null;
        try{
            output = new PrintWriter(new File(fileName));
        }
        catch(java.io.FileNotFoundException e){
            System.err.println("ERROR: CANNOT OPEN " + fileName + "FOR WRITING.");
            System.exit(2);
        }
        return output;
    }
}




