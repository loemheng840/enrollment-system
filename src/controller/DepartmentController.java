package controller;

import model.Department;
import service.DepartmentService;

import java.util.List;
import java.util.Scanner;

public class DepartmentController {
    private final DepartmentService departmentService = new DepartmentService();
    private final Scanner scanner = new Scanner(System.in);

    private void addDepartment() {
        String name = promptInput("Department Name");
        String code = promptInput("Department Code");
        Department dept = departmentService.addDepartment(name, code);
        System.out.println("Department added successfully: " + dept);
    }

    private void getAllDepartments() {
        List<Department> departments = departmentService.getAllDepartments();
        if (departments.isEmpty()) {
            System.out.println("No departments found.");
            return;
        }
        System.out.println("All Departments:");
        for (Department d : departments) {
            System.out.println(d);
        }
    }

    private void getDepartmentByCode() {
        String code = promptInput("Department Code");
        Department dept = departmentService.getDepartmentByCode(code);
        if (dept == null) {
            System.out.println("Department not found for code: " + code);
        } else {
            System.out.println("Department Details: " + dept);
        }
    }

    private String promptInput(String field) {
        System.out.print(field + ": ");
        return scanner.nextLine();
    }

    public void displayMenu() {
        while (true) {
            System.out.println("\n=========DEPARTMENT MENU==========");
            System.out.println("1. Add Department");
            System.out.println("2. Get All Departments");
            System.out.println("3. Get Department By Code");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> addDepartment();
                case 2 -> getAllDepartments();
                case 3 -> getDepartmentByCode();
                case 0 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}
