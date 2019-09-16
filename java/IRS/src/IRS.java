public class IRS
{

    private double txin;
    private final int sorm;
    private final double SLOWER1 = 0;
    private final double SUPPER1 = 27050;
    private final double STAX1   = 0.15;
    private final double SLOWER2 = 27050;
    private final double SUPPER2 = 65550;
    private final double SADD2   = 4057.5;
    private final double STAX2   = 0.275;
    private final double SSUB2   = 27050;
    private final double SLOWER3 = 65550;
    private final double SUPPER3 = 136750;
    private final double SADD3   = 14645;
    private final double STAX3   = 0.305;
    private final double SSUB3   = 65550;
    private final double SLOWER4 = 136750;
    private final double SUPPER4 = 297350;
    private final double SADD4   = 36361;
    private final double STAX4   = 0.355;
    private final double SSUB4   = 136750;
    private final double SLOWER5 = 297350;
    private final double SADD5   = 93374;
    private final double STAX5   = 0.391;
    private final double SSUB5   = 297350;
    private final double MLOWER1 = 0;
    private final double MUPPER1 = 45200;
    private final double MTAX1   = 0.15;
    private final double MLOWER2 = 45200;
    private final double MUPPER2 = 109250;
    private final double MADD2   = 6780;
    private final double MTAX2   = 0.275;
    private final double MSUB2   = 45200;
    private final double MLOWER3 = 109250;
    private final double MUPPER3 = 166500;
    private final double MADD3   = 24393.75;
    private final double MTAX3   = 0.305;
    private final double MSUB3   = 109250;
    private final double MLOWER4 = 166500;
    private final double MUPPER4 = 297350;
    private final double MADD4   = 41855;
    private final double MTAX4   = 0.355;
    private final double MSUB4   = 166500;
    private final double MLOWER5 = 297350;
    private final double MADD5   = 88306;
    private final double MTAX5   = 0.391;
    private final double MSUB5   = 297350;

    public static void main(String[] args)
    {
	// write your code here
        IRS i = new IRS(30000,1);
        i.calculate();
    }

    public IRS(double tx_in,int s_or_m )
    {
        txin = tx_in;
        sorm =  s_or_m;
    }

    public void setTxnIn(double tx_in)
    {
        txin = tx_in;
    }

    public void calculate()
    {
        double fdt = 0.0;
        if(sorm == 1)
        {
            if(txin > SLOWER1 && txin <= SUPPER1  )
            {
                fdt = txin * STAX1;
            }
            else if(txin > SLOWER2 && txin <= SUPPER2)
            {
                fdt = SADD2 + STAX2*(txin - SSUB2);
            }
            else if(txin > SLOWER3 && txin <= SUPPER3)
            {
                fdt = SADD3 + STAX3*(txin - SSUB3);
            }
            else if(txin > SLOWER4 && txin <= SUPPER4)
            {
                fdt = SADD4 + STAX4*(txin - SSUB4);
            }
            else if(txin > SLOWER5)
            {
                fdt = SADD5 + STAX5*(txin - SSUB5);
            }
        }
        else if(sorm == 2)
        {
            if(txin > MLOWER1 && txin <= MUPPER1  )
            {
                fdt = txin * MTAX1;
            }
            else if(txin > MLOWER2 && txin <= MUPPER2)
            {
                fdt = MADD2 + MTAX2*(txin - MSUB2);
            }
            else if(txin > MLOWER3 && txin <= MUPPER3)
            {
                fdt = MADD3 + MTAX3*(txin - MSUB3);
            }
            else if(txin > MLOWER4 && txin <= MUPPER4)
            {
                fdt = MADD4 + MTAX4*(txin - MSUB4);
            }
            else if(txin > MLOWER5)
            {
                fdt = MADD5 + MTAX5 * (txin - MSUB5);
            }
        }
        System.out.printf("Your federal tax = $ %.2f\n", fdt );
        if(sorm == 1){
            System.out.println("Ur single!");
        }
        else if(sorm == 2)
        {
            System.out.println("Ur married!");
        }
    }

}
