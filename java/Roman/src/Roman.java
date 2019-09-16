public class Roman {
    private final int roman;

    public static void main(String[] args) {
        Roman r = new Roman(499);
        String romanStr = r.calculate();
        System.out.println(romanStr);
    }
    public Roman(int numeral ) {
       roman = numeral;

    }
    public String calculate()
    {
        String str = "";
        int I = 1;
        int V = 5;
        int X = 10;
        int L = 50;
        int C = 100;
        int D = 500;
        int M = 1000;
        int num = roman;
        while(num != 0 ){
            if(num >= M && num < 4*M){
                int numx = num/M;
                for(int i = 0;i < numx;i++){
                    str += "M";
                }
                num %= M;
            }
            else if(num >= M - C && num < M){
                str += "CM";
                num -= M - C;
            }
            else if(num >= D && num < M - C){
                str += "D";
                int numx = (num - D)/C;
                for(int i = 0;i < numx;i++){
                    System.out.print("C");
                }
                num %= C;
            }
            else if(num >= D - C && num < D){
                str += "CD";
                num -= D - C;
            }
            else if(num >= C && num < D - C){
                int numx = num/C;
                for(int i = 0;i < numx;i++){
                    str += "C";
                }
                num %= C;
            }
            else if(num >= C - X && num < C){
                str += "XC";
                num -= C - X;

            }
            else if(num >= L && num < C - X){
                str += "L";
                num -= L;
            }
            else if(num >= L - X && num < L){
                str += "XL";
                num -= L - X;
            }
            else if(num >= X && num < L - X){
                int numx = num/X;
                for(int i = 0;i < numx;i++){
                    str += "X";
                }
                num %= X;
            }
            else if(num == X - I){
                str += "IX";
                num = 0;
            }
            else if(num >= V && num <= V + 3*I){
                str += "V";
                num -= V;
            }
            else if(num == V - I){
                str += "IV";
                num = 0;
            }
            else if(num >= I && num <= 3*I){
                int numx = num/I;
                for(int i = 0;i < numx;i++){
                    str += "I";
                }
                num = 0;
            }

        }
        return str;

    }
}
