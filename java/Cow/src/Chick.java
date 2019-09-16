public class Chick implements Animal
{
    private String myType;
    private String mySound;

    public Chick()
    {
        myType = "chick";
        mySound = "cheep";
    }

    public String getSound()
    {
        return mySound;
    }

    public String getType()
    {
        return myType;
    }
}
