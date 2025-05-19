package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Department {
    @Id
    private Integer id;
    private String name;
    private String des;
    @Transient
    private Subject subjectList;

    public Department(Integer idDepartment, String departmentName) {
        this.id = idDepartment;
        this.name = departmentName;
    }

    public Department(Integer idDepartment) {
        this.id = idDepartment;
    }
}
