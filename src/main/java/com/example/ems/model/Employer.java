package com.example.ems.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "employers")
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String industry;
    private String location;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<Employee> employees;

    @OneToMany(mappedBy = "employer", cascade = CascadeType.ALL)
    private List<Project> projects;
}