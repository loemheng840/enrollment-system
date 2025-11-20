package model.statistics;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class StudentCount {
    private Integer totalStudents;
    private Integer totalMale;
    private Integer totalFemale;
}
