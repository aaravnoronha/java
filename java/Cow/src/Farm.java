public class Farm
{
    private Animal [] myFarm;

    public Farm() {
        myFarm = new Animal[3];
        myFarm[0] = new Cow();
        myFarm[1] = new Chick();
        myFarm[2] = new Pig();
    }

    public void animalSounds() {
        Animal temp;
        for (int i = 0; i < myFarm.length; i++)
        {
            temp = myFarm[i];
            System.out.println(temp.getType() + " goes " + temp.getSound());
        }
    }
}