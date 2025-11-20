package controller;

import model.Teacher;
import service.TeacherService;

import java.sql.SQLException;
import java.util.Scanner;
import java.util.UUID;

public class TeacherController {

    private final TeacherService teacherService = new TeacherService();
    private final Scanner scanner = new Scanner(System.in);

    // --------------------------------------------------
    // MAIN MENU
    // --------------------------------------------------
    public void menu() throws SQLException {
        while (true) {
            System.out.println("\n===== TEACHER MENU =====");
            System.out.println("1. Add Teacher");
            System.out.println("2. Get Teacher by ID");
            System.out.println("3. Update Teacher by ID");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addTeacher();
                case "2" -> getTeacherById();
                case "3" -> updateTeacherById();
                case "0" -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("❌ Invalid choice. Try again.");
            }
        }
    }

    // --------------------------------------------------
    // ADD TEACHER
    // --------------------------------------------------
    public void addTeacher() throws SQLException {
        Teacher teacher = Teacher.builder()
                .id(UUID.randomUUID())
                .name(promptRequiredString("Name"))
                .phone(promptRequiredString("Phone"))
                .email(promptRequiredString("Email"))
                .departmentId(promptUUID("Department ID"))
                .majorId(promptUUID("Major ID"))
                .time(promptEnum("Time (MORNING/AFTERNOON/NIGHT)"))
                .build();

        Teacher result = teacherService.addTeacher(teacher);
        System.out.println("✔ Teacher added successfully: " + result);
    }

    // --------------------------------------------------
    // GET TEACHER BY ID
    // --------------------------------------------------
    public void getTeacherById() throws SQLException {
        UUID id = promptUUID("Enter Teacher ID");
        Teacher teacher = teacherService.searchTeacherById(id);

        if (teacher != null) {
            System.out.println("\n=== TEACHER FOUND ===");
            System.out.println(teacher);
        } else {
            System.out.println("❌ Teacher not found");
        }
    }

    // --------------------------------------------------
    // UPDATE TEACHER
    // --------------------------------------------------
    public void updateTeacherById() throws SQLException {
        UUID id = promptUUID("Enter Teacher ID to update");

        Teacher teacher = teacherService.searchTeacherById(id);
        if (teacher == null) {
            System.out.println("❌ Teacher not found!");
            return;
        }

        System.out.println("\n=== Updating Teacher ===");

        teacher.setName(promptRequiredString("Name"));
        teacher.setPhone(promptRequiredString("Phone"));
        teacher.setEmail(promptRequiredString("Email"));
        teacher.setDepartmentId(promptUUID("Department ID"));
        teacher.setMajorId(promptUUID("Major ID"));
        teacher.setTime(promptEnum("Time (MORNING/AFTERNOON/NIGHT)"));

        boolean updated = teacherService.updateTeacherById(id, teacher);
        System.out.println("✔ Teacher updated: " + updated);
    }

    // --------------------------------------------------
    // HELPER FUNCTIONS
    // --------------------------------------------------

    private String promptRequiredString(String field) {
        while (true) {
            System.out.print(field + ": ");
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            System.out.println("❌ " + field + " cannot be empty!");
        }
    }

    private UUID promptUUID(String field) {
        while (true) {
            try {
                System.out.print(field + ": ");
                return UUID.fromString(scanner.nextLine().trim());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid UUID format! Try again.");
            }
        }
    }

    private Teacher.Time promptEnum(String field) {
        while (true) {
            try {
                System.out.print(field + ": ");
                return Teacher.Time.valueOf(scanner.nextLine().trim().toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("❌ Invalid time! Options: MORNING, AFTERNOON, NIGHT");
            }
        }
    }

    private int promptInt(String field) {
        while (true) {
            try {
                System.out.print(field + ": ");
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid number! Try again.");
            }
        }
    }
}
