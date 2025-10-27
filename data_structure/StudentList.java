package data_structure;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class StudentList {
    private List<Student> listStudents;

    public StudentList() {
        this.listStudents = new ArrayList<>();
    }

    public boolean addStudent(Student student) {
        if (!isIdFormat(student.getId())) {
            throw new WrongIdFormatException("Student ID format is invalid: " + student.getId());
        }
        return listStudents.add(student);
    }

    public void getAllStudent() {
        for (Student student : listStudents) {
            System.out.println(student);
        }
    }

    public void getStudentById(String id) {
        for (Student student : listStudents) {
            if (student.getId().equalsIgnoreCase(id)) {
                System.out.println(student);
                return;
            }
        }
        throw new NonExistStudentException("Student with ID " + id + " does not exist!");
    }

    public boolean updateStudentById(String id) {
        Scanner sc = new Scanner(System.in);

        for (Student student : listStudents) {
            if (student.getId().equalsIgnoreCase(id)) {
               
                System.out.println("\nCurrent info:");
                System.out.println(student);

                boolean continueEditing = true;
                while (continueEditing) {
                    System.out.println("\nWhat do you want to update?");
                    System.out.println("1. Name");
                    System.out.println("2. Gender");
                    System.out.println("3. DOB");
                    System.out.println("4. GPA");
                    System.out.println("5. Exit");
                    System.out.print("Choose: ");
                    int choose = Integer.parseInt(sc.nextLine());

                    switch (choose) {
                        case 1:
                            System.out.print("Enter fullname: ");
                            String newName = sc.nextLine();
                            student.setFullName(newName);
                            System.out.println("Name updated successfully!");
                            break;

                        case 2:
                            System.out.print("Enter gender: ");
                            String newGender = sc.nextLine();
                            if (newGender.equalsIgnoreCase("male")) {
                                student.setGender(true);
                            } else if (newGender.equalsIgnoreCase("female")) {
                                student.setGender(false);
                            } else {
                                throw new NonExistGenderException("Gender " + newGender + " is not exist.");
                            }
                            System.out.println("Gender updated successfully!");
                            break;

                        case 3:
                            System.out.print("Enter DOB(dd-mm-yyyy): ");
                            String dobStr = sc.nextLine();
                            try {
                                Date newDob = new SimpleDateFormat("dd-MM-yyyy").parse(dobStr);
                                student.setDob(newDob);
                                System.out.println("DOB Updated");
                            } catch (ParseException e) {
                                System.out.println("Invalid Date Format");
                            }
                            break;

                        case 4:
                            System.out.print("Enter GPA: ");
                            double newGpa = sc.nextDouble();
                            if (newGpa > 0 && newGpa <= 4.0) {
                                student.setGpa(newGpa);
                                System.out.println("Gpa updated");
                            } else {
                                throw new WrongGpaFormatException("GPA must be between 0 and 4");
                            }
                            break;
                        case 5:
                            continueEditing = false;
                            System.out.println("Exit...");
                            break;
                        default:
                            System.out.println("Invalid choice");
                            break;
                    }
                }
            } else {
                throw new NonExistStudentException("Student with ID " + id + " does not exist!");
            }
        }
        return true;
    }

    public boolean deleteStudentById(String id) {
        for (Student student : listStudents) {
            if (student.getId().equalsIgnoreCase(id)) {
                listStudents.remove(student);
                return true;
            }
        }
        throw new NonExistStudentException("Student with ID " + id + " does not exist!");
    }

    private boolean isIdFormat(String id) {
        return id.matches("[A-Z]{2}\\d{4}");
    }
}