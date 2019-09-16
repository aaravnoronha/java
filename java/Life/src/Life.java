import java.io.*;
import java.util.*;

public class Life {

    public static void main(String[] args) throws IOException {
	    // write your code here
        boolean[][] T = readIn();
        boolean[][] NG = computeNextGen(T);
        print2dArr(NG);
        System.out.println();
        System.out.println();
        boolean[][] NG1 = computeNextGen(T);
        print2dArr(NG1);
        System.out.println();
        System.out.println();
        boolean[][] NG2 = computeNextGen(T);
        print2dArr(NG2);
        System.out.println();
        System.out.println();
        boolean[][] NG3 = computeNextGen(T);
        print2dArr(NG3);
        System.out.println();
        System.out.println();
        boolean[][] NG4 = computeNextGen(T);
        print2dArr(NG4);


    }

    public static boolean[][] readIn() throws IOException {
        File file = new File("data/life100.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;
        int N = 20;
        boolean[][] Table = new boolean[N][N];
        int count = 0;
        while ((str = br.readLine()) != null) {
            if(count >= 2){
                String[] split = str.split("");
                for(int i = 4;i < split.length;i++) {
                    //System.out.printf("split[%d] = %s\n", i, split[i]);
                    if(split[i].equals("*")){
                        Table[count - 2][i - 4] = true;
                    }
                    else{
                        Table[count - 2][i - 4] = false;
                    }
                }

            }
            count++;

        }
        return Table;
    }

    public static boolean[][] computeNextGen(boolean[][] G) {
        boolean[][] nextGen = new boolean[G.length][G[0].length];
        for(int i = 0;i < G.length - 1;i++) {
            for(int j = 0;j < G[0].length - 1;j++) {
                // find number of alive cells
                int countAlive = 0;
                for(int k = Math.max(0,i-1);k <= i + 1;k++) {
                    for(int l = j - 1;l <= Math.min(l-1,j+1);l++){
                        if(!(k == i && l == j)) {
                            if(G[k][l]){
                                countAlive++;
                            }

                        }
                    }
                }
                if(countAlive == 0 || countAlive == 1 || countAlive >= 4){
                    countAlive --;
                }
                else if(countAlive == 2 || countAlive == 3){
                    //do nothing
                }
                nextGen[i][j] = true;
            }

        }
        return nextGen;
    }
    public static void print2dArr(boolean[][] arr){
        for(int i = 0;i < arr.length;i++){
            System.out.println();
            for(int j = 0;j < arr.length;j++) {
                if (arr[i][j]) {
                    System.out.print("1 ");
                } else {
                    System.out.print("0 ");
                }

            }
        }
    }







}












