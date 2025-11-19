package service;

import dao.EnrollmentDAO;
import model.Enrollment;

import java.util.List;
import java.util.UUID;

public class EnrollmentService {

    private final EnrollmentDAO enrollmentDAO;

    public EnrollmentService() {
        this.enrollmentDAO = new EnrollmentDAO();
    }

    /** Add enrollment with validation **/
    public Enrollment addEnrollment(Enrollment enrollment) {

        if (!isValidEnrollment(enrollment)) {
            System.out.println("[ERROR] Invalid enrollment data. Cannot add to database.");
            return null;
        }

        return enrollmentDAO.addEnrollment(enrollment);
    }

    /** View all enrollments **/
    public List<Enrollment> viewAllEnrollments() {
        return enrollmentDAO.viewAllEnrollments();
    }

    // =======================
    // Validation Methods
    // =======================

    private boolean isValidEnrollment(Enrollment enrollment) {
        if (enrollment == null) {
            System.out.println("[ERROR] Enrollment cannot be null.");
            return false;
        }

        if (!isValidUUID(enrollment.getId())) return false;
        if (!isValidUUID(enrollment.getStudentId())) return false;
        if (!isValidUUID(enrollment.getMajorId())) return false;

        if (enrollment.getYear() <= 0) {
            System.out.println("[ERROR] Year must be a positive integer.");
            return false;
        }

        if (enrollment.getStatus() == null) {
            System.out.println("[ERROR] Status cannot be null.");
            return false;
        }

        switch (enrollment.getStatus()) {
            case ENROLLED, DROPPED -> {} // valid
            default -> {
                System.out.println("[ERROR] Invalid status: " + enrollment.getStatus());
                return false;
            }
        }

        return true;
    }

    private boolean isValidUUID(UUID id) {
        if (id == null) {
            System.out.println("[ERROR] UUID cannot be null.");
            return false;
        }
        return true;
    }
}
