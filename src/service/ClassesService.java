package service;

import dao.ClassDAO;
import model.Classes;

import java.sql.SQLException;
import java.util.List;
import java.util.UUID;

public class ClassesService {
    private final ClassDAO classDAO;

    public ClassesService() {
        this.classDAO = new ClassDAO();
    }

    // Create a new class
    public Classes createClass(String className, Classes.Time time) throws SQLException {
        Classes c = Classes.builder()
                .classes(className)
                .time(time)
                .build();
        return classDAO.createClass(c);
    }

    // Add student to class
    public void addStudentToClass(UUID classId, UUID studentId) throws SQLException {
        classDAO.addStudentToClass(classId, studentId);
    }

    // Add teacher to class
    public void addTeacherToClass(UUID classId, UUID teacherId) throws SQLException {
        classDAO.addTeacherToClass(classId, teacherId);
    }

    // Add major to class
    public void addMajorToClass(UUID classId, UUID majorId) throws SQLException {
        classDAO.addMajorToClass(classId, majorId);
    }

    // Get class by ID
    public Classes getClassById(UUID classId) throws SQLException {
        return classDAO.getClassById(classId);
    }

    // List all students in a class
    public int countStudents(UUID classId) throws SQLException {
        Classes c = classDAO.getClassById(classId);
        return c != null ? c.getStudents().size() : 0;
    }

    // List all teachers in a class
    public int countTeachers(UUID classId) throws SQLException {
        Classes c = classDAO.getClassById(classId);
        return c != null ? c.getTeacher().size() : 0;
    }

    // List all majors in a class
    public int countMajors(UUID classId) throws SQLException {
        Classes c = classDAO.getClassById(classId);
        return c != null ? c.getMajors().size() : 0;
    }
}
