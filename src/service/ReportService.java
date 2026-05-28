package service;

import dao.ReportDAO;

public class ReportService {
    private final ReportDAO reportDAO = new ReportDAO();

    public int getTotalStudents() { return reportDAO.getTotalStudents(); }
    public int getTotalTeachers() { return reportDAO.getTotalTeachers(); }
    public double getTotalRevenue() { return reportDAO.getTotalRevenue(); }
}
