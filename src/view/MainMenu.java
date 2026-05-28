package view;

import controller.*;
import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
    public void start() throws SQLException {
        Scanner scanner = new Scanner(System.in);
        
        StudentController studentController = new StudentController();
        TeacherController teacherController = new TeacherController();
        DepartmentController departmentController = new DepartmentController();
        MajorController majorController = new MajorController();
        ClassesController classesController = new ClassesController();
        EnrollmentController enrollmentController = new EnrollmentController();
        PaymentController paymentController = new PaymentController();
        ReportController reportController = new ReportController();

        while (true) {
            System.out.println("\n=========== Enrollment System ===========");
            System.out.println("1. Student Management");
            System.out.println("2. Teacher Management");
            System.out.println("3. Department Management");
            System.out.println("4. Major Management");
            System.out.println("5. Class Management");
            System.out.println("6. Enrollment");
            System.out.println("7. Payment");
            System.out.println("8. Reports");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> studentController.start();
                case "2" -> teacherController.menu();
                case "3" -> departmentController.displayMenu();
                case "4" -> majorController.display();
                case "5" -> classesController.menu();
                case "6" -> enrollmentController.display();
                case "7" -> paymentController.menu();
                case "8" -> reportController.menu();
                case "0" -> {
                    System.out.println("Exiting System. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
