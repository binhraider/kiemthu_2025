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
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
public class SchoolYear {
    @Id
    private Integer id;
    private String name;
    private String des;

    public SchoolYear(Integer idSchoolYear, String schoolYearName) {
        this.id = idSchoolYear;
        this.name = schoolYearName;
    }
}
