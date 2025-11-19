package dao;

import model.Teacher;
import utils.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class TeacherDAO {
    private Connection connection;
    public TeacherDAO() {
        connection = DatabaseConnection.getConnection();
    }
    // Add Teacher
    public Teacher addTeacher(Teacher teacher) throws SQLException {
        String sql = "INSERT INTO teacher (id, name, department_id, major_id,email,phone) VALUES (?, ?, ?, ?, ?, ?)";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1 , UUID.randomUUID());
            ps.setObject(2 , teacher.getName());
            ps.setObject(3 , teacher.getDepartmentId());
            ps.setObject(4 , teacher.getMajorId());
            ps.setObject(5 , teacher.getEmail());
            ps.setObject(6 , teacher.getPhone());
            ps.executeUpdate();

            return teacher;
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
    public Teacher searchTeacherById(UUID id, Teacher teacher) throws SQLException {
        String sql = "SELECT * FROM teacher WHERE id = ?";
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1 , id);
            ResultSet rs = ps.executeQuery();
            if(rs.next()){
                teacher.setId(id);
            }
            return teacher;
        }catch (SQLException e){
            System.out.println("[Error] SQLException: ]");
            return null;
        }
    }
    public boolean updateTeacherById(UUID id,Teacher teacher) throws SQLException {
        String sql = """
                UPDATE teacher Set name = ?, department_id = ?, major_id = ?, email = ?, phone = ?
                WHERE id = ?
                """;
        try(PreparedStatement ps = connection.prepareStatement(sql)){
            ps.setObject(1 , teacher.getName());
            ps.setObject(2 , teacher.getDepartmentId());
            ps.setObject(3 , teacher.getMajorId());
            ps.setObject(4 , teacher.getEmail());
            ps.setObject(5 , teacher.getPhone());
            ps.setObject(6 , id);
            return ps.executeUpdate()>0;
        }catch (SQLException e){
            System.out.println("[Error] SQLException: " + e.getMessage());
            return false;
        }
    }
    private Teacher mapResultSetToTeacher(ResultSet rs) throws SQLException {
        return Teacher.builder()
                .id(rs.getObject("id", UUID.class))
                .name(rs.getString("name"))
                .departmentId(rs.getObject("department_id", UUID.class))
                .majorId(rs.getObject("major_id", UUID.class))
                .email(rs.getString("email"))
                .phone(rs.getString("phone"))
                .build();
    }
}
