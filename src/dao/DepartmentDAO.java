package dao;

import model.Department;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DepartmentDAO {
    private final Connection connection;

    public DepartmentDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // CREATE: Add new department
    public Department addDepartment(Department department) {
        String sql = "INSERT INTO department (id, department_name, department_code) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, department.getId()); // use the UUID generated in service/controller
            ps.setString(2, department.getDepartmentName());
            ps.setString(3, department.getDepartmentCode());
            ps.executeUpdate();
            return department;
        } catch (SQLException e) {
            System.out.println("[ERROR] Insert Department Failed: " + e.getMessage());
            return null;
        }
    }

    // READ: Get department by department_code
    public Department getDepartmentByCode(String departmentCode) {
        String sql = "SELECT * FROM department WHERE department_code = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, departmentCode);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToDepartment(rs);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Get Department Failed: " + e.getMessage());
        }
        return null;
    }

    // READ ALL: Get all departments
    public List<Department> getAllDepartments() {
        List<Department> departments = new ArrayList<>();
        String sql = "SELECT * FROM department";
        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(sql);
            while (rs.next()) {
                departments.add(mapResultSetToDepartment(rs));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] Get All Departments Failed: " + e.getMessage());
        }
        return departments;
    }

    // Helper: Map ResultSet to Department
    private Department mapResultSetToDepartment(ResultSet rs) throws SQLException {
        return Department.builder()
                .id(rs.getObject("id", UUID.class))
                .departmentName(rs.getString("department_name"))
                .departmentCode(rs.getString("department_code"))
                .build();
    }
}
