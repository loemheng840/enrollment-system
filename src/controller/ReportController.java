package controller;

import service.ReportService;
import java.util.Scanner;

public class ReportController {
    private final ReportService reportService = new ReportService();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n=========== Report Menu ===========");
            System.out.println("1. System Statistics Summary");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> viewSummary();
                case "0" -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void viewSummary() {
        System.out.println("\n--- System Summary ---");
        System.out.println("Total Students: " + reportService.getTotalStudents());
        System.out.println("Total Teachers: " + reportService.getTotalTeachers());
        System.out.println("Total Revenue: $" + reportService.getTotalRevenue());
    }
}
