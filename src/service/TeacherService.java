package service;

import dao.TeacherDAO;
import model.Teacher;

import java.sql.SQLException;
import java.util.UUID;

public class TeacherService {

    private TeacherDAO teacherDAO = new TeacherDAO();

    public TeacherService() {
        this.teacherDAO = teacherDAO;
    }

    // ----------------------------
    // ADD TEACHER
    // ----------------------------
    public Teacher addTeacher(Teacher teacher) throws SQLException {

        // VALIDATION
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        if (teacher.getName() == null || teacher.getName().isBlank()) {
            throw new IllegalArgumentException("Teacher name cannot be empty");
        }
        if (teacher.getEmail() == null || teacher.getEmail().isBlank()) {
            throw new IllegalArgumentException("Teacher email cannot be empty");
        }

        return teacherDAO.addTeacher(teacher);
    }

    // ----------------------------
    // SEARCH TEACHER BY ID
    // ----------------------------
    public Teacher searchTeacherById(UUID id) throws SQLException {
        if(isValidUUID(id)) return teacherDAO.searchTeacherById(id);
        return teacherDAO.searchTeacherById(id);
    }

    // ----------------------------
    // UPDATE TEACHER BY ID
    // ----------------------------
    public boolean updateTeacherById(UUID id, Teacher teacher) throws SQLException {

        if (id == null) {
            throw new IllegalArgumentException("Teacher ID cannot be null");
        }
        if (teacher == null) {
            throw new IllegalArgumentException("Teacher update data cannot be null");
        }
        return teacherDAO.updateTeacherById(id, teacher);
    }
    private boolean isValidUUID(UUID id) {
        if (id == null) {
            System.out.println("[ERROR] UUID cannot be null.");
            return false;
        }
        return true;
    }
}
