package dao.statistics;

import model.statistics.StudentCount;
import utils.DatabaseConnection;

import java.sql.Connection;

public class CountStudentDAO {
    private Connection connection;
    public void CountStudentDAO() {
        connection = DatabaseConnection.getConnection();
    }
    public StudentCount getStudentCountByClass(String classes){
        return null;
    }
}
