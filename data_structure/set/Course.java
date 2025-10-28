package data_structure.set;

import java.util.HashSet;
import java.util.Set;

public class Course {
    private int id;
    private String courseName;

    public Course() {
    }

    public Course(int id, String courseName) {
        this.id = id;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Course)) {
            return false;
        }

        Course c = (Course) obj;

        return id == c.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "[ id: " + id + ", name: " + courseName + " ]";
    }

    public static Set<Course> getDummyCourses() {
        Set<Course> courses = new HashSet<>();

        courses.add(new Course(1, "Java Programming"));
        courses.add(new Course(2, "Data Structures"));
        courses.add(new Course(3, "Database Systems"));
        courses.add(new Course(4, "Web Development"));
        courses.add(new Course(5, "Operating Systems"));

        return courses;
    }
}