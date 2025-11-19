package service;

import dao.StudentDAO;
import model.Student;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class StudentService {

    private final StudentDAO studentDAO;

    public StudentService() {
        this.studentDAO = new StudentDAO();
    }

    // Add a new student with validation
    public Student addStudent(Student student) {
        if (!isValidStudent(student)) {
            System.out.println("[ERROR] Invalid student data.");
            return null;
        }
        return studentDAO.addStudent(student);
    }

    // Get student by ID
    public Student getStudentById(UUID id) {
        if (!isValidUUID(id)) return null;
        return studentDAO.getStudentById(id);
    }

    // Get all students
    public List<Student> getAllStudents() {
        return studentDAO.getAllStudents();
    }

    // Get students by department
    public List<Student> getStudentsByDepartmentCode(String departmentCode, String departmentName) {
        if (departmentName == null || departmentName.isBlank()) {
            System.out.println("[ERROR] Department name cannot be empty.");
            return List.of();
        }
        if (departmentCode == null || departmentCode.isBlank()) {
            System.out.println("[ERROR] Department code cannot be empty.");
            return List.of();
        }
        return studentDAO.getStudentByDepartmentCode(departmentCode, departmentName);
    }

    // Get students by major
    public List<Student> getStudentsByMajor(String major) {
        if (major == null || major.isBlank()) {
            System.out.println("[ERROR] Major cannot be empty.");
            return List.of();
        }
        return studentDAO.getStudentByMajor(major);
    }

    // Update student
    public boolean updateStudent(UUID id, Student updatedStudent) {
        if (!isValidUUID(id) || !isValidStudent(updatedStudent)) {
            System.out.println("[ERROR] Invalid update parameters.");
            return false;
        }
        return studentDAO.updateStudentByID(id, updatedStudent);
    }

    // Delete student
    public boolean deleteStudent(UUID id) {
        if (!isValidUUID(id)) return false;
        return studentDAO.deleteStudentByID(id);
    }

    // =======================
    // Validation methods
    // =======================

    private boolean isValidUUID(UUID id) {
        if (id == null) {
            System.out.println("[ERROR] UUID cannot be null.");
            return false;
        }
        return true;
    }

    private boolean isValidStudent(Student student) {
        if (student == null) return false;
        if (!isValidUUID(student.getId())) return false;

        // Validate name
        if (student.getName() == null || student.getName().isBlank()) {
            System.out.println("[ERROR] Student name cannot be empty.");
            return false;
        }

        // Validate gender
        if (student.getGender() == null ||
                !(student.getGender().equalsIgnoreCase("male") || student.getGender().equalsIgnoreCase("female"))) {
            System.out.println("[ERROR] Gender must be 'male' or 'female'.");
            return false;
        }

        // Validate email
        if (!isValidEmail(student.getEmail())) {
            System.out.println("[ERROR] Invalid email format.");
            return false;
        }

        // Validate phone
        if (!isValidPhone(student.getPhone())) {
            System.out.println("[ERROR] Invalid phone number.");
            return false;
        }

        // Validate DOB
        if (student.getDob() != null && student.getDob().isAfter(LocalDate.now())) {
            System.out.println("[ERROR] Date of birth cannot be in the future.");
            return false;
        }

        return true;
    }

    private boolean isValidEmail(String email) {
        if (email == null || email.isBlank()) return false;
        // Simple regex for email validation
        String emailRegex = "^[\\w.-]+@[\\w.-]+\\.\\w+$";
        return Pattern.matches(emailRegex, email);
    }

    private boolean isValidPhone(String phone) {
        if (phone == null || phone.isBlank()) return false;
        // Only digits allowed, 7-15 characters
        return Pattern.matches("\\d{7,15}", phone);
    }
}
