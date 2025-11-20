package view;

import controller.*;
import model.Teacher;

import java.sql.SQLException;

public class MainMenu {
    public void start() throws SQLException {
//        StudentController studentController = new StudentController();
//        studentController.start();
//        DepartmentController departmentController = new DepartmentController();
//        departmentController.displayMenu();
//        MajorController majorController = new MajorController();
//        majorController.display();
//        EnrollmentController enrollmentController = new EnrollmentController();
//        enrollmentController.display();
        TeacherController teacherController = new TeacherController();
        teacherController.menu();
    }
}
