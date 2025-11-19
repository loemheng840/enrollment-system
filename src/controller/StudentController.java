package controller;

import model.Student;
import service.StudentService;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;


public class StudentController {
    private final StudentService studentService = new StudentService();
    private final Scanner scanner = new Scanner(System.in);

    public void start() {
        while (true) {
            System.out.println("\n==== Student Management Menu ====");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Search Student by ID");
            System.out.println("4. Delete Student");
            System.out.println("5. Update Student");
            System.out.println("6. Filter Student By Department");
            System.out.println("7. Filter Student By Major");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addStudent();
                case "2" -> viewAllStudents();
                case "3" -> searchStudentById();
                case "4" -> deleteStudent();
                case "5" -> updateStudent();
                case "6" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }
    private void addStudent() {
        System.out.println("\n--- Add New Student ---");
        Student student = Student.builder()
                .id(UUID.randomUUID())
                .name(prompt("Name"))
                .gender(prompt("Gender"))
                .dob(LocalDate.parse(prompt("Dob")))
                .email(prompt("Email"))
                .phone(prompt("Phone"))
                .address(prompt("Address"))
                .departmentId(UUID.fromString(prompt("DepartmentId")))
                .majorId(UUID.fromString(prompt("MajorId")))
                .status(Student.Status.ACTIVE)
                .build();
        studentService.addStudent(student);
    }

    private void viewAllStudents() {
        System.out.println("\n--- All Students ---");
        List<Student> students = studentService.getAllStudents();
        for (Student student : students) {
            System.out.println(student.toString());
        }
    }

    private void searchStudentById() {
        System.out.print("Enter Student ID: ");
        UUID id = UUID.fromString(scanner.nextLine());
        Student student = studentService.getStudentById(id);
        if (student != null) {
            System.out.println(student);
        } else {
            System.out.println("Student not found.");
        }
    }

    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        UUID id = UUID.fromString(scanner.nextLine());
        boolean success = studentService.deleteStudent(id);
        System.out.println(success ? "Student deleted." : "Failed to delete student.");
    }

    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        String input = scanner.nextLine().trim();
        UUID id;
        try {
            id = UUID.fromString(input);
        } catch (IllegalArgumentException e) {
            System.out.println("❌ Invalid UUID format. Example: 550e8400-e29b-41d4-a716-446655440000");
            return; // Stop method
        }

        Student student =studentService.getStudentById(id);
        if (student == null) {
            System.out.println("Student not found.");
        }
        student.setName(prompt("Name"));
        student.setGender(prompt("Gender"));
        student.setDob(LocalDate.parse(prompt("Dob")));
        student.setEmail(prompt("Email"));
        student.setPhone(prompt("Phone"));
        student.setAddress(prompt("Address"));
        student.setStatus(Student.Status.ACTIVE);
        studentService.updateStudent(id, student);
    }

    private String prompt(String field) {
        System.out.print(field + ": ");
        return scanner.nextLine();
    }

}
