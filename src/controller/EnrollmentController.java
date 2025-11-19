package controller;

import model.Enrollment;
import service.EnrollmentService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class EnrollmentController {

    private final EnrollmentService enrollmentService = new EnrollmentService();
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("======= Enrollment Details =======");
            System.out.println("1. Add Enrollment");
            System.out.println("2. View All Enrollments");
            System.out.println("3. Exit Program");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addEnrollment();
                case 2 -> viewEnrollment();
                case 3 -> {
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid choice");
            }
        }
    }

    public void addEnrollment() {
        Enrollment enrollment = Enrollment.builder()
                .id(UUID.randomUUID())
                .studentId(promptUUID("Student Id"))
                .majorId(promptUUID("Major Id"))
                .year(promptInt("Year"))
                .status(Enrollment.Status.ENROLLED)
                .build();

        enrollmentService.addEnrollment(enrollment);
        System.out.println("✅ Enrollment added successfully!");
    }

    public void viewEnrollment() {
        List<Enrollment> enrollments = enrollmentService.viewAllEnrollments();
        for (Enrollment enrollment : enrollments) {
            System.out.println(enrollment.toString());
        }
    }

    /** Prompt valid UUID with retry */
    private UUID promptUUID(String field) {
        UUID value = null;
        while (value == null) {
            try {
                System.out.print(field + ": ");
                String input = scanner.nextLine();
                value = UUID.fromString(input);
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid UUID format! Please try again.");
            }
        }
        return value;
    }

    /** Prompt valid integer with retry */
    private int promptInt(String field) {
        while (true) {
            try {
                System.out.print(field + ": ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number! Please enter a valid integer.");
            }
        }
    }
}
