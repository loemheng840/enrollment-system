package model;

import lombok.*;

import java.util.UUID;
@Builder
@Data
public class Department {
    private UUID id;
    private String departmentName;
    private String departmentCode;
}
