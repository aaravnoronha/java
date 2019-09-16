package com.epi_java;
import java.util.Scanner;
import java.io.*;

public class CDSales {
    double ship, tax = 0.0;
    double total;
    String lineIn=null;
    double[][] calcArray = {{1, 0.0825, 5.95}, {2, 0, 7.95}, {3, 0.065, 9.95}};

    public void computeCDSales() throws FileNotFoundException {
        File f = new File("Orders.txt");
        Scanner s = new Scanner(f);
        PrintWriter pw = new PrintWriter("Receipt.txt");
        String firstWord = s.nextLine() ;
        while(s.hasNext())
        {
            lineIn = s.nextLine();
            pw.println(lineIn);
            total +=  getMoneyAndConvert(lineIn);
            //read first word. Save it as firstWord. This is used to determine tax and shipping later.
        }
        String subtotal = lineIn;
        pw.println("subtotal, $" + total);
        calculate(firstWord);
        pw.println("shipping, $" + ship);
        pw.println("tax, $" + tax);
        pw.println("total, $" + total);
        pw.close();
    }

    public static double getMoneyAndConvert(String lineIn){ //this method is complete
        double cash = 0.0;
        cash=Double.parseDouble(lineIn.substring(lineIn.indexOf('$')+1));
        return cash;
    }

    public void calculate(String input){
        if(input.equals("California"))
        {
            tax = calcArray[0][1] * 100;
            ship = calcArray[0][2];
        }
        else if(input.equals("Oregon"))
        {
            tax =calcArray[1][1] * 100;
            ship = calcArray[1][2];
        }
        else if(input.equals("Washington"))
        {
            tax =calcArray[2][1] * 100;
            ship = calcArray[2][2];
        }
        total += tax + ship;
    }
}
