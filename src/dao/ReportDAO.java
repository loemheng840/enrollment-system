package dao;

import utils.DatabaseConnection;
import java.sql.*;

public class ReportDAO {
    private final Connection connection;

    public ReportDAO() {
        connection = DatabaseConnection.getConnection();
    }

    public int getTotalStudents() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT count(*) FROM student")) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
        }
        return 0;
    }

    public int getTotalTeachers() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT count(*) FROM teacher")) {
            if (rs.next()) return rs.getInt(1);
        } catch (SQLException e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
        }
        return 0;
    }

    public double getTotalRevenue() {
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT sum(amount) FROM payment WHERE status = 'PAID'")) {
            if (rs.next()) return rs.getDouble(1);
        } catch (SQLException e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
        }
        return 0.0;
    }
}
