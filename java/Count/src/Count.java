import java.io.*;
import java.util.*;
public class Count {

    public static void main(String[] args) throws FileNotFoundException  {
	// write your code here
        File file = new File("text.txt");
        try(Scanner sc = new Scanner(new FileInputStream(file))){
            ArrayList<String> allWords = new ArrayList<String>();
            while(sc.hasNext()){
                String word = sc.next();
                // word = word.replace(".", "");
                word = word.replaceAll("([|?:'\\\".,-;])", "").toLowerCase();
                //System.out.print(word + " ") ;
                allWords.add(word);
            }
            System.out.println();
            System.out.println("Number of words: " + allWords.size());
            // System.out.println(allWords);
            countUniqueWords(allWords);
        }

    }
    public static int countUniqueWords(ArrayList<String> allWords){
        ArrayList<String> uniqueWords = new ArrayList<String>();
        int[] counts = new int[allWords.size()];
        for(int i = 0;i < allWords.size();i++){
            String allWord = allWords.get(i);
            boolean foundMatch = false;
            for(int j = 0;j < uniqueWords.size();j++){
                String uniqueWord = uniqueWords.get(j);
                if(allWord.equals(uniqueWord)){
                    counts[j]++;
                    foundMatch = true;
                }
            }
            if(!foundMatch){
                uniqueWords.add(allWord);
                counts[uniqueWords.size()]++;
            }
        }
        System.out.println("\n\nUnique: " + uniqueWords.size());
        for(int i = 0;i < uniqueWords.size();i++){
            System.out.println(uniqueWords.get(i) + ": " + counts[i] );
        }
        return uniqueWords.size();
    }





}
