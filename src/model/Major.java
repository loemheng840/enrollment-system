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
public class Major {
    private UUID id;
    private String major;
    private UUID departmentId;
    private String description;
    private Integer hours;
    private String lecturer;
}
