package data_structure;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentList studentList = new StudentList();
        studentList.addDummyData();
        boolean running = true;

        while (running) {
            System.out.println("\n========== STUDENT MANAGEMENT ==========");
            System.out.println("1. Add new student");
            System.out.println("2. Show all students");
            System.out.println("3. Find student by ID");
            System.out.println("4. Update student by ID");
            System.out.println("5. Delete student by ID");
            System.out.println("6. Sort Student by GPA");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Enter ID (format: AB1234): ");
                        String id = sc.nextLine().toUpperCase();

                        System.out.print("Enter full name: ");
                        String fullName = sc.nextLine();

                        System.out.print("Enter gender (Male/Female): ");
                        String genderStr = sc.nextLine();
                        boolean gender;
                        if (genderStr.equalsIgnoreCase("male")) {
                            gender = true;
                        } else if (genderStr.equalsIgnoreCase("female")) {
                            gender = false;
                        } else {
                            throw new NonExistGenderException("Gender " + genderStr + " is invalid.");
                        }

                        System.out.print("Enter DOB (dd-MM-yyyy): ");
                        String dobStr = sc.nextLine();
                        Date dob;
                        try {
                            dob = new SimpleDateFormat("dd-MM-yyyy").parse(dobStr);
                        } catch (ParseException e) {
                            System.out.println("Invalid date format. Using current date instead.");
                            dob = new Date();
                        }

                        System.out.print("Enter GPA: ");
                        double gpa = sc.nextDouble();

                        if (gpa < 0 || gpa > 4.0) {
                            throw new WrongGpaFormatException("GPA must be between 0.0 and 4.0");
                        }

                        Student student = new Student(id, fullName, gender, dob, gpa);
                        studentList.addStudent(student);
                        System.out.println("Student added successfully!");
                        break;

                    case 2:
                        System.out.println("\nAll students:");
                        studentList.getAllStudent();
                        break;

                    case 3:
                        System.out.print("Enter student ID: ");
                        String searchId = sc.nextLine();
                        studentList.getStudentById(searchId);
                        break;

                    case 4:
                        System.out.print("Enter student ID to update: ");
                        String updateId = sc.nextLine();
                        studentList.updateStudentById(updateId);
                        break;

                    case 5:
                        System.out.print("Enter student ID to delete: ");
                        String deleteId = sc.nextLine();
                        boolean deleted = studentList.deleteStudentById(deleteId);
                        if (deleted) {
                            System.out.println("Student deleted successfully!");
                        }
                        break;
                    case 6:
                        studentList.sortStudentByGpa();
                        studentList.getAllStudent();
                        break;

                    case 7:
                        running = false;
                        System.out.println("Exiting program...");
                        break;

                    default:
                        System.out.println("Invalid choice! Try again.");
                }

            } catch (StudentException e) {
                System.err.println("️Error: " + e.getMessage());
            }
        }

        sc.close();
    }
}
