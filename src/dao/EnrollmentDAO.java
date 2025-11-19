package dao;

import model.Enrollment;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.security.Timestamp;

public class EnrollmentDAO {
    private final Connection connection;

    public EnrollmentDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // CREATE: Add new enrollment
    public Enrollment addEnrollment(Enrollment enrollment) {
        String sql = """
                INSERT INTO enrollment (id, student_id, major_id, year, status)
                VALUES (?, ?, ?, ?, ?)
                """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, enrollment.getId());
            ps.setObject(2, enrollment.getStudentId());
            ps.setObject(3, enrollment.getMajorId());
            ps.setInt(4,  enrollment.getYear());
            ps.setString(5, enrollment.getStatus().name());
            ps.executeUpdate();
            return enrollment;
        } catch (SQLException e) {
            System.out.println("[ERROR] Insert Enrollment Failed: " + e.getMessage());
            return null;
        }
    }

    // READ ALL: Get all enrollments
    public List<Enrollment> viewAllEnrollments() {
    String sql = """
        SELECT
        e.id AS id,
                s.id AS student_id,
        s.name AS student_name,
                s.gender,
                s.dob,
                s.email,
                s.phone,
                s.address,

                m.id AS major_id,
        m.major_name,
                m.description AS major_description,
        m.hours,
                e.enrollment_at,
                e.year,
                e.status
        FROM enrollment e
        JOIN student s ON e.student_id = s.id
        JOIN major m ON e.major_id = m.id;
        """;

        List<Enrollment> enrollments = new ArrayList<>();
        try(Statement stmt = connection.createStatement()){
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                enrollments.add(mapResultSetToEnrollment(rs));
            }
        }catch (SQLException e){
            System.out.println("[ERROR] Insert Enrollment Failed: " + e.getMessage());
        }
        return enrollments;
    }

    // Helper: Map ResultSet -> Enrollment
    private Enrollment mapResultSetToEnrollment(ResultSet rs) throws SQLException {
        return Enrollment.builder()
                .id(rs.getObject("id", UUID.class))
                .studentId(rs.getObject("student_id", UUID.class))
                .studentName(rs.getString("student_name"))
                .gender(rs.getString("gender"))
                .dob(rs.getString("dob"))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .address(rs.getString("address"))
                .majorId(rs.getObject("major_id", UUID.class))
                .majorName(rs.getString("major_name"))
                .majorDescription(rs.getString("major_description"))
                .hours(rs.getInt("hours"))
                .enrollmentAt(rs.getTimestamp("enrollment_at").toLocalDateTime())
                .year(rs.getInt("year"))
                .status(Enrollment.Status.valueOf(rs.getString("status")))
                .build();
    }
}
