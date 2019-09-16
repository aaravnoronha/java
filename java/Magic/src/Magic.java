import java. util. Arrays;

// Magic Square

// LCM, GCD
// Note: LCM(m, n) * GCD(m, n) = m * n

public class Magic {
    private long n = 1;
    private long[] sqrs;
    public static void main(String[] args) {
        // write your code here
        Magic m = new Magic(50000);
        long[] sum = m.sums(5000);
        int num1 = 34;
        int num2 = 51;
        int lecm = m.getLCM(num1, num2);
        System.out.println(lecm);
        int grcd = m.getGCD(num1, num2);
        System.out.println(grcd);
        System.out.println((Arrays.toString(sum)));

        // Test isSquare
        /*
        System.out.printf("isSquare(47) = %s\n", isSquare(47));
        System.out.printf("isSquare(49) = %s\n", isSquare(49));
        System.out.printf("isSquare(63) = %s\n", isSquare(63));
        System.out.printf("isSquare(64) = %s\n", isSquare(64));
        System.out.printf("isSquare(961) = %s\n", isSquare(961));
        */

    }

    public Magic(long num ) {
        n = num;
    }

    public static boolean isSquare(long num){
        long i = 0;
        while (i*i < num) {
            i++;
        }
        return (i*i == num);
    }


    public long[] sums(int n) {
        long[] S = new long[n];
        System.out.println("n" + n);
        for(int i = 1;i < n;i++) {
            S[i] = S[i-1] + i;
            if(isSquare(S[i])){
                System.out.println("The number " + S[i] + " is a magic square.");
            }
        }
        return S;

    }
    public static int[] getFactors(int num){
        int[] factors = new int[100];
        int factor = 2;
        int count = 0;
        while(num != 1){
            if(num % factor == 0){
                num /= factor;
                factors[count] = factor;
                count++;
                // System.out.println(factor);
            }
            else{
                factor++;
            }
        }
        return factors;
    }

    // input is 2 ints, outputs return of int, gets prime factors using getFactors
    public int getLCM(int num1, int num2) {
        int i = 0, j = 0, lcm = 1;
        int[] factors1 = getFactors(num1);
        int[] factors2 = getFactors(num2);
        while (factors1[i] != 0 || factors2[j] != 0) {
            if (factors1[i] == 0){
                lcm *= factors2[j];
                j++;
            }
            else if (factors2[j] == 0){
                lcm *= factors1[i];
                i++;
            }
            else{
                if (factors1[i] == factors2[j]) {
                    lcm *= factors1[i];
                    i++;
                    j++;

                } else {
                    if (factors1[i] < factors2[j]) {
                        lcm *= factors1[i];
                        i++;
                    } else if (factors2[j] < factors1[i]) {
                        lcm *= factors2[j];
                        j++;
                    }
                }
            }
       }
       return lcm;
    }

    public int getGCD(int num1, int num2) {
        int i = 0, j = 0, gcd = 1;
        int[] factors1 = getFactors(num1);
        int[] factors2 = getFactors(num2);
        while (factors1[i] != 0 && factors2[j] != 0) {
            if (factors1[i] == 0){
                j++;
            } else if (factors2[j] == 0){
                i++;
            } else {
                if (factors1[i] == factors2[j]) {
                    gcd *= factors1[i];
                    i++;
                    j++;

                } else {
                    if (factors1[i] < factors2[j]) {
                        i++;
                    } else if (factors2[j] < factors1[i]) {
                        j++;
                    }
                }
            }
        }
        return gcd;
    }
}
