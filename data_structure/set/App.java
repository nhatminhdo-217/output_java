package data_structure.set;

import java.util.Scanner;

public class App {

    Scanner sc = new Scanner(System.in);
    Student student = new Student(1, "Do Nhat Minh");

    public void menu() {
        while (true) {
            try {
                System.out.println("========== Course ==========");
                System.out.println("1. Show Infomation");
                System.out.println("2. Add Course");
                System.out.println("3. Exit");

                System.out.print("Choice(1-3): ");
                String strChoose = sc.nextLine();
                int choose = Integer.parseInt(strChoose);
                program(choose);

            } catch (NumberFormatException e) {
                System.err.println("Must enter number 1 - 3. Try again!");
            } catch (DuplicateCourseException dce) {
                System.err.println(dce.getMessage());
            } catch (NonExistCourseException nec) {
                System.err.println(nec.getMessage());
            }

        }
    }

    public void program(int choose) {
        switch (choose) {
            case 1:
                student.showInformation();
                break;

            case 2:
                System.out.println("========== All Courses ==========");
                CourseManagement courseManagement = new CourseManagement();
                courseManagement.getAllCourse();

                System.out.print("Which course you want to add(by ID): ");
                int wantedCourseId = Integer.parseInt(sc.nextLine());
                Course course = courseManagement.getCourseById(wantedCourseId);
                if (course == null) {
                    throw new NonExistCourseException("This course not exsits.");
                }
                student.addCourse(course);

                System.out.println("Add course successfully");
                break;

            case 3:
                System.out.println("Exiting.....");
                System.exit(0);
            default:
                System.out.println("Not valid choose");
                break;
        }
    }

    public static void main(String[] args) {
        App app = new App();
        app.menu();
    }
}
