package controller;

import model.Major;
import service.MajorService;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class MajorController {
    private final MajorService majorService = new MajorService();
    private final Scanner scanner = new Scanner(System.in);

    public void display() {
        while (true) {
            System.out.println("\n--- Major ---");
            System.out.println("1. Add Major");
            System.out.println("2. View all Majors");
            System.out.println("3. Update Major");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // <<< FIX HERE

            switch (choice) {
                case 1:
                    addMajor();
                    break;
                case 2:
                    viewAllMajors();
                    break;
                case 3:
                    updateMajor();
                    break;
                case 4: System.exit(0);
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    public void addMajor() {
        System.out.println("\n--- Add Major ---");

        Major major = Major.builder()
                .id(UUID.randomUUID())
                .major(prompt("Major"))
                .departmentId(UUID.fromString(prompt("DepartmentId")))
                .description(prompt("Description"))
                .hours(Integer.parseInt(prompt("Hours")))
                .lecturer(prompt("Lecturer"))
                .build();

        majorService.addMajor(major);
        System.out.println("Major added successfully!");
    }

    public void viewAllMajors() {
        System.out.println("\n--- View all Majors ---");
        List<Major> majors = majorService.getAllMajors();
        majors.forEach(System.out::println);
    }
    public void updateMajor() {
        System.out.println("\n--- Update Major ---");

        String input = prompt("Enter Major ID to update");
        UUID id;

        try {
            id = UUID.fromString(input);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid ID.");
            return;
        }

        Major major = majorService.getMajorById(id);
        if (major == null) {
            System.out.println("Major not found.");
            return;  // <<< IMPORTANT FIX
        }

        major.setMajor(prompt("Major"));
        major.setDescription(prompt("Description"));
        major.setHours(Integer.parseInt(prompt("Hours")));
        major.setLecturer(prompt("Lecturer"));

        majorService.updateMajor(id, major);
        System.out.println("Major updated successfully!");
    }

    private String prompt(String field) {
        System.out.print(field + ": ");
        return scanner.nextLine();
    }
}
