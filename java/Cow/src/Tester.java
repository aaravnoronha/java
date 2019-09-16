public class Tester
{
    public static void main(String[] args)
    {
        Cow c = new Cow();
        System.out.println( c.getType() + " goes " + c.getSound() );
        Chick ch = new Chick();
        System.out.println( ch.getType() + " goes " + ch.getSound() );
        Pig p = new Pig();
        System.out.println( p.getType() + " goes " + p.getSound() );

    }
}

