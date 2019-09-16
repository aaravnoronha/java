public class CollegeStudent extends Student {
    private String myMajor;
    private int myYear;
    public CollegeStudent(String name, int age, String gender,
                   String idNum, double gpa, int year, String major ){
        // use the super class' constructor
        super(name, age, gender, idNum, gpa);

        // initialize what's new to Student
        myYear = year;
        myMajor = major;
    }
    public int getYear(){
        return myYear;
    }

    public String getMajor(){
        return myMajor;
    }

    public void setYear(int year){
        myYear = year;
    }

    public void setMajor(String major){
        myMajor = major;
    }
    // overrides the toString method in the parent class
    public String toString(){
        return super.toString() + ", year: " + myYear + ", major: " + myMajor;
    }

}
