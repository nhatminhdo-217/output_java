package data_structure;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Student {
    private String id;
    private String fullName;
    private boolean gender;
    private Date dob;
    private double gpa;

    public Student() {};

    public Student(String id, String fullName, boolean gender, Date dob, double gpa) {
        this.id = id;
        this.fullName = fullName;
        this.gender = gender;
        this.dob = dob;
        this.gpa = gpa;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }
    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean getGender(){
        return gender;
    }
    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public double getGpa() {
        return gpa;
    }
    public void setGpa(double gpa) {
        this.gpa = gpa;
    }

    @Override
    public String toString() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        return "Student: " + getId() + "\n"
        + "Name: " + getFullName() + "\n"
        + "Gender: " + (getGender() ? "Male" : "Female") + "\n"
        + "DOB: " + sdf.format(dob) + "\n"
        + "GPA: " + getGpa();
    }
}
