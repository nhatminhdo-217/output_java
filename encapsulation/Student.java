package encapsulation;

public class Student {
    private int id;
    private String fullName;
    private double gpa;
    private char grade;
    private boolean gender;

    public Student() {}

    public Student(int id, String fullName, double gpa, char grade, boolean gender) {
        setId(id);
        setFullName(fullName);
        setGpa(gpa);
        setGrade(grade);
        setGender(gender);
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("ID must be greater than 0");
        }
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        if (fullName == null || fullName.trim().isEmpty()) {
            throw new IllegalArgumentException("Full name cannot be empty");
        }
        this.fullName = fullName;
    }

    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        if (gpa < 0.0 || gpa > 4.0) {
            throw new IllegalArgumentException("GPA must be between 0.0 and 4.0");
        }
        this.gpa = gpa;
    }

    public char getGrade() {
        return grade;
    }
    public void setGrade(char grade) {
        if ("ABCDF".indexOf(grade) == -1) {
            throw new IllegalArgumentException("Grade must be A, B, C, D, or F");
        }
        this.grade = grade;
    }

    public boolean getGender() {
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student {" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                ", gpa=" + gpa +
                ", grade=" + grade +
                ", gender=" + (gender ? "Male" : "Female") +
                '}';
    }    

    public static void main(String[] args) {
        try {
            Student student = new Student(1, "Nhat Minh", 3.1, 'B', true);
            Student student1 = new Student(2, "Nhat Min", 3.1, 'B', true);
            Student student2 = new Student('a', "Nhat Mi", 3.1, 'B', true);
            Student student3 = new Student(4, "Nhat M", 6, 'B', false);
            Student student4 = new Student(5, "Nhat", 3.1, 'F', false);

            System.out.println(student);
            System.out.println(student1);
            System.out.println(student2);
            System.out.println(student3);
            System.out.println(student4);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}