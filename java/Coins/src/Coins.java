public class Coins
{
    private final int QUARTER = 25;
    private final int DIME = 10;
    private final int NICKEL = 5;
    private final int PENNY = 1;
    private int t;
    public static void main(String[] args)
    {
        Coins c = new Coins(1);
        c.calculate();
    }

    public Coins(int total)
    {
        t = total;
    }

    public void calculate()
    {
        int numQuarters = t / 25;
        System.out.println("Quarters =  " + numQuarters);
        int rem1 = t % 25;
        int numDimes = rem1 / 10;
        System.out.println("Dimes = " + numDimes);
        int rem2 = rem1 % 10;
        int numNickels = rem2 / 5;
        System.out.println("Nickels = " + numNickels);
        int rem3 = rem2 % 5;
        int numPennies = rem3;
        System.out.println("Pennies = " + numPennies);


    }
}
