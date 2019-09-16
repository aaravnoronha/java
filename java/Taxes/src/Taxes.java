public class Taxes {

    private double hrs;
    private double hrRate;

    public static void main(String[] args) {
        Taxes t = new Taxes(30.0, 12.35);
        t.calculate();
    }


    public Taxes(double hours, double hourly_rate ) {
        hrs = hours;
        hrRate = hourly_rate;
    }

    public void calculate() {
        System.out.println("Hours worked: " + hrs);
        System.out.println("Hourly rate: " + hrRate);
        System.out.print("\n\n");
        double gross_pay = hrs * hrRate;
        System.out.println("Gross pay: " + gross_pay);
        System.out.print("\n\n");
        double fdrl_tax = gross_pay * 0.154;
        System.out.println("Federal Tax (15.4%): " + fdrl_tax);
        double fica_tax = gross_pay * 0.0775;
        System.out.println("FICA Tax (7.75%): " + fica_tax);
        double state_tax = gross_pay * 0.04;
        System.out.println("State Tax (4.0%): " + state_tax);
        System.out.print("\n\n");
        double net_pay = gross_pay - fdrl_tax - fica_tax - state_tax;
        System.out.println("Net pay: " + net_pay);



    }
}