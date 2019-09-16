public class Grocery {
    private double i1;
    private double i2;
    private double i3;
    private double i4;
    private double i5;

    public static void main(String[] args) {
	// write your code here
        Grocery g = new Grocery(3.0, 4.0, 5.0, 6.0, 7.0);
        g.calculate();
    }
    public Grocery(double it1, double it2, double it3, double it4, double it5){
        i1 = it1;
        i2 = it2;
        i3 = it3;
        i4 = it4;
        i5 = it5;
    }
    public void calculate() {
        double total = i1 + i2 + i3 + i4 + i5;
        System.out.println("The total is " + total);
    }
}
