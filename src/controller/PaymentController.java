package controller;

import model.Payment;
import service.PaymentService;

import java.sql.Time;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

public class PaymentController {
    private final PaymentService paymentService = new PaymentService();
    private final Scanner scanner = new Scanner(System.in);

    public void menu() {
        while (true) {
            System.out.println("\n=========== Payment Menu ===========");
            System.out.println("1. Add Payment");
            System.out.println("2. View All Payments");
            System.out.println("0. Back to Main Menu");
            System.out.print("Choose option: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1" -> addPayment();
                case "2" -> viewAllPayments();
                case "0" -> {
                    System.out.println("Returning to Main Menu...");
                    return;
                }
                default -> System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private void addPayment() {
        System.out.println("\n--- Add Payment ---");
        try {
            System.out.print("Enter Student ID: ");
            UUID studentId = UUID.fromString(scanner.nextLine().trim());
            System.out.print("Enter Amount: ");
            Double amount = Double.parseDouble(scanner.nextLine().trim());
            System.out.print("Enter Payment Type (BILL/BANK): ");
            Payment.PaymentType type = Payment.PaymentType.valueOf(scanner.nextLine().trim().toUpperCase());
            System.out.print("Enter Status (PAID/UNPAID): ");
            Payment.Status status = Payment.Status.valueOf(scanner.nextLine().trim().toUpperCase());

            Payment payment = Payment.builder()
                    .id(UUID.randomUUID())
                    .studentId(studentId)
                    .amount(amount)
                    .paymentDate(new Time(System.currentTimeMillis()))
                    .status(status)
                    .paymentType(type)
                    .build();

            Payment result = paymentService.addPayment(payment);
            if (result != null) {
                System.out.println("Payment added successfully: " + result.getId());
            } else {
                System.out.println("Failed to add payment.");
            }
        } catch (Exception e) {
            System.out.println("❌ Invalid input! Make sure UUID is correct and Enums match exactly.");
        }
    }

    private void viewAllPayments() {
        System.out.println("\n--- All Payments ---");
        List<Payment> payments = paymentService.getAllPayments();
        if (payments.isEmpty()) {
            System.out.println("No payments found.");
        } else {
            payments.forEach(System.out::println);
        }
    }
}
