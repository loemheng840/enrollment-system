package dao;

import model.Classes;
import model.Major;
import model.Student;
import model.Teacher;
import utils.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ClassDAO {
    private final Connection connection;

    public ClassDAO() {
        connection = DatabaseConnection.getConnection();
    }

    // Create Class
    public Classes createClass(Classes classes) throws SQLException {
        String sql = "INSERT INTO class_info (id, classes, time) VALUES (?, ?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            UUID classId = UUID.randomUUID();
            ps.setObject(1, classId);
            ps.setString(2, classes.getClasses());
            ps.setString(3, classes.getTime().name()); // Save enum as string
            ps.executeUpdate();

            classes.setId(classId); // Set generated UUID
            return classes;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    // Add Student
    public void addStudentToClass(UUID classId, UUID studentId) throws SQLException {
        String sql = "INSERT INTO class_students (class_id, student_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, classId);
            ps.setObject(2, studentId);
            ps.executeUpdate();

        }
    }

    // Add Teacher
    public void addTeacherToClass(UUID classId, UUID teacherId) throws SQLException {
        String sql = "INSERT INTO class_teacher (class_id, teacher_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, classId);
            ps.setObject(2, teacherId);
            ps.executeUpdate();
        }
    }

    // Add Major
    public void addMajorToClass(UUID classId, UUID majorId) throws SQLException {
        String sql = "INSERT INTO class_major (class_id, major_id) VALUES (?, ?)";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, classId);
            ps.setObject(2, majorId);
            ps.executeUpdate();
        }
    }

    // Get Class by ID with all lists
    public Classes getClassById(UUID id) throws SQLException {
        String sql = "SELECT * FROM class_info WHERE id = ?";
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            if (!rs.next()) return null;

            Classes classes = mapClassInfo(rs);
            classes.setStudents(getStudentsByClassId(id));
            classes.setTeacher(getTeachersByClassId(id));
            classes.setMajors(getMajorsByClassId(id));

            return classes;
        }
    }

    // Map ResultSet to Classes object
    private Classes mapClassInfo(ResultSet rs) throws SQLException {
        return Classes.builder()
                .id(rs.getObject("id", UUID.class))
                .classes(rs.getString("classes"))
                .time(Classes.Time.valueOf(rs.getString("time")))
                .build();
    }

    // Get Students
    public List<Student> getStudentsByClassId(UUID classId) throws SQLException {
        String sql = "SELECT s.* FROM student s " +
                "JOIN class_students sc ON s.id = sc.student_id " +
                "WHERE sc.class_id = ?";
        List<Student> students = new ArrayList<>();
        int maleCount = 0;
        int femaleCount = 0;
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, classId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Student student = new Student();
                student.setId(rs.getObject("id", UUID.class));
                student.setName(rs.getString("name"));
                student.setGender(rs.getString("gender"));
                student.setEmail(rs.getString("email"));
                student.setPhone(rs.getString("phone"));
                if("male".equalsIgnoreCase(student.getGender())) maleCount++;
                else if("female".equalsIgnoreCase(student.getGender())) femaleCount++;
//                student.setStatus(rs.getObject("status", Student.Status.class));
                students.add(student);
            }
        }
        return students;
    }
    // Check it again
    public List<Student> countStudentsByGender(UUID classId ,String gender) throws SQLException {
        String sql = """
                SELECT
                    COUNT(*) AS total_student,
                    SUM(CASE WHEN gender = 'male' THEN 1 ELSE 0 END) AS total_male,
                    SUM(CASE WHEN gender = 'female' THEN 1 ELSE 0 END) AS total_female
                FROM student;
                """;
        return countStudentsByGender(classId ,gender);
    }
    // Get Teachers
    public List<Teacher> getTeachersByClassId(UUID classId) throws SQLException {
        String sql = "SELECT t.* FROM teacher t " +
                "JOIN class_teacher ct ON t.id = ct.teacher_id " +
                "WHERE ct.class_id = ?";
        List<Teacher> teachers = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, classId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Teacher teacher = new Teacher();
                teacher.setId(rs.getObject("id", UUID.class));
                teacher.setName(rs.getString("name"));
                teacher.setEmail(rs.getString("email"));
                teacher.setPhone(rs.getString("phone"));
                teachers.add(teacher);
            }
        }
        return teachers;
    }

    // Get Majors
    public List<Major> getMajorsByClassId(UUID classId) throws SQLException {
        String sql = "SELECT m.* FROM major m " +
                "JOIN class_major cm ON m.id = cm.major_id " +
                "WHERE cm.class_id = ?";
        List<Major> majors = new ArrayList<>();
        try (PreparedStatement ps = connection.prepareStatement(sql)) {
            ps.setObject(1, classId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Major major = new Major();
                major.setId(rs.getObject("id", UUID.class));
                major.setMajor(rs.getString("major_name"));
                major.setHours(rs.getInt("hours"));
                major.setDescription(rs.getString("description"));
                major.setLecturer(rs.getString("lecturer"));
                majors.add(major);
            }
        }
        return majors;
    }
}
