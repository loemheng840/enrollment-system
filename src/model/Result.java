package model;

import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.UUID;

@Builder
@Data
public class Result {
    private UUID id;
    private UUID studentId;
    private UUID majorId;
    private Integer semester;
    private Integer year;
    private Double midtermScore;
    private Double finalScore;
    private Float gpa;
}
