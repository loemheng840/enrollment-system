package dao;

import model.Student;
import utils.DatabaseConnection;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class StudentDAO {
    private final Connection connection;

    public StudentDAO() {
        connection = DatabaseConnection.getConnection();
    }
    public Student addStudent(Student student) {
        String sql = """
        INSERT INTO student(id, name, gender, dob, email, phone, address,department_id,major_id, status)
        VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, student.getId());
            ps.setString(2, student.getName());
            ps.setString(3, student.getGender());
            ps.setDate(4, new java.sql.Date(student.getDob().getDayOfYear()));
            ps.setString(5, student.getEmail());
            ps.setString(6, student.getPhone());
            ps.setString(7, student.getAddress());
            ps.setObject(8, student.getDepartmentId());
            ps.setObject(9, student.getMajorId());
            ps.setString(10,student.getStatus().name());
            ps.executeUpdate();
            return student;
        } catch (SQLException e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
            return null;
        }
    }
    // Get student by Department
    public List<Student> getStudentByDepartmentCode(String department_code ,String department) {
        String aql = "SELECT * FROM student WHERE department_code = ?";
        List<Student> students = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(aql)) {
            ps.setString(1, department_code);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        }catch (Exception e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
        }
     return students;
    }
    // Get student by Major
    public List<Student> getStudentByMajor(String major) {
        String aql = "SELECT * FROM student WHERE major = ?";
        List<Student> students = new ArrayList<>();
        try(PreparedStatement ps = connection.prepareStatement(aql)) {
            ps.setString(1, major);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        }catch (Exception e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
        }
        return students;
    }
    // Get student by ID
    public Student getStudentById(UUID id) {
        String sql = "SELECT " +
                "s.id, " +
                "s.name, " +
                "s.gender, " +
                "s.dob, " +
                "s.email, " +
                "s.phone, " +
                "s.address, " +
                "s.status, " +
                "d.department_name, " +
                "m.major_name " +
                "FROM student s " +
                "LEFT JOIN department d ON s.department_id = d.id " +
                "LEFT JOIN major m ON s.major_id = m.id " +
                "WHERE s.id = ?";

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return mapResultSetToStudent(rs);
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
        }
        return null;
    }

    // Get all students
    public List<Student> getAllStudents() {
        String sql = """
            SELECT\s
                s.id,
                s.name,
                s.gender,
                s.dob,
                s.email,
                s.phone,
                s.address,
                s.status,
                d.department_name,
                m.major_name
            FROM student s
            LEFT JOIN department d
                ON s.department_id = d.id
            LEFT JOIN major m
                ON s.major_id = m.id;
            
""";
        List<Student> students = new ArrayList<>();

        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                students.add(mapResultSetToStudent(rs));
            }
        } catch (SQLException e) {
            System.out.println("[ERROR] SQL Exception: " + e.getMessage());
        }

        return students;
    }

    // Update student by ID
    public boolean updateStudentByID(UUID id, Student student) {
        String sql = """
                UPDATE student
                SET name=?, gender=?, dob=?, email=?, phone=?, address=?, status=?
                WHERE id=?
                """;

        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setString(1, student.getName());
            ps.setString(2, student.getGender());
            ps.setDate(3, new Date(student.getDob().getDayOfYear()));
            ps.setString(4, student.getEmail());
            ps.setString(5, student.getPhone());
            ps.setString(6, student.getAddress());
            ps.setString(7, student.getStatus().name());
            ps.setObject(8, id);

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[ERROR] Update failed: " + e.getMessage());
            return false;
        }
    }

    // Delete student by ID
    public boolean deleteStudentByID(UUID id) {
        String sql = "DELETE FROM student WHERE id=?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            System.out.println("[ERROR] Delete failed: " + e.getMessage());
            return false;
        }
    }
    // Helper method to map ResultSet -> Student object
    private Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        return Student.builder()
                .id(UUID.fromString(rs.getString("id")))
                .name(rs.getString("name"))
                .gender(rs.getString("gender"))
                .dob(LocalDate.parse(rs.getString("dob")))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .address(rs.getString("address"))
                .departmentName(rs.getString("department_name"))
                .majorName(rs.getString("major_name"))
                .status(Student.Status.ACTIVE) // convert string -> enum
                .build();
    }
}
