package data_structure.list;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
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
            System.out.println("--------------------");
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
        Student selectedStudent = null;

        for (Student student : listStudents) {
            if (student.getId().equalsIgnoreCase(id)) {
                selectedStudent = student;
                break;
            }
        }

        if (selectedStudent == null) {
            throw new NonExistStudentException("Student with ID " + id + " does not exist!");
        }
        System.out.println("\nCurrent info:");
        System.out.println(selectedStudent);

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
                    selectedStudent.setFullName(newName);
                    System.out.println("Name updated successfully!");
                    break;

                case 2:
                    System.out.print("Enter gender: ");
                    String newGender = sc.nextLine();
                    if (newGender.equalsIgnoreCase("male")) {
                        selectedStudent.setGender(true);
                    } else if (newGender.equalsIgnoreCase("female")) {
                        selectedStudent.setGender(false);
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
                        selectedStudent.setDob(newDob);
                        System.out.println("DOB Updated");
                    } catch (ParseException e) {
                        System.out.println("Invalid Date Format");
                    }
                    break;

                case 4:
                    System.out.print("Enter GPA: ");
                    double newGpa = sc.nextDouble();
                    if (newGpa > 0 && newGpa <= 4.0) {
                        selectedStudent.setGpa(newGpa);
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

    public void sortStudentByGpa() {
        Collections.sort(listStudents, new Comparator<Student>() {
            @Override
            public int compare(Student arg0, Student arg1) {
                return Double.compare(arg0.getGpa(), arg1.getGpa());
            }
        });
    }

    public void addDummyData() {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

            listStudents.add(new Student("ST1001", "Alice Johnson", false, sdf.parse("12-03-2002"), 3.5));
            listStudents.add(new Student("ST1002", "Bob Smith", true, sdf.parse("25-07-2001"), 2.8));
            listStudents.add(new Student("ST1003", "Charlie Kim", true, sdf.parse("09-11-2003"), 3.9));
            listStudents.add(new Student("ST1004", "Diana Lee", false, sdf.parse("21-05-2002"), 3.2));
            listStudents.add(new Student("ST1005", "Ethan Brown", true, sdf.parse("14-02-2000"), 2.4));
            listStudents.add(new Student("ST1006", "Fiona Nguyen", false, sdf.parse("30-09-2001"), 3.7));
            listStudents.add(new Student("ST1007", "George Park", true, sdf.parse("17-01-2002"), 3.1));
            listStudents.add(new Student("ST1008", "Hannah Tran", false, sdf.parse("02-04-2003"), 3.8));
            listStudents.add(new Student("ST1009", "Ivan Lee", true, sdf.parse("27-08-2000"), 2.6));
            listStudents.add(new Student("ST1010", "Jenny Pham", false, sdf.parse("19-10-2001"), 3.0));

            System.out.println("✅ Dummy data added successfully!");
        } catch (ParseException e) {
            System.out.println("❌ Error creating dummy data: " + e.getMessage());
        }
    }

    private boolean isIdFormat(String id) {
        return id.matches("[A-Z]{2}\\d{4}");
    }
}