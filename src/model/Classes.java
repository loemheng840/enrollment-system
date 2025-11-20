package model;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Builder
@Data
public class Classes {

    private UUID id;

    private String classes;

    private Integer totalStudents;

    private Time time;

    public enum Time{
        MORNING, AFTERNOON, EVENING
    }

    private Integer totalMale;

    private Integer totalFemale;

    private List<Student> students;

    private List<Teacher> teacher;

    private List<Major> majors;
}
