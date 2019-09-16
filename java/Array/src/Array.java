import java.io.FileReader;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

public class Array
{
    private static int[] A = new int[1000];
    public static void main(String[] args) {
        // write your code here
        String fileName = "src/numbers.txt";
        String newLine = null;
        int count = 0;
        int sum = 0;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while ((newLine = bufferedReader.readLine()) != null) {
                int convertInt = Integer.parseInt(newLine);
                A[count] = convertInt;
                count++;
            }
            System.out.println(Arrays.toString(A));
            bufferedReader.close();

        } catch (FileNotFoundException e) {
            System.out.println("Cannot open file '" + fileName + "'");

        }
        catch (IOException io) {
            System.out.println("Error reading file '" + fileName + "'");
        }
        for(int i = 0; i < count; i++){
            sum += A[i];
            System.out.println(sum);

        }
        double avg = (double) sum / count;
        System.out.println("avg = " + avg);
        double newNum = 0;
        for(int i = 0; i < count; i++){
            newNum +=  ((A[i] - avg)*(A[i] - avg));

        }
        newNum /= (count - 1);
        newNum = Math.sqrt(newNum);
        System.out.println("Standard Deviation = " + newNum);
        int[] Counts = new int[101];
        for(int i = 0;i < count;i++){
            Counts[A[i]]++;
        }
        System.out.println(Arrays.toString(Counts));
        int mode = Counts[0];
        for(int i = 0;i < Counts.length;i++){
            if(Counts[i] > mode){
                mode = i;
            }
        }
        System.out.println(mode);


    }
}
