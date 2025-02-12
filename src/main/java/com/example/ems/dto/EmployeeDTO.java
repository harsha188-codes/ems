package com.example.ems.dto;

import lombok.Data;
import java.util.Set;

@Data
public class EmployeeDTO {
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private Long employerId;
    private Set<Long> skillIds;
    private Set<Long> projectIds;
}
