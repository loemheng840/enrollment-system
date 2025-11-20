package controller;

import model.Classes;
import service.ClassesService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class ClassesController {

    private final ClassesService service;
    private final Scanner sc;

    public ClassesController() {
        service = new ClassesService();
        sc = new Scanner(System.in);
    }

    public void menu() throws SQLException {
        while (true) {
            System.out.println("\n=== Classes Menu ===");
            System.out.println("1. Create Class");
            System.out.println("2. Add Student to Class");
            System.out.println("3. Add Teacher to Class");
            System.out.println("4. Add Major to Class");
            System.out.println("5. Show Class Details");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int option = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (option) {
                case 1 -> createClass();
                case 2 -> addStudent();
                case 3 -> addTeacher();
                case 4 -> addMajor();
                case 5 -> showClassDetails();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option.");
            }
        }
    }

    private void createClass() throws SQLException {
        System.out.print("Enter class name: ");
        String name = sc.nextLine();
        Classes c = service.createClass(name, Classes.Time.MORNING);
        System.out.println("Class created successfully! ID: " + c.getId());
    }

    private void addStudent() throws SQLException {
        System.out.print("Enter Class ID: ");
        UUID classId = UUID.fromString(sc.nextLine());
        System.out.print("Enter Student ID: ");
        UUID studentId = UUID.fromString(sc.nextLine());
        service.addStudentToClass(classId, studentId);
        System.out.println("Student added to class.");
    }

    private void addTeacher() throws SQLException {
        System.out.print("Enter Class ID: ");
        UUID classId = UUID.fromString(sc.nextLine());
        System.out.print("Enter Teacher ID: ");
        UUID teacherId = UUID.fromString(sc.nextLine());
        service.addTeacherToClass(classId, teacherId);
        System.out.println("Teacher added to class.");
    }

    private void addMajor() throws SQLException {
        System.out.print("Enter Class ID: ");
        UUID classId = UUID.fromString(sc.nextLine());
        System.out.print("Enter Major ID: ");
        UUID majorId = UUID.fromString(sc.nextLine());
        service.addMajorToClass(classId, majorId);
        System.out.println("Major added to class.");
    }

    private void showClassDetails() throws SQLException {
        System.out.print("Enter Class ID: ");
        UUID classId = UUID.fromString(sc.nextLine());
        Classes c = service.getClassById(classId);
        if (c == null) {
            System.out.println("Class not found!");
            return;
        }
        System.out.println("\nClass Name: " + c.getClasses());
        System.out.println("Time: " + c.getTime());
        System.out.print(c.getStudents().size() + " Student(s): ");
        System.out.println("Students: " + c.getStudents());
        System.out.print(c.getTeacher().size() + " Teacher(s): ");
        System.out.println("Teachers: " + c.getTeacher());
        System.out.print(c.getMajors().size() + " Major(s): ");
        System.out.println("Majors: " + c.getMajors());
    }

    // main method for testing
    public static void main(String[] args) throws SQLException {
        ClassesController controller = new ClassesController();
        controller.menu();
    }
}
