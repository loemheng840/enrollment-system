package model;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    private UUID id;
    private String name;
    private String gender;
    private LocalDate dob;
    private String email;
    private String phone;
    private UUID departmentId;
    private UUID majorId;
    private String departmentName;
    private String majorName;
    private String address;
    private Status status;  // enum field (NOT static)
    // Enum to represent possible student statuses
    public enum Status {
        ACTIVE,
        GRADUATED,
        SUSPENDED
    }
    @Override
    public String toString() {
        return "Student(" +
                "id=" + id +
                ", name=" + name +
                ", gender=" + gender +
                ", dob=" + dob +
                ", email=" + email +
                ", phone=" + phone +
                ", address=" + address +
                ", status=" + status +
                ", departmentName=" + departmentName +
                ", majorName=" + majorName +
                ")";
    }

}
