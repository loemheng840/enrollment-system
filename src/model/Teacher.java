package model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher {
    private UUID id;
    private UUID departmentId;
    private String departmentName;
    private UUID majorId;
    private String majorName;
    private Integer hours;
    private String name;
    private String email;
    private String phone;
    private Time time;
    public enum Time {
        MORNING, AFTERNOON, EVENING
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", departmentName='" + departmentName + '\'' +
                ", majorName='" + majorName + '\'' +
                ", name='" + name + '\'' +
                ", hours='" + hours + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", time=" + time +
                '}';
    }
}

