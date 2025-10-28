package data_structure.set;

import java.util.HashSet;
import java.util.Set;

public class Student {

    private int id;
    private String name;
    private Set<Course> setCourses;

    public Student() {
        this.setCourses = new HashSet<>();
    }

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
        this.setCourses = new HashSet<>();
    }

    public Student(int id, String name, Set<Course> setCourses) {
        this.id = id;
        this.name = name;
        this.setCourses = setCourses;
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public Set<Course> getSetCourses() {
        return setCourses;
    }

    public void addCourse(Course course) {
        if (!setCourses.add(course)) {
            throw new DuplicateCourseException("The course: " + course + "is already exist in your course list.");
        }
        
    }

    public void removeCourse(Course course) {
        setCourses.remove(course);
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Student)) {
            return false;
        }

        Student s = (Student) obj;

        return id == s.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    public void showInformation() {
        System.out.println("Student: " + "\n" +
        "Id: " + id + "\n" +
        "Name: " + name + "\n" +
        "Courses: ");
        getAllStudentCourse();
    }

    private void getAllStudentCourse(){
        for (Course course : setCourses) {
            System.out.println(course);
        }
    }
}