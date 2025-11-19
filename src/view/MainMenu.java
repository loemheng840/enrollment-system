package view;

import controller.DepartmentController;
import controller.EnrollmentController;
import controller.MajorController;
import controller.StudentController;

public class MainMenu {
    public void start() {
//        StudentController studentController = new StudentController();
//        studentController.start();
//        DepartmentController departmentController = new DepartmentController();
//        departmentController.displayMenu();
//        MajorController majorController = new MajorController();
//        majorController.display();
        EnrollmentController enrollmentController = new EnrollmentController();
        enrollmentController.display();
    }
}
