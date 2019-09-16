/**
 * This program will ask the user for an input of the amount of trials
 * to get an average number of spins it will take to get the same number in a row.
 * The number of sides ranges from 4 to 20 and will dislpay the number as well as asterisks
 * to give an idea of how it compares to other numbers of sides
 */

public class Casino{
    private static int[] avgSpins = new int[17];
    private static int numTrials;
    private final static int MAX_NUMSIDES = 20;
    private static int[] avgs = new int[MAX_NUMSIDES + 1];

    public static void main(String[] args){
        getInput();
        for (int numSides = 4; numSides <= MAX_NUMSIDES; numSides++) {
            Dice d = new Dice(numSides);
            runTrials(numSides, d);
        }
        printResults();
    }

    public Casino() {

    }

    public static void getInput(){
        System.out.println("\n\n");
        numTrials = Prompt.getInt("The number of trials (10 - 100000)");
        if(numTrials < 10 || numTrials > 100000){
            System.out.println("Enter a number between 10 and 100000 inclusive");
        }
    }

    public static int[] spin(int numSides, Dice d) {
        int[] dice = new int[4];
        for (int i = 0; i < dice.length; i++) {
            dice[i] = d.roll();
        }
        return dice;
    }

    public static boolean isJackpot(int[] d) {
        boolean jackpot = true;
        for (int i = 0; i < d.length - 1; i++) {
            if (d[i] != d[i + 1]) {
                jackpot = false;
            }
        }
        return jackpot;
    }

    public static int runTrial(int numSides, Dice d){
        boolean jackpot = false;
        int count = 0;
        while(!jackpot) {
            int[] dice = spin(numSides, d);
            jackpot = isJackpot(dice);
            count++;
        }
        // System.out.printf("count = %d\n", count);
        return count;
    }

    public static void runTrials(int numSides, Dice d){
        int total = 0;
        int count = 0;
        for(int i = 0;i < numTrials; i++) {
            count = runTrial(numSides, d);
            total += count;
        }
        int avg = total/numTrials;
        avgs[numSides] = avg;
    }
    public static void printResults() {
        int maxAvg = getMax(avgs);
        System.out.printf("Number\n  of   Ave number\nsides   of spins\n");;
        for (int nSides = 4; nSides <= MAX_NUMSIDES; nSides++) {
            int currAvg = avgs[nSides];
            String currStr = getAsteriskString(maxAvg, currAvg);
            System.out.printf("  %2d: \t%5d  %s\n", nSides, avgs[nSides], currStr);
        }
    }
    public static int getMax(int[] A){
        int max = A[0];
        for(int i = 1;i < A.length; i++){
            if(A[i] > max){
                max = A[i];
            }
        }
        return max;
    }
    public static String getAsteriskString(int maxAvg, int currAvg){
        int MAX_STARS = 60;
        int currStars = (int)(((double)currAvg/maxAvg) * MAX_STARS);
        String currStr = "";
        for(int i = 0; i < currStars; i++){
            currStr += " *";
        }
        return currStr;
    }


}
