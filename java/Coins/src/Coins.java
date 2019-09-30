public class Coins {
    private final int QUARTER = 25;
    private final int DIME = 10;
    private final int NICKEL = 5;
    private int t;
    public static void main(String[] args) {
        Coins c = new Coins(67);
        c.calculate();
    }
    public Coins(int total) {
        t = total;
    }
    public void calculate() {
        int numQuarters = t / QUARTER;
        System.out.println("Quarters =  " + numQuarters);
        int rem1 = t % QUARTER;
        int numDimes = rem1 / DIME;
        System.out.println("Dimes = " + numDimes);
        int rem2 = rem1 % DIME;
        int numNickels = rem2 / NICKEL;
        System.out.println("Nickels = " + numNickels);
        int rem3 = rem2 % NICKEL;
        int numPennies = rem3;
        System.out.println("Pennies = " + numPennies);

    }
}
