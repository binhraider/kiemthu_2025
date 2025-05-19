package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Semester {
    @Id
    private Integer id;
    private String name;
    private String des;

    public Semester(Integer idSemester, String semesterName) {
        this.id = idSemester;
        this.name = semesterName;
    }
}
