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
public class StudyWeek {
    @Id
    private Integer id;
    private String name;
    private String des;

    public StudyWeek(Integer idWeek, String weekName, String weekDes) {
        this.id = idWeek;
        this.name = weekName;
        this.des = weekDes;
    }
}
