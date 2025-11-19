package service;

import dao.DepartmentDAO;
import model.Department;

import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

public class DepartmentService {

    private final DepartmentDAO departmentDAO = new DepartmentDAO();

    /** Add a new department with validation */
    public Department addDepartment(String name, String code) {
        if (!isValidName(name) || !isValidCode(code)) {
            System.out.println("[ERROR] Invalid department data. Cannot add to database.");
            return null;
        }

        Department department = Department.builder()
                .id(UUID.randomUUID())
                .departmentName(name)
                .departmentCode(code)
                .build();

        return departmentDAO.addDepartment(department);
    }

    /** Get department by code with validation */
    public Department getDepartmentByCode(String code) {
        if (!isValidCode(code)) {
            System.out.println("[ERROR] Invalid department code.");
            return null;
        }
        return departmentDAO.getDepartmentByCode(code);
    }

    /** Get all departments */
    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    // ========================
    // Validation Methods
    // ========================

    private boolean isValidName(String name) {
        if (name == null || name.isBlank()) {
            System.out.println("[ERROR] Department name cannot be empty.");
            return false;
        }
        return true;
    }

    private boolean isValidCode(String code) {
        if (code == null || code.isBlank()) {
            System.out.println("[ERROR] Department code cannot be empty.");
            return false;
        }
        // Optional: enforce simple alphanumeric code, 2-10 chars
        String regex = "^[a-zA-Z0-9]{2,10}$";
        if (!Pattern.matches(regex, code)) {
            System.out.println("[ERROR] Department code must be alphanumeric (2-10 characters).");
            return false;
        }
        return true;
    }
}
