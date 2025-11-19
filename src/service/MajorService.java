package service;


import dao.MajorDAO;
import model.Major;

import java.util.List;
import java.util.UUID;

public class MajorService {
    private final MajorDAO majorDAO;
    public MajorService() {
        this.majorDAO = new MajorDAO();
    }
    // Add major
    public Major addMajor(Major major) {
        if (major == null || major.getMajor() == null) {
            return null;
        }
        return majorDAO.addMajor(major);
    }
    // Get major
    public Major getMajorById(UUID id) {
        return majorDAO.getMajorById(id);
    }
    // Get All major
    public List<Major> getAllMajors() {
        return majorDAO.getAllMajors();
    }
    // Update major
    public boolean updateMajor(UUID id, Major major) {
        if(major == null || major.getMajor() == null){
            System.out.println("[ERROR] Insert Major Failed: " + major);
            return false;
        }
        return majorDAO.updateMajor(id, major);
    }
}
