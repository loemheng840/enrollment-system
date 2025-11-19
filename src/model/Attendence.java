package model;

import lombok.Builder;
import lombok.Data;

import java.sql.Time;
import java.util.UUID;

@Builder
@Data
public class Attendence {
    private UUID id;
    private UUID studentId;
    private UUID majorId;
    private Time date;
    private Status status;
    private enum Status {
        PRESENT,ABSENT,LATE
    };
}
