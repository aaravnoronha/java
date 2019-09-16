import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Average {

    public static void main(String[] args) {
	// write your code here
        String fileName = "data/numbers.txt";
        String newLine = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            int count = 0;
            int max = 0;
            int min = 0;
            int sum = 0;
            while((newLine = bufferedReader.readLine()) != null) {
                System.out.println(newLine);
                int convertInt = Integer.parseInt(newLine);
                if(count == 0){
                    max = convertInt;
                    min = convertInt;
                    sum = convertInt;
                }

                if(convertInt > max){
                    max = convertInt;
                }
                if(convertInt < min){
                    min = convertInt;
                }


                count++;
                sum+=convertInt;

            }
            double avg = (double)sum/count;
            bufferedReader.close();
            System.out.println("max = " + max);
            System.out.println("min = " + min);
            System.out.println("sum = " + sum);
            System.out.println("count = " + count);
            System.out.println("avg = " + avg);
        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file '" + fileName + "'");

        }
        catch (IOException io) {
            System.out.println("Error reading file '" + fileName + "'");
        }

    }

}
