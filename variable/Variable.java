package variable;

public class Variable {
    int studentId = 123;
    String studentLastName = "Minh";
    String studentFirstName = "Do";
    int studentAge = 22;
    double studentGPA = 3.1;
    char studentGrade = 'C';
    final int MINUTES_PER_HOUR = 60;

    public static void main(String[] args) {
        Variable v = new Variable();
        System.out.println("Student");
        System.out.println("ID: " + v.studentId);
        System.out.println("Name: " + v.studentFirstName + " " + v.studentLastName);
        System.out.println("Age: " + v.studentAge);
        System.out.println("GPA: " + v.studentGPA);
        System.out.println("Grade: " + v.studentGrade);
        System.out.println("There are " + v.MINUTES_PER_HOUR + " munites in one hour.");
    }
}
