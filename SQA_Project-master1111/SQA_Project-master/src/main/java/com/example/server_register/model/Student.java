package com.example.server_register.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@Table(name = "tblsinhvien")
public class Student extends Member{
    private String studentId;

    public Student(String studentCode, Integer idStudent, String studentName) {
        super(idStudent, studentName);
        this.studentId= studentCode;
    }
}
