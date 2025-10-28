package data_structure.set;

import java.util.Set;

public class CourseManagement {
    private Set<Course> setCourses;

    public CourseManagement() {
        this.setCourses = Course.getDummyCourses();
    }

    public Course getCourseById(int id){
        for (Course course : setCourses) {
            if (course.getId() == id) {
                return course;
            }
        }

        return null;
    }

    public void getAllCourse(){
        for (Course course : setCourses) {
            System.out.println(course);
        }
    }
}
