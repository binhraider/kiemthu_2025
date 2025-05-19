package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subject {
    @Id
    private Integer id;
    private String name;
    //so tin chi
    private Integer credit;
    private String des;
    @Transient
    private List<SubjectScore> scoreList;
    //mon tien quyet
    @Transient
    private List<Subject> prerequisiteSubject;

    public Subject(Integer idSubject, String subjectName, Integer credit) {
        this.id = idSubject;
        this.name = subjectName;
        this.credit = credit;
    }

    public Subject(Integer credit){
        this.credit = credit;
    }
}
