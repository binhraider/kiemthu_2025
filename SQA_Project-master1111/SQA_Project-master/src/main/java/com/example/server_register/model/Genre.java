package com.example.server_register.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
// bo mon
public class Genre {
    @Id
    private Integer id;
    private String name;
    private String des;
    @Transient
    private Department department;
    @Transient
    private List<Subject> subjectList;
    @Transient
    private List<Teacher> teacherList;
}
