package dao;

import model.Payment;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PaymentDAO {
    private final Connection connection;

    public PaymentDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public Payment addPayment(Payment payment) {
        String sql = """
                INSERT INTO payment (id, student_id, amount, payment_date, status, payment_type)
                VALUES (?, ?, ?, ?, ?, ?)
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, payment.getId());
            ps.setObject(2, payment.getStudentId());
            ps.setDouble(3, payment.getAmount());
            ps.setTime(4, payment.getPaymentDate());
            ps.setString(5, payment.getStatus().name());
            ps.setString(6, payment.getPaymentType().name());
            ps.executeUpdate();
            return payment;
        } catch (SQLException e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
            return null;
        }
    }

    public List<Payment> getAllPayments() {
        String sql = "SELECT * FROM payment";
        List<Payment> payments = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                payments.add(Payment.builder()
                        .id(UUID.fromString(rs.getString("id")))
                        .studentId(UUID.fromString(rs.getString("student_id")))
                        .amount(rs.getDouble("amount"))
                        .paymentDate(rs.getTime("payment_date"))
                        .status(Payment.Status.valueOf(rs.getString("status")))
                        .paymentType(Payment.PaymentType.valueOf(rs.getString("payment_type")))
                        .build());
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
        }
        return payments;
    }
}
