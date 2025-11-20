package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.UUID;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private UUID id;

    private UUID studentId;

    private String studentName;

    private String gender;

    private String dob;

    private String email;

    private String phone;

    private String address;

    private UUID majorId;

    private String majorName;

    private String majorDescription;

    private Integer hours;

    private LocalDateTime enrollmentAt;

    private Integer year;

    private Status status;

    public enum Status {
        ENROLLED,DROPPED
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", studentName='" + studentName + '\'' +
                ", gender='" + gender + '\'' +
                ", dob='" + dob + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", address='" + address + '\'' +
                ", majorName='" + majorName + '\'' +
                ", majorDescription='" + majorDescription + '\'' +
                ", hours=" + hours +
                ", enrollmentAt=" + enrollmentAt +
                ", year=" + year +
                ", status=" + status +
                '}';
    }
}
