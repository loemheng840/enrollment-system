package model;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;
@Data
@Builder
public class Admin {
    private UUID id;
    private String username;
    private String password;
    private Roles roles;
    public enum Roles {
        ADMIN,LECTURER,STUDENT
    };
}
