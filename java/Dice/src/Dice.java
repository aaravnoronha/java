public class Dice {

    public static void main(String[] args) {
	// write your code here
    }
    private int numSides, rollCount, value;

    // Constructor - assumes there are 6 sides to the die; initializes other fields
    public Dice ( ) {
        numSides = 6;
        rollCount = value = 0;
    }

    // Constructor - parameter specifies the number of sides
    public Dice (int n) {
        numSides = n;
        rollCount = value = 0;
    }

    /**
     *  Rolls the die to generate a random number
     *
     *  @return   a random number from the roll of the die
     */
    public int roll() {
        rollCount++;
        value = (int)(Math.random() * numSides) + 1;
        return value;
    }

    /**
     *  Returns the number of rolls
     *
     *  @return    the number of rolls
     */
    public int getRollCount ( )
    {
        return rollCount;
    }

    /**
     *  Returns the value of the die roll
     *
     *  @return   value of the die roll
     */
    public int getValue ( )
    {
        return value;
    }
}
