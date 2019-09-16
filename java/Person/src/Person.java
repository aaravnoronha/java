public class Person
{
    private String myName ;  // name of the person
    private int myAge;       // person's age
    private String myGender; // "M" for male, "F" for female

    public static void main(String[] args) {
	// write your code here
        Person bob = new Person("bob", 30, "M");
        System.out.println(bob.toString());
        Student joe = new Student("joe", 15, "M", "123", 4.0);
        System.out.println(joe.toString());
        Teacher dwight = new Teacher("dwight", 60, "M", "karate", 1.0);
        System.out.println(dwight.toString());
        CollegeStudent jim = new CollegeStudent("jim", 20, "M", "321", 0.0,
                2019, "CS");
        System.out.println(jim.toString());
    }

    // constructor
    public Person(String name, int age, String gender){
        myName = name;
        myAge = age;
        myGender = gender;
    }

    public String getName(){
        return myName;
    }


    public int getAge(){
        return myAge;
    }

    public String getGender(){
        return myGender;
    }

    public void setName(String name){
        myName = name;
    }

    public void setAge(int age){
        myAge = age;
    }

    public void setGender(String gender){
        myGender = gender;
    }

    public String toString(){
        return myName + ", age: " + myAge + ", gender: " +
                myGender;
    }

}
