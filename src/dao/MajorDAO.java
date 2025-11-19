package dao;

import model.Major;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MajorDAO {
    private final Connection connection;

    public MajorDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // CREATE
    public Major addMajor(Major major) {
        String sql = "INSERT INTO major (id, major_name, department_id, description, hours, lecturer) VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, major.getId());
            ps.setString(2, major.getMajor());
            ps.setObject(3, major.getDepartmentId());
            ps.setString(4, major.getDescription());
            ps.setInt(5, major.getHours());
            ps.setString(6, major.getLecturer());
            ps.executeUpdate();
            return major;

        } catch (SQLException e) {
            System.out.println("[ERROR] Insert Major Failed: " + e.getMessage());
            return null;
        }
    }

    // READ BY ID
    public Major getMajorById(UUID id) {
        String sql = "SELECT * FROM major WHERE id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return mapResultSetToMajor(rs);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Get Major Failed: " + e.getMessage());
        }
        return null;
    }

    // READ ALL
    public List<Major> getAllMajors() {
        List<Major> majors = new ArrayList<>();
        String sql = "SELECT * FROM major";

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                majors.add(mapResultSetToMajor(rs));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Get All Majors Failed: " + e.getMessage());
        }
        return majors;
    }

    // UPDATE
    public boolean updateMajor(UUID id, Major major) {
        String sql = "UPDATE major SET major_name=?, department_id=?, description=?, hours=?, lecturer=? WHERE id=?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, major.getMajor());
            ps.setObject(2, major.getDepartmentId());
            ps.setString(3, major.getDescription());
            ps.setInt(4, major.getHours());
            ps.setString(5, major.getLecturer());
            ps.setObject(6, id);

            return ps.executeUpdate() > 0;

        } catch (SQLException e) {
            System.out.println("[ERROR] Update Major Failed: " + e.getMessage());
            return false;
        }
    }
    // MAPPING
    private Major mapResultSetToMajor(ResultSet rs) throws SQLException {
        return Major.builder()
                .id(UUID.fromString(rs.getString("id")))
                .major(rs.getString("major_name"))
                .departmentId(UUID.fromString(rs.getString("department_id")))
                .description(rs.getString("description"))
                .hours(rs.getInt("hours"))
                .lecturer(rs.getString("lecturer"))
                .build();
    }
}
